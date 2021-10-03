package org.westos.demo;

import org.westos.utils.MD5Utils;
import org.westos.utils.UUIDUtils;

import java.security.NoSuchAlgorithmException;

public class MyTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        //String uuid = UUIDUtils.getUUID();
        //System.out.println(uuid);//cddfea3aa9144f26a1680e78129df391
        String salt="cddfea3aa9144f26a1680e78129df391";
        String password="123456";
        password=password+salt;
        password= MD5Utils.md5Str(password);
        System.out.println(password);//de225eb698c7bfcb9c9996718e8f549d
        String p2="123456";
        p2=p2+salt;
        p2=MD5Utils.md5Str(p2);
        System.out.println(p2.equals(password));
        //System.out.println(s.equals("123456"));
    }
}
