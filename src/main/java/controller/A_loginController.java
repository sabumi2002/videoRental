package controller;

import model.StaffDTO;

import java.sql.Connection;

public class A_loginController {
    // id, firstname, lastname, address, email, storeId, active, username, password

    private Connection connection;

    A_loginController(Connection connection){
        this.connection = connection;
    }
    public StaffDTO selectOne(int id){
        StaffDTO s = new StaffDTO();

        return s;
    }




}
