package com.learnredisfromscratch.repository.db;

import redis.clients.jedis.JedisPool;

public class JedisPoolFactory {

    private JedisPoolFactory() {}

    private static JedisPool jedisPool;

    public static JedisPool getJedisPool() {

        if (jedisPool != null) {
            return jedisPool;
        }

        synchronized (JedisPoolFactory.class) {

            if (jedisPool == null) {
                jedisPool = new JedisPool("localhost", 6379);
            }

            return jedisPool;
        }
    }
}
