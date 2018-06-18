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

public class TabelaParsingXML extends DefaultHandler {
    
    private final FileManager fileManager = new FileManager("tabelaParsing.txt", false);

    @Override
    public void startElement(String uri, 
    String localName, String qName, Attributes attributes) throws SAXException {

        if(qName.equalsIgnoreCase("item")) {
            fileManager.fileWrite(attributes.getValue("codigo"));
            fileManager.fileWrite(attributes.getValue("derivacao"));
            System.out.println(attributes.getValue("codigo"));
            System.out.println(attributes.getValue("derivacao"));
        }
    }

    @Override
    public void endElement(String uri, 
    String localName, String qName) throws SAXException {
        //System.out.println("End element:" + qName);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        System.out.println("Chars tabela:" + new String(ch) + "End Chars");
        fileManager.fileWrite(new String(ch));
    }
}
