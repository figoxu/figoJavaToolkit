package me.figoxu.middleware.app.packet;

/**
 * Created by figo on 2015/6/15.
 */
public class SysException  extends Exception {
    private Integer code;          //ϵͳ�������
    private String msg;            //ϵͳ�����������
    public SysException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

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
