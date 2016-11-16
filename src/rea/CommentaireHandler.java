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
public class CommentaireHandler  extends DefaultHandler{
    
    private Vector personnes;
    String idTag = "close";
    String adresseOffreTag = "close";
    String prixOffreTag = "close";
    String titreOffreTag = "close";
    String dateAjoutTag = "close";
    String superficieOffreTag = "close";
    String photoOffreTag = "close";
    String croquisTag = "close";
    String typeProprietaireTag = "close";
    String natureOffreTag = "close";
    String noteTag = "close";
    String descriptionOffreTag = "close";

    public  CommentaireHandler() {
        personnes = new Vector();
    }

    public Commmentaire[] getPersonne() {
        Commmentaire[] personness = new Commmentaire[personnes.size()];
        personnes.copyInto(personness);
        return personness;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Commmentaire currentPersonne;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentPersonne = new Commmentaire();
            //2ème methode pour parser les attributs
            currentPersonne.setId(Integer.parseInt(attributes.getValue("id")));
            currentPersonne.setAuteur(attributes.getValue("auteur"));
            currentPersonne.setNote(Integer.parseInt(attributes.getValue("note")));
            currentPersonne.setContenu(attributes.getValue("contenu"));
            currentPersonne.setOffre(Integer.parseInt(attributes.getValue("offre_id")));
            
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
        } else if (qName.equals("auteur")) {
            adresseOffreTag = "close";
        } else if (qName.equals("note")) {
            prixOffreTag = "close";
        } else if (qName.equals("contenu")) {
            superficieOffreTag = "close";
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
    

