package com.gupao.memento;

public class Memento {
    private  String title;
    private  String content;
    private  String img;


    public Memento(String title, String content, String img) {
        this.title = title;
        this.content = content;
        this.img = img;
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

    public ArticleMemento save(){
        ArticleMemento memento = new ArticleMemento(this);
        return  memento;
    }

    public void undo( ArticleMemento memento){
       this.title=memento.getTitle();
       this.content=memento.getContent();
       this.img=memento.getImg();
    }

    @Override
    public String toString() {
        return "Memento{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
