package controller;

import model.ActorDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActorController {
    private Connection connection;

    ActorController(Connection connection) {
        this.connection = connection;
    }

    public ActorDTO selectOne(int id) {
        ActorDTO actorDTO = new ActorDTO();
        String query = "SELECT * FROM `actor` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                actorDTO = new ActorDTO();
                actorDTO.setId(resultSet.getInt("id"));
                actorDTO.setFirst_name(resultSet.getString("first_name"));
                actorDTO.setLast_name(resultSet.getString("last_name"));
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actorDTO;
    }


    public ArrayList<ActorDTO> selectList(){
        ArrayList<ActorDTO> list = new ArrayList<>();
        String query = "SELECT * FROM `actor`";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                ActorDTO actorDTO = new ActorDTO();
                actorDTO.setId(resultSet.getInt("id"));
                actorDTO.setFirst_name(resultSet.getString("first_name"));
                actorDTO.setLast_name(resultSet.getString("last_name"));

                list.add(actorDTO);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}






