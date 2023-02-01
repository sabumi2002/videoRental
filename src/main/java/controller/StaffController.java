package controller;

import model.StaffDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffController {
    // 삽입, 수정, 삭제, selectAll, selectOne, 인증

    private Connection connection;
    public StaffController(Connection connection){
        this.connection = connection;
    }

    public StaffDTO selectOne(int id){
        StaffDTO s = new StaffDTO();
        String query = "SELECT * FROM `staff` WHERE `staff_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                s.setStaff_id(resultSet.getInt("staff_id"));
                s.setFirst_name(resultSet.getString("first_name"));
                s.setLast_name(resultSet.getString("last_name"));
                s.setAddress_id(resultSet.getInt("address_id"));
                s.setEmail(resultSet.getString("email"));
                s.setStore_id(resultSet.getInt("store_id"));
                s.setUsername(resultSet.getString("username"));
                s.setPassword(resultSet.getString("password"));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return s;
    }
    public ArrayList<StaffDTO> selectAll(){
        ArrayList<StaffDTO> list = new ArrayList<>();
        String query = "SELECT * FORM `staff`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                StaffDTO s = new StaffDTO();
                s.setStaff_id(resultSet.getInt("staff_id"));
                s.setFirst_name(resultSet.getString("first_name"));
                s.setLast_name(resultSet.getString("last_name"));
                s.setAddress_id(resultSet.getInt("address_id"));
                s.setEmail(resultSet.getString("email"));
                s.setStore_id(resultSet.getInt("store_id"));
                s.setUsername(resultSet.getString("username"));
                s.setPassword(resultSet.getString("password"));

                list.add(s);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public StaffDTO auth(StaffDTO logIn){
        String query = "SELECT * FROM `staff` WHERE `username` = ? AND `password` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, logIn.getUsername());
            pstmt.setString(2, logIn.getPassword());
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()) {
                StaffDTO s = new StaffDTO();
                s.setStaff_id(resultSet.getInt("staff_id"));
                s.setFirst_name(resultSet.getString("first_name"));
                s.setLast_name(resultSet.getString("last_name"));
                s.setAddress_id(resultSet.getInt("address_id"));
                s.setEmail(resultSet.getString("email"));
                s.setStore_id(resultSet.getInt("store_id"));
                s.setUsername(resultSet.getString("username"));
                s.setPassword(resultSet.getString("password"));

                return s;
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



}


























