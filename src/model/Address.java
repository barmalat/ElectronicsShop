package model;

public class Address {
    private String country;
    private String city;
    private String street;
    private String streetNumber;
    private String apartmentNumber;

    public Address(String country, String city, String street, String streetNumber, String apartmentNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
    }
}