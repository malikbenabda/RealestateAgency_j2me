/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.media.MediaException;
import javax.microedition.midlet.*;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import pidevmobilev02.GoogleMapsTestForm;

/**
 * @author Malik Bebanda
 * @author Ala Mejri
 * @version 1.2
 */
public class MidletREA extends MIDlet implements CommandListener, Runnable {

    Display disp = Display.getDisplay(this);


    /*
     ======================================================================================
     --------------------------------------------------------------------------------------
     *********************************** Malik's Global Variables************************************
     --------------------------------------------------------------------------------------
     ======================================================================================
    
     */
    Form formAcceuilMalik = new Form("Bienvenue");
    Form formLoginMalik = new Form("Login");
    Form formsignupMalik = new Form("SignUp");
    Form formOffresMalik = new Form("Offer List");
    Form formEditMalik = new Form("Edit profile");
    Command cmdnextMalik = new Command("next", Command.SCREEN, 0);
    Command cmdbackMalik = new Command("back", Command.SCREEN, 0);
    Command cmdhomeMalik = new Command("Home", Command.SCREEN, 0);
    Command cmdlogoutMalik = new Command("Logout", Command.SCREEN, 0);
    Command cmdloginMalik = new Command("Login", Command.SCREEN, 0);
    Command cmdSignupMalik = new Command("SignUp", Command.SCREEN, 0);
    Command cmdOffreMalik = new Command("Offres", Command.SCREEN, 0);
    Command cmdEditprofileMalik = new Command("Edit Profile", Command.SCREEN, 0);
    Command cmdmailMalik = new Command("send email", Command.SCREEN, 0);
    Command cmdsmsMalik = new Command("send sms", Command.SCREEN, 0);
    Command cmdcallMalik = new Command("Call Us", Command.SCREEN, 0);
    TextField txtloginMalik = new TextField("Login", "", 22, TextField.ANY);
    TextField txtnomMalik = new TextField("nom", "", 55, TextField.ANY);
    TextField txtprenomMalik = new TextField("prenom", "", 55, TextField.ANY);
    TextField txtadresseMalik = new TextField("Adresse", "", 105, TextField.ANY);
    TextField txtpwdMalik = new TextField("password", "", 22, TextField.PASSWORD);
    TextField txtpwdcnfMalik = new TextField("password confirmation", "", 22, TextField.PASSWORD);
    TextField txttelMalik = new TextField("telephone", "", 8, TextField.PHONENUMBER);
    TextField txtemailMalik = new TextField("Email", "@gmail.com", 100, TextField.EMAILADDR);
    DateField datensMalik = new DateField("Date of Birth", DateField.DATE);
    String LoginCurrentMalik = "";
    // SMS varible declaration

    TextBox messageBoxMalik;
    Alert alertMalik;
    Command cmdSendSMSMalik;
    MessageConnection clientConnMalik;

    // Musuci palyer declaration 
    PlayerManagerMalik managerMalik;
    Command cmdPauseMalik = new Command("Pause", Command.ITEM, 1);
    Command cmdResumeMalik = new Command("Play", Command.ITEM, 1);

    // Mail declaration 
    Form formComposeMailMalik;
    TextField txtSenderMailMalik;
    TextBox txtCorpsMailMalik;

    /*
     ======================================================================================
     --------------------------------------------------------------------------------------
     ************************** END of Malik's Global Variables****************************
     --------------------------------------------------------------------------------------
     ======================================================================================
    
     */
    /*
     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     --------------------------------------------------------------------------------------
     ******************************** Ala's Global Variables********************************
     --------------------------------------------------------------------------------------
     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
     */
    Command cmdParse = new Command(" Menu", Command.SCREEN, 0);
    Command cmdBack = new Command("retourner", Command.BACK, 0);
    Offre[] personnes;
    Commmentaire[] commentaires;
    List lst = new List("Liste des Offre", List.IMPLICIT);
    List lstC = new List("Liste des Commenatire", List.IMPLICIT);
    Form f = new Form("Accueil");
    String titreOffre = "";
    Form form = new Form("Infos Offre");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    StringBuffer sbC = new StringBuffer();
    String idOffre;
    String addressOffre;

