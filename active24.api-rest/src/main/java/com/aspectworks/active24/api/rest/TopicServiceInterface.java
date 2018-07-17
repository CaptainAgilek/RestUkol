package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.TopicVO;
import com.aspectworks.active24.api.rest.vo.CommentVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TopicServiceInterface {
    void createTopic(@RequestBody TopicVO topic);
    void deleteTopic(@PathVariable("name") String name);
    List<TopicVO> getAllTopics(String searchText, String sortBy, String sortOrder);
    void addCommentToTopic(@RequestBody String topicName, CommentVO comment);
    void sortTopicsAlphabeticallAsc();
    void sortTopicsAlphabeticallDesc();
    void sortTopicsByDateAsc();
    void sortTopicsByDateDesc();
    List<TopicVO> searchTopicsWithText(@PathVariable("text") String text);
}
