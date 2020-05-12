package com.gupao.memento;

public class Test {

    public static void main(String[] args) {
        DraftsBox box = new DraftsBox();
        System.err.println("初始话信息");
        Memento memento = new Memento("测试", "我是大牛", "11225.img");
        ArticleMemento memento1 = memento.save();
        box.add(memento1);
        System.err.println(memento);

        System.err.println("=========首次修改==============");
        memento.setTitle("测试");
        memento.setContent("我是菜鸟");
        memento.setImg("11111.jpg");
        ArticleMemento memento2 = memento.save();
        box.add(memento2);
        System.err.println(memento);

        System.err.println("===============第二次修改======================");
        memento.setTitle("测试");
        memento.setContent("我是架构");
        memento.setImg("22222.jpg");
        ArticleMemento s2 = memento.save();
        //box.add(s2);
        System.err.println(memento);

        System.err.println("============第一次撤销操作====================");
        ArticleMemento undo = box.get();
        memento.undo(undo);
        System.err.println(memento);

        System.err.println("============第二次撤销操作====================");
        ArticleMemento undo2 = box.get();
        memento.undo(undo2);
        System.err.println(memento);
    }
}
