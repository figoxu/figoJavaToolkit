package me.figoxu.middleware.code;

import org.xxiongdi.figo.CodeConstants;
import org.xxiongdi.figo.middleware.data.mybatis.MapperGenerator;
import org.xxiongdi.figo.middleware.file.PackageScanner;
import org.xxiongdi.zfct.middleware.FileReadWriteHelper;

import javax.persistence.Table;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: figoMac
 * Date: 14-11-12
 * Time: 上午7:38
 * To change this template use File | Settings | File Templates.
 */
public class GenIBatisXmlContent {
    private final static String basePath = CodeConstants.codeProperty.getProperty(CodeConstants.code_iBatisConfPath);   //other configuration are in class VelocityUtil;

    public static void generatorOneMybatis(Class clazz) {
        String simpleName = clazz.getSimpleName();
        String fileName = basePath + "/mapping-" + simpleName + ".xml";
        String appendFileName = basePath + "/mapping-" + simpleName + "-Append.xml";
        String jdbcManagerPackage = CodeConstants.codeProperty.getProperty(CodeConstants.code_jdbcManagerPackage);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            String dayInfo = simpleDateFormat.format(date);
            File file = new File(fileName);
            if (file.exists()) {
                File newFile = new File(file.getAbsolutePath() + "." + dayInfo);
                file.renameTo(newFile);
//                file.delete();
            }
            MapperGenerator.genXmlContentWithIdentityInsert(clazz, fileName);
            File appendFile = new File(appendFileName);
            if (!appendFile.exists()) {
                FileReadWriteHelper.appendFile(appendFileName, "" +
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                        "<mapper namespace=\""+jdbcManagerPackage+"."+ simpleName +"Mapper\">\n\n" + "</mapper>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generatorAll() {
        List<String> classList = PackageScanner.scanAllClasses(CodeConstants.codeProperty.getProperty(CodeConstants.code_classScanPath));
        StringBuffer classConfBuffer = new StringBuffer("");
        StringBuffer xmlRefConfBuffer = new StringBuffer("");

        for (String clazz : classList) {
            try {
                Class<?> clazzClass = Class.forName(clazz);
                Table annotation = clazzClass.getAnnotation(Table.class);
                if (annotation == null) {
                    continue;
                }
                generatorOneMybatis(clazzClass);
                classConfBuffer.append("\t<typeAlias type=\"" + clazz + "\"" + " alias=\"" + clazzClass.getSimpleName() + "\"/>\n");
                xmlRefConfBuffer.append("\t<mapper resource=\"mybatis/mapping-" + clazzClass.getSimpleName() + "-Append.xml\"/>\n");
                xmlRefConfBuffer.append("\t<mapper resource=\"mybatis/mapping-" + clazzClass.getSimpleName() + ".xml\"/>\n");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


        System.out.println("############ ibatis conf class #######################");
        System.out.println(classConfBuffer.toString());
        System.out.println("############ ibatis conf xml ref #######################");
        System.out.println(xmlRefConfBuffer.toString());

    }
}
