package org.westos.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.westos.bean.Users;
import org.westos.utils.MD5Utils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

public class UserDaoImpl implements UserDao {
    private static DataSource ds;
    static {
        try {
            InputStream in = UserDaoImpl.class.getClassLoader().getResourceAsStream("druid.properties");

            Properties properties = new Properties();
            properties.load(in);
            ds = new DruidDataSourceFactory().createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean checkUsername(String username) throws SQLException {
        QueryRunner qr = new QueryRunner(ds);
        Users users = qr.query("select * from users where username=?", new BeanHandler<Users>(Users.class),username);
        return users==null;
    }

    @Override
    public boolean registerUser(Users users) throws SQLException {
        QueryRunner qr = new QueryRunner(ds);
        String username = users.getUsername();
        Integer age = users.getAge();
        String password = users.getPassword();
        String email = users.getEmail();
        String phone = users.getPhone();
        String salt = users.getSalt();
        Integer sex = users.getSex();
        int i = qr.update("insert into users values(?,?,?,?,?,?,?,?)", Types.NULL, username, password, email, phone, sex, age, salt);
        return i==1;
    }

    @Override
    public boolean checkLogin(Users users) throws SQLException, NoSuchAlgorithmException {
        QueryRunner qr = new QueryRunner(ds);
        String username = users.getUsername();
        String password = users.getPassword();

        Users query = qr.query("select * from users where username=?", new BeanHandler<Users>(Users.class), username);
        if(query!=null){
            String salt = query.getSalt();
            password=password+salt;
            password=MD5Utils.md5Str(password);
        } else if (query == null) {
            return false;
        }

        Users queryUser=qr.query("select * from users where username=? and password=?", new BeanHandler<Users>(Users.class), username,password);
        return queryUser!=null;
    }
}
