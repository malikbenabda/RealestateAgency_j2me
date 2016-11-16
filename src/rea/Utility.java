/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Stark
 */
public class Utility {
//===============get image from server with given link==================

    public static Image getImageconnection(String urlString) {

        Image imagecx;
        HttpConnection hc;
        DataInputStream dis;
        byte[] data;
        int size;

        try {

            hc = (HttpConnection) Connector.open(urlString);
            dis = hc.openDataInputStream();
            size = (int) hc.getLength();
            data = new byte[size];
            dis.readFully(data);
            imagecx = Image.createImage(data, 0, size);
            return imagecx;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

//==================get image from server with given link===============
    public static StringBuffer getStringconnection(String urlString) {
        StringBuffer sb = new StringBuffer();
        HttpConnection hc;
        DataInputStream dis;

        try {

            hc = (HttpConnection) Connector.open(urlString);
            dis = hc.openDataInputStream();
            int ch = -1;
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb;

    }

    public static String Date2String(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        String datef = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH);
        return datef;
    }

    public static String replaceALL(String oldString, String replacement, String GlobalString) {
        String result = "";
        int index = GlobalString.indexOf(oldString);
        if (index == 0) {
            result = replacement + GlobalString.substring(oldString.length());
            return replaceALL(oldString, replacement, result);
        } else if (index > 0) {
            result = GlobalString.substring(0, index) + replacement + GlobalString.substring(index + oldString.length());
            return replaceALL(oldString, replacement, result);
        } else {
            return GlobalString;
        }
    }
}
