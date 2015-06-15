package me.figoxu.middleware.code;

import org.xxiongdi.figo.CodeConstants;
import org.xxiongdi.figo.middleware.file.PackageScanner;

import javax.persistence.Table;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: figoMac
 * Date: 14-11-12
 * Time: 上午7:37
 * To change this template use File | Settings | File Templates.
 */
public class GenSpringDBConf {

    public static void generatorSpringDB_HibernateConf() {
        List<String> classList = PackageScanner.scanAllClasses(CodeConstants.codeProperty.getProperty(CodeConstants.code_classScanPath));
        System.out.println("################# hibernate annonation conf ######################");
        for (String clazz : classList) {
            try {
                Class<?> clazzClass = Class.forName(clazz);
                Table annotation = clazzClass.getAnnotation(Table.class);
                if (annotation == null) {
                    continue;
                }
                System.out.println("<value>" + clazz + "</value>");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("#################  ######################");
    }

}
