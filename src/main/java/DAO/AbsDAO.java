package DAO;
import com.google.gson.Gson;
import model.Geo;
import redis.clients.jedis.Jedis;

public abstract class AbsDAO {
    public static final Gson gson = new Gson();
    public static Jedis getConnection() {
        return new Jedis("redis://127.0.0.1:6379");
    }
}
