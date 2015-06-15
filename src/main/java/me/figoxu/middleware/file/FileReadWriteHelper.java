package me.figoxu.middleware.file;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * User: figo.xu
 * Date: 12-10-26
 * Time: 下午3:28
 *
 */
public class FileReadWriteHelper {
    private static Logger logger = Logger.getLogger(FileReadWriteHelper.class);

    public static final String NEW_LINE = "\r\n";

    public static void checkAndSafeTheDirectory(String filePath) throws IOException {
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        if(!file.exists()){
            file.createNewFile();
        }
    }

    /**
     * @param filepath 文件路径
     * @return 逐字列表
     * @throws IOException
     */
    public static List<String> readFromEachLine(String filepath) {
        List<String> stringList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            checkAndSafeTheDirectory(filepath);
//            br = new BufferedReader(new FileReader(filepath));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "utf-8"));
            String data = br.readLine();
            while (data != null) {
                stringList.add(data);
                data = br.readLine();
            }
            br.close();
        } catch (Exception ex) {
            logger.error("",ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
        return stringList;
    }

    /**
     * 清除文件的所有内容
     * @param filepath
     */
    public static void cleanFile(String filepath){
        File file = new File(filepath);
        if(file.exists()){
            file.delete();
        }
        try {
            checkAndSafeTheDirectory(filepath);
            file.createNewFile();
        } catch (IOException e) {
            logger.error("",e);
        }
    }

    /**
     * 全量更新文件
     * @param filepath
     * @param content
     */
    public static void updateFile(String filepath,String content){
        cleanFile(filepath);
        File file = new File(filepath);
        OutputStreamWriter outputStreamWriter = null;
        try {
            checkAndSafeTheDirectory(filepath);
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            outputStreamWriter.write(content, 0, content.length());
        } catch (FileNotFoundException e) {
            logger.error("",e);
        } catch (IOException e) {
            logger.error("",e);
        }finally {
            if( outputStreamWriter!=null ){
                try {
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
    }

    /**
     * 尾部增量更新
     * @param filepath
     * @param content
     */
    public static void appendFile(String filepath,String content){
        RandomAccessFile randomFile = null;
        try {
            checkAndSafeTheDirectory(filepath);
            // 打开一个随机访问文件流，按读写方式
            randomFile = new RandomAccessFile(filepath, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            content = new String(content.getBytes(), "utf-8");
            randomFile.write(content.getBytes());
        } catch (IOException e) {
            logger.error("",e);
        }finally {
            if( randomFile!= null ){
                try {
                    randomFile.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("c:/figo"+ UUID.randomUUID()+".txt");
        file.createNewFile();
    }
}
