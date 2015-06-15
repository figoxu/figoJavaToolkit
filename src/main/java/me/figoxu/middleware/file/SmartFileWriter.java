package me.figoxu.middleware.file;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

/**
 * .
 * User: figo
 * Date: 12-8-11
 * Time: 上午11:40
 *
 */
public class SmartFileWriter {
    private static File getSmartFile(String fileFullName) {
        File file = new File(fileFullName);
        if (file.exists()) {
            file = new File(fileFullName + ".merge.txt");
            if (file.exists()) {
                file.deleteOnExit();
                file = new File(fileFullName + ".merge.txt");
            }
        }
        return file;
    }

    public static void writeFile(String fileFullName, String content) {
        try {
            File file = getSmartFile(fileFullName);
            OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            os.write(content);
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void writeXml(String fileFullName, String content) {
        try {
            File file = getSmartFile(fileFullName);
            Document document = DocumentHelper.parseText(content);
            XMLWriter output = null;
            /** 格式化输出,类型IE浏览一样 */
            OutputFormat format = OutputFormat.createPrettyPrint();
            /** 指定XML字符集编码 */
            format.setEncoding("UTF-8");
            output = new XMLWriter(new FileWriter(file), format);
            output.write(document);
            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
