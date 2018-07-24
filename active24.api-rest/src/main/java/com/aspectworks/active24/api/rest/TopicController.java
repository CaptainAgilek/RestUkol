package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import com.aspectworks.active24.api.rest.vo.TopicVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value="/topics" , description = "Operations about topics")
@RequestMapping("/topics")
@PropertySource("file:${restapi.config.dir}/restapi.properties")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Value("${superproperty:666}")
    private int superProperty;

    @ApiOperation(value = "Creates new topic.")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createTopic(@RequestBody TopicVO topic){
        TopicEntity topicEntity = new TopicEntity(topic);
        System.out.println(superProperty);
        topicService.createTopic(topicEntity);
    }
    @ApiOperation(value = "Deletes topic.")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{name}")
    public void deleteTopic(@ApiParam(value = "Name of the topic you want to delete.", required = true) @PathVariable("name") String name){
        topicService.deleteTopic(name);
    }

    @ApiOperation(value = "Returns specified topics.")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicVO> getTopics(@ApiParam(value = "Text you want to search.") @RequestParam(value = "searchText", required = false) String searchText, @ApiParam(value = "Specifies whether you want to sort by name or date.") @RequestParam(value ="sortBy", required = false) String sortBy, @ApiParam(value = "Specifies ascending or descending order for sort.") @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        return topicService.getTopics(searchText, sortBy, sortOrder).stream().map(topic->new TopicVO(topic)).collect(Collectors.toList());
    }

    @ApiOperation(value = "Adds new comment to specified topic.")
    @RequestMapping( value = "/{name}/comments", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addCommentToTopic(@ApiParam(value = "Name of the topic you want to add coment to.") @RequestHeader(value="param-creditcard") @PathVariable("name") String topicName, @ApiParam(value = "Comment you want to add.") @RequestBody CommentVO comment) {
         topicService.addCommentToTopic(topicName, comment);
    }

    @ApiOperation(value = "Returns all comment for specified topic.")
    @RequestMapping( value = "/{name}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentVO> getAllComments(@PathVariable("name") String name) {
        return topicService.getAllComments(name);
    }
}
