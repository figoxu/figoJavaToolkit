package org.xxiongdi.figo.middleware;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: figoMac
 * Date: 14-11-12
 * Time: 上午7:03
 * To change this template use File | Settings | File Templates.
 */
public class BaseProperties {
    private  Properties props;
    private String filename;

    public BaseProperties(String filename) {
        this.filename = filename;
        InputStream is=BaseProperties.class.getResourceAsStream(filename);
        props = new Properties();
        try {
            props.load(is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  String getProperty(String name){
        String val = props.getProperty(name.trim());
        if(val == null){
            return null;
        }else{
            //去除前后端空格
            return val.trim();
        }
    }

    public  String getProperty(String name, String defaultValue) {
        String value = getProperty(name);
        if(value == null){
            value = defaultValue;
        }
        return value.trim();
    }

    //获得整数属性值
    public  int getIntProperty(String name,int defaultVal){
        int val = defaultVal;
        String valStr = getProperty(name);
        if(valStr != null){
            val = Integer.parseInt(valStr);
        }
        return val;
    }


    public  boolean getBooleanItem(String name, boolean defaultValue) {
        boolean b = defaultValue;
        String valStr = getProperty(name);
        if(valStr != null){
            b = Boolean.parseBoolean(valStr);
        }
        return b;
    }

    public  List<String> getListItem(String item){
        List<String> list = new ArrayList<String>();
        String value = getProperty(item, "");
        String sepChar = ",";
        if(value.indexOf(";")!=-1){
            sepChar = ";";
        }
        String[] sa = value.split(sepChar);
        for (int i = 0; i < sa.length; i++) {
            list.add(sa[i].trim());
        }
        return list;
    }

    /**
     * 设置属性
     * @should test
     * @param name
     * @param value
     */
    public  void setProperty(String name ,String value){
        props.setProperty(name, value);
        try {
            OutputStream fos = new FileOutputStream(this.getClass().getResource(filename).getPath());
            props.store(fos,"this is header");
            fos.close();
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
