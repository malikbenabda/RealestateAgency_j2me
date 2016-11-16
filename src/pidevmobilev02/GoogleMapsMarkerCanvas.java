package pidevmobilev02;

import com.jappit.midmaps.googlemaps.GoogleMaps;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;


import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;

public class GoogleMapsMarkerCanvas extends GoogleMapsTestCanvas implements GoogleStaticMapHandler
{
	GoogleMaps gMaps = null;
	
	GoogleStaticMap map = null;
        
        public double latitude;
        public double longitude;
        public String ville;
	
	public GoogleMapsMarkerCanvas(MIDlet m, Displayable testListScreen)
	{
		
            
                super(m, testListScreen);
		
		gMaps = new GoogleMaps();
		
		map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);
		
		map.setHandler(this);
		
		getMapscoordinates(latitude,longitude,"tunis");
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
	
	protected void paint(Graphics g)
	{
		map.draw(g, 0, 0, Graphics.TOP | Graphics.LEFT);
	}
	
	public void GoogleStaticMapUpdateError(GoogleStaticMap map, int errorCode, String errorMessage)
	{
		showError("map error: " + errorCode + ", " + errorMessage);
	}
	public void GoogleStaticMapUpdated(GoogleStaticMap map)
	{
		repaint();
	}
	
        public void getMapscoordinates(double latitude,double longitude,String ville){
           
            if(ville.equals("tunis") ){
                this.latitude=36.89931;
                this.longitude=10.188750;

            }else if(ville.equals("ariana")){
                 this.latitude=36.9216264;
                 this.longitude=10.15703201;
            
            }else if(ville.equals("beja") || ville.equals("béja")){
                
            }else if(ville=="Ben Arous" || ville=="BenArous"){
                
            }else if(ville.equals("bizerte")){
                 this.latitude=37.26804221;
                 this.longitude=9.86658096;
                
            }else if(ville.equals("sousse")){
                 this.latitude=35.9022267;
                 this.longitude=10.3497895;
                
            }else if(ville=="nabeul"){
                
            }else{
                this.latitude=35.9022267;
                 this.longitude=10.3497895;
            }
        

             //this.latitude=37.26804221;
             //this.longitude=9.86658096;
                    }
        
        
}