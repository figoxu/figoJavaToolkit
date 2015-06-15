package me.figoxu.middleware.message;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 *
 * User: figo.xu
 * Date: 12-11-7
 * Time: 上午9:45
 *
 */
public class MessageHelper {
    private static Logger logger = Logger.getLogger(MessageHelper.class);

    private static Properties props;

    static {
        String filename = "properties/message.properties";
        InputStream is = MessageHelper.class.getResourceAsStream("/" + filename);
        props = new Properties();
        try {
            props.load(is);
        } catch (Exception e) {
            logger.error("Cann't access the " + filename, e);
        }
    }

    public static String getProperty(String name) {
        String val = props.getProperty(name.trim());
        if (val == null) {
            return null;
        } else {
            //去除前后端空格
            return val.trim();
        }
    }

    public static String getProperty(String name, String defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            value = defaultValue;
        }
        return value.trim();
    }


    public static String getMessage(String key, Object[] array) {
        String message = getProperty(key);
        String value = MessageFormat.format(message, array);
        return value;
    }


    public static void main(String[] args) {
        String message = "{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}{12}{13}{14}{15}{16}";
        Object[] array = new Object[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"};
        String value = MessageFormat.format(message, array);
        logger.info(value);
        message = "Hello {0},How are you?I am {1},{2}";
        array = new Object[]{"Figo", "Fine", "And you ?"};
        value = MessageFormat.format(message, array);
        logger.info(value);
        array = new Object[]{"许建辉", "很好", "那你呢 ?"};
        value = MessageFormat.format(message, array);
        logger.info(value);
    }
}
