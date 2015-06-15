package me.figoxu.middleware.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: figo
 * Date: 15-5-7
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
public class FileByteHelper {
    public static byte[] getBytesFromFile(File file) {
        if (file == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int readCount = 0;
            for (int n; (n = stream.read(b)) != -1; ) {
                out.write(b, 0, n);
                readCount++;
            }
            System.out.println(readCount);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
