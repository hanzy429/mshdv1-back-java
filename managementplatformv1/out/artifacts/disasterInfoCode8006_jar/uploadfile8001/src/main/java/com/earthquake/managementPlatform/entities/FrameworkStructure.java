package com.earthquake.managementPlatform.entities;

public class FrameworkStructure {
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
    public FrameworkStructure() {
    }


    public FrameworkStructure(String id, String date, String location, Double basicallyIntactSquare, Double slightDamagedSquare, Double moderateDamagedSquare, Double seriousDamagedSquare, Double destroyedSquare, String note, String reportingUnit, String earthquakeId, String province, String city, String country, String town, String category, String village) {
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
        return "???????????????????????????????????????????????????" + '\n' +
                "?????????" + location + '???'+'\n' +
                "???????????????" + date + '???'+'\n' +
                "?????????????????????" + basicallyIntactSquare + '???'+'\n' +
                "?????????????????????" + slightDamagedSquare + '???'+'\n' +
                "?????????????????????" + moderateDamagedSquare + '???'+'\n' +
                "?????????????????????" + seriousDamagedSquare + '???'+'\n' +
                "???????????????" + destroyedSquare + '???'+'\n' +
                "?????????????????????" + note + '???'+'\n' +
                "???????????????" + reportingUnit + '???'+'\n' +
                "????????????????????????????????????4?????????3?????????2?????????1??????\n?????????" + id.substring(18) ;
    }
}
