/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rea;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

/**
 *
 * @author Stark
 */
class PlayerManagerMalik implements Runnable, CommandListener, PlayerListener {

    Form form;
    List list;
    Player player;
    String locator;
    Display display;

    private Command stopCommand;
    private Command pauseCommand;
    private Command startCommand;

    public PlayerManagerMalik(Form form, String fichier, Display display) {
        this.form = form;

        this.locator = fichier;
        this.display = display;

        // stop, pause and restart commands
        stopCommand = new Command("Stop", Command.STOP, 1);
        pauseCommand = new Command("Pause", Command.ITEM, 1);
        startCommand = new Command("Play", Command.ITEM, 1);

    }

    public void run() {

        try {

            // since we are loading data over the network, a delay can be
            // expected
            Alert alert = new Alert("Loading. Please wait ....");
            alert.setTimeout(Alert.FOREVER);
            display.setCurrent(alert);

            player = Manager.createPlayer(locator);
            

            // a listener to handle player events like starting, closing etc
            player.addPlayerListener(this);

            player.setLoopCount(-1); // play indefinitely
            player.prefetch(); // prefetch
            player.realize(); // realize

            player.start(); // and start
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }

    }

    public void commandAction(Command command, Displayable disp) {

    }

    /* Handle player events */
    public void playerUpdate(Player player, String event, Object eventData) {

        // if the event is that the player has started, show the form
        // but only if the event data indicates that the event relates to newly
        // stated player, as the STARTED event is fired even if a player is
        // restarted. Note that eventData indicates the time at which the start
        // event is fired.
        if (event.equals(PlayerListener.STARTED)
                && new Long(0L).equals((Long) eventData)) {

            display.setCurrent(form);

        } else if (event.equals(PlayerListener.CLOSED)) {

            form.deleteAll(); // clears the form of any previous controls
        }
    }

}
