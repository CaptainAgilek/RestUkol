package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import com.aspectworks.active24.api.rest.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    TopicServiceImpl topicService;
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createTopic(@RequestBody TopicVO topic){
        TopicEntity topicEntity = new TopicEntity(topic);
        topicService.createTopic(topicEntity);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{name}")
    public void deleteTopic(@PathVariable("name") String name){
        topicService.deleteTopic(name);
    }

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<TopicVO> getTopics(@RequestParam(value = "searchText", required=false) String searchText, @RequestParam(value ="sortBy", required = false) String sortBy, @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        return topicService.getTopics(searchText, sortBy, sortOrder).stream().map(topic->new TopicVO(topic)).collect(Collectors.toList());
    }

    @RequestMapping( value = "/{name}/comments", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addCommentToTopic(@PathVariable("name") String topicName, @RequestBody CommentVO comment) {
         topicService.addCommentToTopic(topicName, comment);
    }
    @RequestMapping( value = "/{name}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentVO> getAllComments(@PathVariable("name") String name) {
        return topicService.getAllComments(name);
    }
}
