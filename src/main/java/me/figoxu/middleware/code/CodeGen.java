package me.figoxu.middleware.code;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: figoMac
 * Date: 14-11-9
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 */
public class CodeGen {
    private static Logger logger = Logger.getLogger(CodeGen.class);

    public static void main(String[] args)  {
        GenDBManager.generatorAll();
        GenSpringDBConf.generatorSpringDB_HibernateConf();
        GenIBatisXmlContent.generatorAll();

}


}
