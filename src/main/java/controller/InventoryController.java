package controller;

import model.InventoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryController {
    Connection connection;
    public InventoryController(Connection connection){
        this.connection = connection;
    }
    public InventoryDTO selectOne(int id){
        InventoryDTO i = new InventoryDTO();
        String query = "SELECT * FROM `inventory` WHERE `inventory_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                i.setInventory_id(resultSet.getInt("inventory_id"));
                i.setFilm_id(resultSet.getInt("film_id"));
                i.setStore_id(resultSet.getInt("store_id"));

            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
    public ArrayList<InventoryDTO> selectByFilmStore(int film_id, int store_id){
        ArrayList<InventoryDTO> list = new ArrayList<>();
        String query = "SELECT * FROM sakila.inventory WHERE film_id = ? AND store_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, film_id);
            pstmt.setInt(2, store_id);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                InventoryDTO i = new InventoryDTO();
                i.setInventory_id(resultSet.getInt("inventory_id"));
                i.setFilm_id(resultSet.getInt("film_id"));
                i.setStore_id(resultSet.getInt("store_id"));

                list.add(i);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
