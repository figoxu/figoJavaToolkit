package me.figoxu.middleware.http;

import org.apache.http.entity.mime.content.ContentBody;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-23
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public class RequestItem {
    private ContentBody body;
    private String key;

    public RequestItem(ContentBody body, String key) {
        this.body = body;
        this.key = key;
    }

    public ContentBody getBody() {
        return body;
    }

    public void setBody(ContentBody body) {
        this.body = body;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
