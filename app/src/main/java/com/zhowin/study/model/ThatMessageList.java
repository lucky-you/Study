package com.zhowin.study.model;

/**
 * item的model
 */
public class ThatMessageList {

    private int id;
    private String title;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ThatMessageList(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public ThatMessageList(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

}
