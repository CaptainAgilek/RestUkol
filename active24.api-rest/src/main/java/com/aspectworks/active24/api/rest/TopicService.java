package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.TopicEntity;
import com.aspectworks.active24.api.rest.vo.CommentVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TopicService {
    void createTopic(TopicEntity topic);
    void deleteTopic(String name);
    List<TopicEntity> getTopics(String searchText, String sortBy, String sortOrder);
    void addCommentToTopic(String topicName, CommentVO comment);
    List<CommentVO> getAllComments(String name);
}
