package me.figoxu.middleware.app.packet;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * User: figo.xu
 * Date: 13-5-20
 * Time: 下午2:26
 *
 */
public class ResultJson  implements Serializable {
    private Boolean status;                                             //状态 true:接口请求成功     false:接口请求失败
    private LinkedList<ErrorInfo> errors = new LinkedList<ErrorInfo>();     //接口请求失败的异常码
    private Object content;                                          //接口返回的对象

    /**
     * @should test_json
     * @return
     */
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LinkedList<ErrorInfo> getErrors() {
        return errors;
    }

    public void setErrors(LinkedList<ErrorInfo> errors) {
        this.errors = errors;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

}
