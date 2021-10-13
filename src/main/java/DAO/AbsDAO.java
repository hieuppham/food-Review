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
    public static final Jedis getConnection(){
        return new Jedis("redis://default:K8EGW3Ryk2LG8TS0BcjsLZi8d6dOPNcL@redis-15847.c292.ap-southeast-1-1.ec2.cloud.redislabs.com:15847");
    }
}
