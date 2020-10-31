package model;

public class Address {
    private int addressID;
    private String country;
    private String city;
    private String postalCode;

    public Address(String country, String city, String postalCode) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
    }

    public int getAddressID() {
        return this.addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
