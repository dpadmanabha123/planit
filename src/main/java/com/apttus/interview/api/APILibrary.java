package com.apttus.interview.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;


public class APILibrary {
	
	public String EndPoint = "http://api.openweathermap.org/data/3.0/stations";
	
	public Response postStationsWithoutAppId() {
		Response response = RestAssured.given()
						   .header("Content-Type", "application/json")
						   .post(EndPoint);
		return response;
	}
	
	public String postStations(Map<String,String> stationData) {
		CreateStationsPOJO createStations = new CreateStationsPOJO();
		CreateStationsPOJO pojo = createStations.createStation(stationData);
		String requrestBody = new Gson().toJson(pojo);
		Response response = RestAssured.given()
						   .header("Content-Type", "application/json")
						   .queryParam("appid", "bfc5aa6a5a8e899e9f8cca07271606fd")
						   .body(requrestBody)
						   .post(EndPoint);
		String responseBody = response.getBody().asString();
		JsonPath path = new JsonPath(responseBody);
		return path.get("ID");
	}
	
	public Map<String,String> getStations(String stationId) {
		Map<String,String> returnData = new HashMap<String,String>();
		Response response = RestAssured.given()
						   .header("Content-Type", "application/json")
						   .queryParam("appid", "bfc5aa6a5a8e899e9f8cca07271606fd")
						   .queryParam("station_id", stationId)
						   .queryParam("type", "m")
						   .queryParam("limit", 100)
						   .get(EndPoint);
		String responseBody = response.getBody().asString();
		JsonPath path = new JsonPath(responseBody);
		List<Object> fullObject= path.get("");
		for(int i=0 ;i<fullObject.size();i++) {
			String id = path.getString("["+i+"].id");
			if(id.equalsIgnoreCase(stationId)) {
				String externalId = path.getString("["+i+"].external_id");
				String name = path.getString("["+i+"].name");
				returnData.put("externalId", externalId);
				returnData.put("name", name);
			}
		}
		return returnData;
	}
	
	public Response deleteStations(String stationId) {
		Response response = RestAssured.given()
						   .header("Content-Type", "application/json")
						   .queryParam("appid", "bfc5aa6a5a8e899e9f8cca07271606fd")
						   .delete(EndPoint+"/"+stationId);
		return response;
	}
	
	public Response updateStations(String stationId, Map<String,String> stationData) {
		CreateStationsPOJO createStations = new CreateStationsPOJO();
		CreateStationsPOJO pojo = createStations.createStation(stationData);
		String requrestBody = new Gson().toJson(pojo);
		Response response = RestAssured.given()
				   					   .header("Content-Type", "application/json")
				   					   .queryParam("appid", "bfc5aa6a5a8e899e9f8cca07271606fd")
				   					   .body(requrestBody)
				   					   .put(EndPoint+"/"+stationId);
		return response;
	}
}