    Image img;
    ImageItem imgItem;
    Image img2;
    ImageItem imgItem2;

    ImageItem imgItemphotoOffre = new ImageItem("", null, ImageItem.LAYOUT_CENTER, null);
    byte[] data;
    int size;
    String imagePath = "4b60e6576955da55a647e91d22d90dbdcd8d6217.jpeg";

    String urlImage = "";

    Images[] imagesP;
    String pa;
    String idImage;
    /**
     * ************************
     */
    Display disp2 = Display.getDisplay(this);
    //Form 1
    Form f1 = new Form("Inscription");
    TextField tfauteur = new TextField("auteur", null, 100, TextField.ANY);
    TextField tfnote = new TextField("note", null, 100, TextField.ANY);
    Gauge note = new Gauge("Note", true, 5, 3);
    TextField tfcontenu = new TextField("contenu", null, 100, TextField.ANY);
    DateField tfdate2 = new DateField("Date de naissance", DateField.DATE);
    TextField tfdate = new TextField("date", null, 100, TextField.ANY);
    TextField tfoffre = new TextField("offre id", null, 100, TextField.ANY);

    Command cmdBack2 = new Command("retourner", Command.BACK, 1);
    Command cmdValider = new Command("valider", Command.SCREEN, 2);

    Form f2 = new Form("Welcome");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parsing/ajoutCommentaire.php";
    StringBuffer sb2 = new StringBuffer();
    int ch;

    ///
    Command cmdRefrech = new Command("refrecher", Command.BACK, 1);
    public double latitude;
    public double longitude;
    public String ville;

    Command back;

    GoogleMaps gMaps = null;
    GoogleStaticMap map = null;

    Displayable testListScreen;
    MIDlet midlet;

    ImageItem mapItem;

    /**
     * ********
     */
    Command cmdAjouterCommentaire = new Command(" Commenter", Command.SCREEN, 0);
    Command cmdMap = new Command(" Map", Command.SCREEN, 0);

    List lstM;
    Command cmdBack3 = new Command("retourner", Command.BACK, 0);
    Gauge gInteractive = new Gauge("Volume", true, 20, 0);

