package com.expense_tracker.UserService.Deserialize;

import com.expense_tracker.UserService.Entity.UserInfo;
import org.apache.kafka.clients.consumer.internals.Deserializers;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.nio.ByteBuffer;
import java.util.Map;


public class UserInfoDeserializer implements Deserializer<UserInfo> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public UserInfo deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper =new ObjectMapper();
        UserInfo user =null;

        try {
            user =objectMapper.readValue(s,UserInfo.class);
        } catch (JacksonException e) {
            System.out.println("can Not serialized");
            throw new RuntimeException(e);

        }
        return  user;
    }

    @Override
    public UserInfo deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public UserInfo deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
