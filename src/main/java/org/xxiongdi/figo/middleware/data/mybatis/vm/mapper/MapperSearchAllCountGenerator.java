package org.xxiongdi.figo.middleware.data.mybatis.vm.mapper;

import org.xxiongdi.figo.common.domain.User;
import org.xxiongdi.figo.middleware.data.mybatis.MyBatisNameHelper;
import org.xxiongdi.figo.middleware.template.velocity.VelocityUtil;

import java.util.HashMap;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午10:15
 *
 */
public class MapperSearchAllCountGenerator extends BaseMapperGenerator {
    public static String getDefaultSqlName(Class clazz){
        String sqlName = "search"+clazz.getSimpleName()+"AllCount";
        return sqlName;
    }


    public static String gensql(Class clazz) throws Exception {
        String template = "/org/xxiongdi/figo/middleware/data/mybatis/vm/mapper/mapper_search_all_count.vm";
        String tableName = MyBatisNameHelper.getTableName(clazz);
        String sqlName = getDefaultSqlName(clazz);
        String javaType = clazz.getSimpleName();
        String columnNames = MyBatisNameHelper.getColumnNames(clazz);
        HashMap templateData = new HashMap();
        templateData.put("tableName",tableName);
        templateData.put("sqlName",sqlName);
        templateData.put("javaType",javaType);
        templateData.put("columnNames",columnNames);
        commonParamSetting(templateData);
        String val = VelocityUtil.render(template, templateData);
        return val;
    }

    public static void main(String[] args) throws Exception {
        String sql = gensql(User.class);
        System.out.println(sql);
    }
}