    /*
     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     --------------------------------------------------------------------------------------
     ****************************** END OF Ala's Global Variables*******************************
     --------------------------------------------------------------------------------------
     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
     */
    public void startApp() {
        musicMalik("http://localhost/rea/onestop.mid");
   

        disp.setCurrent(new SplashScreenMalik(this));
        try {

            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        disp.vibrate(2000);
        disp.flashBacklight(2000);
        AcceuilMalik();
        formAcceuilMalik.addCommand(cmdPauseMalik);

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
        destroyApp(unconditional);
    }

    public void commandAction(Command c, Displayable d) {
        commandsMalik(c, d);
        commandActionsALA(c, d);
    }

    /*
     ======================================================================================
     --------------------------------------------------------------------------------------
     *********************************** Malik's Private Methods************************************
     --------------------------------------------------------------------------------------
     ======================================================================================
    
    
     */
    private void commandsMalik(Command c, Displayable d) {
        if (c == cmdcallMalik) {
            try {
                /*
                 If the URL specified is of the form tel:<number>, as specified in RFC2806, 
                 then the platform MUST interpret this as a request to initiate a voice call.
                 The request MUST be passed to the "phone" application to handle 
                 if one is present in the platform. 
                 The "phone" application, 
                 if present, MUST be able to set up local and global phone calls 
                 and also perform DTMF post dialing.
                 */
                platformRequest("tel:123456789");
                pauseApp();
                resumeRequest();
                disp.setCurrent(new Alert("thank you for calling"), formAcceuilMalik);
                System.out.println("call done");
            } catch (ConnectionNotFoundException ex) {
                disp.setCurrent(new Alert("Error calling"), formAcceuilMalik);

            }
        }

        if (c == cmdSignupMalik) {
            signUpformuleMalik();

        }
        if (c == cmdloginMalik) {
            loginFormule();
        }
        if (c == cmdnextMalik && d == formLoginMalik) {
            loggingInMalik();
        }
        if (c == cmdhomeMalik) {
            AcceuilMalik();
        }
        if (c == cmdsmsMalik) {
            smsFormMalik();
        }
        if (c == cmdSendSMSMalik && d == messageBoxMalik) {
            smsSendingMalik();
        }

        // pause musicMalik
        if (c == cmdPauseMalik) {
            pauseMusic();

        }

        // resume playing  musicMalik
        if (c == cmdResumeMalik) {
            resumeMusicMalik();
        }

        if (c == cmdnextMalik && d == formsignupMalik) {
            // test if entry valid 
            if (validSignupEntryMalik()) {
                registerMalik();
            } else {
                disp.setCurrent(new Alert("input invalid"), formsignupMalik);
            }

        }

        if (c == cmdlogoutMalik) {
            LoginCurrentMalik = "";
            AcceuilMalik();

        }

        if (c == cmdOffreMalik) {
            pauseMusic();
            startAla();
        }

        if (c == cmdEditprofileMalik) {
            clearFormsMalik();
            formEditMalik.addCommand(cmdnextMalik);
            formEditMalik.addCommand(cmdhomeMalik);
            formEditMalik.setCommandListener(this);
            formEditMalik.setTitle("edit profile : " + LoginCurrentMalik);
            //here put code that initiates and fills textfields from DB
            //********************************************************
            formEditMalik.setTicker(new Ticker("please fill all fields **Warning : any field left empty will be deleted ***"));
            formEditMalik.append(txtpwdMalik);
            formEditMalik.append(txtprenomMalik);
            formEditMalik.append(txtnomMalik);
            formEditMalik.append(txtemailMalik);
            formEditMalik.append(datensMalik);
            formEditMalik.append(txtadresseMalik);
            formEditMalik.append(txttelMalik);
            disp.setCurrent(formEditMalik);

        }
        if (c == cmdnextMalik && d == formEditMalik) {

            if (validEditEntryMalik()) {
                editingProfileMalik();
            } else {
                disp.setCurrent(new Alert("please fill all the fields"), formEditMalik);
            }

        }

        //mailing 
        if (c == cmdmailMalik) {
            mailForm1Malik();
        }
        if (c == cmdnextMalik && d == formComposeMailMalik) {
            mailForm2Malik();
        }
        if (c == cmdnextMalik && d == txtCorpsMailMalik) {
            mailing(txtSenderMailMalik.getString().trim(), txtCorpsMailMalik.getString().trim());
        }

        if (c == cmdbackMalik && d == txtCorpsMailMalik) {
            disp.setCurrent(formComposeMailMalik);
        }

    }

    private void clearFormsMalik() {
        formAcceuilMalik.deleteAll();
        formLoginMalik.deleteAll();
        formsignupMalik.deleteAll();
        formOffresMalik.deleteAll();
        formEditMalik.deleteAll();

        txtloginMalik.setString("");
        txtadresseMalik.setString("");
        txtpwdMalik.setString("");
        txtpwdcnfMalik.setString("");
        txtnomMalik.setString("");
        txtprenomMalik.setString("");
        txtemailMalik.setString("");
        txttelMalik.setString("");
        datensMalik.setDate(null);

    }

    private void signUpformuleMalik() {
        clearFormsMalik();
        formsignupMalik.append(txtloginMalik);
        formsignupMalik.append(txtpwdMalik);
        formsignupMalik.append(txtpwdcnfMalik);
        formsignupMalik.append(txtprenomMalik);
        formsignupMalik.append(txtnomMalik);
        formsignupMalik.append(txtemailMalik);
        formsignupMalik.append(datensMalik);
        formsignupMalik.append(txtadresseMalik);
        formsignupMalik.append(txttelMalik);

        formsignupMalik.addCommand(cmdnextMalik);
        formsignupMalik.addCommand(cmdmailMalik);
        formsignupMalik.addCommand(cmdsmsMalik);
        formsignupMalik.addCommand(cmdloginMalik);
        formsignupMalik.addCommand(cmdhomeMalik);
        formsignupMalik.setCommandListener(this);
        disp.setCurrent(formsignupMalik);
    }

    private void AcceuilMalik() {

        clearFormsMalik();

        formAcceuilMalik.setTicker(new Ticker("Welcome to EspritDev's RealEstate Agency"));
        try {
            formAcceuilMalik.append(Image.createImage("rea/LogoS.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        formAcceuilMalik.addCommand(cmdOffreMalik);
        if (LoginCurrentMalik == "") {
            formAcceuilMalik.removeCommand(cmdEditprofileMalik);
            formAcceuilMalik.removeCommand(cmdlogoutMalik);

            formAcceuilMalik.addCommand(cmdSignupMalik);
            formAcceuilMalik.addCommand(cmdloginMalik);
        } else {
            formAcceuilMalik.addCommand(cmdEditprofileMalik);
            formAcceuilMalik.addCommand(cmdlogoutMalik);
            formAcceuilMalik.removeCommand(cmdSignupMalik);
            formAcceuilMalik.removeCommand(cmdloginMalik);

        }

        formAcceuilMalik.addCommand(cmdmailMalik);
        formAcceuilMalik.addCommand(cmdsmsMalik);
        formAcceuilMalik.setCommandListener(this);
        formAcceuilMalik.addCommand(cmdcallMalik);
        //spalsh
        disp.setCurrent(new SplashScreenMalik(this));

        //acceuil
        disp.setCurrent(formAcceuilMalik);

    }

    void loginFormule() {
        clearFormsMalik();
        formLoginMalik.addCommand(cmdnextMalik);
        formLoginMalik.addCommand(cmdSignupMalik);
        formLoginMalik.addCommand(cmdsmsMalik);
        formLoginMalik.addCommand(cmdmailMalik);
        formLoginMalik.addCommand(cmdhomeMalik);
        formLoginMalik.setCommandListener(this);
        txtloginMalik.setString("");
        txtpwdMalik.setString("");

        formLoginMalik.append(txtloginMalik);
        formLoginMalik.append(txtpwdMalik);

        disp.setCurrent(formLoginMalik);
    }

    private void smsSendingMalik() {
        String log;
        if (LoginCurrentMalik.equals("")) {
            log = "Annonymous Client:\n";
        } else {
            log = LoginCurrentMalik;
        }

        //*******************************************************************
        // the Actual Work 
        //*******************************************************************
        {
            String mno = "123456789";
            String msg = "Message sent from " + log
                    + messageBoxMalik.getString().trim();

            try {
                clientConnMalik = (MessageConnection) Connector.open("sms://" + mno + ":" + "1992");

            } catch (Exception e) {
                alertMalik = new Alert("Alert");
                alertMalik.setString("Network problem");
                alertMalik.setTimeout(2000);
                e.printStackTrace();
                disp.setCurrent(alertMalik);
            }
            try {
                TextMessage textmessage = (TextMessage) clientConnMalik.newMessage(MessageConnection.TEXT_MESSAGE);
                textmessage.setAddress("sms://123456789:5000");
                textmessage.setPayloadText(msg);
                clientConnMalik.send(textmessage);
                clientConnMalik.close();
                disp.setCurrent(new Alert("message sent", "thank you for contacting Us \n"
                        + " we will call you in the next 24H ", null, AlertType.INFO), formAcceuilMalik);
                AcceuilMalik();
            } catch (Exception e) {
                alertMalik = new Alert("Alert", "", null, AlertType.INFO);
                alertMalik.setTimeout(Alert.FOREVER);
                e.printStackTrace();
                alertMalik.setString("Unable to send");
                disp.setCurrent(alertMalik);
            }

        }

    }

    private void smsFormMalik() {
        clearFormsMalik();
        //instanciation

        messageBoxMalik = new TextBox("Send us an SMS", "", 600, TextField.ANY);
        cmdSendSMSMalik = new Command("Send", Command.BACK, 0);

        messageBoxMalik.addCommand(cmdSendSMSMalik);
        messageBoxMalik.addCommand(cmdhomeMalik);

        messageBoxMalik.setCommandListener(this);
        disp.setCurrent(messageBoxMalik);

    }

    private void mailForm1Malik() {
        clearFormsMalik();
        //instanciation
        formComposeMailMalik = new Form("Email Us");
        txtSenderMailMalik = new TextField("Client Email", "example@gmail.com", 100, TextField.EMAILADDR);
        formComposeMailMalik.append(txtSenderMailMalik);
        formComposeMailMalik.addCommand(cmdnextMalik);
        formComposeMailMalik.addCommand(cmdhomeMalik);
        formComposeMailMalik.setCommandListener(this);
        disp.setCurrent(formComposeMailMalik);

    }

    private void mailForm2Malik() {

        //instanciation
        txtCorpsMailMalik = new TextBox("Message Content", "", 5000, TextField.ANY);
        txtCorpsMailMalik.setTicker(
                new Ticker("Tip : type ;; to start a new line"));

        txtCorpsMailMalik.addCommand(cmdnextMalik);
        txtCorpsMailMalik.addCommand(cmdbackMalik);
        txtCorpsMailMalik.addCommand(cmdhomeMalik);
        txtCorpsMailMalik.setCommandListener(this);
        disp.setCurrent(txtCorpsMailMalik);

    }

    private void musicMalik(String file) {
        managerMalik = new PlayerManagerMalik(formAcceuilMalik, file, disp);
        formAcceuilMalik.setCommandListener(managerMalik);
        Thread runner = new Thread(managerMalik);
        runner.start();

    }

    private void pauseMusic() {
        try {
            formAcceuilMalik.removeCommand(cmdPauseMalik);
            managerMalik.player.stop();
            formAcceuilMalik.addCommand(cmdResumeMalik);
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }

    private void resumeMusicMalik() {
        try {
            formAcceuilMalik.removeCommand(cmdResumeMalik);
            managerMalik.player.start();
            formAcceuilMalik.addCommand(cmdPauseMalik);
        } catch (MediaException ex) {
            ex.printStackTrace();
        }

    }

    private boolean validSignupEntryMalik() {
        boolean date_valide = false;
        try {
            Utility.Date2String(datensMalik.getDate());
            date_valide = true;
        } catch (Exception e) {
            date_valide = false;
        }

        return ((txtloginMalik.getString().trim().length() > 3)
                && (txtpwdMalik.getString().length() > 3 && txtpwdMalik.getString().equals(txtpwdcnfMalik.getString()))
                && (date_valide)
                && (txttelMalik.getString().length() == 8));

    }

    private boolean validEditEntryMalik() {
        boolean date_valide = false;
        try {
            Utility.Date2String(datensMalik.getDate());
            date_valide = true;
        } catch (Exception e) {
            date_valide = false;
        }
        return (datensMalik.toString().length() > 2
                && txtpwdMalik.getString().length() > 3
                && date_valide
                && txttelMalik.getString().length() == 8
                && txtadresseMalik.getString().length() > 2
                && txtemailMalik.getString().length() > 4
                && txtprenomMalik.getString().length() > 2
                && txtnomMalik.getString().length() > 2);

    }

    private void mailing(String SenderMail, String corps) {
        String log;
        if (LoginCurrentMalik.equals("")) {
            log = "Anonymous_User";
        } else {
            log = LoginCurrentMalik;
        }
        System.out.println("entry success :" + SenderMail + "\n " + corps);
        corps = Utility.replaceALL(";;", " <br>", corps);
        corps = Utility.replaceALL(" ", "%20", corps);

        System.out.println("corps with spaces as %20 symbles and ;; with <br> tag ; \n" + corps);
        String url = "http://localhost/rea/mail.php?"
                + "mail=" + SenderMail.trim()
                + "&corps=" + corps.trim()
                + "&login=" + log.trim();

        String rez = Utility.getStringconnection(url).toString().trim();

        System.out.println("mailing done\n" + rez);
        Alert info = new Alert(rez);

        if (rez.equals("Message has been sent")) {

            txtSenderMailMalik.setString("");
            txtCorpsMailMalik.setString("");
            formComposeMailMalik.deleteAll();
            clearFormsMalik();
            info.setType(AlertType.INFO);
            AcceuilMalik();
            disp.setCurrent(new Alert(rez), formAcceuilMalik);
        } else if (rez.equals("Message could not be sent.")) {

            info.setType(AlertType.ERROR);
            disp.setCurrent(info, formComposeMailMalik);
        }

    }

    private void registerMalik() {
        String log = txtloginMalik.getString().trim();
        String rez = Utility.getStringconnection("http://localhost/rea/clientExist.php?"
                + "login=" + log).toString().trim();

        if (rez.equals("exist")) {
            disp.setCurrent(new Alert("Client " + log + " exist"), formsignupMalik);
        } else if (rez.equals("clear")) {
            String adding = Utility.getStringconnection("http://localhost/rea/signup.php?"
                    + "login=" + log
                    + "&pwd=" + txtpwdMalik.getString()
                    + "&email=" + txtemailMalik.getString()
                    + "&nom=" + txtnomMalik.getString()
                    + "&prenom=" + txtprenomMalik.getString()
                    + "&dob=" + Utility.Date2String(datensMalik.getDate())
                    + "&tel=" + txttelMalik.getString()
                    + "&adr=" + txtadresseMalik.getString()).toString().trim();
            System.out.println(adding + " client   " + log);
            System.out.println(datensMalik.toString());
            if (adding.equals("success")) {
                LoginCurrentMalik = log;

                disp.setCurrent(new Alert("thank You for Joining Us"), formAcceuilMalik);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                AcceuilMalik();
            } else {
                disp.setCurrent(new Alert("SignUp Failed"), formsignupMalik);
            }
        }

    }

    private void loggingInMalik() {
        String log = Utility.getStringconnection("http://localhost/rea/clientExist.php?"
                + "login=" + txtloginMalik.getString().trim()).toString().trim();
        if (log.equals("exist")) {
            String responce = Utility.getStringconnection("http://localhost/rea/loginclient.php?"
                    + "login=" + txtloginMalik.getString().trim()
                    + "&pwd=" + txtpwdMalik.getString()).toString().trim();
            if (responce.equals("bad password")) {
                disp.setCurrent(new Alert("password invalid"), formLoginMalik);
            } else {
                LoginCurrentMalik = responce;
                formAcceuilMalik.removeCommand(cmdloginMalik);
                formAcceuilMalik.addCommand(cmdlogoutMalik);

                disp.setCurrent(new Alert("Hello", "Welcome back " + responce, null, AlertType.INFO), formAcceuilMalik);
            }
        } else if (log.equals("clear")) {
            disp.setCurrent(new Alert("Login invalid"), formLoginMalik);
        }
    }

    void editingProfileMalik() {

        String update = Utility.getStringconnection("http://localhost/rea/editClient.php?"
                + "login=" + LoginCurrentMalik.trim()
                + "&pwd=" + txtpwdMalik.getString()
                + "&email=" + txtemailMalik.getString()
                + "&nom=" + txtnomMalik.getString()
                + "&prenom=" + txtprenomMalik.getString()
                + "&dob=" + Utility.Date2String(datensMalik.getDate())
                + "&tel=" + txttelMalik.getString()
                + "&adr=" + txtadresseMalik.getString()).toString().trim();
        if (update.equals("successfull update")) {
            clearFormsMalik();
            System.out.println(update + LoginCurrentMalik);
            AcceuilMalik();
        } else {
            disp.setCurrent(new Alert("Something went wrong with the update"), formEditMalik);
        }
    }
    /*
     ======================================================================================
     --------------------------------------------------------------------------------------
     *********************************** End Of Malik's Work ************************************
     --------------------------------------------------------------------------------------
     ======================================================================================
    
    
     */

    /*
     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     --------------------------------------------------------------------------------------
     *********************************** START Of ALA's Work ************************************
     --------------------------------------------------------------------------------------
     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    
     */
    public void startAla() {
        try {
            img = Image.createImage("image/House-icon.png");
            imgItem = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);

            img2 = Image.createImage("image/detached-house2.jpg");
            imgItem2 = new ImageItem("", img2, ImageItem.LAYOUT_CENTER, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        f.append(imgItem2);
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        f.addCommand(cmdhomeMalik);
        lst.setCommandListener(this);
        lst.addCommand(cmdBack3);
        lst.addCommand(cmdhomeMalik);
        form.addCommand(cmdBack);
        form.setCommandListener(this);

        disp.setCurrent(new SplashScreen1(this, f));

        /**
         * *************
         */
        // f1.append(tfoffre);
        //pas besoin d'afficher id -_-
        f1.append(tfauteur);
        tfauteur.setString(LoginCurrentMalik);
        f1.append(note);
        f1.append(tfcontenu);

        f2.append("votre commentaire a eté inseré avec succès !!");
        f3.append("erreur lors de l'insertion de votre commentaire  !!");

        tfdate2.setDate(Calendar.getInstance().getTime());
        //tfdate.setString((Calendar.getInstance().getTime().toString());

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dateWithoutTime = cal.getTime();

        tfdate.setString((dateWithoutTime).toString());

        //f1.append(tfdate);
        f1.addCommand(cmdValider);
        f1.setCommandListener(this);
        f1.addCommand(cmdBack2);
        f1.setCommandListener(this);

        f2.addCommand(cmdBack2);
        f2.setCommandListener(this);

        ///
        form.addCommand(cmdRefrech);
        form.addCommand(cmdMap);
    }

    public void commandActionsALA(Command c, Displayable d) {

        if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }

        if (c == List.SELECT_COMMAND) {
            form.deleteAll();
            form.append("Titre: " + personnes[lst.getSelectedIndex()].getTitreOffre() + "\n");

            idImage = getIdImage(lst.getSelectedIndex());

            imagePath = showImage();
            urlImage = "http://localhost/PiDev03/web/uploads/" + imagePath;

            try {

                hc = (HttpConnection) Connector.open(urlImage);
                dis = hc.openDataInputStream();
                size = (int) hc.getLength();
                data = new byte[size];
                dis.readFully(data);
                // missing pictures

                imgItemphotoOffre.setImage(Image.createImage(data, 0, size));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            form.append(imgItemphotoOffre);

            //form.append(imgItem);
            form.append(showPersonne(lst.getSelectedIndex()));

            //int x = lst.getSelectedIndex();
            //x = x +4;
            //idOffre = Integer.toString(x);
            idOffre = getOffreId(lst.getSelectedIndex());
            addressOffre = getaddressOffre(lst.getSelectedIndex());

            showCommentaire();
            form.addCommand(cmdAjouterCommentaire);

            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }

        if (c == cmdBack3) {
            form.deleteAll();
            disp.setCurrent(lstM);
        }

        /**
         * *
         */
        if (c == cmdAjouterCommentaire) {
            tfoffre.setString(idOffre);

            disp.setCurrent(f1);
            /*
           
             */

        }

        if (c == cmdValider) {
            try {
                hc = (HttpConnection) Connector.open(url + "?offre=" + tfoffre.getString().trim() + "&auteur=" + tfauteur.getString().trim() + "&note=" + note.getValue() + "&contenu=" + tfcontenu.getString());
                //tfdate.getDate().toString().trim()
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char) ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(f2);
                    refrech();
                } else {
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (c == cmdBack2) {

            disp.setCurrent(form);
        }

        if (c == cmdRefrech) {

            //refrech();
        }
        if (c == cmdMap) {
            disp.setCurrent(new GoogleMapsTestForm(this, d, addressOffre));
            //refrech();
        }
        /**
         * *
         */
    }

    private String showPersonne(int i) {
        String res = "";
        if (personnes.length > 0) {
            //sb.append("*id: ");
            //sb.append(personnes[i].getId());
            sb.append("\n");
            sb.append("*AdresseOffre: ");
            sb.append(personnes[i].getAdresseOffre());
            sb.append("\n");
            sb.append("*PrixOffre: ");
            sb.append(personnes[i].getPrixOffre());
            sb.append("\n");
            //sb.append("*TitreOffre: ");
            //sb.append(personnes[i].getTitreOffre());
            //sb.append("\n");
            sb.append("*date Ajout: ");
            sb.append(personnes[i].getDateAjout());
            sb.append("\n");
            sb.append("*SuperficieOffre: ");
            sb.append(personnes[i].getSuperficieOffre());
            sb.append("\n");
            sb.append("*TypeProprietaire: ");
            sb.append(personnes[i].getTypeProprietaire());
            sb.append("\n");
            sb.append("*NatureOffre: ");
            sb.append(personnes[i].getNatureOffre());
            sb.append("\n");
            sb.append("*Note: ");
            sb.append(personnes[i].getNote());
            sb.append("\n");

        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private String getOffreId(int i) {
        String res = "";
        if (personnes.length > 0) {

            sb.append(personnes[i].getId());

        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private String getaddressOffre(int i) {
        String res = "";
        if (personnes.length > 0) {

            sb.append(personnes[i].getAdresseOffre());

        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private String getIdImage(int i) {
        String res = "";
        if (personnes.length > 0) {
            sb.append(personnes[i].getPhotoOffre());

        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    private String showImage() {

        try {
            // this will handle our XML
            ImageHandler imageHandler = new ImageHandler();
            // get a parser object
            SAXParser parserImage = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hcImage = (HttpConnection) Connector.open("http://localhost/parsing/getImage.php?id=" + idImage);
            DataInputStream disImage = new DataInputStream(hcImage.openDataInputStream());
            parserImage.parse(disImage, imageHandler);
            // display the result

            Images[] imagesP = imageHandler.getPersonne();

            if (imagesP.length > 0) {
                for (int i = 0; i < imagesP.length; i++) {

                    pa = imagesP[i].getPath();

                }
            } else {

                form.append("pas d'image pour cet Offre");
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }

        //form.append("pas d'image pour cet Offre");
        //pa="8b736bdaa3518eea3138c8aa882f85214fce0bce.jpeg";
        return pa;
    }

    private String showCommentaire() {

        try {
            // this will handle our XML
            CommentaireHandler CommentaireHandler = new CommentaireHandler();
            // get a parser object
            SAXParser parserC = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hcC = (HttpConnection) Connector.open("http://localhost/parsing/getXmlCommentaire_Attributes.php/?id=" + idOffre);
            DataInputStream disC = new DataInputStream(hcC.openDataInputStream());
            parserC.parse(disC, CommentaireHandler);
            // display the result
            commentaires = CommentaireHandler.getPersonne();

            form.append("**Evaluer l'Offe**");

            if (commentaires.length > 0) {
                for (int i = 0; i < commentaires.length; i++) {
                    form.append("Auteur: " + commentaires[i].getAuteur() + "\nnote: "
                            + commentaires[i].getNote() + "\nCommentaire :" + commentaires[i].getContenu() + "\n");
                }
            } else {

                form.append("pas encore de commentaire , soyer le 1er !!");
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }

        return null;

    }

    public void refrech() {
        form.deleteAll();
        form.append("Titre: " + personnes[lst.getSelectedIndex()].getTitreOffre() + "\n");

        idImage = getIdImage(lst.getSelectedIndex());

        imagePath = showImage();
        urlImage = "http://localhost/PiDev03/web/uploads/" + imagePath;

        try {

            hc = (HttpConnection) Connector.open(urlImage);
            dis = hc.openDataInputStream();
            size = (int) hc.getLength();
            data = new byte[size];
            dis.readFully(data);
            imgItemphotoOffre.setImage(Image.createImage(data, 0, size));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        form.append(imgItemphotoOffre);

        //form.append(imgItem);
        form.append(showPersonne(lst.getSelectedIndex()));

        //int x = lst.getSelectedIndex();
        //x = x +4;
        //idOffre = Integer.toString(x);
        idOffre = getOffreId(lst.getSelectedIndex());

        showCommentaire();
        form.addCommand(cmdAjouterCommentaire);

        disp.setCurrent(form);
    }

    /*
     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     --------------------------------------------------------------------------------------
     *********************************** End Of ALA's Work ************************************
     --------------------------------------------------------------------------------------
     xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    
    
     */
    public void run() {
        try {
            // this will handle our XML
            OffreHandler offreHandler = new OffreHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsing/getXmlOffres_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, offreHandler);
            // display the result
            personnes = offreHandler.getPersonne();

            if (personnes.length > 0) {
                for (int i = 0; i < personnes.length; i++) {
                    lst.append("Titre: " + personnes[i].getTitreOffre(), null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        //disp.setCurrent(lst);
        Menu menu = new Menu(this, f, lst);
        lstM = menu.getList();

    }
}
