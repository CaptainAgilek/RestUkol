package com.aspectworks.active24.api.rest.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicVO {
    private String name;
    private Date dateCreated;
    private String text;
    private List<CommentVO> comments = new ArrayList<>();

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
