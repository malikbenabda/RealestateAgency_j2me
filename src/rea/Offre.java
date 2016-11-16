/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import java.util.Date;

/**
 *
 * @author Soul Slayer
 */
public class Offre {
    private int id;
    private String adresseOffre;
    private float prixOffre;
    private String titreOffre;
    private String dateAjout;
    private int superficieOffre;
    private int photoOffre;
    private String croquis;  
    private String typeProprietaire;
    private String natureOffre;
    private float note; 
    private String descriptionOffre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresseOffre() {
        return adresseOffre;
    }

    public void setAdresseOffre(String adresseOffre) {
        this.adresseOffre = adresseOffre;
    }

    public float getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(float prixOffre) {
        this.prixOffre = prixOffre;
    }

    public String getTitreOffre() {
        return titreOffre;
    }

    public void setTitreOffre(String titreOffre) {
        this.titreOffre = titreOffre;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public int getSuperficieOffre() {
        return superficieOffre;
    }

    public void setSuperficieOffre(int superficieOffre) {
        this.superficieOffre = superficieOffre;
    }

    public int getPhotoOffre() {
        return photoOffre;
    }

    public void setPhotoOffre(int photoOffre) {
        this.photoOffre = photoOffre;
    }

    public String getCroquis() {
        return croquis;
    }

    public void setCroquis(String croquis) {
        this.croquis = croquis;
    }

    public String getTypeProprietaire() {
        return typeProprietaire;
    }

    public void setTypeProprietaire(String typeProprietaire) {
        this.typeProprietaire = typeProprietaire;
    }

    public String getNatureOffre() {
        return natureOffre;
    }

    public void setNatureOffre(String natureOffre) {
        this.natureOffre = natureOffre;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getDescriptionOffre() {
        return descriptionOffre;
    }

    public void setDescriptionOffre(String descriptionOffre) {
        this.descriptionOffre = descriptionOffre;
    }

    public Offre() {
    }

    public Offre(int id, String adresseOffre, float prixOffre, String titreOffre, String dateAjout, int superficieOffre, int photoOffre, String croquis, String typeProprietaire, String natureOffre, float note, String descriptionOffre) {
        this.id = id;
        this.adresseOffre = adresseOffre;
        this.prixOffre = prixOffre;
        this.titreOffre = titreOffre;
        this.dateAjout = dateAjout;
        this.superficieOffre = superficieOffre;
        this.photoOffre = photoOffre;
        this.croquis = croquis;
        this.typeProprietaire = typeProprietaire;
        this.natureOffre = natureOffre;
        this.note = note;
        this.descriptionOffre = descriptionOffre;
    }

    public String toString() {
        return "Offre{" + "id=" + id + ", adresseOffre=" + adresseOffre + ", prixOffre=" + prixOffre + ", titreOffre=" + titreOffre + ", dateAjout=" + dateAjout + ", superficieOffre=" + superficieOffre + ", photoOffre=" + photoOffre + ", croquis=" + croquis + ", typeProprietaire=" + typeProprietaire + ", natureOffre=" + natureOffre + ", note=" + note + ", descriptionOffre=" + descriptionOffre + '}';
    }
    
   
    
    
  
    
    
}
