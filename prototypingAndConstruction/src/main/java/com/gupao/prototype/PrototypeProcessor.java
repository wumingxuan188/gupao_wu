package com.gupao.prototype;

import com.alibaba.fastjson.JSON;

/**
 * deep接口实现类
 */
public class PrototypeProcessor implements IDeepClone {
    public <T> T deepClone(T obj) {
        String userStr = JSON.toJSONString(obj);
        return (T) JSON.parseObject(userStr,User.class);
    }
}
