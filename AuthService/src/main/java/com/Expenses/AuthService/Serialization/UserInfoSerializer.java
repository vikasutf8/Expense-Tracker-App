package com.Expenses.AuthService.Serialization;

import com.Expenses.AuthService.Dto.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoDto> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }
//    src/main/java/com/Expenses/AuthService/Serialization/UserInfoSerializer.java
    @Override
    public byte[] serialize(String key, UserInfoDto userInfoDto) {
        byte[] retVal =null;
        ObjectMapper objectMapper =new ObjectMapper();
        try{
            retVal=objectMapper.writeValueAsString(key).getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, UserInfoDto data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
