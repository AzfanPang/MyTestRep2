package org.westos.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: ShenMouMou
 * @Company: 西部开源教育科技有限公司
 * @Description: 简简单单，只为教育。
 * @Date: 2021/8/13 14:48
 */
public class MD5Utils {
    public static String md5Str(String pwd) throws NoSuchAlgorithmException {
        //MD5加密 不好之处 明文加密完的密串是固定的  123456----asdfasdfasdfeadfefsdsfeaf
        //123456  123456+随机盐值(asdfadfasdfasdfasdfasdf)

        //(123456 + 随机盐值(asdfadfasdfasdfasdfasdf)).md5() ------ 密文
        //   (123456 + 随机盐值(sdfsdfsddfswwwwwwww)).md5()--------密文

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = pwd.getBytes();
        byte[] bytes2 = md5.digest(pwd.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes2) {
            //  &:有0则0
            // |:有1则1
            int num = aByte & 0xff; // |  &
            String ch = Integer.toHexString(num);
            //不够两位的补够2位
            if (ch.length() < 2) {
                ch = ch + '0';
            }
            //System.out.println(ch);
            sb.append(ch);
        }
        return sb.toString();
    }
}
