package controller;

import model.RentalDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentalController {
    // 삽입, 수정, 삭제, selectAll, selectOne
    Connection connection;

    public RentalController(Connection connection){
        this.connection = connection;
    }

    public RentalDTO isNullByInventory(int inventory_id){
        RentalDTO r = new RentalDTO();
        String query = "SELECT * FROM sakila.rental\n" +
                "WHERE inventory_id = ? AND return_date is null;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, inventory_id);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                r.setRental_id(resultSet.getInt("rental_id"));
                r.setRental_date(resultSet.getDate("rental_date"));
                r.setInventory_id(resultSet.getInt("inventory_id"));
                r.setCustomer_id(resultSet.getInt("customer_id"));
                r.setReturn_date(resultSet.getDate("return_date"));
                r.setStaff_id(resultSet.getInt("staff_id"));

            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return r;
    }
    public ArrayList<RentalDTO> isNullByCustomer(int customer_id){
        ArrayList<RentalDTO> list = new ArrayList<>();
        String query = "SELECT * FROM sakila.rental\n" +
                "WHERE customer_id = ? and return_date is null;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, customer_id);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                RentalDTO r = new RentalDTO();
                r.setRental_id(resultSet.getInt("rental_id"));
                r.setCustomer_id(resultSet.getInt("customer_id"));
                r.setStaff_id(resultSet.getInt("staff_id"));
                r.setInventory_id(resultSet.getInt("inventory_id"));
                r.setRental_date(resultSet.getDate("rental_date"));
                r.setReturn_date(resultSet.getDate("return_date"));

                list.add(r);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public RentalDTO selectOne(int rental_id){
        RentalDTO r = new RentalDTO();
        String query = "SELECT * FROM `rental` WHERE `rental_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, rental_id);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                r.setRental_id(resultSet.getInt("rental_id"));
                r.setCustomer_id(resultSet.getInt("customer_id"));
                r.setInventory_id(resultSet.getInt("inventory_id"));
                r.setStaff_id(resultSet.getInt("staff_id"));
                r.setRental_date(resultSet.getDate("rental_date"));
                r.setReturn_date(resultSet.getDate("return_date"));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return r;
    }
    public ArrayList<RentalDTO> selectAllById(int id){
        ArrayList<RentalDTO> list = new ArrayList<>();
        String query = "SELECT * FROM sakila.rental\n" +
                "WHERE customer_id = ?;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                RentalDTO r = new RentalDTO();
                r.setRental_id(resultSet.getInt("rental_id"));
                r.setCustomer_id(resultSet.getInt("customer_id"));
                r.setInventory_id(resultSet.getInt("inventory_id"));
                r.setStaff_id(resultSet.getInt("staff_id"));
                r.setRental_date(resultSet.getDate("rental_date"));
                r.setReturn_date(resultSet.getDate("return_date"));

                list.add(r);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
    public void insert(RentalDTO rentalDTO){
        String query = "INSERT INTO `rental`(`rental_date`, `inventory_id`, `customer_id`, `staff_id`) " +
                "VALUES(NOW(), ?, ?, ?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, rentalDTO.getInventory_id());
            pstmt.setInt(2, rentalDTO.getCustomer_id());
            pstmt.setInt(3, rentalDTO.getStaff_id());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(RentalDTO rentalDTO){
        String query = "UPDATE `rental` SET `return_date` = NOW() WHERE `rental_id` = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, rentalDTO.getRental_id());

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void delete(int id){
        String query = "DELETE FROM `rental` WHERE `rental_id` = ?";
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











