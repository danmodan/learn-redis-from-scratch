package com.learnredisfromscratch.repository.db;

import java.util.Collection;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisDbResource implements DbResource {

    private final JedisPool jedisPool;

    public JedisDbResource(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public List<String> getSubList(String listName, int start, int size) {

        try (Jedis jedis = jedisPool.getResource()) {

            int stop = start + size - 1;
            return jedis.lrange(listName, start, stop);
        }
    }

    @Override
    public List<String> getAllList(String listName) {

        try (Jedis jedis = jedisPool.getResource()) {

            return jedis.lrange(listName, 0, -1);
        }
    }

    @Override
    public void addItens(String listName, String... itens) {

        try (Jedis jedis = jedisPool.getResource()) {

            jedis.rpush(listName, itens);
        }
    }
}
