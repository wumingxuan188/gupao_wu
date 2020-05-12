package com.template.course;

public abstract class AbstractCourse {
    public final  void execute(){
        postPreResoucse();
        createPPT();
        liveVideo();
        postResource();
        postHomework();
        if(isCheck()){
            checkHome();
        }

    }

    protected abstract void checkHome();

    /**
     * 钩子方法
     * @return
     */
    public boolean isCheck(){
        return false;
    }
    protected  void postHomework(){
        System.err.println("布置作业");
    }

    protected  void postResource(){
        System.err.println("上传课后资料");
    }

    protected  void liveVideo(){
        System.err.println("直播授课");
    }

    protected void createPPT() {
        System.err.println("制作PPT");
    }

    protected  void postPreResoucse(){
        System.err.println("发布预习资料");
    }
}
