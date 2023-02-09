package com.learnredisfromscratch.db;

import java.util.function.Consumer;

import redis.clients.jedis.Jedis;

public class JediClient {

    public void onJedis(Consumer<Jedis> onJedis) {

        try(Jedis jedis = JedisPoolFactory.getJedisPool().getResource()) {

            onJedis.accept(jedis);
        }
    }
}
