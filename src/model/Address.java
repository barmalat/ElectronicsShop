package model;

public class Address {
    private final String country;
    private final String city;
    private final String street;
    private final String streetNumber;
    private final String apartmentNumber;

    public Address(String country, String city, String street, String streetNumber, String apartmentNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString() {
        return country + ", " +
                city + ", " +
                street + ", " +
                streetNumber + ", " +
                apartmentNumber;
    }
}