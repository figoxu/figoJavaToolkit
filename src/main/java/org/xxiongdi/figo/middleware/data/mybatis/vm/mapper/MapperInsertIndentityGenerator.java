package org.xxiongdi.figo.middleware.data.mybatis.vm.mapper;

import org.xxiongdi.figo.common.domain.User;
import org.xxiongdi.figo.middleware.data.mybatis.MyBatisNameHelper;
import org.xxiongdi.figo.middleware.template.velocity.VelocityUtil;

import java.util.HashMap;

/**
 * .
 * User: figo.xu
 * Date: 12-9-25
 * Time: 下午2:23
 *
 */
public class MapperInsertIndentityGenerator extends BaseMapperGenerator {
    public static String getDefaultSqlName(Class clazz){
        String sqlName = "insert"+clazz.getSimpleName();
        return sqlName;
    }

    public static String gensql(Class clazz) throws Exception {
        String template = "/org/xxiongdi/figo/middleware/data/mybatis/vm/mapper/mapper_insert_identity.vm";
        String tableName = MyBatisNameHelper.getTableName(clazz);
        String sqlName = getDefaultSqlName(clazz);
        String javaType = clazz.getSimpleName();
        String columnKeyValues = MyBatisNameHelper.getColumnKeyValues(clazz);
        String columnNames = MyBatisNameHelper.getColumnNamesWithOutId(clazz);
        String columnValues = MyBatisNameHelper.getColumnValuesWithOutId(clazz);
        HashMap templateData = new HashMap();
        templateData.put("tableName",tableName);
        templateData.put("sqlName",sqlName);
        templateData.put("javaType",javaType);
        templateData.put("columnKeyValues",columnKeyValues);
        templateData.put("columnNames",columnNames);
        templateData.put("columnValues",columnValues);
        commonParamSetting(templateData);
        String val = VelocityUtil.render(template, templateData);
        return val;
    }

    public static void main(String[] args) throws Exception {
        String sql = gensql(User.class);
        System.out.println(sql);
    }
}
