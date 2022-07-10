package com.wyu.zmall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 面向对象 提取基类
 * 该基类独立存在的意义不大 所以可以标注为抽象类 抽象类不被继承的话意义是不大的
 * 抽取出基类之后的一个问题 查询数据返回的这三个字段为null，但是数据库是有值的
 * 解决方法 @MappedSuperclass声明这是一个Entry的基类 与数据库映射
 *
 * @author zwx
 * @date 2022-07-03 21:40
 */
@MappedSuperclass
@Data
public abstract class BaseEntry {
    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;

    @JsonIgnore
    private Date deleteTime;
}
