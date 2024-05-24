/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author Long1
 */
public class AccountDAO extends MyDAO {

    //kiểm tra tài khoản
    public Account checkAccount(String username, String password) {
        String sql = "SELECT [id]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[email]\n"
                + "      ,[phonenumber]\n"
                + "      ,[role]\n"
                + "  FROM [dbo].[Account] where username = ? and password = ?";
        //String fullname = null;

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("phonenumber"), rs.getInt("role"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //đăng kí
    public void register(Account a) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[phonenumber]\n"
                + "           ,[role])\n"
                + "             VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getUsername());
            st.setString(2, a.getPassword());
            st.setString(3, a.getEmail());
            st.setString(4, a.getPhonenumber());
            st.setInt(5, a.getRole());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //đổi mật khẩu
    public void changePassword(Account a) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + " WHERE [username]=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, a.getPassword());
            st.setString(2, a.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    //kiểm tra trùng username
    public boolean existedUser(String username) {
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[email]\n"
                + "      ,[phonenumber]\n"
                + "      ,[role]\n"
                + "  FROM [dbo].[Account] where username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    //kiểm tra trùng email
    public boolean existedEmail(String email) {
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[email]\n"
                + "      ,[phonenumber]\n"
                + "      ,[role]\n"
                + "  FROM [dbo].[Account] where email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    //kiểm tra trùng số điện thoại
    public boolean existedPhoneNumber(String phonenumber) {
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[email]\n"
                + "      ,[phonenumber]\n"
                + "      ,[role]\n"
                + "  FROM [dbo].[Account] where phonenumber=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phonenumber);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
