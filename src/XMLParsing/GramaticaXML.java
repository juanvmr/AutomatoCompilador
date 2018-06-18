/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLParsing;

/**
 *
 * @author juanvmr
 */
import FileManager.FileManager;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GramaticaXML extends DefaultHandler {
   
    private final FileManager fileManager = new FileManager("gramaticaParsing.txt", false);

    @Override
    public void startElement(String uri, 
    String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.startsWith("terminal")) {
            fileManager.fileWrite(attributes.getValue("codigo"));
            fileManager.fileWrite(attributes.getValue("simbolo"));
        }
        else if (qName.startsWith("naoTerminal")) {
            fileManager.fileWrite(attributes.getValue("codigo"));
            fileManager.fileWrite(attributes.getValue("simbolo"));
        }
    }

    @Override
    public void endElement(String uri, 
    String localName, String qName) throws SAXException {
        //System.out.println("End element:" + qName);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        //System.out.println("Chars gramatica:" + new String(ch) + "End chars");
    }
}
