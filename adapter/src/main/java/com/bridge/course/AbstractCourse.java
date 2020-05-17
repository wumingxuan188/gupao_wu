package com.bridge.course;

public abstract class AbstractCourse implements ICourse {
    private IVideo iVideo;

    private INote iNote;


    public IVideo getiVideo() {
        return iVideo;
    }

    public INote getiNote() {
        return iNote;
    }

    public void setiVideo(IVideo iVideo) {
        this.iVideo = iVideo;
    }

    public void setiNote(INote iNote) {
        this.iNote = iNote;
    }
}
