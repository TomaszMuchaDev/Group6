package com.napier.coursework;


// All variables needed for reports
public class City {
    private String name;
    private String district;
    private int population;
    private String country;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {return district;}
    public void setDistrict(String district) {this.district = district;}

    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

}
