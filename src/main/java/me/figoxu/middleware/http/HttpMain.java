package me.figoxu.middleware.http;

import me.figoxu.middleware.RetryExecutor;
import org.apache.http.Header;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: figo
 * Date: 15-5-14
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */
public class HttpMain {

    public static void main(String[] args) throws UnsupportedEncodingException {
        final String url = "http://localhost:8080/subscribe/payImg/upload.do";
        RequestItemAppender requestItemAppender = new RequestItemAppender();
        requestItemAppender.append("sid",new StringBody("102")).append("img",new FileBody(new File("D:\\figo\\project\\zfct\\workspace\\svn_all\\trunk\\zfct\\appServer\\web\\img\\adv\\adv1.jpg")));
        final MultipartEntity transform = requestItemAppender.transform();
        RetryExecutor retryExecutor = new RetryExecutor() {
            @Override
            public void doing() throws Exception {
                HttpHelper httpHelper = new HttpHelper();
                String val = httpHelper.post(transform, url, new Header[]{});
                System.out.println(val);
//                ResultJson resultJson = ResultJsonHelper.parse(val, LinkedList.class, ApiTimeVo.class);
            }
        };
        retryExecutor.execute(3);
    }
}
