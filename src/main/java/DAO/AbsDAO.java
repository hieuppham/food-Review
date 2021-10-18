package DAO;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;


public abstract class AbsDAO {
    public static final Gson gson = new Gson();

    public static final Jedis getConnection() {
        return new Jedis("redis://default:1259pjQF8MZ1i8cvcahZXegQifZIN2Hy@redis-19316.c292.ap-southeast-1-1.ec2.cloud.redislabs.com:19316");
    }
}
