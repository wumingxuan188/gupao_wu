package com.gupao.memento;

public class ArticleMemento {
    private  String title;
    private  String content;
    private  String img;

    public ArticleMemento(Memento memento) {
        this.title = memento.getTitle();
        this.content = memento.getContent();
        this.img =memento.getImg();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
