package rea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sana
 */
import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

/**
 * A simple splash screen.
 */
public class SplashScreen1 extends Canvas implements Runnable {
    private Image mImage;
    private MidletREA projectMIDlet;
    private Form form;
    
    
    /**
     * The constructor attempts to load the named image and begins a timeout
     * thread. The splash screen can be dismissed with a key press,
     * a pointer press, or a timeout
     * @param projectMIDlet instance of MIDlet
     */
    public SplashScreen1(MidletREA projectMIDlet,Form form){
        this.form=form;
        this.projectMIDlet = projectMIDlet;
       
        

        
        try{
        mImage = Image.createImage("image/House-icon.png");
        Thread t = new Thread(this);
        t.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Paints the image centered on the screen.
     */
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        //set background color to overdraw what ever was previously displayed
        g.setColor(0x000000);
        g.fillRect(0,0, width, height);
        g.drawImage(mImage, width / 2, height / 2,Graphics.HCENTER | Graphics.VCENTER);

    }
    /**
     * Dismisses the splash screen with a key press or a pointer press
     */
    public void dismiss() {
        if (isShown())
            Display.getDisplay(projectMIDlet).setCurrent(form);

    }
    /**
     * Default timeout with thread
     */
    public void run() {
        
        try {
           playViaHttp();
           Thread.sleep(4000);//set for 3 seconds
            
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        }
        
        dismiss();
    }
    /**
     * A key release event triggers the dismiss()
     * method to be called.
     */
    
    
    
    private void playViaHttp() {
    try {
      Player player = Manager.createPlayer("http://localhost/media/Computer_Start-Up.mp3");
      player.start();
    } catch (Exception e) {
      showException(e);
      return;
    }
     //Display.getDisplay(projectMIDlet).setCurrent(form);
  }

  

  private void showException(Exception e) {
    Alert a = new Alert("Exception", e.toString(), null, null);
    a.setTimeout(Alert.FOREVER);
     Display.getDisplay(projectMIDlet).setCurrent(form);
  }
    
}
