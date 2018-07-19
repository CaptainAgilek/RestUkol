package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void createTopic(TopicEntity topic) {
        if(topicRepository.findByName(topic.getName()) == null) {//check if topic with same name already does not exist
            topic.setDate(new Date());
            topicRepository.save(topic);
        }
    }

    /**
     * A co pouzit deleteByName(String name)? usetris tak jednu operaci do databaze
     * @param name
     */
    @Override
    public void deleteTopic(String name) {
         topicRepository.deleteByName(name);
         System.out.println("Deleting topic with name: " + name);
    }

    public List<TopicEntity> getTopics(String searchText, String sortBy, String sortOrder) {
        Sort.Direction direction;
        if(sortOrder == null)
            sortOrder = "asc"; //by default sort by ascending order
        if(sortOrder.equals("asc"))
            direction = Sort.Direction.ASC;
        else direction = Sort.Direction.ASC.DESC;

        if(sortBy == null)
            sortBy = "name"; //by default sort by name

        Sort sort = new Sort(new Sort.Order(direction, sortBy));
        System.out.println("Getting topics");
        if(searchText != null) {
            return topicRepository.findByTextContainingIgnoreCaseOrNameContainingIgnoreCase(searchText, searchText, sort);//search by text
        }
        else return topicRepository.findAll(sort);//get all topics
    }

    @Override
    public void addCommentToTopic(String topicName, CommentVO comment) {
        TopicEntity entity = topicRepository.findByName(topicName);
        entity.getComments().add(comment);
        topicRepository.save(entity);
        System.out.println("Adding comment to topic with name: " + topicName);
    }

    @Override
    public List<CommentVO> getAllComments(String name) {
        TopicEntity entity = topicRepository.findByName(name);
        System.out.println("Getting comment to topic with name: " + entity.getName());
        return entity.getComments();
    }
}
