/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

/**
 *
 * @author Soul Slayer
 */
public class Images {
    private int id;
    private String path;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
    
    public Images() {
    }

    public Images(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public String toString() {
        return "Image{" + "id=" + id + ", path=" + path + '}';
    }
  
    

}
