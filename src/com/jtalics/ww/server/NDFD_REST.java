package com.jtalics.ww.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.w3c.dom.CharacterData;

import com.jtalics.ww.shared.City;
import com.jtalics.ww.shared.LatLon;


public class NDFD_REST {

	public static final Map<City, LatLon> cityToLatlon = new HashMap<City, LatLon>();

	private static void loadCityToLatlon() throws ParserConfigurationException, SAXException, IOException {
		String url="http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?listCitiesLevel=12";
		String s = ndfdRequest(url);
		Document doc=getXmlDoc(s);
		NodeList nl=doc.getElementsByTagName("cityNameList");
		String cityNameList = getCharacterDataFromElement((Element) nl.item(0));
		nl=doc.getElementsByTagName("latLonList");
		String latLonList = getCharacterDataFromElement((Element) nl.item(0));
		
		String[] cityNames = cityNameList.split("\\|");
		String[] latlonNames = latLonList.split(" ");
		
		if (cityNames.length != latlonNames.length) {
			throw new RuntimeException();
		}
		for (int i=0; i<cityNames.length; i++) {
			cityToLatlon.put(new City(cityNames[i]),new LatLon(latlonNames[i]));
		}
		return;
	}
	
	private static Document getXmlDoc(String xml) throws ParserConfigurationException, SAXException, IOException {
	    
//		String xmlRecords = "<data><employee><name>A</name>"+ "<title>Manager</title></employee></data>";
	    String xml3 = "<?xml version='1.0' ?><dwml version='1.0' xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://graphical.weather.gov/xml/DWMLgen/schema/DWML.xsd\"><latLonList>35.4,-97.6 36.2,-95.9 35.82,-83.98 33.65,-84.42 39.5,-119.78 35.13,-111.67 33.44,-112.02 32.12,-110.93 35.60,-88.92 35.22,-92.38 34.027,-118.329 32.73,-117.17 38.29,-104.52 39.75,-104.87 38.85,-77.04 24.58,-81.75 25.80,-80.28 28.55,-81.33 27.97,-82.53 43.57,-116.22 41.98,-87.9 39.85,-89.67 39.37,-101.7 37.07,-88.77 30.03,-90.03 44.801,-68.778 46.861,-68.012 43.65,-70.32 39.18,-76.67 42.23,-83.33 46.54,-87.41 46.83,-92.18 44.88,-93.22 34.27,-88.77 38.75,-90.37 45.8,-108.53 45.95,-112.5 48.22,-106.62 46.92,-114.08 40.83,-115.78 36.08,-115.17 35.05,-106.6 42.75,-73.8 42.93,-78.73 43.12,-76.12 40.77,-73.98 46.77,-100.75 46.9,-96.8 48.27,-101.28 41.42,-81.87 40.00,-82.88 41.6,-83.8 45.6,-122.6 42.08,-80.18 39.88,-75.25 40.35,-79.93 41.73,-72.65 43.58,-96.73 36.17,-86.78 30.3,-97.7 25.9,-97.43 32.9,-97.03 31.8,-106.4 32.00,-102.08 29.65,-95.28 29.53,-98.47 40.78,-111.97 47.63,-117.53 42.95,-87.9 37.5,-77.33 18.40,-66.00 18.34,-64.96 17.70,-64.90 61.17,-150.02 71.30,-156.78 64.82,-147.87 58.37,-134.58 64.50,-165.43 39.32,-94.72 40.85,-96.75 39.07,-95.62 33.9,-98.5 37.65,-97.43 34.8372,-82.3716 38.37,-81.6 37.32,-79.97 35.1975,-80.8345 32.7,-83.65 33.37,-81.96 40.56,-122.36 33.57,-86.75 34.65,-86.77 32.65,-114.62 35.83,-90.65 33.45,-94.00 36.77,-119.72 37.15,-107.75 39.12,-108.53 29.173,-82.224 26.68,-80.09 42.48,-114.48 40.67,-89.68 38.05,-87.53 41.00,-85.2 40.40,-86.87 39.73,-86.27 41.7,-86.32 41.53,-93.65 37.77,-99.97 37.05,-100.97 38.23,-85.67 30.53,-91.15 30.12,-93.22 32.47,-93.82 44.88,-70.88 44.90,-67.01 45.657,-68.710 42.27,-71.87 45.07,-83.57 42.92,-82.53 46.53,-90.13 42.77,-84.6 43.53,-84.08 46.47,-84.37 48.57,-93.38 32.32,-90.08 38.82,-92.22 36.77,-90.47 37.23,-93.38 48.55,-109.77 46.60,-112.00 48.3,-114.27 40.6,-98.43 41.13,-100.68 39.28,-114.85 43.2,-71.5 33.3,-104.53 42.17,-76.9 43.12,-77.67 35.63,-77.30 34.78,-76.88 35.27,-75.55 35.87,-78.78 47.95,-97.18 39.046,-84.662 39.9,-84.2 45.68,-118.85 43.25,-123.35 44.92,-123.00 34.15,-79.71 33.95,-81.14 45.45,-98.43 44.38,-100.28 44.05,-103.07 44.92,-97.15 35.05,-90.00 35.44,-86.79 31.33,-94.72 35.23,-101.7 32.42,-99.68 33.65,-101.82 31.37,-100.5 31.62,-97.22 43.54,-72.95 44.47,-73.15 46.97,-122.9 47.45,-122.3 43.87,-91.25 43.13,-89.33 44.00,-88.57 45.63,-89.45 42.74,-105.39 41.78,-107.22 44.55,-110.42 43.03,-108.41 36.9,-76.2 38.13,-78.45 37.36,-121.92 18.12,-65.45 19.72,-155.06 21.33,-157.94 21.98,-159.34 20.89,-156.44 19.74,-156.04 51.88,-176.65 66.92,-151.52 55.35,-131.70 62.97,-155.62 60.18,-149.75 13.48,144.81 14.16,145.23 14.96,145.65 15.21,145.72</latLonList><cityNameList>Oklahoma City,OK|Tulsa,OK|Knoxville,TN|Atlanta,GA|Reno,NV|Flagstaff,AZ|Phoenix,AZ|Tucson,AZ|Jackson,TN|Little Rock,AR|Los Angeles,CA|San Diego,CA|Pueblo,CO|Denver,CO|Washington,DC|Key West,FL|Miami,FL|Orlando,FL|Tampa,FL|Boise,ID|Chicago,IL|Springfield,IL|Goodland,KS|Paducah,KY|New Orleans,LA|Bangor,ME|Caribou,ME|Portland,ME|Baltimore,MD|Detroit,MI|Marquette,MI|Duluth,MN|Minneapolis,MN|Tupelo,MS|St Louis,MO|Billings,MT|Butte,MT|Glasgow,MT|Missoula,MT|Elko,NV|Las Vegas,NV|Albuquerque,NM|Albany,NY|Buffalo,NY|Syracuse,NY|New York,NY|Bismarck,ND|Fargo,ND|Minot,ND|Cleveland,OH|Columbus,OH|Toledo,OH|Portland,OR|Erie,PA|Philadelphia,PA|Pittsburgh,PA|Hartford,CT|Sioux Falls,SD|Nashville,TN|Austin,TX|Brownsville,TX|Dallas,TX|El Paso,TX|Midland,TX|Houston,TX|San Antonio,TX|Salt Lake City,UT|Spokane,WA|Milwaukee,WI|Richmond,VA|San Juan,PR|St Thomas,VI|St Croix,VI|Anchorage,AK|Barrow,AK|Fairbanks,AK|Juneau,AK|Nome,AK|Kansas City,MO|Lincoln,NE|Topeka,KS|Wichita Falls,TX|Wichita,KS|Greenville,SC|Charleston,WV|Roanoke,VA|Charlotte,NC|Macon,GA|Augusta,GA|Redding,CA|Birmingham,AL|Huntsville,AL|Yuma,AZ|Jonesboro,AR|Texarkana,AR|Fresno,CA|Durango,CO|Grand Junction,CO|Ocala,FL|Palm Beach,FL|Twin Falls,ID|Peoria,IL|Evansville,IN|Fort Wayne,IN|Lafayette,IN|Indianapolis,IN|South Bend,IN|Des Moines,IA|Dodge City,KS|Liberal,KS|Louisville,KY|Baton Rouge,LA|Lake Charles,LA|Shreveport,LA|Rumford,ME|Eastport,ME|Millinocket,ME|Worcester,MA|Alpena,MI|Port Huron,MI|Ironwood,MI|Lansing,MI|Saginaw,MI|Sault St Marie,MI|International Falls,MN|Jackson,MS|Columbia,MO|Poplar Bluff,MO|Springfield,MO|Havre,MT|Helena,MT|Kalispell,MT|Hastings,NE|North Platte,NE|Ely,NV|Concord,MA|Roswell,NM|Elmira,NY|Rochester,NY|Greenville,NC|Newport,NC|Cape Hatteras,NC|Raleigh-Durham,NC|Grand Forks,ND|Cincinnati,OH|Dayton,OH|Pendleton,OR|Roseburg,OR|Salem,OR|Florence,SC|Columbia,SC|Aberdeen,SD|Pierre,SD|Rapid City,SD|Watertown,SD|Memphis,TN|Lewisburg,TN|Lufkin,TX|Amarillo,TX|Abilene,TX|Lubbock,TX|San Angelo,TX|Waco,TX|Rutland,VT|Burlington,VT|Olympia,WA|Seattle,WA|LaCrosse,WI|Madison,WI|Oshkosh,WI|Rhinelander,WI|Douglas,WY|Rawlins,WY|Yellowstone,WY|Riverton,WY|Norfolk,VA|Charlottesville,VA|San Jose,CA|Vieques,PR|Hilo,HI|Honolulu,HI|Lihue,HI|Kahului,HI|Kona,HI|Adak,AK|Bettles,AK|Ketchikan,AK|McGrath,AK|Seward,AK|Guam,GU|Rota,GU|Tinian,GU|Saipan,GU</cityNameList></dwml>";

	    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xml3));

	    Document doc = db.parse(is);
