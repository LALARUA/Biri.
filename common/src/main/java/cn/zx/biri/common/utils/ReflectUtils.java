package cn.zx.biri.common.utils;

import java.lang.reflect.Field;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 13:54 2019/3/17 0017
 */
public class ReflectUtils {
    public static String keyByFields(Object o){
        Class<?> clazz = o.getClass();
        StringBuilder key = new StringBuilder();
        try {
            while (clazz!=Object.class){
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field f : declaredFields) {
                    f.setAccessible(true);
                    Object fieldValue = f.get(o);
                    if (fieldValue != null){
                        key.append(f.getName()+"-"+f.get(o)+"/");
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (IllegalAccessException e) {

        }
        return key.toString();
    }
}
