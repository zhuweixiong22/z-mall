package com.wyu.zmall.optional;


import org.junit.Test;

import java.util.Optional;

/**
 * @author zwx
 * @date 2022-07-11 15:28
 */
public class OptionalTest {
    @Test
    public void testOptional() {
        System.out.println("hello");
        Optional<String> empty = Optional.empty();
        Optional<String> t1 = Optional.of("hello");
        Optional<String> t2 = Optional.ofNullable(null);
        t1.get();
        t2.ifPresent(t -> System.out.println("有值 " + t));
        String s = t2.orElse("默认值");
        System.out.println(s);
    }
}
