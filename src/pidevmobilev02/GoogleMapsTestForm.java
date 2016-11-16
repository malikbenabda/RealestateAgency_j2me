package pidevmobilev02;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.midlet.MIDlet;

import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;

public class GoogleMapsTestForm extends Form implements GoogleStaticMapHandler, CommandListener
{
	Command back;
	
	GoogleMaps gMaps = null;
	GoogleStaticMap map = null;
	
	Displayable testListScreen;
	MIDlet midlet;
	
	ImageItem mapItem;
        
        
        public double latitude;
        public double longitude;
        public String ville;
        
	
	public GoogleMapsTestForm(MIDlet m, Displayable testListScreen,String ville)
	{
		super("Map de : "+ville);
		
		this.ville=ville;
                this.midlet = m;
		this.testListScreen = testListScreen;
		
		addCommand(back = new Command("Back", Command.BACK, 1));
		
		setCommandListener(this);
		
		mapItem = new ImageItem("Loading map...", null, Item.LAYOUT_TOP, "Sample map");
		
		append(mapItem);
		
		gMaps = new GoogleMaps();
		
		map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
		
		map.setHandler(this);
                
		getMapscoordinates(latitude,longitude,ville);
                System.out.println("latitude: "+latitude);
                System.out.println("longitude: "+longitude);
                
                //latitude=37.26804221;
                //longitude=9.86658096;
                
                
                map.setCenter(new GoogleMapsCoordinates(latitude, longitude));
		
		GoogleMapsMarker redMarker = new GoogleMapsMarker(new GoogleMapsCoordinates(latitude, longitude));
		redMarker.setColor(GoogleStaticMap.COLOR_RED);
		redMarker.setSize(GoogleMapsMarker.SIZE_MID);
		redMarker.setLabel('E');
		
		map.addMarker(redMarker);
		
		map.setZoom(15);
		
		map.update();
	}
	public void GoogleStaticMapUpdateError(GoogleStaticMap map, int errorCode, String errorMessage)
	{
		System.out.println("map error: " + errorCode + ", " + errorMessage);
	}
	public void GoogleStaticMapUpdated(GoogleStaticMap map)
	{
		System.out.println("map ok");
		
		mapItem.setImage(map.getImage());
		
		mapItem.setLabel("Map loaded! : "+ville);
	}
	public void commandAction(Command c, Displayable d)
	{
		if(c == back)
		{
			Display.getDisplay(midlet).setCurrent(testListScreen);
		}
	}
        
          public void getMapscoordinates(double latitude,double longitude,String ville){
           
            if(ville.equals("tunis") ){
                this.latitude=36.89931;
                this.longitude=10.188750;

            }else if(ville.equals("ariana")){
                 this.latitude=36.85843735;
                 this.longitude=10.19537687;
            
            }else if(ville.equals("beja") || ville.equals("béja")){
                
            }else if(ville=="Ben Arous" || ville=="BenArous"){
                
            }else if(ville.equals("bizerte")){
                 this.latitude=37.26804221;
                 this.longitude=9.86658096;
                
            }else if(ville.equals("sousse")){
                 this.latitude=35.825603;
                 this.longitude=10.608395;
                
            }else if(ville.equals("nabeul")){
                 this.latitude=36.454167;
                 this.longitude=10.734722;
                
            }else if(ville.equals("mahdia") || ville.equals("Mahdia") ){
                 this.latitude=35.5024461;
                 this.longitude=11.045721;
                
            }else{
                this.latitude=36.89931;
                this.longitude=10.188750;
            }
        

             //this.latitude=37.26804221;
             //this.longitude=9.86658096;
                    }
        
}
