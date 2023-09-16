package vn.edu.iuh.fit.week1_www.repositories;

import vn.edu.iuh.fit.week1_www.db.ConnectDB;
import vn.edu.iuh.fit.week1_www.models.Role;
import vn.edu.iuh.fit.week1_www.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    private Connection connection;

    public RoleRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Role> getAll() throws SQLException, ClassNotFoundException {
        List<Role> listRole = new ArrayList<>();
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM role";
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getString("role_id"));
                role.setRoleName(rs.getString("role_name"));
                role.setDescription(rs.getString("description"));
                role.setStatus(Status.receiveStatus(rs.getInt("status")));

                listRole.add(role);
            }
            return listRole;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Role getByID(String id) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM role WHERE role_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getString("role_id"));
                role.setRoleName(rs.getString("role_name"));
                role.setDescription(rs.getString("description"));
                role.setStatus(Status.receiveStatus(rs.getInt("status")));
            }
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean createRole(Role role) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO role(role_id, role_name, description, status) VALUES (?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, role.getRoleId());
            stm.setString(2, role.getRoleName());
            stm.setString(3, role.getDescription());
            stm.setInt(4, role.getStatus().getValue());

            int result = stm.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateRole(Role role) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "UPDATE role SET role_name = ?, description = ?, status = ? WHERE role_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, role.getRoleName());
            stm.setString(2, role.getDescription());
            stm.setInt(3, role.getStatus().getValue());
            stm.setString(4, role.getRoleId());

            int result = stm.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteRole(String id) throws SQLException, ClassNotFoundException {
        connection = ConnectDB.getInstance().getConnection();
        PreparedStatement stm = null;
        try {
            String sql = "DELETE FROM role WHERE role_id = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, id);

            int result = stm.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
