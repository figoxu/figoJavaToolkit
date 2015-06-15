package me.figoxu.middleware;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jianhui.xu
 * Date: 13-8-12
 * Time: 下午12:02
 * To change this template use File | Settings | File Templates.
 */
public class HttpHelper {
    public String post(String url,List<BasicHeader> headerList,MultipartEntity entity) throws IOException {
        HttpPost httppost = new HttpPost(url);
        for(BasicHeader header:headerList) {
            httppost.addHeader(header);
        }
        httppost.setEntity(entity);
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

    public String post(String url) throws IOException {
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(new MultipartEntity());
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
