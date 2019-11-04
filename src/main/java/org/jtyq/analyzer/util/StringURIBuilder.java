package org.jtyq.analyzer.util;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author TYQ
 * @create 2018-04-08 11:01
 * @desc 生成URI
 **/
public class StringURIBuilder {

    private StringBuilder URI = new StringBuilder();

    public StringURIBuilder(String scheme, String host, String port) {
        URI.append(scheme);
        URI.append("://");
        URI.append(host);
        URI.append(":");
        URI.append(port);
    }
    public StringURIBuilder(String host, String port){
        URI.append("http");
        URI.append("://");
        URI.append(host);
        URI.append(":");
        URI.append(port);
    }

    public StringURIBuilder(String URIPrefix){
        URI.append(URIPrefix);
    }

    public StringURIBuilder appendPath(String path){
        if(path ==null){
            return this;
        }
        if(!path.startsWith("/")){
            URI.append("/").append(path);
        }else{
            URI.append(path);
        }
        return this;
    }

    public StringURIBuilder addParameter(String name, String value){
        if(!URI.toString().contains("?")){
            URI.append("?");
        }else{
            URI.append("&");
        }
        String encodeName = null;
        String encodeVal = null;
        try {
            encodeName= URLEncoder.encode(name, "UTF-8"); //ISO8859-1
            encodeVal = URLEncoder.encode(value,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        URI.append(encodeName).append("=").append(encodeVal);
        return this;
    }

    /**
     * 将URI编码
     * @return
     */
    public String getURI () {
        return URI.toString();
    }


}
