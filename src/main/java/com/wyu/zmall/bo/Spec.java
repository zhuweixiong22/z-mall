package com.wyu.zmall.bo;

/**
 * 为了返回Spec返回到前端的是json格式而不是字符串，所以定义一个Spec业务对象，该对象不对应数据库的一张表
 * 字符串形式："specs": "[{\"key\": \"颜色\", \"value\": \"青蓝色\", \"key_id\": 1, \"value_id\": 1}, {\"key\": \"尺寸\", \"value\": \"7英寸\", \"key_id\": 2, \"value_id\": 5}]"
 * @author zwx
 * @date 2022-07-10 14:51
 */
public class Spec {

    private String key;

    private Long keyId;

    private String value;

    private Long valueId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getValueId() {
        return valueId;
    }

    public void setValueId(Long valueId) {
        this.valueId = valueId;
    }
}
