package me.figoxu.middleware.data.mybatis;

import me.figoxu.middleware.data.mybatis.vm.mapper.*;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

import me.figoxu.CodeConstants;
import me.figoxu.middleware.template.velocity.VelocityUtil;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午4:32
 *
 */
public class MapperGenerator {
    public static void genXmlContent(Class clazz,String fileFullPath) throws Exception {
        String template = "me/figoxu/middleware/data/mybatis/vm/mapper/mapper.vm";
        System.out.println(clazz.getResource("/").toString()+clazz.getPackage().getName().replaceAll("\\x2E","/"));
        String jdbcManagerPackage = CodeConstants.codeProperty.getProperty(CodeConstants.code_jdbcManagerPackage);

        HashMap templateData = new HashMap();
        ArrayList<String> sqlList = new ArrayList<String>();

        sqlList.add(MapperInsertGenerator.gensql(clazz));
        sqlList.add(MapperDeleteGenerator.gensql(clazz));
        sqlList.add(MapperUpdateGenerator.gensql(clazz));
        sqlList.add(MapperSearchAllCountGenerator.gensql(clazz));
        sqlList.add(MapperSearchAllGenerator.gensql(clazz));
        sqlList.add(MapperSearchAllPagingGenerator.gensql(clazz));
        sqlList.add(MapperSearchByKeyGenerator.gensql(clazz));
        templateData.put("sqlItems", sqlList);
        templateData.put("classShortName",clazz.getSimpleName());
        templateData.put("jdbcManagerPackage",jdbcManagerPackage);
        String val = VelocityUtil.render(template, templateData);
        formatXMLFile(fileFullPath,val);
    }

    public static void genXmlContentWithIdentityInsert(Class clazz,String fileFullPath) throws Exception {
        String template = "me/figoxu/middleware/data/mybatis/vm/mapper/mapper.vm";

        String jdbcManagerPackage = CodeConstants.codeProperty.getProperty(CodeConstants.code_jdbcManagerPackage);
        System.out.println(clazz.getResource("/").toString()+clazz.getPackage().getName().replaceAll("\\x2E","/"));

        HashMap templateData = new HashMap();
        ArrayList<String> sqlList = new ArrayList<String>();

        sqlList.add(MapperInsertIndentityGenerator.gensql(clazz));
        sqlList.add(MapperDeleteGenerator.gensql(clazz));
        sqlList.add(MapperUpdateGenerator.gensql(clazz));
        sqlList.add(MapperSearchAllCountGenerator.gensql(clazz));
        sqlList.add(MapperSearchAllGenerator.gensql(clazz));
        sqlList.add(MapperSearchAllPagingGenerator.gensql(clazz));
        sqlList.add(MapperSearchByKeyGenerator.gensql(clazz));
        templateData.put("sqlItems", sqlList);
        templateData.put("classShortName",clazz.getSimpleName());
        templateData.put("jdbcManagerPackage",jdbcManagerPackage);

        String val = VelocityUtil.render(template, templateData);
        formatXMLFile(fileFullPath,val);
    }

    /**
     * 格式化XML文档,并解决中文问题
     *
     * @param fileFullPath
     * @return
     */
    public static void formatXMLFile(String fileFullPath, String xml) {
        try {
//            File file = new File(fileFullPath);
//            if(!file.exists()){
//                file.createNewFile();
//            }
            Document document = DocumentHelper.parseText(xml);
            XMLWriter output = null;
            /** 格式化输出,类型IE浏览一样 */
            OutputFormat format = OutputFormat.createPrettyPrint();
            /** 指定XML字符集编码 */
            format.setEncoding("UTF-8");
            output = new XMLWriter(new FileWriter(new File(fileFullPath)), format);
            output.write(document);
            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        genXmlContent(User.class,"C:\\temp\\123test.xml");
    }
}