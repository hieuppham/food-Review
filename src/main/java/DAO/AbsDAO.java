package DAO;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;


public abstract class AbsDAO {
    public static final Gson gson = new Gson();

    public static final Jedis getConnection() {
        return new Jedis(System.getenv().get("REDIS_CLOUD"));
    }
}
