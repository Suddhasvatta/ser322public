package edu.asupoly.ser322.xmlex;

import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class PersonXPathExample {
    public static void main(String args[]) throws Exception {
	// Create XPathFactory object
	XPathFactory xpathFactory = XPathFactory.newInstance();
	// Create XPath object
	XPath xpath = xpathFactory.newXPath();

	// You need an InputSource for each expression you evaluate
        InputSource source = new InputSource(new FileReader(args[0]));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        factory.setValidating(false);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(source);

        Element root = doc.getDocumentElement();
        root.normalize();

        // DOM Navigation
        System.out.println("ROOT NODE:");
        System.out.println("Type:" + root.getNodeType() + "\t" + 
                            "Name:" + root.getNodeName() + "\t" +
                            "Value: *" + root.getNodeValue() + "*");

        System.out.println("ROOT CHILDREN:");
        NodeList rootChildren = root.getChildNodes();
        for (int i=0; i < rootChildren.getLength(); i++) {
            Node x = rootChildren.item(i);
            System.out.println("Type:" + x.getNodeType() + "\t" + 
                                "Name:" + x.getNodeName() + "\t" +
                                "Value: *" + x.getNodeValue() + "*");
        }

        // Name is in form Last, First
        String name = root.getChildNodes().item(1)
                          .getChildNodes().item(3)
                          .getChildNodes().item(0).getNodeValue()
                      + ", " +
                      root.getChildNodes().item(1)
                          .getChildNodes().item(1)
                          .getChildNodes().item(0).getNodeValue();
        System.out.println("Name (DOM): " + name);


        //////////////////////// XPath examples ///////////////////////

        Node first = (Node)xpath.evaluate("//name/first/text()", root, XPathConstants.NODE);
        Node last  = (Node)xpath.evaluate("//name/last/text()", root, XPathConstants.NODE);
        name = last.getNodeValue() + ", " + first.getNodeValue();
        System.out.println("Name (XPath): " + name);

        // XPath returning a collection of nodes
        NodeList emailNodes = (NodeList)xpath.evaluate("//email", root, XPathConstants.NODESET);
        for (int i=0; i < emailNodes.getLength(); i++) {
            Node emailNode = (Node) emailNodes.item(i);
            Node emailText = (Node)xpath.evaluate("text()", emailNode, XPathConstants.NODE);
            System.out.println("Email (XPath): " + emailText.getNodeValue());
        }

        Node x = (Node)xpath.evaluate("//address[@location='work']/street/text()", root, XPathConstants.NODE);
        System.out.println(x.getNodeValue());
    }
}
