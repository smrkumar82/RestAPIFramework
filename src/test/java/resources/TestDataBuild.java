package resources;

import pojos.AddPlace;
import pojos.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address)
    {
        AddPlace ap=new AddPlace();
        Location loc=new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        ap.setLocation(loc);
        ap.setAccuracy(50);
        ap.setName(name);
        ap.setPhone_number("(+91) 983 893 3937");
        ap.setAddress(address);
        ap.setWebsite("http://google.com");
        ap.setLanguage(language);
        List<String> type = new ArrayList<String>();
        type.add("shoe park");
        type.add("shop");
        ap.setTypes(type);

        return ap;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\r\n  \"place_id\":\""+placeId+"\"\r\n}";
    }
}
