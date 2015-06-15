package me.figoxu.middleware.app.packet;

import java.io.Serializable;

/**
 *
 * User: figo.xu
 * Date: 13-5-24
 * Time: 上午11:27
 *
 */
public class ErrorInfo  implements Serializable {
    private Integer code;     //错误编码
    private String msg;       //错误描述

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
