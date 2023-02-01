package controller;

import model.CategoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryController {
    private Connection connection;

    public CategoryController(Connection connection){
        this.connection = connection;
    }

    public CategoryDTO selectOne(int id) {
        CategoryDTO categoryDTO = new CategoryDTO();
        String query = "SELECT * FROM `category` WHERE `category_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                categoryDTO.setCategory_id(resultSet.getInt("category_id"));
                categoryDTO.setName(resultSet.getString("name"));
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryDTO;
    }


    public ArrayList<CategoryDTO> selectAll(){
        ArrayList<CategoryDTO> list = new ArrayList<>();
        String query = "SELECT * FROM `category`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategory_id(resultSet.getInt("category_id"));
                categoryDTO.setName(resultSet.getString("name"));

                list.add(categoryDTO);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
