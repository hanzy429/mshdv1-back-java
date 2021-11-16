package com.earthquake.managementPlatform.entities;

public class LandslideRecord {
    private String id;
    private String location;
    private String date;
    private String type;
    private String status;
    private String note;
    private String picture;
    private String reportingUnit;
    private String earthquakeId;
    private String province;
    private String city;
    private String country;
    private String town;
    private String category;

    public LandslideRecord() {
    }

    public LandslideRecord(String id, String location, String date, String type, String status, String note, String picture, String reportingUnit, String earthquakeId, String province, String city, String country, String town, String category) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.type = type;
        this.status = status;
        this.note = note;
        this.picture = picture;
        this.reportingUnit = reportingUnit;
        this.earthquakeId = earthquakeId;
        this.province = province;
        this.city = city;
        this.country = country;
        this.town = town;
        this.category = category;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getEarthquakeId() {
        return earthquakeId;
    }

    public void setEarthquakeId(String earthquakeId) {
        this.earthquakeId = earthquakeId;
    }

    @Override
    public String toString() {
        return "该编码为滑坡记录编码：" + '\n' +
                "地点：" + location + '，'+'\n' +
                "上报日期：" + date + '，'+'\n' +
                "类型共四类（特大型1，大型2，中型3，小型4），\n此次为" + type + '，'+'\n' +
                "灾情描述：" + note + '，'+'\n' +
                "上报单位：" + reportingUnit + '，'+'\n' +
                "严重程度评级共四级（一般4，较大3，.重大2，特大1），\n此次为" + id.substring(18) ;
    }
}
