package org.jtyq.analyzer.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author TYQ
 * @create 2018-04-08 14:35
 * @desc http请求发送工具类
 **/
public class HttpRequestClient {

    //连接池最大连接数
    private static final int DEFAULT_MAX_CONNECTIONS = 1024;

    //连接成功后，读取数据超过指定的时间报错异常
    private static final int DEFAULT_SOCKET_TIMEOUT = 50 * 1000;

    //验证连接池中的有效的连接的间隔时间
    private static final int DEFAULT_VALIDATE_AFTER_INACTIVITY = 50 * 1000;

    //表示连接服务器等待的时间超出了指定的时间
    private static final int DEFAULT_CONNECT_TIMEOUT = 50 * 1000;

    //请求连接池中连接的所等待的时间
    private static final int DEFAULT_CONNECT_REQUEST_TIMEOUT = 50 * 1000;

    //连接池中回收空闲的连接的间隔时间
    private static final long DEFAULT_IDLE_CONNECTION_TIME = 2 * 60 * 1000;


    private static HttpClientConnectionManager connectionManager = createHttpClientConnectionManager();

    private RequestConfig requestConfig;

    private CloseableHttpClient client;

    public HttpRequestClient() {
        RequestConfig.Builder reqConfigBuilder = RequestConfig.custom();
        reqConfigBuilder.setConnectionRequestTimeout(DEFAULT_CONNECT_REQUEST_TIMEOUT);
        reqConfigBuilder.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT);
        reqConfigBuilder.setConnectTimeout(DEFAULT_CONNECT_TIMEOUT);
        this.requestConfig = reqConfigBuilder.build();
        this.client = createHttpClient(this.connectionManager);

    }

    private CloseableHttpClient createHttpClient(HttpClientConnectionManager connectionManager) {
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .disableContentCompression()
                .disableAutomaticRetries()
                .evictExpiredConnections()
                .evictIdleConnections(DEFAULT_IDLE_CONNECTION_TIME, TimeUnit.MILLISECONDS)
                .setUserAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36")
                .build();
    }

    private static HttpClientConnectionManager createHttpClientConnectionManager() {

        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslSocketFactory)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS);
        connectionManager.setMaxTotal(DEFAULT_MAX_CONNECTIONS);
        connectionManager.setValidateAfterInactivity(DEFAULT_VALIDATE_AFTER_INACTIVITY);
        connectionManager.setDefaultSocketConfig(SocketConfig.custom().
                setSoTimeout(DEFAULT_SOCKET_TIMEOUT).setTcpNoDelay(true).build());
        return connectionManager;

    }


    /**
     * 发送get请求
     *
     * @param urlPrefix 接口调用前缀
     * @param path      接口调用后缀
     * @param params    URL参数
     * @return 返回json格式的字符串
     */
    public String sendHttpGet(String urlPrefix, String path, Map<String, String> params) {
        String url = addUrlParameters(urlPrefix, path, params);
//        logger.info("请求调用地址："+url);
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        return execute(get);
    }

    public String sendHttpGet(String url) {
//        logger.info("请求调用地址："+url);
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        return execute(get);
    }

    /**
     * 拼接url参数
     *
     * @param urlPrefix   接口调用前缀
     * @param path        接口调用后缀
     * @param queryParams 查询参数
     * @return URI字符串
     */
    private String addUrlParameters(String urlPrefix, String path, Map<String, String> queryParams) {
        StringURIBuilder urlBuilder = new StringURIBuilder(urlPrefix);
        urlBuilder.appendPath(path);
        if (queryParams != null && !queryParams.isEmpty()) {
            Set<Map.Entry<String, String>> queryEntries = queryParams.entrySet();
            for (Map.Entry<String, String> entry : queryEntries) {
                String key = entry.getKey();
                String val = entry.getValue();
                urlBuilder.addParameter(key, val);
            }
        }
        return urlBuilder.getURI();
    }

    /**
     * 上传单个文件
     *
     * @param urlPrefix   接口调用前缀
     * @param path        接口调用后缀
     * @param queryParams 查询参数
     * @param params      表单域参数
     * @param file        文件
     * @param fileType    文件MIME类型
     * @return 返回json格式的字符串
     */
    public String uploadSingleFile(String urlPrefix, String path, Map<String, String> queryParams, Map<String, String> params, File file, ContentType fileType) {
        String url = addUrlParameters(urlPrefix, path, queryParams);
//        logger.info("请求调用地址："+url);
        HttpPost post = new HttpPost(url);
        MultipartEntityBuilder meb = MultipartEntityBuilder.create();
        FileBody fileBody = new FileBody(file, fileType);
        Set<Map.Entry<String, String>> entries = params.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            meb.addTextBody(entry.getKey(), entry.getValue());
        }
        meb.addPart("file", fileBody);
        HttpEntity entity = meb.build();
        post.setEntity(entity);
        post.setConfig(this.requestConfig);
        return execute(post);
    }

    /**
     * 发送POST请求
     *
     * @param urlPrefix  接口调用前缀
     * @param path       接口调用后缀
     * @param queryParam 查询参数
     * @param params     表单域参数
     * @return 返回json格式的字符串
     */
    public String sendHttpPost(String urlPrefix, String path, Map<String, String> queryParam, Map<String, String> params) {
        String url = addUrlParameters(urlPrefix, path, queryParam);
//        logger.info("请求调用地址："+url);
        List<NameValuePair> pairs = null;
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(params.size());
            for (String key : params.keySet()) {
                pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        if (pairs != null && pairs.size() > 0) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return execute(httpPost);
    }

    /**
     * 执行方法
     *
     * @param req
     * @return 返回json格式的字符串
     */
    private String execute(HttpUriRequest req) {
        HttpResponse response = null;
        String result = null;
        try {
            response = client.execute(req);
            HttpEntity resultEntity = response.getEntity();
            result = EntityUtils.toString(resultEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
