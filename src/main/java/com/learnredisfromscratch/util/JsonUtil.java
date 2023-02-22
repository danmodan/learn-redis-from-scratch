package com.learnredisfromscratch.util;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private JsonUtil() {
    }

    private static ObjectMapper objectMapper;

    public static ObjectMapper getObjectMapper() {

        if (objectMapper != null) {
            return objectMapper;
        }

        synchronized (JsonUtil.class) {

            if (objectMapper == null) {
                objectMapper = new ObjectMapper();
            }

            return objectMapper;
        }
    }

    public static <T> List<T> readerForListOf(Class<T> clazz, String value) {

        try {

            return getObjectMapper()
                    .readerForListOf(clazz)
                    .readValue(value);

        } catch (Exception e) {
            throw new RuntimeException(String.format("%s - %s", clazz.getName(), value), e);
        }
    }

    public static byte[] writeObject(Object object) {

        try {

            return getObjectMapper()
                .writeValueAsBytes(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}