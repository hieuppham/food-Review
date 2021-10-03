package DAO;
import com.google.gson.Gson;
import model.Geo;
import redis.clients.jedis.Jedis;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public abstract class AbsDAO {
    public static final Gson gson = new Gson();
    public static Jedis getConnection() {
        try {
            TrustManager bogusTrustManager = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{bogusTrustManager}, new java.security.SecureRandom());

            HostnameVerifier bogusHostnameVerifier = (hostname, session) -> true;

            return new Jedis(URI.create("rediss://:pd4a7cc0bcc9b3fdd2b263b0ed11cb2aacf0877309f0b6efbf727d8f5aaed3b6c@ec2-34-239-208-3.compute-1.amazonaws.com:10810"),
                    sslContext.getSocketFactory(),
                    sslContext.getDefaultSSLParameters(),
                    bogusHostnameVerifier);

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Cannot obtain Redis connection!", e);
        }
    }
}
