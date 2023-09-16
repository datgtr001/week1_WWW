package vn.edu.iuh.fit.week1_www.repositories;

import vn.edu.iuh.fit.week1_www.db.ConnectDB;
import vn.edu.iuh.fit.week1_www.models.Account;
import vn.edu.iuh.fit.week1_www.models.Log;
import vn.edu.iuh.fit.week1_www.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class LogRepository {
    private Connection connection;

    public LogRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Log> getAll() throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        List<Log> listLog = new ArrayList<>();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM log";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getString("account_id"), rs.getString("full_name"),
                        rs.getString("password"), rs.getString("email"), rs.getString("phone"), Status.receiveStatus(rs.getInt("status")));
                Log log = new Log(rs.getString("log_id"), acc, rs.getDate("time_logon"), rs.getDate("time_logout"), rs.getString("note"));
                listLog.add(log);
            }
            return listLog;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Log getById(String id) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM log WHERE log_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getString("account_id"), rs.getString("full_name"),
                        rs.getString("password"), rs.getString("email"), rs.getString("phone"), Status.receiveStatus(rs.getInt("status")));
                Log log = new Log(rs.getString("log_id"), acc, rs.getDate("time_logon"), rs.getDate("time_logout"), rs.getString("note"));
                return log;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean createLog(Log log) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO log(log_id, account_id, time_logon, time_logout, note) VALUES (?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, log.getLogId());
            stm.setString(2, log.getAccount().getAccountId());
            stm.setDate(3, log.getLoginDate());
            stm.setDate(4, log.getLogoutDate());
            stm.setString(5, log.getNote());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateLog(Log log) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE log SET account_id = ?, time_logon = ?, time_logout = ?, note = ? WHERE log_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, log.getAccount().getAccountId());
            stm.setDate(2, log.getLoginDate());
            stm.setDate(3, log.getLogoutDate());
            stm.setString(4, log.getNote());
            stm.setString(5, log.getLogId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteLog(String id) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "DELETE FROM log WHERE log_id = ?";
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

