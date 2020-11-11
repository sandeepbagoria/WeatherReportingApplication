package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class TriggerWeatherAPI {
    String uri = "http://api.openweathermap.org/data/2.5/weather";
    String city = "Bengaluru";
    String appid = "7fe67bf08c80ded756e598d6f8fedaea";
    String units  = "metric";

    public Response createWeatherAPIRequest(){
        RequestSpecification request = RestAssured.given();
        Response response = request.queryParams(createQueryParams()).get(uri);
        return response;
    }

    public Map createQueryParams(){
        Map params = new HashMap();
        params.put("q",city);
        params.put("appid",appid);
        params.put("units",units);
        return params;
    }
}
