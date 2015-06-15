package org.xxiongdi.figo.middleware.template.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.AbstractContext;
import org.xxiongdi.figo.CodeConstants;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午4:37
 *
 */
public class VelocityUtil {
    public static boolean isInit = false;

    public static void init() {
        if (!isInit) {
            String dir = CodeConstants.codeProperty.getProperty(CodeConstants.code_baseResourcePath);
            Velocity.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, dir);
            Velocity.setProperty(Velocity.INPUT_ENCODING,"UTF-8");
            Velocity.setProperty(Velocity.OUTPUT_ENCODING,"UTF-8");
            Velocity.setProperty(Velocity.ENCODING_DEFAULT,"UTF-8");
            try {
                Velocity.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
            isInit = true;
        }
    }

    public static VelocityEngine getEngine() {
        if (!isInit) {
            init();
        }
        VelocityEngine velocityEngine = new VelocityEngine();
        return velocityEngine;
    }

    public static VelocityEngine getEngine(String propertiesPath) throws Exception {
        VelocityEngine velocityEngine = getEngine();
        VelocityEngine ve = new VelocityEngine();
        InputStream inputStream = VelocityUtil.class.getClassLoader().getResourceAsStream(propertiesPath);
        Properties properties = new Properties();
        properties.load(inputStream);
        ve.init(properties);
        return velocityEngine;
    }

    public static String render(String templateClassPath, Map model) throws Exception {
        init();
        VelocityContext velocityContext = new VelocityContext(model);
        StringWriter result = new StringWriter();
        Template template = Velocity.getTemplate(templateClassPath);
        template.merge(velocityContext, result);
        return result.toString();
    }

    public static String render(String templateClassPath, AbstractContext context) throws Exception {
        init();
        StringWriter result = new StringWriter();
        Template template = Velocity.getTemplate(templateClassPath);
        template.merge(context, result);
        return result.toString();
    }

}
