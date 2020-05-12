package com.gupao.prototype;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.*;
import java.util.Date;
import java.util.List;

@Data
public class User implements Cloneable, Serializable {
    private Integer id;

    private Date bir;

    private String name;

    private String addr;

    private List<String> hobbies;

    @Override
    protected User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    /**
     * 序列化实现深拷贝
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public User deepCloneSer() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream obj = new ObjectOutputStream(outputStream);
        obj.writeObject(this);
        ByteArrayInputStream in = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInput input = new ObjectInputStream(in);
        User user = (User) input.readObject();
        return user;

    }

    public static User deepFromJson(User user){
        String userStr = JSON.toJSONString(user);

        User parser = (User)JSON.parseObject(userStr,User.class);
        return  parser;
    }


}
