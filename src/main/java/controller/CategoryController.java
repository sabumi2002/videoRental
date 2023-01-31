package controller;

import model.CategoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryController {
    private Connection connection;

    CategoryController(Connection connection){
        this.connection = connection;
    }


}
