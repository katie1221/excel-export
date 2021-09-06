package com.example.easypoiexceldemo.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * List<Map<String,Object>>到List<T>数据转换
 * @author qzz
 */
public class MapToEntity {

    /**
     * List<Map<String, Object>> 到 List<T> 数据转换
     */
    public static <T> List<T> setList(final List<Map<String, Object>> srcList, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (int i=0;i<srcList.size();i++){
            try {
                T t = clazz.newInstance();
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (!"serialVersionUID".equals(field.getName())) {
                        //设置对象的访问权限，保证对private的属性的访问
                        field.setAccessible(true);
                        //读取配置转换字段名，并从map中取出数据
                        Object v = srcList.get(i).get(field.getName());
                        field.set(t, convert(v, field.getType()));
                    }
                }
                list.add(t);
            } catch (Exception ex) {
                ex.toString();
            }

        };
        return list;
    }

    /**
     * 字段类型转换
     */
    private static <T> T convert(Object obj, Class<T> type) throws ParseException {
        if (obj != null && StringUtils.isNotBlank(obj.toString())) {
            if (type.equals(String.class)) {
                return (T) obj.toString();
            } else if (type.equals(BigDecimal.class)) {
                return (T) new BigDecimal(obj.toString());
            }else if(type.equals(Double.class)){
                return (T) Double.valueOf(obj.toString());
            }else if(type.equals(Integer.class)){
                return (T) Integer.valueOf(obj.toString());
            }else if(type.equals(Date.class)){
                if(obj!=null){
                    String timeStr = String.valueOf(obj);
                    String s[] = timeStr.split("T");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return (T) sdf.parse(s[0]+" "+s[1]);
                }else{
                    return null;
                }
            }
            else{
                //其他类型转换
                return (T) obj.toString();
            }

        }
        return null;
    }
}
