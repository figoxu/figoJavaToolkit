package me.figoxu.middleware.app.packet;


import com.google.gson.*;
import me.figoxu.middleware.gson.GsonInstance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * User: figo.xu
 * Date: 13-5-20
 * Time: 下午2:26
 */
public class ResultJsonHelper {
    ResultJson resultJson = new ResultJson();

    public ResultJson toJsonObject() {
        return resultJson;
    }

    public ResultJsonHelper setStatus(boolean b) {
        resultJson.setStatus(b);
        return this;
    }

    public ResultJsonHelper setContent(Object content) {
        resultJson.setContent(content);
        return this;
    }

    public ResultJsonHelper addError(SysException sysException) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode(sysException.getCode());
        errorInfo.setMsg(sysException.getMsg());
        resultJson.getErrors().add(errorInfo);
        return this;
    }


    public static <T> ResultJson parse(String json, Class<T> contentClazz) {
        return parse(json, contentClazz, null);
    }

    /**
     * @param json
     * @param contentClazz
     * @param <T>
     * @return
     * @should test_with_clazz
     */
    public static <T> ResultJson parse(String json, Class<T> contentClazz,
                                       Class typeClazz) {
        ResultJson resultJson = new ResultJson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        Gson gson = GsonInstance.instance();
        JsonElement contentElement = jsonObject.get("content");
        // JsonReader jr = jsonObject.get
        if (contentElement != null) {

            // if(contentClazz typeof)
            // xxxxx
            // typeof
            /**
             * 处理基本数据类型
             */
            if (contentClazz.equals(String.class)) {
                String content = contentElement.getAsString();
                resultJson.setContent(content);
            } else if (contentClazz.equals(Long.class)) {
                Long content = contentElement.getAsLong();
                resultJson.setContent(content);
            } else if (contentClazz.equals(Integer.class)) {
                Integer content = contentElement.getAsInt();
                resultJson.setContent(content);
            } else if (contentClazz.equals(Boolean.class)) {
                Boolean content = contentElement.getAsBoolean();
                resultJson.setContent(content);
            } else if (contentClazz.equals(List.class)
                    || contentClazz.equals(LinkedList.class)) {

                if(contentElement.isJsonArray()){
                    JsonArray jsonArray = contentElement.getAsJsonArray();
                    if (typeClazz.equals(String.class)) {
                        List<String> elementList = new ArrayList<String>();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonElement jsonObj = jsonArray.get(i);
                            String entity = (String) gson.fromJson(jsonObj,
                                    typeClazz);
                            elementList.add(entity);

                        }
                        resultJson.setContent(elementList);
                    } else {
                        List<T> elementList = new ArrayList<T>();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonElement jsonObj = jsonArray.get(i);
                            T entity = (T) gson.fromJson(jsonObj, typeClazz);
                            elementList.add(entity);

                        }
                        resultJson.setContent(elementList);
                    }
                }

            } else {
                JsonObject contentObj = contentElement.getAsJsonObject();
                T content = gson.fromJson(contentObj, contentClazz);
                resultJson.setContent(content);
            }
        }
        JsonElement statusElement = jsonObject.get("status");
        if (statusElement != null) {
            resultJson.setStatus(statusElement.getAsBoolean());
        }

        JsonElement errorsElement = jsonObject.get("errors");
        if (errorsElement != null) {
            JsonArray jsonArray = errorsElement.getAsJsonArray();
            LinkedList<ErrorInfo> errors = new LinkedList<ErrorInfo>();
            if (jsonArray != null) {
                Iterator<JsonElement> elementIterator = jsonArray.iterator();
                while (elementIterator.hasNext()) {
                    JsonElement jsonElement = elementIterator.next();
                    ErrorInfo error = gson.fromJson(jsonElement,
                            ErrorInfo.class);
                    errors.add(error);
                }
            }
            resultJson.setErrors(errors);
        }
        return resultJson;
    }

    /**
     * @param json
     * @param <T>
     * @return
     * @should test
     */
    public static <T> ResultJson parse(String json) {
        ResultJson resultJson = GsonInstance.instance().fromJson(json,
                ResultJson.class);
        return resultJson;
    }
}
