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
public class Commmentaire {

    private int id;
    private String auteur;
    private int note;
    private String contenu;
    private Date date;
    private int offre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOffre() {
        return offre;
    }

    public void setOffre(int offre) {
        this.offre = offre;
    }

    public Commmentaire() {
    }

    public Commmentaire(int id, String auteur, int note, String contenu, Date date, int offre) {
        this.id = id;
        this.auteur = auteur;
        this.note = note;
        this.contenu = contenu;
        this.date = date;
        this.offre = offre;
    }

    public String toString() {
        return "Commmentaire{" + "id=" + id + ", auteur=" + auteur + ", note=" + note + ", contenu=" + contenu + ", date=" + date + ", offre=" + offre + '}';
    }
    

}
