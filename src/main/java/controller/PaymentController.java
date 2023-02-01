package controller;

import model.PaymentDTO;
import model.RentalDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentController {
    Connection connection;

    public PaymentController(Connection connection) {
        this.connection = connection;
    }

    public PaymentDTO selectOne(int id) {
        PaymentDTO p = new PaymentDTO();
        String query = "SELECT * FROM `payment` WHERE `payment_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                p.setPayment_id(resultSet.getInt("payment_id"));
                p.setCustomer_id(resultSet.getInt("customer_id"));
                p.setStaff_id(resultSet.getInt("staff_id"));
                p.setRental_id(resultSet.getInt("rental_id"));
                p.setAmount(resultSet.getInt("amount_id"));
                p.setPayment_date(resultSet.getDate("payment_date"));
            }

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return p;
    }

    public double sumPaymentById(int id) {
        double sum = 0;
        String query = "SELECT customer_id, sum(amount) AS sum FROM sakila.payment\n" +
                "WHERE customer_id = ?\n" +
                "GROUP BY customer_id;";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                sum = resultSet.getDouble("sum");
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sum;
    }


    public void insert(PaymentDTO paymentDTO) {
        String query = "INSERT INTO `payment`(`customer_id`, `staff_id`, `rental_id`, `amount`, `payment_date`) " +
                "VALUES(?, ?, ?, ?, NOW())";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, paymentDTO.getCustomer_id());
            pstmt.setInt(2, paymentDTO.getStaff_id());
            pstmt.setInt(3, paymentDTO.getRental_id());
            pstmt.setInt(4, paymentDTO.getAmount());


            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM `payment` WHERE `payment_id` = ?";
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





















