package org.westos.service;

import org.westos.bean.Users;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface UserService {
    public boolean registerUser(Users users) throws SQLException, NoSuchAlgorithmException;
    public boolean loginUser(Users users) throws SQLException, NoSuchAlgorithmException;
}
