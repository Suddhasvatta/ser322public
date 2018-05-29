package edu.asupoly.ser322.xmlex;

import java.io.*;

import org.xml.sax.InputSource;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class XmlMethod {
    public static void main(String args[]) throws Exception {
	// Create XPathFactory object
	XPathFactory xpathFactory = XPathFactory.newInstance();
	// Create XPath object
	XPath xpath = xpathFactory.newXPath();

	// You need an InputSource for each expression you evaluate
        InputSource source = new InputSource(new FileReader(args[0]));
	String methodName = xpath.evaluate("//methodCall/methodName/text()", source);
        System.out.println("MethodName: " + methodName);

	XPathExpression expr = xpath.compile("//methodCall/params/param");
	source = new InputSource(new FileReader(args[0]));
	NodeList params = (NodeList)expr.evaluate(source, XPathConstants.NODESET);
        for (int i = 0; i < params.getLength(); i++) {
            String paramString = xpath.evaluate("value/*/text()", (Node)params.item(i));
            System.out.println("param: " + paramString);
        }
    }
}
