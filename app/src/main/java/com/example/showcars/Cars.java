package com.example.showcars;

public class Cars {
    private String id;
    String brand;
    String image;
    boolean type;
    String construction_year;

    public Cars(String brand, String image, boolean type, String construction_year) {
        this.brand = brand;
        this.image = image;
        this.type = type;
        this.construction_year = construction_year;
        this.construction_year = construction_year;
    }

    public Cars() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String title) {
        this.brand = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getConstructionYear() {
        return construction_year;
    }

    public void setConstructionYear(String construction_year) {
        this.construction_year = construction_year;
    }

}
