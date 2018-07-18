package com.aspectworks.active24.api.rest.vo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TopicEntity {
    private String name;
    private Date dateCreated;
    private String text;
    @OneToMany(cascade=CascadeType.ALL)
    List<CommentVO> comments = new ArrayList<>();

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

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
