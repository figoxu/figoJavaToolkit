package me.figoxu.middleware.data.mybatis.vm.mapper;

import java.util.HashMap;

/**
 * Created by figoxu on 15/4/13.
 */
public class BaseMapperGenerator {
    protected static void commonParamSetting(HashMap templateData) {
        templateData.put("idParam","#{id}");
        templateData.put("startParam","#{start}");
        templateData.put("limitParam","#{limit}");
        //todo 后面可以更改到一个properties里面，自动读取所有到key，当成默认参数，设置进去
    }
}
