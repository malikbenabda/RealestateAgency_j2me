/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Soul Slayer
 */
public class OffreHandler extends DefaultHandler {

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

    public OffreHandler() {
        personnes = new Vector();
    }

    public Offre[] getPersonne() {
        Offre[] personness = new Offre[personnes.size()];
        personnes.copyInto(personness);
        return personness;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Offre currentPersonne;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentPersonne = new Offre();
            //2ème methode pour parser les attributs
            currentPersonne.setId(Integer.parseInt(attributes.getValue("id")));
            currentPersonne.setAdresseOffre(attributes.getValue("adresseOffre"));
            currentPersonne.setPrixOffre(Integer.parseInt(attributes.getValue("prixOffre")));
            currentPersonne.setTitreOffre(attributes.getValue("titreOffre"));

            
            
            currentPersonne.setDateAjout(attributes.getValue("dateAjout"));

            currentPersonne.setPhotoOffre(Integer.parseInt(attributes.getValue("photoOffre_id")));

            currentPersonne.setSuperficieOffre(Integer.parseInt(attributes.getValue("SuperficieOffre")));
            currentPersonne.setTypeProprietaire(attributes.getValue("typeProprietaire"));
            currentPersonne.setNatureOffre(attributes.getValue("natureOffre"));
            currentPersonne.setNote(Integer.parseInt(attributes.getValue("note")));
             //currentPersonne.setDescriptionOffre(attributes.getValue("description"));

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
        } else if (qName.equals("adresseOffre")) {
            adresseOffreTag = "close";
        } else if (qName.equals("prixOffre")) {
            prixOffreTag = "close";
        } else if (qName.equals("superficieOffre")) {
            superficieOffreTag = "close";
        } else if (qName.equals("typeProprietair")) {
            typeProprietaireTag = "close";
        } else if (qName.equals("natureOffre")) {
            natureOffreTag = "close";
        } else if (qName.equals("note")) {
            noteTag = "close";
        }//else if (qName.equals("descriptionOffre")) {
        //descriptionOffreTag = "close";
        //}
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPersonne != null) {
            // don't forget to trim excess spaces from the ends of the string

        }
    }

}
