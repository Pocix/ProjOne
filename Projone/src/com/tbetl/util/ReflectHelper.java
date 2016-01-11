package com.tbetl.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *	反射工具
 */
public class ReflectHelper {
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getValueByFieldName(Object obj, String fieldName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if(field!=null){
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void setValueByFieldName(Object obj, String fieldName,
			Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
	
	/**
	 * 清空实体属性值
	 * @param obj
	 */
	public static void setObjectFieldsEmpty(Object obj) {  
        // 对obj反射  
        Class objClass = obj.getClass();  
        Method[] objmethods = objClass.getDeclaredMethods();  
        Map objMeMap = new HashMap();  
        for (int i = 0; i < objmethods.length; i++) {  
            Method method = objmethods[i];  
            objMeMap.put(method.getName(), method);  
        }  
        for (int i = 0; i < objmethods.length; i++) {  
            {  
                String methodName = objmethods[i].getName();  
                if (methodName != null && methodName.startsWith("get")) {  
                    try {  
                        Object returnObj = objmethods[i].invoke(obj,  
                                new Object[0]);  
                        Method setmethod = (Method) objMeMap.get("set"  
                                + methodName.split("get")[1]);  
                        if (returnObj != null) {  
                            returnObj = null;  
                        }  
                        setmethod.invoke(obj, returnObj);  
                    } catch (IllegalArgumentException e) {  
                        e.printStackTrace();  
                    } catch (IllegalAccessException e) {  
                        e.printStackTrace();  
                    } catch (InvocationTargetException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
  
        }  
    } 
}
