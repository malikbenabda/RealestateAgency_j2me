/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.List;

/**
 *
 * @author Soul Slayer
 */
public class Menu implements CommandListener {
    
    private MidletREA projectMIDlet;
    private Form form;
    private List lst;
    
    Image img;
    
    Image img2;
    
    Image img3;
    
    List lstM = new List("Menu", List.IMPLICIT);

    /**
     * The constructor attempts to load the named image and begins a timeout
     * thread. The splash screen can be dismissed with a key press, a pointer
     * press, or a timeout
     *
     * @param projectMIDlet instance of MIDlet
     */
    public Menu(MidletREA projectMIDlet, Form form, List lst) {
        this.form = form;
        this.projectMIDlet = projectMIDlet;
        this.lst = lst;
        
        try {
            img = Image.createImage("image/green-house.png");
            
            img2 = Image.createImage("image/Profile-icon.png");
            
            img3 = Image.createImage("image/stats_views.png");
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        lstM.append(" Liste des Offres ", img);
        lstM.append(" Mon compte ", img2);
        lstM.append(" Statistique ", img3);
        //lstM.append(null, img);
        //lstM.append("", img2);
        lstM.setCommandListener(this);
        
        Display.getDisplay(projectMIDlet).setCurrent(lstM);
        //return lstM;

    }
    
    public List getList() {
        
        return lstM;
    }
    
    public void commandAction(Command c, Displayable d) {
        
        if (c == lstM.SELECT_COMMAND) {
            
            int x = lstM.getSelectedIndex();
            
            if (x == 0) {
                Display.getDisplay(projectMIDlet).setCurrent(lst);
            } else if (x == 1) {
                if (projectMIDlet.LoginCurrentMalik != "") {
                    projectMIDlet.editingProfileMalik();
                } else {
                    Alert alert = new Alert("Not Logged in ! ", "please Login first",null, AlertType.INFO);
                    alert.addCommand(projectMIDlet.cmdloginMalik);
                    alert.addCommand(projectMIDlet.cmdSignupMalik);
                    alert.setCommandListener(projectMIDlet);
                    projectMIDlet.disp.setCurrent(alert);
                    
                }
            } else if (x == 2) {
                Display.getDisplay(projectMIDlet).setCurrent(lst);
            } else {
                Display.getDisplay(projectMIDlet).setCurrent(lst);
            }
            
        }
    }

    /**
     * A key release event triggers the dismiss() method to be called.
     */
}
