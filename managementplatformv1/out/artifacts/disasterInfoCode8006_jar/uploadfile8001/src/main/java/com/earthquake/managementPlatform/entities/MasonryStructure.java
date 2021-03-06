package com.earthquake.managementPlatform.entities;

public class MasonryStructure {
    private String id;
    private String date;
    private String location;
    private Double basicallyIntactSquare;
    private Double slightDamagedSquare;
    private Double moderateDamagedSquare;
    private Double seriousDamagedSquare;
    private Double destroyedSquare;
    private String note;
    private String reportingUnit;
    private String earthquakeId;
    private String province;
    private String city;
    private String country;
    private String town;
    private String category;
    private String village;
    public MasonryStructure() {
    }

    public MasonryStructure(String id, String date, String location, Double basicallyIntactSquare, Double slightDamagedSquare, Double moderateDamagedSquare, Double seriousDamagedSquare, Double destroyedSquare, String note, String reportingUnit, String earthquakeId, String province, String city, String country, String town, String category, String village) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.basicallyIntactSquare = basicallyIntactSquare;
        this.slightDamagedSquare = slightDamagedSquare;
        this.moderateDamagedSquare = moderateDamagedSquare;
        this.seriousDamagedSquare = seriousDamagedSquare;
        this.destroyedSquare = destroyedSquare;
        this.note = note;
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

    public Double getBasicallyIntactSquare() {
        return basicallyIntactSquare;
    }

    public void setBasicallyIntactSquare(Double basicallyIntactSquare) {
        this.basicallyIntactSquare = basicallyIntactSquare;
    }

    public Double getSlightDamagedSquare() {
        return slightDamagedSquare;
    }

    public void setSlightDamagedSquare(Double slightDamagedSquare) {
        this.slightDamagedSquare = slightDamagedSquare;
    }

    public Double getModerateDamagedSquare() {
        return moderateDamagedSquare;
    }

    public void setModerateDamagedSquare(Double moderateDamagedSquare) {
        this.moderateDamagedSquare = moderateDamagedSquare;
    }

    public Double getSeriousDamagedSquare() {
        return seriousDamagedSquare;
    }

    public void setSeriousDamagedSquare(Double seriousDamagedSquare) {
        this.seriousDamagedSquare = seriousDamagedSquare;
    }

    public Double getDestroyedSquare() {
        return destroyedSquare;
    }

    public void setDestroyedSquare(Double destroyedSquare) {
        this.destroyedSquare = destroyedSquare;
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
        return "该编码为砖混结构房屋破坏统计编码：" + '\n' +
                "地点：" + location + '，'+'\n' +
                "上报日期：" + date + '，'+'\n' +
                "基本完好面积：" + basicallyIntactSquare + '，'+'\n' +
                "轻微破坏面积：" + slightDamagedSquare + '，'+'\n' +
                "中等破坏面积：" + moderateDamagedSquare + '，'+'\n' +
                "严重破坏面积：" + seriousDamagedSquare + '，'+'\n' +
                "毁坏面积：" + destroyedSquare + '，'+'\n' +
                "破坏情况描述：" + note + '，'+'\n' +
                "上报单位：" + reportingUnit + '，'+'\n' +
                "严重程度评级共四级（特大4，大型3，中型2，一般1），\n此次为" + id.substring(18) ;
    }
}
