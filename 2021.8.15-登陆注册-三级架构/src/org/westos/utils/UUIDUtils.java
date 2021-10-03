package org.westos.utils;

import java.util.UUID;

/**
 * @Author: ShenMouMou
 * @Company: 西部开源教育科技有限公司
 * @Description: 简简单单，只为教育。
 * @Date: 2021/8/13 15:08
 */
public class UUIDUtils {
    public static String getUUID() {
        /*
        *
        *  UUID由以下几部分的组合：



（1）当前日期和时间，UUID的第一个部分与时间有关，如果你在生成一个UUID之后，过几秒又生成一个UUID，则第一个部分不同，其余相同。



（2）时钟序列。



（3）全局唯一的IEEE机器识别号，如果有网卡，从网卡MAC地址获得，没有网卡以其他方式获得。



UUID的唯一缺陷在于生成的结果串会比较长。关于UUID这个标准使用最普遍的是微软的GUID(Globals Unique Identifiers)。在ColdFusion中可以用CreateUUID()函数很简单地生成UUID，
        *
        * */
        //UUID 根据电脑硬件的一些参数，来随机生成的字符串，理论上全球唯一
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        s = s.replace("-", "");
        //System.out.println(s);
        return s;
    }
}
