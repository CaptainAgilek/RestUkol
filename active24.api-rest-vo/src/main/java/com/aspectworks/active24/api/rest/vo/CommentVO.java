package com.aspectworks.active24.api.rest.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class CommentVO {
    private String authorUsername;
    private String text;
    private Date createdDate;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    public String getAuthorUsername() {
        return authorUsername;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
