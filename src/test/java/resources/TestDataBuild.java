package resources;

import java.util.ArrayList;

import Pojo.AddPlace;
import Pojo.Location;

public class TestDataBuild {
	
	public AddPlace add_place_payload(String name, String language, String address)
	{
		AddPlace a = new AddPlace();
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		a.setAccuracy("50");
		a.setName(name);
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress(address);
		a.setWebsite("http://google.com");
		a.setLanguage(language);
		
		ArrayList<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		a.setTypes(mylist);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		a.setLocation(l);
		return a;
	}
	
	public String deletePlacePayload(String placeID)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}";
	}

}
