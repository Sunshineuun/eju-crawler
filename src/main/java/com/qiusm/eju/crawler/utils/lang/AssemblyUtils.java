package com.qiusm.eju.crawler.utils.lang;

import org.apache.log4j.Logger;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p>组装通过反射实现，但是要尊重，类中属性的名称相同。</p>
 *
 * @author qiushengming
 * @Date 2016-10-28
 * @Page lunar.u.utils
 */
public class AssemblyUtils {

    private static Logger log = Logger.getLogger(AssemblyUtils.class);

    /**
     * <p>o1 复制到 o2</p>
     * <p>o1.getClass().toString() 的返回结果 是[class 包.类]，所有需要将[class ]这段截取掉</p>
     *
     * @param
     * @return Object
     * @autch qiushengming
     * @date 2016-10-28
     */
    public static Object assemblyIn(Object o1, Object o2) {
        try {
            Class<?> o1Clazz = Class.forName(o1.getClass().getName());
            Class<?> o2Clazz = Class.forName(o2.getClass().getName());
            Class<?> o2SubClazz =
                Class.forName(o2.getClass().getSuperclass().getName());
            Field[] o2Fields = concat(o2Clazz.getDeclaredFields(),
                o2SubClazz.getDeclaredFields());

            for (Field f : o2Fields) {
                PropertyDescriptor o1Pd = newInstance(f.getName(), o1Clazz);
                //如果o2中的字段o1中没有，返回null。
                if (o1Pd == null) {
                    continue;
                }
                PropertyDescriptor o2Pd = newInstance(f.getName(), o2Clazz);
                Method o2Method = o2Pd.getWriteMethod();
                Method o1Method = o1Pd.getReadMethod();

                Class<?>[] parameterType = o2Method.getParameterTypes();
                if (parameterType[0].getName() == "java.lang.Integer") {
                    o2Method.invoke(o2, (Integer) o1Method.invoke(o1));
                } else if (parameterType[0].getName() == "java.lang.String") {
                    o2Method.invoke(o2, String.valueOf(o1Method.invoke(o1)));
                }
            }
        } catch (ClassNotFoundException e) {
            log.error(e);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o2;
    }

    /**
     * <p>o中的值是否包含conditions条件</p>
     * <p>如果包含return m.getName()，如果不包含return null</p>
     *
     * @return String
     * @autch qiushengming
     * @date 2016-11-18
     */
    public static String contains(Object o, String conditions) {
        try {
            Class<?> clazz = Class.forName(o.getClass().getName());
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                PropertyDescriptor o2Pd = newInstance(f.getName(), clazz);
                Method m = o2Pd.getReadMethod();
                Object temp = m.invoke(o);
                if (String.valueOf(temp == null ? "" : temp)
                    .contains(conditions)) {
                    return m.getName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PropertyDescriptor newInstance(String name, Class<?> clazz) {
        if (name != null && !name.isEmpty() && clazz != null) {
            try {
                return new PropertyDescriptor(name, clazz);
            } catch (IntrospectionException e) {
                return null;
            }
        } else {
            throw new NullPointerException("Filed name or Clazz is null!!!");
        }
    }

    /**
     * <p>将f1与f2进行合并</p>
     * 首次是将父类的Field合并到子类Field中。
     *
     * @return Field[]
     * @autch qiushengming
     * @date 2016-11-18
     */
    private static Field[] concat(Field[] f1, Field[] f2) {
        Field[] result = new Field[f1.length + f2.length];
        for (int j = 0; j < f1.length; j++) {
            result[j] = f1[j];
        }
        for (int j = 0; j < f2.length; j++) {
            result[j + f1.length] = f2[j];
        }
        return result;
    }

    public static Object assemblyInMap(Map<String, String> map, Object o) {
        try {
            Class<?> clazz = Class.forName(o.getClass().getName());
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                PropertyDescriptor o2Pd = newInstance(f.getName(), clazz);
                if (o2Pd == null) {
                    continue;
                }
                Method m = o2Pd.getWriteMethod();
                Class<?>[] parameterType = m.getParameterTypes();
                if (parameterType[0].getName() == "java.lang.Integer") {
                    m.invoke(o, Integer.valueOf(map.get(f.getName())));
                } else if (parameterType[0].getName() == "java.lang.String") {
                    m.invoke(o, map.get(f.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
