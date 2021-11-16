package com.earthquake.managementPlatform.entities;

public class CommDisaster {
    private String id;
    private String date;
    private String location;
    private String type;
    private String grade;
    private String picture;
    private String note;
    private String reportingUnit;
    private String earthquakeId;
    private String province;
    private String city;
    private String country;
    private String town;
    private String category;

    public CommDisaster() {
    }

    public CommDisaster(String id, String date, String location, String type, String grade, String picture, String note, String reportingUnit, String earthquakeId, String province, String city, String country, String town, String category) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.type = type;
        this.grade = grade;
        this.picture = picture;
        this.note = note;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        return "该编码为通信系统灾情统计编码：" + '\n' +
                "地点：" + location + '，'+'\n' +
                "上报日期：" + date + '，'+'\n' +
                "类型共两类（中心控制室1，通信线路2），\n此次为" + type + '，'+'\n' +
                "灾情描述：" + note + '，'+'\n' +
                "上报单位：" + reportingUnit + '，'+'\n' +
                "严重程度评级共五级（毁坏5，严重破坏4，中等破坏3，轻微破坏2，基本完好1），\n此次为" + id.substring(18) ;
    }
}
