package controller;

import model.StoreDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StoreController {
    // 삽입, 수정, 삭제, selectAll, selectOne
    Connection connection;
    public StoreController(Connection connection){
        this.connection = connection;
    }
    public StoreDTO selectOne(int id){
        StoreDTO s = new StoreDTO();
        String query = "SELECT * FROM store WHERE store_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                s.setId(resultSet.getInt("store_id"));
                s.setAddress_id(resultSet.getInt("address_id"));
                s.setManager_staff_Id(resultSet.getInt("manager_staff_id"));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }
    public ArrayList<StoreDTO> selectAll(){
        ArrayList<StoreDTO> list = new ArrayList<>();
        String query = "SELECT * FROM `store`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()){
                StoreDTO s = new StoreDTO();
                s.setId(resultSet.getInt("id"));
                s.setAddress_id(resultSet.getInt("address_id"));
                s.setManager_staff_Id(resultSet.getInt("manager_staff_id"));

                list.add(s);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
