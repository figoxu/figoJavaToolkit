package org.xxiongdi.figo.common.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;
import org.xxiongdi.figo.CodeConstants;
import org.xxiongdi.figo.middleware.data.mybatis.vm.mapper.MapperDeleteGenerator;
import org.xxiongdi.figo.middleware.data.mybatis.vm.mapper.MapperInsertGenerator;
import org.xxiongdi.figo.middleware.data.mybatis.vm.mapper.MapperSearchAllCountGenerator;
import org.xxiongdi.figo.middleware.data.mybatis.vm.mapper.MapperSearchAllGenerator;
import org.xxiongdi.figo.middleware.data.mybatis.vm.mapper.MapperSearchAllPagingGenerator;
import org.xxiongdi.figo.middleware.data.mybatis.vm.mapper.MapperSearchByKeyGenerator;
import org.xxiongdi.figo.middleware.data.mybatis.vm.mapper.MapperUpdateGenerator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * .
 * User: figo
 * Date: 12-8-11
 * Time: 上午10:23
 *
 */
@Component
public class MyBatisBaseDAO<T> extends SqlSessionDaoSupport {
    private Class<T> persistentClass = null;


    static String jdbcManagerPackage = CodeConstants.codeProperty.getProperty(CodeConstants.code_jdbcManagerPackage);
    protected String getPrefixMapperName(){
        String simpleName = this.getPersistentClass().getSimpleName();
        String mapperName = jdbcManagerPackage+"."+simpleName+"Mapper";
        return mapperName;
    }

    protected String wrapSqlName(String sqlName){
        String prefixMapperName = getPrefixMapperName();
        if(sqlName.startsWith(prefixMapperName)){
            return sqlName;
        }
        return prefixMapperName +"."+sqlName;
    }

    protected Type[] getActualTypeArguments() {
        Type genericSuperClass = getClass().getGenericSuperclass();

        // Get the generic super class of the super class if it's a cglib proxy
        if (getClass().getName().contains("$$EnhancerByCGLIB$$")) {
            genericSuperClass = getClass().getSuperclass().getGenericSuperclass();
        }

        return ((ParameterizedType) genericSuperClass).getActualTypeArguments();
    }

    protected Class<T> getPersistentClass() {
        if (this.persistentClass == null) {
            Type[] actualTypeArguments = getActualTypeArguments();
            this.persistentClass = (Class<T>) actualTypeArguments[0];
        }
        return this.persistentClass;
    }

    public void batchUpdate(String sqlName,List entityList,SqlSessionFactory sqlSessionFactory){
        if(entityList.size()<=0){
            return ;
        }
        sqlName=wrapSqlName(sqlName);
        SqlSession session = null;
        try {
            session =  sqlSessionFactory.openSession(ExecutorType.BATCH);
            int index = 0;
            int i=0;
            for(Object entity:entityList){
                 session.update(sqlName,entity);
                i++;
                if(i%50==0){
                    session.commit();
                }
            }
            session.commit();
        } catch (Exception e) {
            logger.error("",e);
        }finally {
            if( session != null ){
                session.close();
            }
        }
    }


    public int[] batchInsert(List<T> entityList,SqlSessionFactory sqlSessionFactory) {
        if(entityList.size()<=0){
            return new int[]{};
        }
        String sqlName = MapperInsertGenerator.getDefaultSqlName(getPersistentClass());
        sqlName=wrapSqlName(sqlName);
        int[] idArr = new int[entityList.size()];
        SqlSession session = null;
        try {
            session =  sqlSessionFactory.openSession(ExecutorType.BATCH);
            int index = 0;
            int i=0;
            for(T entity:entityList){
                idArr[index++] = session.update(sqlName,entity);
                i++;
                if(i%50==0){
                    session.commit();
                }
            }
            session.commit();
        } catch (Exception e) {
            logger.error("",e);
        }finally {
            if( session != null ){
                session.close();
            }
        }
        return idArr;
    }

    public void executeInsert(T entity){
       String sqlName = MapperInsertGenerator.getDefaultSqlName(getPersistentClass());
        sqlName=wrapSqlName(sqlName);
        getSqlSession().insert(sqlName,entity);
    }

    public void executeUpdate(T entity){
        String sqlName = MapperUpdateGenerator.getDefaultSqlName(getPersistentClass());
        sqlName=wrapSqlName(sqlName);
        getSqlSession().update(sqlName,entity);
    }

    public void executeDelete(Long id){
        String sqlName = MapperDeleteGenerator.getDefaultSqlName(getPersistentClass());
        sqlName=wrapSqlName(sqlName);
        HashMap parameter = new HashMap();
        parameter.put("id",id);
        getSqlSession().delete(sqlName, parameter);
    }

    public T searchByKey(String id){
        String sqlName = MapperSearchByKeyGenerator.getDefaultSqlName(getPersistentClass());
        sqlName=wrapSqlName(sqlName);
        HashMap parameter = new HashMap();
        parameter.put("id",id);
        T entity = (T) getSqlSession().selectOne(sqlName,parameter);
        return entity;
    }

    public List<T> searchAll(){
        String sqlName = MapperSearchAllGenerator.getDefaultSqlName(getPersistentClass());
        sqlName=wrapSqlName(sqlName);
        List<T> entities = this.getSqlSession().selectList(sqlName);
        return entities;
    }

    public Integer searchAllCount(){
        String sqlName = MapperSearchAllCountGenerator.getDefaultSqlName(getPersistentClass());
        sqlName=wrapSqlName(sqlName);
        Integer count = (Integer) this.getSqlSession().selectOne(sqlName);
        return count;
    }

    public List<T> searchAllPaging(int start,int limit){
        String sqlName = MapperSearchAllPagingGenerator.getDefaultSqlName(getPersistentClass());
        sqlName=wrapSqlName(sqlName);
        HashMap parameter = new HashMap();
        parameter.put("start",start);
        parameter.put("limit",limit);
        return this.getSqlSession().selectList(sqlName,parameter);
    }


}
