package com.aspectworks.active24.api.rest.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@ApiModel(value = "CommentVO", description="Comment object.")
public class CommentVO {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @ApiModelProperty(value = "Username of the comment's author.")
    private String authorUsername;
    @ApiModelProperty(value = "Text of the comment.")
    private String text;

    public String getAuthorUsername() {
        return authorUsername;
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
