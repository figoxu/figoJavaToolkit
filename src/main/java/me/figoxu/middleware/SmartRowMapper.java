package me.figoxu.middleware;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: figo
 * Date: 15-1-8
 * Time: 下午12:34
 * To change this template use File | Settings | File Templates.
 */

public class SmartRowMapper<T> implements RowMapper<T> {
    BeanPropertyRowMapper<T> beanPropertyRowMapper = null;

    public SmartRowMapper(Class<T> tClass){
        beanPropertyRowMapper = BeanPropertyRowMapper.newInstance(tClass);
        //beanPropertyRowMapper最好能加上反射的缓存，以便性能的提升
    }



    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return beanPropertyRowMapper.mapRow(rs,rowNum);
    }

}
