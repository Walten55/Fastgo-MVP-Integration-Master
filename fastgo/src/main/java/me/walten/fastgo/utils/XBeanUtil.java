package me.walten.fastgo.utils;

import org.jsoup.helper.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class XBeanUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");

    public static void copyPropertiesIgnoreNull(Object source, Object dest) {
        copyProperties(source, dest, true);
    }

    public static void copyProperties(Object source, Object dest, boolean ignoreNull) {
        try {
            for (Method method : source.getClass().getMethods()) {
                if (method.getDeclaringClass() == Object.class) continue;
                if (method.getName().startsWith("get")) {
                    Object obj = method.invoke(source, new Object[]{});

                    if (obj == null || ((obj instanceof String) && (obj.toString().length() == 0))) {
                        if (ignoreNull) {
                            continue;
                        }
                    }
                    for (Method destMethod : dest.getClass().getMethods()) {
                        if (destMethod.getDeclaringClass() == Object.class) continue;
                        if (destMethod.getName().startsWith("set") && (destMethod.getName().substring(3).equals(method.getName().substring(3)))) {
                            destMethod.invoke(dest, obj);
                            break;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static void copyMapPropertiesIgnoreNull(Map source, Object dest) {
        copyMapProperties(source, dest, true);
    }

    public static void copyMapProperties(Map source, Object dest, boolean ignoreNull) {
        try {
            for (Method method : dest.getClass().getMethods()) {
                if (method.getDeclaringClass() == Object.class) continue;
                if (!method.getName().startsWith("set")) continue;
                String property = method.getName().substring(3);
                if (source.containsKey(property)) {
                    if (source.get(property) == null || ((source.get(property) instanceof String) && (source.get(property).toString().length() == 0))) {
                        if (ignoreNull)
                            continue;
                    }
                } else if (source.containsKey(property.toLowerCase())) {
                    if (ignoreNull) {
                        if (source.get(property.toLowerCase()) == null || ((source.get(property.toLowerCase()) instanceof String) && (source.get(property.toLowerCase()).toString().length() == 0)))
                            continue;
                    }
                    property = property.toLowerCase();
                } else {
                    String first = property.substring(0, 1).toLowerCase();
                    String rest = property.substring(1, property.length());
                    String newProperty = new StringBuffer(first).append(rest).toString();
                    if (source.containsKey(newProperty)) {
                        if (source.get(newProperty) == null || ((source.get(newProperty) instanceof String) && (source.get(newProperty).toString().length() == 0))) {
                            if (ignoreNull)
                                continue;
                        }
                        property = newProperty;
                    }else
                        continue;
                }
                if (method.getParameterTypes()[0].equals(source.get(property).getClass()))
                    method.invoke(dest, source.get(property));
                else if (method.getParameterTypes()[0].equals(Date.class) && StringUtil.isNumeric(source.get(property).toString())) {
                    if (source.get(property).toString().length() == 13)
                        method.invoke(dest, new Date(Long.parseLong(source.get(property).toString())));
                    else if (source.get(property).toString().length() == 14) {
                        Date date = null;
                        try {
                            date = dateFormat.parse(source.get(property).toString());
                            method.invoke(dest, date);
                        } catch (ParseException e) {
                        }
                    } else if (source.get(property).toString().length() == 8) {
                        Date date = null;
                        try {
                            date = dayFormat.parse(source.get(property).toString());
                            method.invoke(dest, date);
                        } catch (ParseException e) {
                        }
                    }
                }
                else if (method.getParameterTypes()[0].equals(Integer.class) ) {
                    if(StringUtil.isNumeric(source.get(property).toString()))
                        method.invoke(dest, Integer.parseInt(source.get(property).toString()));
                    else
                        continue;
                } else if (method.getParameterTypes()[0].equals(Long.class)) {
                    if(StringUtil.isNumeric(source.get(property).toString()))
                        method.invoke(dest, Long.parseLong(source.get(property).toString()));
                    else
                        continue;
                }else if (method.getParameterTypes()[0].equals(Float.class)) {
                    StringUtil.isNumeric(source.get(property).toString());
                    if(StringUtil.isNumeric(source.get(property).toString()))
                        method.invoke(dest, Float.parseFloat(source.get(property).toString()));
                    else
                        continue;
                }else if (method.getParameterTypes()[0].equals(Double.class)) {
                    StringUtil.isNumeric(source.get(property).toString());
                    if(StringUtil.isNumeric(source.get(property).toString()))
                        method.invoke(dest, Double.parseDouble(source.get(property).toString()));
                    else
                        continue;
                }
                else
                {
                    try
                    {
                        method.invoke(dest, source.get(property));
                    }catch(Exception e){
                        e.printStackTrace();
                        continue;
                    }
                }

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static String addGetString(String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        return sb.toString();
    }

    public static String addSetString(String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("set");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        return sb.toString();
    }

    public static Map beanToMap(Object source) {
        Map m = new HashMap();
        try {
            for (Method method : source.getClass().getMethods()) {
                if (method.getDeclaringClass() == Object.class)
                    continue;
                if (!method.getName().startsWith("get"))
                    continue;
                String property = method.getName().substring(3);
                String first = property.substring(0, 1).toLowerCase();
                String rest = property.substring(1, property.length());
                String newProperty = new StringBuffer(first).append(rest).toString();
                Object value = method.invoke(source, new Object[]{});
                if (method.getReturnType().equals(Date.class)) {
                    m.put(newProperty, ((Date) value).getTime());
                } else {
                    m.put(newProperty, value);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return m;
    }
}