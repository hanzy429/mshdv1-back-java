package com.earthquake.managementPlatform.entities;

public class OtherRecord {
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
    private String village;
    public OtherRecord() {
    }

    public OtherRecord(String id, String location, String date, String type, String status, String note, String picture, String reportingUnit, String earthquakeId, String province, String city, String country, String town, String category, String village) {
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
        this.village = village;
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
        return "?????????????????????????????????????????????" + '\n' +
                "?????????" + location + '???'+'\n' +
                "???????????????" + date + '???'+'\n' +
                "?????????????????????????????????????????????????????????????????????????????????????????????\n?????????" + type + '???'+'\n' +
                "???????????????" + note + '???'+'\n' +
                "???????????????" + reportingUnit + '???'+'\n' +
                "????????????????????????????????????4?????????3???.??????2?????????1??????\n?????????" + id.substring(18) ;
    }
}
