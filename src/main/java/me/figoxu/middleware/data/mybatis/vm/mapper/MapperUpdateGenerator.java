package me.figoxu.middleware.data.mybatis.vm.mapper;

import me.figoxu.common.domain.User;
import me.figoxu.middleware.data.mybatis.MyBatisNameHelper;
import me.figoxu.middleware.template.velocity.VelocityUtil;

import java.util.HashMap;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午10:15
 *
 */
public class MapperUpdateGenerator extends BaseMapperGenerator {
    public static String getDefaultSqlName(Class clazz){
        String sqlName = "update"+clazz.getSimpleName();
        return sqlName;
    }
    public static String gensql(Class clazz) throws Exception {
        String template = "/me/figoxu/middleware/data/mybatis/vm/mapper/mapper_update.vm";
        String tableName = MyBatisNameHelper.getTableName(clazz);
        String sqlName = getDefaultSqlName(clazz);
        String columnKeyValues = MyBatisNameHelper.getColumnKeyValues(clazz);
        HashMap templateData = new HashMap();
        templateData.put("tableName",tableName);
        templateData.put("sqlName",sqlName);
        templateData.put("columnKeyValues",columnKeyValues);
        commonParamSetting(templateData);
        String val = VelocityUtil.render(template, templateData);
        return val;
    }

    public static void main(String[] args) throws Exception {
        String sql = gensql(User.class);
        System.out.println(sql);
    }
}
