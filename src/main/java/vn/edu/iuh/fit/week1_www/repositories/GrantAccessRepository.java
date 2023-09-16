package vn.edu.iuh.fit.week1_www.repositories;

import vn.edu.iuh.fit.week1_www.models.GrantAccess;
import vn.edu.iuh.fit.week1_www.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.iuh.fit.week1_www.models.Account;
import vn.edu.iuh.fit.week1_www.models.Role;
import vn.edu.iuh.fit.week1_www.db.ConnectDB;

public class GrantAccessRepository {
    private Connection connection;

    public GrantAccessRepository(Connection connection) {
        this.connection = connection;
    }

    public List<GrantAccess> getALl() throws SQLException, ClassNotFoundException {
        List<GrantAccess> listGrant = new ArrayList<>();
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "Select * from grant_access " +
                    "join account on grant_access.account_id = account.account_id " +
                    "join role on grant_access.role_id = role.role_id)";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), Status.receiveStatus(rs.getInt("status")));
                Role role = new Role(rs.getString("role_id"), rs.getString("role_name"), rs.getString("description"), Status.receiveStatus(rs.getInt("status")));
                GrantAccess grantAccess = new GrantAccess(acc, role, rs.getBoolean("is_grant"), rs.getString("note"));
                listGrant.add(grantAccess);
            }
            return listGrant;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public GrantAccess getById(String accId, String roleId) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM grant_access join account on grant_access.account_id = account.account_id" +
                    " join role on grant_access.role_id = role.role_id WHERE grant_access.account_id = ? AND grant_access.role_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, accId);
            stm.setString(2, roleId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), Status.receiveStatus(rs.getInt("status")));
                Role role = new Role(rs.getString("role_id"), rs.getString("role_name"), rs.getString("description"), Status.receiveStatus(rs.getInt("status")));
                GrantAccess grantAccess = new GrantAccess(acc, role, rs.getBoolean("is_grant"),
                        rs.getString("note"));
                return grantAccess;
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createGrantAccess(GrantAccess grantAccess) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO grant_access(account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, grantAccess.getAccount().getAccountId());
            stm.setString(2, grantAccess.getRole().getRoleId());
            stm.setBoolean(3, grantAccess.isIs_grant());
            stm.setString(4, grantAccess.getNote());
            int row = stm.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateGrantAccess(GrantAccess grantAccess) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE grant_access SET is_grant = ?, note = ? WHERE account_id = ? AND role_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, grantAccess.isIs_grant());
            stm.setString(2, grantAccess.getNote());
            stm.setString(3, grantAccess.getAccount().getAccountId());
            stm.setString(4, grantAccess.getRole().getRoleId());
            int row = stm.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteGrantAccess(String accId, String roleId) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "DELETE FROM grant_access WHERE account_id = ? AND role_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, accId);
            stm.setString(2, roleId);
            int row = stm.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
