package model;

import com.mysql.cj.xdevapi.AddResult;

import java.util.Date;

public class AddressDTO {
    private int address_id;
    String address;
    int city_id;
    String postal_code;
    Date last_update;   // current_timeStamp
    private String city;    // city_Table
    private String country; // county_Table

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
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

    public AddressDTO(){

    }
    public AddressDTO(int id){
        this.address_id = id;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof AddressDTO){
            AddressDTO a = (AddressDTO) o;
            return address_id == a.address_id;
        }
        return false;
    }
}
