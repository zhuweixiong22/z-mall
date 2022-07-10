package com.wyu.zmall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * 希望jpa替我们调用这个类完成特殊类型的统一的序列化和反序列化，而不是需要我们每次都要在特殊类型的entity中getter和setter方法里自己进行序列化/反序列化。
 * 这个类需要实现AttributeConverter<X, Y>接口
 * 第一个泛型X是希望Jpa处理我们java entity中的复杂类型，第二个泛型Y是数据库的类型（Json也就是String）
 *
 * @author zwx
 * @date 2022-07-10 15:11
 */
@Converter
public class MapAndJsonConverter implements AttributeConverter<Map<String, Object>, String> {

    // 注入内置的jackson的mapper对象
    @Autowired
    private ObjectMapper mapper;

    /**
     * 序列化
     *
     * @param stringObjectMap
     * @return
     */
    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {
        // IO异常需要手动try catch
        try {
            return mapper.writeValueAsString(stringObjectMap);
        } catch (JsonProcessingException e) {
            // 这种io异常详细信息不太需要返回给前端 一般是记录日志
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 反序列化
     *
     * @param s
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            // 第一个参数字符串，第二个参数反序列化的原类，用HashMap，因为Map只是一个接口
            return mapper.readValue(s, HashMap.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
