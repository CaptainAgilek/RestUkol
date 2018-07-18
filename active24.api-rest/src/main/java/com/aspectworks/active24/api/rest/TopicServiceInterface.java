package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.TopicEntity;
import com.aspectworks.active24.api.rest.vo.CommentVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TopicServiceInterface {
    void createTopic(@RequestBody TopicEntity topic);
    void deleteTopic(@PathVariable("name") String name);
    List<TopicEntity> getTopics(String searchText, String sortBy, String sortOrder);
    void addCommentToTopic(@PathVariable("name") String topicName, CommentVO comment);
    List<TopicEntity> searchTopicsWithText(@PathVariable("text") String text);
    List<CommentVO> getAllComments(String name);
}
