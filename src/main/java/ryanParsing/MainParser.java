package ryanParsing;

import ApiUtil.GoogleApiManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainParser {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String filename = args[0];

        ArrayList<Way> a = parseFileName(filename);

        System.out.println(a.size());

    }


    public static ArrayList<Way> parseInputStream(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);

        return parseWithDoc(doc);
    }

    public static ArrayList<Way> parseFileName(String filename) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        return parseWithDoc(doc);
    }

    private static ArrayList<Way> parseWithDoc(Document doc) {

        doc.getDocumentElement().normalize();

        HashMap<Long, Node> nodes = new HashMap<Long, Node>();
        HashMap<Long, Integer> goodNodes = new HashMap<Long, Integer>();

        NodeList nList = doc.getElementsByTagName("node");
        //System.out.println(nList.getLength());

        // CHECK IF BAD WORD
        NodeList wayNodes = doc.getElementsByTagName("way");
        for (int i = 0; i < wayNodes.getLength(); i++) {
            NodeList childNodes = wayNodes.item(i).getChildNodes();
            ArrayList<Long> currNodes = new ArrayList<Long>();

            boolean bad = false;

            for (int j = 0; j< childNodes.getLength(); j++) {
                org.w3c.dom.Node childNode = childNodes.item(j);
                if (childNode.getNodeName().equals("nd")) {
                    Element e = (Element) childNode;
                    long id = Long.parseLong(e.getAttribute("ref"));
                    currNodes.add(id);
                } else if (childNode.getNodeName().equals("tag")) {
                    Element e = (Element) childNode;
                    String k = e.getAttribute("k");
                    String v = e.getAttribute("v");
                    if (k.equals("highway") && (v.equals("footway") || v.equals("path"))) {
                        bad = true;
                        break;
                    } else if (MainParser.isBad(k)) {
                        bad = true;
                        break;
                    }
                }
            }

            // if its not bad add all nodes to occurrence list
            if (!bad) {
                for (Long id : currNodes) {
                    if (goodNodes.containsKey(id)) {
                        goodNodes.put(id, goodNodes.get(id)+1);
                    } else goodNodes.put(id, 1);
                }
            }


        }

        // adding nodes that are on roads to nodes map
        for (int i = 0; i < nList.getLength(); i++) {
            org.w3c.dom.Node curr = nList.item(i);

            if (curr.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element e = (Element) curr;
                long id = Long.parseLong(e.getAttribute("id"));
                double lat = Double.parseDouble(e.getAttribute("lat"));
                double lon = Double.parseDouble(e.getAttribute("lon"));

                if (goodNodes.containsKey(id)) {
                    nodes.put(id, new Node(lat, lon, id, goodNodes.get(id)));
                }
            }
        }


        ArrayList<Way> ways = new ArrayList<Way>();

        ArrayList<Node> currWayNodes = new ArrayList<Node>();
        ArrayList<Node> currWayIntersections = new ArrayList<Node>();

        ArrayList<Double> deltas = new ArrayList<>();

        for (int i = 0; i < wayNodes.getLength(); i++) {
            NodeList childNodes = wayNodes.item(i).getChildNodes();
            currWayNodes.clear();
            currWayIntersections.clear();

            boolean bad = false;

            ArrayList<Way> waysToAddIfGood = new ArrayList<Way>();
            String speed = null;

            for (int j = 0; j< childNodes.getLength(); j++) {
                org.w3c.dom.Node childNode = childNodes.item(j);
                if (childNode.getNodeName().equals("nd")) {
                    Element e = (Element) childNode;
                    long id = Long.parseLong(e.getAttribute("ref"));
                    Node currNode = nodes.get(id);

                    if (currNode == null) continue;

                    if (currNode.getOccurences() == 1) {
                        currWayNodes.add(currNode);
                    } else if (currNode.getOccurences() > 1) { // intersection case
                        if (currWayIntersections.isEmpty()) { // first intersection
                            currWayNodes = new ArrayList<Node>(); // reset curr way nodes and add the current node to both
                            currWayIntersections.add(currNode);
                            currWayNodes.add(currNode);
                        } else { //
                            currWayNodes.add(currNode);
                            currWayIntersections.add(currNode);
                            Way wayToAdd = new Way(new ArrayList<Node>(currWayNodes), new ArrayList<Node>(currWayIntersections));
                            waysToAddIfGood.add(wayToAdd);

                            //make new lists
                            currWayNodes = new ArrayList<Node>();
                            currWayIntersections = new ArrayList<Node>();
                            currWayNodes.add(currNode);
                            currWayIntersections.add(currNode);
                        }
                    }
                } else if (childNode.getNodeName().equals("tag")) {
                    Element e = (Element) childNode;
                    String k = e.getAttribute("k");
                    String v = e.getAttribute("v");

                    if (k.equals("maxspeed")) {
                        speed = v;
                    }

                    if (k.equals("highway") && (v.equals("footway") || v.equals("path"))) {
                        bad = true;
                        break;
                    } else if (MainParser.isBad(k)) {
                        bad = true;
                        break;
                    }
                }
            }
            if (!bad) {
                if (speed != null) {
                    int intspeed = Integer.parseInt(speed.split(" ")[0]);

                    for (Way way:waysToAddIfGood) {
                        way.setSpeed(intspeed);
                        JSONObject json = GoogleApiManager.generateAndMakeDistanceRequest(way.getstartx(), way.getstarty(), way.getendx(), way.getendy());
                        //{"destination_addresses":["114 W 29th St, New York, NY 10001, USA"],
// "rows":[{"elements":[{"duration":{"text":"1 min","value":27},
// "distance":{"text":"230 ft","value":70},"status":"OK"}]}],
// "origin_addresses":["6 Avenue & 29 St, New York, NY 10001, USA"],
// "status":"OK"}
                        Gson gson = new Gson();
                        SegmentData obj = gson.fromJson(json.toString(), SegmentData.class);


                        Double distance = Double.parseDouble(obj.rows.get(0).elements.get(0).distance.value);
                        int time = Integer.parseInt(obj.rows.get(0).elements.get(0).duration.get("value"));

                        way.setDistance(distance);
                        way.setGivenTime(time);

                        double delta = way.isGood();
                        deltas.add(delta);
                        //System.out.println(delta);
                    }





                }
                ways.addAll(new ArrayList<>(waysToAddIfGood));
            }
        }

        double sum = 0;
        for (double d:deltas) sum+=d;
        sum /= deltas.size();
        for (Way way : ways) {
            double temp = way.isGood(sum);
            System.out.println(temp);
        }

        return ways;
    }


    private static boolean isBad(String s) {
        String[] badWords = new String[]{"bycycle",
                                        "foot",
                                        "landuse",
                                        "power",
                                        "natural",
                                        "leisure",
                                        "water",
                                        "building",
                                        "barrier",
                                        "waterway"};
        for (String word : badWords) {
            if (s.equals(word)) return true;
        }
        return false;

    }


}
