package com.insigma.http;

import java.io.IOException;
import java.net.URLDecoder;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpRequestUtils {
    private static Log log = LogFactory.getLog(HttpRequestUtils.class);    //��־��¼
 
    /**
     * httpPost
     * @param url  ·��
     * @param jsonParam ����
     * @return
     */
    public static String httpPost(String url,JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }
 
    /**
     * post����
     * @param url         url��ַ
     * @param jsonParam     ����
     * @param noNeedResponse    ����Ҫ���ؽ��
     * @return
     */
    public static String httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
        //post���󷵻ؽ��
    	DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        String str = "";
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //���������������
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**�����ͳɹ������õ���Ӧ**/
            if (result.getStatusLine().getStatusCode() == 200) {
               
                try {
                    /**��ȡ���������ع�����json�ַ�������**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**��json�ַ���ת����json����**/
                    //jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    log.error("post�����ύʧ��:" + url, e);
                }
            }
        } catch (IOException e) {
            log.error("post�����ύʧ��:" + url, e);
        }
        return str;
    }
 
 
    /**
     * ����get����
     * @param url    ·��
     * @return
     */
    public static String httpGet(String url){
        //get���󷵻ؽ��
        JSONObject jsonResult = null;
        String strResult="";
        try {
        	DefaultHttpClient client = new DefaultHttpClient();
            //����get����
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
 
            /**�����ͳɹ������õ���Ӧ**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**��ȡ���������ع�����json�ַ�������**/
                 strResult = EntityUtils.toString(response.getEntity());
                /**��json�ַ���ת����json����**/
                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                log.error("get�����ύʧ��:" + url);
            }
        } catch (IOException e) {
        	log.error("get�����ύʧ��:" + url, e);
        }
        return strResult;
    }
}
