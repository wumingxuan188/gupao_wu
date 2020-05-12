package com.gupao.prototype;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        User user = new User();

        user.setAddr("北京");
        user.setId(1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,1995);
       // calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_YEAR,1);
        List<String> list=new ArrayList<String>();

        list.add("读书");
        list.add("学习");
        list.add("上班");

        user.setHobbies(list);

        user.setBir(calendar.getTime());
        user.setName("Xuan");

        System.err.println(user);
        //浅复制
        User user1 = user.clone();
        user1.getHobbies().add("金钱");

        System.err.println(user1);
        System.err.println(user);
        System.err.println(user==user1);

       // User user2 = user.deepClone();

        //System.err.println(user2);
    }

    /**
     * 深复制
     * @throws Exception
     */
    @org.junit.Test
    public void testDeep() throws Exception{
        User user = new User();
        user.setAddr("北京");
        user.setId(1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,1995);
        calendar.set(Calendar.DAY_OF_YEAR,1);
        List<String> list=new ArrayList<String>();
        list.add("读书");
        list.add("学习");
        list.add("上班");
        user.setHobbies(list);
        user.setBir(calendar.getTime());
        user.setName("Xuan");
        System.err.println(user);
      //序列化深复制
        User user1 = user.deepCloneSer();
        user1.getHobbies().add("金钱");

        System.err.println(user1);
        System.err.println(user);

        //json深复制
        User user2 = User.deepFromJson(user);
        user2.getHobbies().add("金钱");
        System.err.println("=======================================");
        System.err.println("user2:   "+user2);
        System.err.println("user:  "+user);

        PrototypeProcessor processor = new PrototypeProcessor();

        User user3 = processor.deepClone(user);


    }
}
