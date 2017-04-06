package com.example.bugs.ppe4;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParseur {
    private Document document;

    public XMLParseur(String xml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            document = db.parse(is);
        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
        }
    }

    public String getValue(String nomElement) {
        NodeList listeNoeuds = this.document.getElementsByTagName(nomElement);
        Element element = (Element) listeNoeuds.item(0);
        Node child;
        String resultat = "";
        if (element != null) {
            if (element.hasChildNodes()) {
                child = element.getFirstChild();
                while ((child != null) && (child.getNodeType() != Node.TEXT_NODE))
                    child.getNextSibling();
                if (child != null) resultat = child.getNodeValue();
            }
        }
        return resultat;
    }
}