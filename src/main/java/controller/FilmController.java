package controller;

import model.A_filmDTO;
import model.FilmDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmController {
    // 삽입, 수정, 삭제, selectAll, selectOne
    private Connection connection;
    public FilmController(Connection connection) {
        this.connection = connection;
    }

    public FilmDTO selectOne(int filmId) {
        FilmDTO f = new FilmDTO();
        String query = "SELECT DISTINCT * FROM `film` WHERE film_id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, filmId);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                f.setFilm_id(resultSet.getInt("film_id"));
                f.setTitle(resultSet.getString("title"));
                f.setDescription(resultSet.getString("description"));
                f.setRelease_year(resultSet.getInt("release_year"));
                f.setLanguage_id(resultSet.getInt("language_id"));
                f.setOriginal_language_id(resultSet.getInt("original_language_id"));
                f.setRental_duration(resultSet.getInt("rental_duration"));
                f.setRental_rate(resultSet.getInt("rental_rate"));
                f.setLength(resultSet.getInt("length"));
                f.setReplacement_cost(resultSet.getInt("replacement_cost"));
                f.setSpecial_features(resultSet.getString("special_features"));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }
}
