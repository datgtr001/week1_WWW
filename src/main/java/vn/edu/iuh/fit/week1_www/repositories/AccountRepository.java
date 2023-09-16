package vn.edu.iuh.fit.week1_www.repositories;

import vn.edu.iuh.fit.week1_www.db.ConnectDB;
import vn.edu.iuh.fit.week1_www.models.Account;
import vn.edu.iuh.fit.week1_www.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Account> getById(String id) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        List<Account> listAcc = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account WHERE account_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            Account acc = new Account();
            if (rs.next()) {
                acc.setAccountId(rs.getString("account_id"));
                acc.setName(rs.getString("full_name"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setPhone(rs.getString("phone"));
                acc.setStatus(Status.receiveStatus(rs.getInt("status")));

                listAcc.add(acc);
                return listAcc;
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Account> getAll() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        List<Account> listAcc = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getString("account_id"));
                acc.setName(rs.getString("full_name"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setPhone(rs.getString("phone"));
                acc.setStatus(Status.receiveStatus(rs.getInt("status")));

                listAcc.add(acc);
            }
            return listAcc;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean creatAccount(Account account) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO account(account_id, full_name, password, email, phone, status) VALUES (?, ?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getAccountId());
            stm.setString(2, account.getName());
            stm.setString(3, account.getPassword());
            stm.setString(4, account.getEmail());
            stm.setString(5, account.getPhone());
            stm.setInt(6, account.getStatus().getValue());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateAccount(Account account) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE account SET full_name = ?, password = ?, email = ?, phone = ?, status = ? WHERE account_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, account.getName());
            stm.setString(2, account.getPassword());
            stm.setString(3, account.getEmail());
            stm.setString(4, account.getPhone());
            stm.setInt(5, account.getStatus().getValue());
            stm.setString(6, account.getAccountId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteAccount(String id) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "DELETE FROM account WHERE account_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
