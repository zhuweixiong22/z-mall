package com.wyu.zmall.util;

import com.wyu.zmall.bo.PageCounter;

import java.util.Date;

/**
 * @author zwx
 * @date 2022-07-04 20:07
 */
public class CommonUtil {
    public static PageCounter PageParamConverter(Integer start, Integer count) {
        int pageNum = start / count;
        return new PageCounter(pageNum, count);
    }

    /**
     * 判断领取优惠券时间是否合法
     * @param now
     * @param start
     * @param end
     * @return
     */
    public static boolean isValidTime(Date now, Date start, Date end) {
        long nowTime = now.getTime();
        long startTime = start.getTime();
        long endTime = end.getTime();
        return startTime < nowTime && nowTime < endTime;
    }
 }
