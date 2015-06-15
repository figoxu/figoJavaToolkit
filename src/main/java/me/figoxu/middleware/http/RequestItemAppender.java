package me.figoxu.middleware.http;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-23
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class RequestItemAppender {
    List<RequestItem> requestItemList = new ArrayList<RequestItem>();
    HttpHelper httpHelper = new HttpHelper();

    public List<RequestItem> getRequestItemList() {
        return requestItemList;
    }

    public void setRequestItemList(List<RequestItem> requestItemList) {
        this.requestItemList = requestItemList;
    }

    public RequestItemAppender append(String key, ContentBody body) {
        RequestItem requestItem = new RequestItem(body, key);
        requestItemList.add(requestItem);
        return this;
    }
    public RequestItemAppender append(String key, String body) {
        RequestItem requestItem = new RequestItem(httpHelper.getStringBody(body), key);
        requestItemList.add(requestItem);
        return this;
    }


    public MultipartEntity transform() {
        MultipartEntity multipartEntity = new MultipartEntity();
        for (RequestItem requestItem : requestItemList) {
            ContentBody body = requestItem.getBody();
            if(body==null){
                System.out.println("================");
                System.out.println("@key:"+requestItem.getKey()+" is null");
                System.out.println("================");
                continue;
            }
            multipartEntity.addPart(requestItem.getKey(), body);
        }
        return multipartEntity;
    }
}
