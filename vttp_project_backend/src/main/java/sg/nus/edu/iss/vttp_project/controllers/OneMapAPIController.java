package sg.nus.edu.iss.vttp_project.controllers;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(path="/api")
public class OneMapAPIController {
    
    RestTemplate restTem = new RestTemplate(); 
    HttpHeaders headers = new HttpHeaders(); 
        
    @GetMapping(path="/destCoordinates")
    public String searchForGeofence(){
        String result = restTem.getForObject(apiURL, String.class);
        return result; 
        //Returns both latitude, longitude and x,y coordinates of the searched location.
    }

    String apiURL = "/api/common/elastic/search";
     //Sample request: /api/common/elastic/search?searchVal=200640&returnGeom=Y&getAddrDetails=Y&pageNum=1
    @GetMapping(path = "/common/elastic/search")
    public String filterSearchResults(@RequestParam(name = "searchVal", required = true) String searchVal,
                                   @RequestParam(name = "returnGeom", required = true) String returnGeom,
                                   @RequestParam(name = "getAddrDetails", required = true) String addrDetails) {

        String searchResults = restTem.getForObject(apiURL + "?searchVal=" + searchVal + "&returnGeom=" + returnGeom + "&getAddrDetails=" + addrDetails, String.class);
                //Example: ?searchVal=paya%20lebar%20mrt (for Paya Lebar MRT)
                //Enter Y to return with geometry value (else N)   
                //Enter Y to return with address details returned (else N)                                    
        return searchResults ; 
    }
    //Tested: https://developers.onemap.sg/commonapi/search?searchVal=singpost%20centre&returnGeom=Y&getAddrDetails=Y


    //Routing (calculates distance between start and end position in WGS84 coordinates form)
        //can set params like "maxWalkDistance", mode (TRANSIT, BUS, RAIL), etc.
        //https://www.onemap.gov.sg/apidocs/apidocs/#routing
    @GetMapping(path = "/public/routingsvc/route")
    public String getTravelRoute(@RequestParam(name = "start", required = true) String startPoint,
                                   @RequestParam(name = "end", required = true) String endPoint,
                                   @RequestParam(name = "routeType", required = true) String routeType,
                                   @RequestParam(name = "date", required = true) String startDate,
                                   @RequestParam(name = "time", required = true) String startTime,
                                   @RequestParam(name = "mode", required = true) String modeOfTransport,
                                   @RequestParam(name = "maxWalkDistance", required = false) String maxWalkDist,
                                   @RequestParam(name = "numItineraries", required = false) String numItineraries) {

        StringBuilder apiURLBuilder = new StringBuilder(apiURL);
            apiURLBuilder.append("?start=").append(startPoint);
            apiURLBuilder.append("&end=").append(endPoint);
            apiURLBuilder.append("&routeType=").append(routeType);
            apiURLBuilder.append("&date=").append(startDate);
            apiURLBuilder.append("&time=").append(startTime);
            apiURLBuilder.append("&mode=").append(modeOfTransport);
            if(maxWalkDist != null){
                 apiURLBuilder.append("&maxWalkDistance=").append(maxWalkDist);
            }
            if(numItineraries != null){
                 apiURLBuilder.append("&numItineraries=").append(numItineraries);
            }

        String apiURLwithQueryParams = apiURLBuilder.toString(); 

        String travelRoute = restTem.getForObject(apiURLwithQueryParams, String.class); 
        return travelRoute; 
    }
}