/*	    NodeList nodes = doc.getElementsByTagName("cityNameList");
	    for (int i = 0; i < nodes.getLength(); i++) {
	        Element element = (Element) nodes.item(i);
	        System.out.println("ELEMENT: " + getCharacterDataFromElement(element));
	      }
*/
/*		    
	    String xml2 = "<data><employee><name>A</name>"+ "<title>Manager</title></employee></data>";
//	    String xml3 = "<?xml version='1.0' ?><dwml version='1.0' xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"http://graphical.weather.gov/xml/DWMLgen/schema/DWML.xsd\"><latLonList>35.4,-97.6 36.2,-95.9 35.82,-83.98 33.65,-84.42 39.5,-119.78 35.13,-111.67 33.44,-112.02 32.12,-110.93 35.60,-88.92 35.22,-92.38 34.027,-118.329 32.73,-117.17 38.29,-104.52 39.75,-104.87 38.85,-77.04 24.58,-81.75 25.80,-80.28 28.55,-81.33 27.97,-82.53 43.57,-116.22 41.98,-87.9 39.85,-89.67 39.37,-101.7 37.07,-88.77 30.03,-90.03 44.801,-68.778 46.861,-68.012 43.65,-70.32 39.18,-76.67 42.23,-83.33 46.54,-87.41 46.83,-92.18 44.88,-93.22 34.27,-88.77 38.75,-90.37 45.8,-108.53 45.95,-112.5 48.22,-106.62 46.92,-114.08 40.83,-115.78 36.08,-115.17 35.05,-106.6 42.75,-73.8 42.93,-78.73 43.12,-76.12 40.77,-73.98 46.77,-100.75 46.9,-96.8 48.27,-101.28 41.42,-81.87 40.00,-82.88 41.6,-83.8 45.6,-122.6 42.08,-80.18 39.88,-75.25 40.35,-79.93 41.73,-72.65 43.58,-96.73 36.17,-86.78 30.3,-97.7 25.9,-97.43 32.9,-97.03 31.8,-106.4 32.00,-102.08 29.65,-95.28 29.53,-98.47 40.78,-111.97 47.63,-117.53 42.95,-87.9 37.5,-77.33 18.40,-66.00 18.34,-64.96 17.70,-64.90 61.17,-150.02 71.30,-156.78 64.82,-147.87 58.37,-134.58 64.50,-165.43 39.32,-94.72 40.85,-96.75 39.07,-95.62 33.9,-98.5 37.65,-97.43 34.8372,-82.3716 38.37,-81.6 37.32,-79.97 35.1975,-80.8345 32.7,-83.65 33.37,-81.96 40.56,-122.36 33.57,-86.75 34.65,-86.77 32.65,-114.62 35.83,-90.65 33.45,-94.00 36.77,-119.72 37.15,-107.75 39.12,-108.53 29.173,-82.224 26.68,-80.09 42.48,-114.48 40.67,-89.68 38.05,-87.53 41.00,-85.2 40.40,-86.87 39.73,-86.27 41.7,-86.32 41.53,-93.65 37.77,-99.97 37.05,-100.97 38.23,-85.67 30.53,-91.15 30.12,-93.22 32.47,-93.82 44.88,-70.88 44.90,-67.01 45.657,-68.710 42.27,-71.87 45.07,-83.57 42.92,-82.53 46.53,-90.13 42.77,-84.6 43.53,-84.08 46.47,-84.37 48.57,-93.38 32.32,-90.08 38.82,-92.22 36.77,-90.47 37.23,-93.38 48.55,-109.77 46.60,-112.00 48.3,-114.27 40.6,-98.43 41.13,-100.68 39.28,-114.85 43.2,-71.5 33.3,-104.53 42.17,-76.9 43.12,-77.67 35.63,-77.30 34.78,-76.88 35.27,-75.55 35.87,-78.78 47.95,-97.18 39.046,-84.662 39.9,-84.2 45.68,-118.85 43.25,-123.35 44.92,-123.00 34.15,-79.71 33.95,-81.14 45.45,-98.43 44.38,-100.28 44.05,-103.07 44.92,-97.15 35.05,-90.00 35.44,-86.79 31.33,-94.72 35.23,-101.7 32.42,-99.68 33.65,-101.82 31.37,-100.5 31.62,-97.22 43.54,-72.95 44.47,-73.15 46.97,-122.9 47.45,-122.3 43.87,-91.25 43.13,-89.33 44.00,-88.57 45.63,-89.45 42.74,-105.39 41.78,-107.22 44.55,-110.42 43.03,-108.41 36.9,-76.2 38.13,-78.45 37.36,-121.92 18.12,-65.45 19.72,-155.06 21.33,-157.94 21.98,-159.34 20.89,-156.44 19.74,-156.04 51.88,-176.65 66.92,-151.52 55.35,-131.70 62.97,-155.62 60.18,-149.75 13.48,144.81 14.16,145.23 14.96,145.65 15.21,145.72</latLonList><cityNameList>Oklahoma City,OK|Tulsa,OK|Knoxville,TN|Atlanta,GA|Reno,NV|Flagstaff,AZ|Phoenix,AZ|Tucson,AZ|Jackson,TN|Little Rock,AR|Los Angeles,CA|San Diego,CA|Pueblo,CO|Denver,CO|Washington,DC|Key West,FL|Miami,FL|Orlando,FL|Tampa,FL|Boise,ID|Chicago,IL|Springfield,IL|Goodland,KS|Paducah,KY|New Orleans,LA|Bangor,ME|Caribou,ME|Portland,ME|Baltimore,MD|Detroit,MI|Marquette,MI|Duluth,MN|Minneapolis,MN|Tupelo,MS|St Louis,MO|Billings,MT|Butte,MT|Glasgow,MT|Missoula,MT|Elko,NV|Las Vegas,NV|Albuquerque,NM|Albany,NY|Buffalo,NY|Syracuse,NY|New York,NY|Bismarck,ND|Fargo,ND|Minot,ND|Cleveland,OH|Columbus,OH|Toledo,OH|Portland,OR|Erie,PA|Philadelphia,PA|Pittsburgh,PA|Hartford,CT|Sioux Falls,SD|Nashville,TN|Austin,TX|Brownsville,TX|Dallas,TX|El Paso,TX|Midland,TX|Houston,TX|San Antonio,TX|Salt Lake City,UT|Spokane,WA|Milwaukee,WI|Richmond,VA|San Juan,PR|St Thomas,VI|St Croix,VI|Anchorage,AK|Barrow,AK|Fairbanks,AK|Juneau,AK|Nome,AK|Kansas City,MO|Lincoln,NE|Topeka,KS|Wichita Falls,TX|Wichita,KS|Greenville,SC|Charleston,WV|Roanoke,VA|Charlotte,NC|Macon,GA|Augusta,GA|Redding,CA|Birmingham,AL|Huntsville,AL|Yuma,AZ|Jonesboro,AR|Texarkana,AR|Fresno,CA|Durango,CO|Grand Junction,CO|Ocala,FL|Palm Beach,FL|Twin Falls,ID|Peoria,IL|Evansville,IN|Fort Wayne,IN|Lafayette,IN|Indianapolis,IN|South Bend,IN|Des Moines,IA|Dodge City,KS|Liberal,KS|Louisville,KY|Baton Rouge,LA|Lake Charles,LA|Shreveport,LA|Rumford,ME|Eastport,ME|Millinocket,ME|Worcester,MA|Alpena,MI|Port Huron,MI|Ironwood,MI|Lansing,MI|Saginaw,MI|Sault St Marie,MI|International Falls,MN|Jackson,MS|Columbia,MO|Poplar Bluff,MO|Springfield,MO|Havre,MT|Helena,MT|Kalispell,MT|Hastings,NE|North Platte,NE|Ely,NV|Concord,MA|Roswell,NM|Elmira,NY|Rochester,NY|Greenville,NC|Newport,NC|Cape Hatteras,NC|Raleigh-Durham,NC|Grand Forks,ND|Cincinnati,OH|Dayton,OH|Pendleton,OR|Roseburg,OR|Salem,OR|Florence,SC|Columbia,SC|Aberdeen,SD|Pierre,SD|Rapid City,SD|Watertown,SD|Memphis,TN|Lewisburg,TN|Lufkin,TX|Amarillo,TX|Abilene,TX|Lubbock,TX|San Angelo,TX|Waco,TX|Rutland,VT|Burlington,VT|Olympia,WA|Seattle,WA|LaCrosse,WI|Madison,WI|Oshkosh,WI|Rhinelander,WI|Douglas,WY|Rawlins,WY|Yellowstone,WY|Riverton,WY|Norfolk,VA|Charlottesville,VA|San Jose,CA|Vieques,PR|Hilo,HI|Honolulu,HI|Lihue,HI|Kahului,HI|Kona,HI|Adak,AK|Bettles,AK|Ketchikan,AK|McGrath,AK|Seward,AK|Guam,GU|Rota,GU|Tinian,GU|Saipan,GU</cityNameList></dwml>";
	    String xml3 = "<?xml version='1.0' ?><dwml><latLonList>35.4,-97.6 36.2,-95.9 35.82,-83.98 33.65,-84.42 39.5,-119.78 35.13,-111.67 33.44,-112.02 32.12,-110.93 35.60,-88.92 35.22,-92.38 34.027,-118.329 32.73,-117.17 38.29,-104.52 39.75,-104.87 38.85,-77.04 24.58,-81.75 25.80,-80.28 28.55,-81.33 27.97,-82.53 43.57,-116.22 41.98,-87.9 39.85,-89.67 39.37,-101.7 37.07,-88.77 30.03,-90.03 44.801,-68.778 46.861,-68.012 43.65,-70.32 39.18,-76.67 42.23,-83.33 46.54,-87.41 46.83,-92.18 44.88,-93.22 34.27,-88.77 38.75,-90.37 45.8,-108.53 45.95,-112.5 48.22,-106.62 46.92,-114.08 40.83,-115.78 36.08,-115.17 35.05,-106.6 42.75,-73.8 42.93,-78.73 43.12,-76.12 40.77,-73.98 46.77,-100.75 46.9,-96.8 48.27,-101.28 41.42,-81.87 40.00,-82.88 41.6,-83.8 45.6,-122.6 42.08,-80.18 39.88,-75.25 40.35,-79.93 41.73,-72.65 43.58,-96.73 36.17,-86.78 30.3,-97.7 25.9,-97.43 32.9,-97.03 31.8,-106.4 32.00,-102.08 29.65,-95.28 29.53,-98.47 40.78,-111.97 47.63,-117.53 42.95,-87.9 37.5,-77.33 18.40,-66.00 18.34,-64.96 17.70,-64.90 61.17,-150.02 71.30,-156.78 64.82,-147.87 58.37,-134.58 64.50,-165.43 39.32,-94.72 40.85,-96.75 39.07,-95.62 33.9,-98.5 37.65,-97.43 34.8372,-82.3716 38.37,-81.6 37.32,-79.97 35.1975,-80.8345 32.7,-83.65 33.37,-81.96 40.56,-122.36 33.57,-86.75 34.65,-86.77 32.65,-114.62 35.83,-90.65 33.45,-94.00 36.77,-119.72 37.15,-107.75 39.12,-108.53 29.173,-82.224 26.68,-80.09 42.48,-114.48 40.67,-89.68 38.05,-87.53 41.00,-85.2 40.40,-86.87 39.73,-86.27 41.7,-86.32 41.53,-93.65 37.77,-99.97 37.05,-100.97 38.23,-85.67 30.53,-91.15 30.12,-93.22 32.47,-93.82 44.88,-70.88 44.90,-67.01 45.657,-68.710 42.27,-71.87 45.07,-83.57 42.92,-82.53 46.53,-90.13 42.77,-84.6 43.53,-84.08 46.47,-84.37 48.57,-93.38 32.32,-90.08 38.82,-92.22 36.77,-90.47 37.23,-93.38 48.55,-109.77 46.60,-112.00 48.3,-114.27 40.6,-98.43 41.13,-100.68 39.28,-114.85 43.2,-71.5 33.3,-104.53 42.17,-76.9 43.12,-77.67 35.63,-77.30 34.78,-76.88 35.27,-75.55 35.87,-78.78 47.95,-97.18 39.046,-84.662 39.9,-84.2 45.68,-118.85 43.25,-123.35 44.92,-123.00 34.15,-79.71 33.95,-81.14 45.45,-98.43 44.38,-100.28 44.05,-103.07 44.92,-97.15 35.05,-90.00 35.44,-86.79 31.33,-94.72 35.23,-101.7 32.42,-99.68 33.65,-101.82 31.37,-100.5 31.62,-97.22 43.54,-72.95 44.47,-73.15 46.97,-122.9 47.45,-122.3 43.87,-91.25 43.13,-89.33 44.00,-88.57 45.63,-89.45 42.74,-105.39 41.78,-107.22 44.55,-110.42 43.03,-108.41 36.9,-76.2 38.13,-78.45 37.36,-121.92 18.12,-65.45 19.72,-155.06 21.33,-157.94 21.98,-159.34 20.89,-156.44 19.74,-156.04 51.88,-176.65 66.92,-151.52 55.35,-131.70 62.97,-155.62 60.18,-149.75 13.48,144.81 14.16,145.23 14.96,145.65 15.21,145.72</latLonList><cityNameList>Oklahoma City,OK|Tulsa,OK|Knoxville,TN|Atlanta,GA|Reno,NV|Flagstaff,AZ|Phoenix,AZ|Tucson,AZ|Jackson,TN|Little Rock,AR|Los Angeles,CA|San Diego,CA|Pueblo,CO|Denver,CO|Washington,DC|Key West,FL|Miami,FL|Orlando,FL|Tampa,FL|Boise,ID|Chicago,IL|Springfield,IL|Goodland,KS|Paducah,KY|New Orleans,LA|Bangor,ME|Caribou,ME|Portland,ME|Baltimore,MD|Detroit,MI|Marquette,MI|Duluth,MN|Minneapolis,MN|Tupelo,MS|St Louis,MO|Billings,MT|Butte,MT|Glasgow,MT|Missoula,MT|Elko,NV|Las Vegas,NV|Albuquerque,NM|Albany,NY|Buffalo,NY|Syracuse,NY|New York,NY|Bismarck,ND|Fargo,ND|Minot,ND|Cleveland,OH|Columbus,OH|Toledo,OH|Portland,OR|Erie,PA|Philadelphia,PA|Pittsburgh,PA|Hartford,CT|Sioux Falls,SD|Nashville,TN|Austin,TX|Brownsville,TX|Dallas,TX|El Paso,TX|Midland,TX|Houston,TX|San Antonio,TX|Salt Lake City,UT|Spokane,WA|Milwaukee,WI|Richmond,VA|San Juan,PR|St Thomas,VI|St Croix,VI|Anchorage,AK|Barrow,AK|Fairbanks,AK|Juneau,AK|Nome,AK|Kansas City,MO|Lincoln,NE|Topeka,KS|Wichita Falls,TX|Wichita,KS|Greenville,SC|Charleston,WV|Roanoke,VA|Charlotte,NC|Macon,GA|Augusta,GA|Redding,CA|Birmingham,AL|Huntsville,AL|Yuma,AZ|Jonesboro,AR|Texarkana,AR|Fresno,CA|Durango,CO|Grand Junction,CO|Ocala,FL|Palm Beach,FL|Twin Falls,ID|Peoria,IL|Evansville,IN|Fort Wayne,IN|Lafayette,IN|Indianapolis,IN|South Bend,IN|Des Moines,IA|Dodge City,KS|Liberal,KS|Louisville,KY|Baton Rouge,LA|Lake Charles,LA|Shreveport,LA|Rumford,ME|Eastport,ME|Millinocket,ME|Worcester,MA|Alpena,MI|Port Huron,MI|Ironwood,MI|Lansing,MI|Saginaw,MI|Sault St Marie,MI|International Falls,MN|Jackson,MS|Columbia,MO|Poplar Bluff,MO|Springfield,MO|Havre,MT|Helena,MT|Kalispell,MT|Hastings,NE|North Platte,NE|Ely,NV|Concord,MA|Roswell,NM|Elmira,NY|Rochester,NY|Greenville,NC|Newport,NC|Cape Hatteras,NC|Raleigh-Durham,NC|Grand Forks,ND|Cincinnati,OH|Dayton,OH|Pendleton,OR|Roseburg,OR|Salem,OR|Florence,SC|Columbia,SC|Aberdeen,SD|Pierre,SD|Rapid City,SD|Watertown,SD|Memphis,TN|Lewisburg,TN|Lufkin,TX|Amarillo,TX|Abilene,TX|Lubbock,TX|San Angelo,TX|Waco,TX|Rutland,VT|Burlington,VT|Olympia,WA|Seattle,WA|LaCrosse,WI|Madison,WI|Oshkosh,WI|Rhinelander,WI|Douglas,WY|Rawlins,WY|Yellowstone,WY|Riverton,WY|Norfolk,VA|Charlottesville,VA|San Jose,CA|Vieques,PR|Hilo,HI|Honolulu,HI|Lihue,HI|Kahului,HI|Kona,HI|Adak,AK|Bettles,AK|Ketchikan,AK|McGrath,AK|Seward,AK|Guam,GU|Rota,GU|Tinian,GU|Saipan,GU</cityNameList></dwml>";
	    DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xml2));
		Document doc = dBuilder.parse(is);
//		doc.normalize();
		NodeList nodeList=doc.getElementsByTagName("employee");
*/
		return doc;
	}

	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}

	static String ndfdRequest(String urlString) {
		StringBuilder sb = new StringBuilder("");
		try {
			URL url = new URL(urlString);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String s = null;
			while (null != (s = br.readLine())) {
				sb.append(s);
			}
			System.out.println("length" + sb.length());
			System.out.println(sb);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	static {
		try {
			loadCityToLatlon();
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		loadCityToLatlon();
		String s = "http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?listLatLon=38.99,-77.02%2039.70,-104.80%2047.6,-122.30&product=time-series&begin=2004-01-01T00:00:00&end=2013-04-20T00:00:00&Unit=e&maxt=maxt&mint=mint";
		/* Two latLon */
		float startLat = 39.0f, startLon = -77.0f;
		/* Grid */
		// s="http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?listLat1=35.00&listLon1=-82.00&listLat2=35.5&listLon2=-81.5";
		/* zipcodes */
		// int startZip=60647;
		// s="http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?zipCodeList="+startZip;
		for (int j = 0; j < 50; j++) {
			s = "http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?listLatLon="
					+ startLat + "," + startLon;
			for (int i = 1; i < 10; i++) {
				// s=s+"+"+(i+startZip);
				s = s + "%20" + (startLat + i * 0.1f) + ","
						+ (startLon + i * 0.1f);
			}
			System.out.println(s);
			
			System.out.println("J=" + j);
		}
	}
}