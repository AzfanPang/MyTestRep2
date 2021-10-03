package org.westos.dao;

import org.westos.bean.Users;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface UserDao {
    public boolean checkUsername(String username) throws SQLException;
    public boolean registerUser(Users users) throws SQLException;
    public boolean checkLogin(Users users) throws SQLException, NoSuchAlgorithmException;
}
