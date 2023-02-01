package controller;

import model.A_filmDTO;
import model.InventoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class A_filmController {
    // 영화id, 제목, 줄거리, 개봉연도, 언어.개봉언어, 상영길이,  특징, 카테고리.카테고리
    Connection connection;

    public A_filmController(Connection connection) {
        this.connection = connection;
    }

    public A_filmDTO selectOne(int filmId, int storeId) {
        A_filmDTO f = new A_filmDTO();
        String query = "SELECT DISTINCT f.*, c.name AS category, l.name AS language, i.store_id FROM sakila.film AS f" +
                " INNER JOIN sakila.film_category AS fc" +
                " ON f.film_id = fc.film_id" +
                " INNER JOIN sakila.category AS c" +
                " ON fc.category_id = c.category_id" +
                " INNER JOIN sakila.language AS l" +
                " ON f.language_id = l.language_id" +
                " INNER JOIN sakila.inventory AS i" +
                " ON f.film_id = i.film_id" +
                " where f.film_id = ? AND i.store_id = ?" +
                " ORDER BY f.film_id";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, filmId);
            pstmt.setInt(2, storeId);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                f.setFilm_id(resultSet.getInt("film_id"));
                f.setTitle(resultSet.getString("title"));
                f.setDescription(resultSet.getString("description"));
                f.setRelease_year(resultSet.getInt("release_year"));
                f.setLanguage(resultSet.getString("language"));
                f.setLength(resultSet.getInt("length"));
                f.setSpecial_features(resultSet.getString("special_features"));
                f.setCategory(resultSet.getString("category"));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public ArrayList<A_filmDTO> selectAll(int storeId){
        ArrayList<A_filmDTO> list = new ArrayList<>();
        String query = "SELECT DISTINCT f.*, c.name AS category, l.name AS language, i.store_id FROM sakila.film AS f" +
                " INNER JOIN sakila.film_category AS fc" +
                " ON f.film_id = fc.film_id" +
                " INNER JOIN sakila.category AS c" +
                " ON fc.category_id = c.category_id" +
                " INNER JOIN sakila.language AS l" +
                " ON f.language_id = l.language_id" +
                " INNER JOIN sakila.inventory AS i" +
                " ON f.film_id = i.film_id" +
                " where i.store_id = ?" +
                " ORDER BY f.film_id;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, storeId);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                A_filmDTO f = new A_filmDTO();
                f.setFilm_id(resultSet.getInt("film_id"));
                f.setTitle(resultSet.getString("title"));
                f.setDescription(resultSet.getString("description"));
                f.setRelease_year(resultSet.getInt("release_year"));
                f.setLanguage(resultSet.getString("language"));
                f.setLength(resultSet.getInt("length"));
                f.setSpecial_features(resultSet.getString("special_features"));
                f.setCategory(resultSet.getString("category"));

                list.add(f);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<A_filmDTO> selectByTitle(String title, int storeId){  // 타이틀별 검색
        ArrayList<A_filmDTO> list = new ArrayList<>();

        String query = "SELECT DISTINCT f.*, c.name AS category, l.name AS language, i.store_id FROM sakila.film AS f" +
                " INNER JOIN sakila.film_category AS fc" +
                " ON f.film_id = fc.film_id" +
                " INNER JOIN sakila.category AS c" +
                " ON fc.category_id = c.category_id" +
                " INNER JOIN sakila.language AS l" +
                " ON f.language_id = l.language_id" +
                " INNER JOIN sakila.inventory AS i" +
                " ON f.film_id = i.film_id" +
                " WHERE i.store_id = ? AND f.title LIKE ?" +
                " ORDER BY f.film_id";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, storeId);
            pstmt.setString(2, "%"+title+"%");
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                A_filmDTO f = new A_filmDTO();
                f.setFilm_id(resultSet.getInt("film_id"));
                f.setTitle(resultSet.getString("title"));
                f.setDescription(resultSet.getString("description"));
                f.setRelease_year(resultSet.getInt("release_year"));
                f.setLanguage(resultSet.getString("language"));
                f.setLength(resultSet.getInt("length"));
                f.setSpecial_features(resultSet.getString("special_features"));
                f.setCategory(resultSet.getString("category"));

                list.add(f);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<A_filmDTO> selectByCategory(int category_id, int store_id){   // 카테고리별 검색
        ArrayList<A_filmDTO> list = new ArrayList<>();

        String query = "SELECT DISTINCT f.*, c.category_id, c.name AS category, l.name AS language, i.store_id FROM sakila.film AS f" +
                " INNER JOIN sakila.film_category AS fc" +
                " ON f.film_id = fc.film_id\n" +
                " INNER JOIN sakila.category AS c" +
                " ON fc.category_id = c.category_id" +
                " INNER JOIN sakila.language AS l" +
                " ON f.language_id = l.language_id" +
                " INNER JOIN sakila.inventory AS i" +
                " ON f.film_id = i.film_id" +
                " WHERE i.store_id = ? AND c.category_id = ?" +
                " ORDER BY f.film_id;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, store_id);
            pstmt.setInt(2, category_id);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                A_filmDTO f = new A_filmDTO();
                f.setFilm_id(resultSet.getInt("film_id"));
                f.setTitle(resultSet.getString("title"));
                f.setDescription(resultSet.getString("description"));
                f.setRelease_year(resultSet.getInt("release_year"));
                f.setLanguage(resultSet.getString("language"));
                f.setLength(resultSet.getInt("length"));
                f.setSpecial_features(resultSet.getString("special_features"));
                f.setCategory(resultSet.getString("category"));

                list.add(f);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<A_filmDTO> selectByLanguage(String language, int store_id){   // 언어별 검색
        ArrayList<A_filmDTO> list = new ArrayList<>();

        String query = "SELECT DISTINCT f.*, c.category_id, c.name AS category, l.name AS language, i.store_id FROM sakila.film AS f" +
                " INNER JOIN sakila.film_category AS fc" +
                " ON f.film_id = fc.film_id" +
                " INNER JOIN sakila.category AS c" +
                " ON fc.category_id = c.category_id" +
                " INNER JOIN sakila.language AS l" +
                " ON f.language_id = l.language_id" +
                " INNER JOIN sakila.inventory AS i" +
                " ON f.film_id = i.film_id" +
                " where i.store_id = ? AND l.name = ?" +
                " ORDER BY f.film_id;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, store_id);
            pstmt.setString(2, language);
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                A_filmDTO f = new A_filmDTO();
                f.setFilm_id(resultSet.getInt("film_id"));
                f.setTitle(resultSet.getString("title"));
                f.setDescription(resultSet.getString("description"));
                f.setRelease_year(resultSet.getInt("release_year"));
                f.setLanguage(resultSet.getString("language"));
                f.setLength(resultSet.getInt("length"));
                f.setSpecial_features(resultSet.getString("special_features"));
                f.setCategory(resultSet.getString("category"));

                list.add(f);
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



}
