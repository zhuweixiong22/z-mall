package com.wyu.zmall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zwx
 * @date 2022-07-10 21:32
 */
@Component
public class GenericAndJsonConverter {

    // 直接对静态变量进行依赖注入是不合理的
    private static ObjectMapper mapper;

    // 对静态变量进行依赖注入，因为方法不是static的，所以可以通过setter方法注入
    @Autowired
    public void setMapper(ObjectMapper mapper) {
        GenericAndJsonConverter.mapper = mapper;
    }

    public static <T> String objectToJson(T obj) {
        try {
            return GenericAndJsonConverter.mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // json转单体 其实这个方法可以不需要，下面的jsonToObject也能做
    public static <T> T jsonToObject(String s, Class<T> classT) {
        if (s == null) {
            return null;
        }
        try {
            T t = GenericAndJsonConverter.mapper.readValue(s, classT);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 由于Java泛型的缺陷导致反序列化是比较麻烦的
    // 第一种思路是将List<K>整体当做一个泛型T，但是我们从Sku.Spec的getter方法中直接传List<Spec>.class是不行的，是一种语法错误，java没有这种写法。
    // 而readValue还有一种重载方法是传入jackson的抽象类TypeReference<T>，它也是一种泛型，只需要在setter方法中传入抽象类的匿名实现
    public static <T> T jsonToObject(String s, TypeReference<T> typeT) {
        if (s == null) {
            return null;
        }
        try {
            T t =  GenericAndJsonConverter.mapper.readValue(s, typeT);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

/*    // 第二种思路是将List<T>里面是泛型，这种的不行的实际上最后反序列化出来的list是一个HashMap类型，并没有帮我们转成Spec
    public static <T> List<T> jsonToList(String s) {
        if (s == null) {
            return null;
        }
        try {
            List<T> list =  GenericAndJsonConverter.mapper.readValue(s, new TypeReference<List<T>>() {
            });
            return list;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }*/
}
