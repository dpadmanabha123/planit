package com.planit.training.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.planit.training.libraries.APILibrary;

public class InterviewTest {
	
	APILibrary library = new APILibrary();
	Map<String,String> stationData1 = new HashMap<String,String>();
	Map<String,String> stationData2 = new HashMap<String,String>();
	String stationId1;
	String stationId2;
	
	@Test(description ="Posting station without APPID", priority=1)
	public void postStationWithoutAppId() {
		Response response = library.postStationsWithoutAppId();
		Integer expectedCode = 401;
		Integer statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, expectedCode);
	}
	
	@Test(description ="Posting station with APPID", priority=2)
	public void postStation() {
		stationData1.put("externalId", "DEMO_TEST001");
		stationData1.put("name", "Interview-Station-123");
		stationData1.put("latitude", "33.33");
		stationData1.put("longitude", "-111.43");
		stationData1.put("altitude", "444");
		
		stationData2.put("externalId", "Interview1");
		stationData2.put("name", "Interview-Station-456");
		stationData2.put("latitude", "33.44");
		stationData2.put("longitude", "-12.44");
		stationData2.put("altitude", "444");
		
		stationId1 = library.postStations(stationData1);
		Assert.assertNotNull(stationId1);
		stationId2 = library.postStations(stationData2);
		Assert.assertNotNull(stationId2);
	}
	
	@Test(description ="Fetching station with stationId", priority=3)
	public void getStation() {
		Map<String,String> fetchStation1 = library.getStations(stationId1);
		Assert.assertEquals(fetchStation1.get("externalId"), stationData1.get("externalId"));
		Assert.assertEquals(fetchStation1.get("name"), stationData1.get("name"));
		
		Map<String,String> fetchStation2 = library.getStations(stationId2);
		Assert.assertEquals(fetchStation2.get("externalId"), stationData2.get("externalId"));
		Assert.assertEquals(fetchStation2.get("name"), stationData2.get("name"));
	}
	
	@Test(description ="Updating station with stationId", priority=4)
	public void updateStation() {
		Map<String,String> station1 = new HashMap<String,String>();
		station1.put("externalId", "DEMO_TEST0011");
		station1.put("name", "Interview-Station-1231");
		station1.put("latitude", "33.331");
		station1.put("longitude", "-111.431");
		station1.put("altitude", "4441");
		Response station1Response = library.updateStations(stationId1,station1);
		Integer expectedCode = 200;
		Integer station1StatusCode = station1Response.getStatusCode();
		Assert.assertEquals(station1StatusCode,expectedCode);
		
		Map<String,String> station2 = new HashMap<String,String>();
		station2.put("externalId", "Interview11");
		station2.put("name", "Interview-Station-4561");
		station2.put("latitude", "33.441");
		station2.put("longitude", "-12.441");
		station2.put("altitude", "4441");
		Response station2Response = library.updateStations(stationId2,station2);
		Integer station2StatusCode = station2Response.getStatusCode();
		Assert.assertEquals(station2StatusCode,expectedCode);
	}
	
	@Test(description ="Deleting station with stationId", priority=5)
	public void deleteStation() {
		Response station1Response = library.deleteStations(stationId1);
		Integer expectedCode = 204;
		Integer station1StatusCode = station1Response.getStatusCode();
		Assert.assertEquals(station1StatusCode,expectedCode);
		
		Response station2Response = library.deleteStations(stationId2);
		Integer station2StatusCode = station2Response.getStatusCode();
		Assert.assertEquals(station2StatusCode,expectedCode);
	}
	
	@Test(description ="Deleting station with Non Existing stationId", priority=6)
	public void deleteNotExistingStation() {
		Response station1Response = library.deleteStations(stationId1);
		Integer expectedCode = 404;
		Integer station1StatusCode = station1Response.getStatusCode();
		Assert.assertEquals(station1StatusCode,expectedCode);
		
		Response station2Response = library.deleteStations(stationId1);
		Integer station2StatusCode = station2Response.getStatusCode();
		Assert.assertEquals(station2StatusCode,expectedCode);
	}

}
