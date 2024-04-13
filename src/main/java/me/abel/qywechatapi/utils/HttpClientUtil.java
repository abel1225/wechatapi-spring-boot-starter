/**
 *
 */
package me.abel.qywechatapi.utils;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.SerializableEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
*@description:
*@author: able.li
*@create: 2018/8/3 18:00
*/
public class HttpClientUtil {

    private final HttpClient httpClient;

    public HttpClientUtil(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Object postObject(String url, Serializable param) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        SerializableEntity params = new SerializableEntity(param, ContentType.create("application/x-java-serialized-object"));
        httpPost.addHeader("Content-Type", "application/x-java-serialized-object");
        httpPost.setEntity(params);

        return httpClient.execute(httpPost, response -> {
            int status = response.getCode();
            HttpEntity entity = response.getEntity();
            if (status >= 200 && status < 300) {
                byte[] result = EntityUtils.toByteArray(entity);
                try(ByteArrayInputStream bais1 = new ByteArrayInputStream(result);
                    ObjectInputStream ois1 = new ObjectInputStream(bais1)){
                    EntityUtils.consume(entity);
                    return ois1.readObject();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                EntityUtils.consume(entity);
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        });
    }

    public String postString(String url, String contentType) throws IOException {
        return postString(url, null, contentType);
    }

    public String postString(String url, String param, String contentType) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", contentType);
        headers.put("Connection", "close");
        return postStringWithHeader(url, param, headers);
    }

    public String postStringWithHeader(String url, String param, Map<String, String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (headers != null && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if(!"".equals(param)){
            StringEntity params = new StringEntity(param, ContentType.TEXT_PLAIN);
            httpPost.setEntity(params);
        }

        return httpClient.execute(httpPost, this::getResponse);
    }

    private String getString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return httpClient.execute(httpGet, this::getResponse);
    }

    /**
     *
     * @param url
     * @param params
     * @param attach
     * @param contentType
     * @return
     */
    public String postParamWithAttach(String url, Map<String, String> params, File attach, String contentType) throws IOException {
        CloseableHttpResponse response = null;
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        // 上传的文件
        if(attach != null){
            builder.addBinaryBody("attach", attach);
        }
        // 设置其他参数
        for(Entry<String, String> entry : params.entrySet()) {
            builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
        }

        HttpEntity httpEntity = builder.setMode(HttpMultipartMode.EXTENDED).build();
        httpPost.setEntity(httpEntity);

        return httpClient.execute(httpPost, this::getResponse);
    }

    private String getResponse(ClassicHttpResponse response) throws IOException, ParseException {
        int status = response.getCode();
        HttpEntity entity = response.getEntity();
        if (status >= 200 && status < 300) {
            String res = "";
            if(entity != null) {
                res = EntityUtils.toString(entity,"utf-8");
            }
            EntityUtils.consume(entity);
            return entity != null ? res : null;
        } else {
            EntityUtils.consume(entity);
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    }

    public String postJson(String url, String param) throws IOException {
        return postString(url, param, "application/json");
    }

    public String postXml(String url, String param) throws IOException {
        return postString(url, param, "text/xml");
    }

    public String getResponce(String url) throws IOException {
        return getString(url);
    }

    public String postXmlWithAttach(String url, Map<String, String> params, File attach) throws IOException {
        return postParamWithAttach(url, params, attach, "text/xml");
    }

    public String postJsonWithAttach(String url, Map<String, String> params, File attach) throws IOException {
        return postParamWithAttach(url, params, attach, "application/json");
    }

    public String postJsonWithMap(String url, Map<String, String> params) throws IOException {
        return postParamWithAttach(url, params, null, "application/json");
    }
}
