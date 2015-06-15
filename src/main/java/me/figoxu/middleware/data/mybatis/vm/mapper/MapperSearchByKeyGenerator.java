package me.figoxu.middleware.data.mybatis.vm.mapper;

import me.figoxu.common.domain.User;
import me.figoxu.middleware.data.mybatis.MyBatisNameHelper;
import me.figoxu.middleware.template.velocity.VelocityUtil;

import java.util.HashMap;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午9:43
 *
 */
public class MapperSearchByKeyGenerator extends BaseMapperGenerator {
    public static String getDefaultSqlName(Class clazz) {
        String sqlName = "search" + clazz.getSimpleName() + "ByKey";
        return sqlName;
    }

    public static String gensql(Class clazz) throws Exception {
        String template = "/me/figoxu/middleware/data/mybatis/vm/mapper/mapper_search_by_key.vm";
        String tableName = MyBatisNameHelper.getTableName(clazz);
        String sqlName = getDefaultSqlName(clazz);
        HashMap templateData = new HashMap();

        String javaType = clazz.getSimpleName();
        String columnNames = MyBatisNameHelper.getColumnNames(clazz);

        templateData.put("columnNames", columnNames);
        templateData.put("javaType", javaType);
        templateData.put("tableName", tableName);
        templateData.put("sqlName", sqlName);
        commonParamSetting(templateData);
        String val = VelocityUtil.render(template, templateData);
        return val;
    }

    public static void main(String[] args) throws Exception {
        String sql = gensql(User.class);
        System.out.println(sql);
    }
}
