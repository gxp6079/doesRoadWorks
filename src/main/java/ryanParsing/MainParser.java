package ryanParsing;

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

        NodeList nList = doc.getElementsByTagName("node");
        //System.out.println(nList.getLength());

        for (int i = 0; i < nList.getLength(); i++) {
            org.w3c.dom.Node curr = nList.item(i);

            if (curr.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element e = (Element) curr;
                long id = Long.parseLong(e.getAttribute("id"));
                double lat = Double.parseDouble(e.getAttribute("lat"));
                double lon = Double.parseDouble(e.getAttribute("lon"));

                nodes.put(id, new Node(lat, lon, id));
            }
        }


        NodeList wayNodes = doc.getElementsByTagName("way");
        for (int i = 0; i < wayNodes.getLength(); i++) {
            NodeList childNodes = wayNodes.item(i).getChildNodes();
            for (int j = 0; j< childNodes.getLength(); j++) {
                org.w3c.dom.Node childNode = childNodes.item(j);
                if (childNode.getNodeName().equals("nd")) {
                    Element e = (Element) childNode;
                    long id = Long.parseLong(e.getAttribute("ref"));
                    nodes.get(id).addOccurences();
                }
            }
        }

        ArrayList<Way> ways = new ArrayList<Way>();

        ArrayList<Node> currWayNodes = new ArrayList<Node>();
        ArrayList<Node> currWayIntersections = new ArrayList<Node>();

        for (int i = 0; i < wayNodes.getLength(); i++) {
            NodeList childNodes = wayNodes.item(i).getChildNodes();
            currWayNodes.clear();
            currWayIntersections.clear();
            for (int j = 0; j< childNodes.getLength(); j++) {
                org.w3c.dom.Node childNode = childNodes.item(j);
                if (childNode.getNodeName().equals("nd")) {
                    Element e = (Element) childNode;
                    long id = Long.parseLong(e.getAttribute("ref"));
                    Node currNode = nodes.get(id);
                    if (currNode.getOccurences() == 1) {
                        currWayNodes.add(currNode);
                    } else if (currNode.getOccurences() > 1) {
                        currWayNodes.add(currNode);
                        currWayIntersections.add(currNode);
                    }
                }
            }
            ways.add(new Way(new ArrayList<Node>(currWayNodes), new ArrayList<Node>(currWayIntersections)));
        }

        return ways;
    }





}
