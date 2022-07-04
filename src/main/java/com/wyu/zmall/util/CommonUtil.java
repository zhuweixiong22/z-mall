package com.wyu.zmall.util;

import com.wyu.zmall.bo.PageCounter;

/**
 * @author zwx
 * @date 2022-07-04 20:07
 */
public class CommonUtil {
    public static PageCounter PageParamConverter(Integer start, Integer count) {
        int pageNum = start / count;
        return new PageCounter(pageNum, count);
    }

}
