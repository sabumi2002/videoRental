package controller;

import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerController {
    // 삽입, 수정, 삭제, selectAll, selectOne
    private Connection connection;

    public CustomerController(Connection connection) {
        this.connection = connection;
    }

    public CustomerDTO selectOne(int id) {
        CustomerDTO c = null;
        String query = "SELECT * FROM `customer` WHERE `id`=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                c = new CustomerDTO();
                // customer_id, store_id, first_name, last_name, address_id, create_date
                c.setId(resultSet.getInt("customer_id"));
                c.setStore_id(resultSet.getInt("store_id"));
                c.setFirst_name(resultSet.getString("first_name"));
                c.setLast_name(resultSet.getString("last_name"));
                c.seteMail(resultSet.getString("eMail"));
                c.setAddress_id(resultSet.getInt("address_id"));
                c.setCreate_date(resultSet.getDate("create_date"));

            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }
    public ArrayList<CustomerDTO> selectAll(){
        ArrayList<CustomerDTO> list = new ArrayList<>();
        String query = "SELECT * FROM `customer`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                CustomerDTO c = new CustomerDTO();
                c.setId(resultSet.getInt("customer_id"));
                c.setStore_id(resultSet.getInt("store_id"));
                c.setFirst_name(resultSet.getString("first_name"));
                c.setLast_name(resultSet.getString("last_name"));
                c.seteMail(resultSet.getString("eMail"));
                c.setAddress_id(resultSet.getInt("address_id"));
                c.setCreate_date(resultSet.getDate("create_date"));

                list.add(c);
            }


            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }


}













