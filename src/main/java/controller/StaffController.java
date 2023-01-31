package controller;

import model.StaffDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffController {
    // 삽입, 수정, 삭제, selectAll, selectOne, 인증

    private Connection connection;
    StaffController(Connection connection){
        this.connection = connection;
    }

    public StaffDTO selectOne(int id){
        StaffDTO s = new StaffDTO();
        String query = "SELECT * FROM `staff` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                s.setStaff_id(resultSet.getInt("staff_id"));

            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return s;
    }
}


























