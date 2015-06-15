package me.figoxu.middleware.code;


import me.figoxu.CodeConstants;
import me.figoxu.middleware.file.FileReadWriteHelper;
import me.figoxu.middleware.file.PackageScanner;

import javax.persistence.Table;
import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: figoMac
 * Date: 14-11-13
 * Time: 上午6:49
 * To change this template use File | Settings | File Templates.
 */
public class GenDBManager {

    public final static String managerPackage = CodeConstants.codeProperty.getProperty(CodeConstants.code_jdbcManagerPackage);

    private static String managerFolder;
    static {
        managerFolder = CodeConstants.codeProperty.getProperty(CodeConstants.code_classScanPath)+"/"+managerPackage.replaceAll("\\.","/");
        System.out.println(managerFolder);
    }

    public static void generatorAll()  {
        List<String> classList = PackageScanner.scanAllClasses(CodeConstants.codeProperty.getProperty(CodeConstants.code_classScanPath));
        for(String clazz:classList){
            try{
            Class<?> clazzClass = Class.forName(clazz);
            Table annotation = clazzClass.getAnnotation(Table.class);
            if(annotation==null){
                continue;
            }
            generator(clazzClass);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }


    }

    private static void generator(Class<?> clazzClass) {
        String className = clazzClass.getSimpleName()+"Manager";
        String fileName = managerFolder+"/"+clazzClass.getSimpleName()+"Manager.java";
        File file = new File(fileName);
        if(file.exists()){
            return;
        }
        StringBuffer fileContent = new StringBuffer( "package "+managerPackage+";\n");
        fileContent.append("import org.springframework.context.annotation.Scope;\n");
        fileContent.append("import org.springframework.stereotype.Component;\n");
        fileContent.append("import org.xxiongdi.figo.common.dao.MyBatisBaseDAO;\n");
        fileContent.append("import "+clazzClass.getName()+";\n\n\n");
        fileContent.append("@Scope(\"prototype\")\n");
        fileContent.append("@Component\n");
        fileContent.append("public class "+className+" extends MyBatisBaseDAO<"+clazzClass.getSimpleName()+"> {\n");
        fileContent.append("}");
        FileReadWriteHelper.updateFile(fileName, fileContent.toString());
    }
}
