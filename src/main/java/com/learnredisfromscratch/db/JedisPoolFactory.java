package com.learnredisfromscratch.db;

import redis.clients.jedis.JedisPool;

class JedisPoolFactory {

    private JedisPoolFactory() {}

    private static JedisPool jedisPool;

    static JedisPool getJedisPool() {

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
