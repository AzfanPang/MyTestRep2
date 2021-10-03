package org.westos.service;


import org.westos.bean.Users;
import org.westos.dao.UserDao;
import org.westos.dao.UserDaoImpl;
import org.westos.utils.MD5Utils;
import org.westos.utils.UUIDUtils;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    @Override
    public boolean registerUser(Users users) throws SQLException, NoSuchAlgorithmException {
        String username = users.getUsername();
        String password = users.getPassword();
        String salt = UUIDUtils.getUUID();
        password=password+salt;
        password= MD5Utils.md5Str(password);
        users.setPassword(password);
        users.setSalt(salt);
        boolean b = userDao.checkUsername(username);
        if(b){
            userDao.registerUser(users);
        }
        return b;
    }

    @Override
    public boolean loginUser(Users users) throws SQLException, NoSuchAlgorithmException {
        boolean b = userDao.checkLogin(users);
        return b;
    }
}
