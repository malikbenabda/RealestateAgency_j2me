/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Soul Slayer
 */
public class ImageHandler extends DefaultHandler{
 private Vector personnes;
    String idTag = "close";
    String pathTag = "close";
   
    

    public  ImageHandler() {
        personnes = new Vector();
    }

    public Images[] getPersonne() {
        Images[] personness = new Images[personnes.size()];
        personnes.copyInto(personness);
        return personness;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Images currentPersonne;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentPersonne = new Images();
            //2ème methode pour parser les attributs
            currentPersonne.setId(Integer.parseInt(attributes.getValue("id")));
            currentPersonne.setPath(attributes.getValue("path"));
            
            
            /**
             * *
             */
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            // we are no longer processing a <reg.../> tag
            personnes.addElement(currentPersonne);
            currentPersonne = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("path")) {
            pathTag = "close";
        } 
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPersonne != null) {
            // don't forget to trim excess spaces from the ends of the string
            
        }
    }

}
    