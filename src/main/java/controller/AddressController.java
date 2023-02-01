package controller;

import model.AddressDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddressController {
    private Connection connection;

    public AddressController(Connection connection) {
        this.connection = connection;
    }

    public AddressDTO selectOne(int id) {
        AddressDTO addressDTO = new AddressDTO();
        String query = "SELECT a.*, c.city, ctr.country FROM sakila.address AS a" +
                " INNER JOIN sakila.city AS c" +
                " ON a.city_id = c.city_id" +
                " INNER JOIN sakila.country as ctr" +
                " ON c.country_id = ctr.country_id" +
                " WHERE a.address_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                addressDTO.setAddress_id(resultSet.getInt("address_id"));
                addressDTO.setAddress(resultSet.getString("address"));
                addressDTO.setCity_id(resultSet.getInt("city_id"));
                addressDTO.setPostal_code(resultSet.getString("postal_code"));
                addressDTO.setCity(resultSet.getString("city"));
                addressDTO.setCountry(resultSet.getString("country"));

            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressDTO;
    }
    public ArrayList<AddressDTO> selectAll(){
        ArrayList<AddressDTO> list = new ArrayList<>();
        String query = "SELECT a.*, c.city, ctr.country FROM sakila.address AS a\n" +
                "INNER JOIN sakila.city AS c\n" +
                "ON a.city_id = c.city_id\n" +
                "INNER JOIN sakila.country as ctr\n" +
                "ON c.country_id = ctr.country_id"+
                "ORDER BY a.address_id";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setAddress_id(resultSet.getInt("id"));
                addressDTO.setAddress(resultSet.getString("address"));
                addressDTO.setCity_id(resultSet.getInt("city_id"));
                addressDTO.setPostal_code(resultSet.getString("postal_code"));
                addressDTO.setCity(resultSet.getString("city"));
                addressDTO.setCountry(resultSet.getString("country"));

                list.add(addressDTO);
            }



            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

}


















