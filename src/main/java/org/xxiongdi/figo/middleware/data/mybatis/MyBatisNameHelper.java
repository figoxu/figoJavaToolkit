package org.xxiongdi.figo.middleware.data.mybatis;

import org.xxiongdi.figo.common.domain.BaseDomain;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午4:42
 *
 */
public class MyBatisNameHelper {
    public static String getTableName(Class clazz){
        String tableName = clazz.getSimpleName();
        return tableName;
    }

    public static List<String> getColumnNameList(Class clazz) {
        List<String> nameList = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            nameList.add(getColumnName(field));
        }
        return nameList;
    }
    public static List<String> getFieldNameList(Class clazz) {
        List<String> nameList = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            nameList.add(field.getName());
        }
        return nameList;
    }


    public static HashMap<String,String> getColumnFieldMap(Class clazz){
        HashMap<String,String> columnFieldMap = new HashMap<String, String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String key = getColumnName(field);
            String value = field.getName();
            columnFieldMap.put(key,value);
        }
        return columnFieldMap;
    }

    public static String getColumnNames(Class clazz){
       List<String> columnNameList = getColumnNameList(clazz);
        StringBuffer columnNames = new StringBuffer();
        for(int i=0;i<columnNameList.size();i++){
            if(i>0){
                columnNames.append(",");
            }
            String columnName = columnNameList.get(i);
            columnNames.append(columnName);
        }
        return columnNames.toString();
    }

    private static List<String> pickupId(List<String> inputList){
        List<String> outputList = new ArrayList<String>();
        for(String input:inputList){
           if(input.equals("id")){
               continue;
           }
            outputList.add(input);
        }
        return outputList;
    }

    public static String getColumnNamesWithOutId(Class clazz) {
        List<String> columnNameList = getColumnNameList(clazz);
        columnNameList = pickupId(columnNameList);
        StringBuffer columnNames = new StringBuffer();
        for(int i=0;i<columnNameList.size();i++){
            if(i>0){
                columnNames.append(",");
            }
            String columnName = columnNameList.get(i);
            columnNames.append(columnName);
        }
        return columnNames.toString();
    }

    public static String getColumnValues(Class clazz){
        List<String> fieldNameList = getFieldNameList(clazz);
        StringBuffer fieldNames = new StringBuffer();
        for(int i=0;i<fieldNameList.size();i++){
            if(i>0){
                fieldNames.append(",");
            }
            String fieldName = fieldNameList.get(i);
            fieldNames.append("#{").append(fieldName).append("}");
        }
        return fieldNames.toString();
    }
    public static String getColumnValuesWithOutId(Class clazz) {
        List<String> fieldNameList = getFieldNameList(clazz);
        fieldNameList = pickupId(fieldNameList);
        StringBuffer fieldNames = new StringBuffer();
        for(int i=0;i<fieldNameList.size();i++){
            if(i>0){
                fieldNames.append(",");
            }
            String fieldName = fieldNameList.get(i);
            fieldNames.append("#{").append(fieldName).append("}");
        }
        return fieldNames.toString();
    }


    public static String getColumnKeyValues(Class clazz){
        Map columnFieldMap = getColumnFieldMap(clazz);
        StringBuffer columnKeyValues = new StringBuffer();
        Iterator<String> keyIterator = columnFieldMap.keySet().iterator();
        int index=0;
        while (keyIterator.hasNext()){
            String key = keyIterator.next();
            String value = (String) columnFieldMap.get(key);
            if(index>0){
                columnKeyValues.append(",");
            }
            columnKeyValues.append(key).append("=#{").append(value).append("}");
            index++;
        }
        return columnKeyValues.toString();
    }




    public static String getColumnName(Field field) {
        String name = field.getName();
        if (field.getType().isInstance(BaseDomain.class)) {
            return name + "_id";
        }
        return name;
    }

}