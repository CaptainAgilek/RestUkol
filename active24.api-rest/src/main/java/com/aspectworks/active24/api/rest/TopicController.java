package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    TopicServiceImpl topicService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createTopic(@RequestBody TopicVO topic){
        topicService.createTopic(topic);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{name}")
    public void deleteTopic(@PathVariable("name") String name){
        topicService.deleteTopic(name);
    }

    @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<TopicVO> getAllTopics(@RequestParam(value = "searchText", required=false) String searchText, @RequestParam(value ="sortBy", required = false) String sortBy, @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        return topicService.getAllTopics(searchText, sortBy, sortOrder);
    }

    @RequestMapping( value = "/{name}/comments", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addCommentToTopic(@PathVariable("name") String topicName, @RequestBody CommentVO comment) {
         topicService.addCommentToTopic(topicName, comment);
    }
}
