package me.figoxu.middleware.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: jianhui.xu
 * Date: 13-8-12
 * Time: 下午12:02
 * To change this template use File | Settings | File Templates.
 */
public class HttpHelper {
    public StringBody getStringBody(Object object) {
        if (object == null) {
            return null;
        }
        try {
            StringBody stringBody = new StringBody(object + "");
            return stringBody;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FileBody getFileBody(String url) {
        return getFileBody(new File(url));
    }

    public FileBody getFileBody(File file) {
        if (file != null) {
            FileBody fileBody = new FileBody(file);
            return fileBody;
        }
        return null;
    }

    public String post(String url,Header[] headers,RequestItem ... requestItems) throws IOException {
        MultipartEntity multipartEntity = new MultipartEntity();
        for(RequestItem requestItem:requestItems){
            multipartEntity.addPart(requestItem.getKey(),requestItem.getBody());
        }
        return post(multipartEntity,url,headers);
    }


    public String post(MultipartEntity reqEntity,String url,Header[] headers) throws IOException {
       return post(reqEntity, url, headers,false);
    }

    public String post(MultipartEntity reqEntity,String url,Header[] headers,boolean proxy) throws IOException {
        HttpPost httppost = new HttpPost(url);
          if(proxy){
              HttpHost proxyHost = new HttpHost("127.0.0.1", 8080, "http");
              httppost.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxyHost);
          }

        if(headers !=null){
            httppost.setHeaders(headers);
        }
        httppost.setEntity(reqEntity);
        System.out.println("执行: " + httppost.getRequestLine());
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httppost);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode is " + statusCode);
        HttpEntity resEntity = response.getEntity();
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        String result = null;
        if (resEntity != null) {
            System.out.println("返回长度: " + resEntity.getContentLength());
            System.out.println("返回类型: " + resEntity.getContentType());
            InputStream in = resEntity.getContent();
            result = inputStream2String(in);
            System.out.println("返回内容： " + result);
        }
        if(statusCode!=200){
            throw new IOException("请求失败");
        }
        if (resEntity != null) {
            resEntity.consumeContent();
        }
        return result;
    }


    public String inputStream2String (InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
