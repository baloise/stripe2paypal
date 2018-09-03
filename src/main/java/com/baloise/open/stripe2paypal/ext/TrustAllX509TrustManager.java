/**
 * 
 */
package com.baloise.open.stripe2paypal.ext;

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * DO NOT USE IN PRODUCTION!!!!
 * 
 * This class will simply trust everything that comes along.
 * 
 * @author frank
 * 
 * FROM: https://stackoverflow.com/questions/19723415/java-overriding-function-to-disable-ssl-certificate-check/19723687
 */
public class TrustAllX509TrustManager implements X509TrustManager {
    public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
}
