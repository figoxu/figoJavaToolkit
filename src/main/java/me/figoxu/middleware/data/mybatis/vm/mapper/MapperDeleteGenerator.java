package me.figoxu.middleware.data.mybatis.vm.mapper;


import me.figoxu.middleware.template.velocity.VelocityUtil;
import me.figoxu.middleware.data.mybatis.MyBatisNameHelper;

import java.util.HashMap;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午9:43
 *
 */
public class MapperDeleteGenerator extends BaseMapperGenerator {
    public static String getDefaultSqlName(Class clazz){
        String sqlName = "delete"+clazz.getSimpleName();
        return sqlName;
    }

    public static String gensql(Class clazz) throws Exception {
        String template = "/me/figoxu/middleware/data/mybatis/vm/mapper/mapper_delete.vm";
        String tableName = MyBatisNameHelper.getTableName(clazz);
        String sqlName = getDefaultSqlName(clazz);
        HashMap templateData = new HashMap();
        templateData.put("tableName", tableName);
        templateData.put("sqlName", sqlName);

        commonParamSetting(templateData);
        String val = VelocityUtil.render(template, templateData);
        return val;
    }


    public static void main(String[] args) throws Exception {
//        String sql = gensql(User.class);
//        System.out.println(sql);
    }
}
