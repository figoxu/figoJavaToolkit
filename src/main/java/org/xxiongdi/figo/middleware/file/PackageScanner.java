package org.xxiongdi.figo.middleware.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * .
 * User: figo
 * Date: 12-8-11
 * Time: 上午11:54
 */
public class PackageScanner {
    public static List<String> scanAllClasses(String basePath) {
        ArrayList<String> classNameList = new ArrayList<String>();
        basePath = basePath.replaceAll("\\\\", "/");
        List<String> fileFullPathList = listAllFile(basePath);
        for (String fileFullPath : fileFullPathList) {
            String filePathLowerCase = fileFullPath.toLowerCase();
            System.out.println("scan..." + filePathLowerCase);
            boolean javaFileFlag = filePathLowerCase.endsWith(".java");
            boolean classFileFlag = filePathLowerCase.endsWith(".class");
            if (!javaFileFlag && !classFileFlag) {
                System.out.println("this is not an java file");
                continue;
            }
            fileFullPath = fileFullPath.replaceAll("\\\\", "/");
            String className = fileFullPath.replaceFirst(basePath, "");
            if (className.startsWith("/")) {
                className = className.replaceFirst("/", "");
            }
            className = className.replaceAll("/", "\\.");
            className = className.replace(".java", "");
            className = className.replace(".JAVA", "");
            className = className.replace(".class", "");
            className = className.replace(".CLASS", "");
            classNameList.add(className);
        }
        return classNameList;
    }

    public static List<String> listAllFile(String dir) {
        File parentFile = new File(dir);
        List<String> fileList = new ArrayList<String>();
        if (parentFile.isFile()) {
            fileList.add(parentFile.getAbsolutePath());
            return fileList;
        }
        File[] files = parentFile.listFiles();
        if (files == null) {
            return fileList;
        }
        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                List<String> subFiles = listAllFile(file.getAbsolutePath());
                fileList.addAll(subFiles);
            }
        }
        return fileList;
    }

    public static void main(String[] args) {
        List<String> stringList = scanAllClasses("C:\\figo\\workspace\\figo\\mybatis\\xxd_mybatis_util\\src\\main\\java");
        for (String string : stringList) {
            System.out.println(string);
        }
    }
}
