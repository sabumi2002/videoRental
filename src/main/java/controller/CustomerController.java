package controller;

import model.CustomerDTO;
import model.RentalDTO;

import java.beans.PropertyEditorManager;
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
        String query = "SELECT * FROM `customer` WHERE `customer_id`=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                c = new CustomerDTO();
                // customer_id, store_id, first_name, last_name, address_id, create_date
                c.setCustomer_id(resultSet.getInt("customer_id"));
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

    public ArrayList<CustomerDTO> selectAll() {
        ArrayList<CustomerDTO> list = new ArrayList<>();
        String query = "SELECT * FROM `customer`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                CustomerDTO c = new CustomerDTO();
                c.setCustomer_id(resultSet.getInt("customer_id"));
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

    public ArrayList<CustomerDTO> selectByName(String name) {
        ArrayList<CustomerDTO> list = new ArrayList<>();
        String query = "SELECT * FROM sakila.customer\n" +
                "WHERE first_name like ? or last_name like ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + name + "%");
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                CustomerDTO c = new CustomerDTO();
                c.setCustomer_id(resultSet.getInt("customer_id"));
                c.setStore_id(resultSet.getInt("store_id"));
                c.setFirst_name(resultSet.getString("first_name"));
                c.setLast_name(resultSet.getString("last_name"));
                c.seteMail(resultSet.getString("eMail"));
                c.setAddress_id(resultSet.getInt("address_id"));
                c.setCreate_date(resultSet.getDate("create_date"));

                list.add(c);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public void insert(CustomerDTO customerDTO){
        String query = "INSERT INTO `customer`(`store_id`, `first_name`, `last_name`, `eMail`, `address_id`, `create_date`) " +
                "VALUES(1, ?, ?, ?, ?, NOW())";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, customerDTO.getFirst_name());
            pstmt.setString(2, customerDTO.getLast_name());
            pstmt.setString(3, customerDTO.geteMail());
            pstmt.setInt(4, customerDTO.getAddress_id());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CustomerDTO customerDTO){
        String query = "UPDATE `customer` SET `fist_name` = ?, `last_name`=?, `email`=?, `address_id` = ? " +
                "WHERE `customer_id` = ?, ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, customerDTO.getFirst_name());
            pstmt.setString(2, customerDTO.getLast_name());
            pstmt.setString(3, customerDTO.geteMail());
            pstmt.setInt(4, customerDTO.getAddress_id());
            pstmt.setInt(5, customerDTO.getCustomer_id());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void delete(int id){
        String query = "DELETE FROM `customer` WHERE `customer_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}













