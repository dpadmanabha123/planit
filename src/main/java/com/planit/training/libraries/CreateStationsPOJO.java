package com.planit.training.libraries;

import java.util.Map;

public class CreateStationsPOJO {
	
	private String external_id;
	private String name;
	private Double latitude;
	private Double longitude;
	private Integer altitude;
	
	public String getExternal_id() {
		return external_id;
	}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getAltitude() {
		return altitude;
	}
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	
	public CreateStationsPOJO createStation(Map<String,String> stationData) {
		CreateStationsPOJO pojo = new CreateStationsPOJO();
		pojo.setExternal_id(stationData.get("externalId"));
		pojo.setName(stationData.get("name"));
		pojo.setLatitude(Double.parseDouble(stationData.get("latitude")));
		pojo.setLongitude(Double.parseDouble(stationData.get("longitude")));
		pojo.setAltitude(Integer.parseInt(stationData.get("altitude")));
		return pojo;
	}

}
