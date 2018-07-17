package com.aspectworks.active24.api.rest.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicEntity {
    private String name;
    private Date dateCreated;
    private String text;
    List<CommentVO> comments = new ArrayList<>();

    public TopicEntity() {
    }
    public TopicEntity(TopicVO topic) {
    this.name = topic.getName();
    this.dateCreated = topic.getDateCreated();
    this.text = topic.getText();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public List<CommentVO> getComments() {
        return comments;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }
}
