package com.earthquake.managementPlatform.entities;

import java.io.Serializable;

public class BasicEarthquakeInfo implements Serializable {
    private String id;
    private String date;
    private String location;
    private float longitude;
    private float latitude;
    private float depth;
    private float magnitude;
    private String picture;
    private String reportingUnit;
    private String province;
    private String city;
    private String country;
    private String town;
    private String category;
    private String village;
    private String influencefield;


    public BasicEarthquakeInfo() {
    }

    public BasicEarthquakeInfo(String id, String date, String location, float longitude, float latitude, float depth, float magnitude, String picture, String reportingUnit, String province, String city, String country, String town, String category, String village, String influencefield) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.depth = depth;
        this.magnitude = magnitude;
        this.picture = picture;
        this.reportingUnit = reportingUnit;
        this.province = province;
        this.city = city;
        this.country = country;
        this.town = town;
        this.category = category;
        this.village = village;
        this.influencefield = influencefield;
    }

    public String getInfluencefield() {
        return influencefield;
    }

    public void setInfluencefield(String influencefield) {
        this.influencefield = influencefield;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getReportingUnit() {
        return reportingUnit;
    }

    public void setReportingUnit(String reportingUnit) {
        this.reportingUnit = reportingUnit;
    }

    @Override
    public String toString() {
        return "该编码为地震基本信息编码：" + '\n' +
                "地点：" + location + '，'+'\n' +
                "日期：" + date + '，'+'\n' +
                "经度：" + longitude + '，'+'\n' +
                "纬度：" + latitude + '，'+'\n' +
                "深度：" + depth + '，'+'\n' +
                "震级：" + magnitude + '，'+'\n' +
                "上报单位：" + reportingUnit ;
    }
}
