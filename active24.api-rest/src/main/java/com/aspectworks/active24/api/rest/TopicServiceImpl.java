package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import com.aspectworks.active24.api.rest.vo.TopicVO;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicServiceInterface {
    List<TopicEntity> topics = new ArrayList<>();

    public TopicServiceImpl() {
      /* TopicEntity t = new TopicEntity();
        t.setName("Politika v CR");
        t.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi scelerisque luctus velit. Integer lacinia. Vivamus luctus egestas leo. Aenean id metus id velit ullamcorper pulvinar. Mauris tincidunt sem sed arcu. Pellentesque pretium lectus id turpis. Phasellus rhoncus. Sed convallis magna eu sem. Donec iaculis gravida nulla. Vivamus luctus egestas leo. Nullam eget nisl. Nulla non lectus sed nisl molestie malesuada. Aliquam erat volutpat. Nunc tincidunt ante vitae massa. Aenean placerat. Morbi leo mi, nonummy eget tristique non, rhoncus non leo.");
        t.setDateCreated(new Date());
        CommentVO c = new CommentVO();
        c.setAuthorUsername("Karel");
        c.setText("Nas pan premier je...");
        t.setComments(new ArrayList<CommentVO>(){{add(c);}});
        topics.add(t);
        t = new TopicEntity();
        t.setName("Babis opsal diplomku");
        t.setText("Kralici, capi hnizdo,...");
        t.setDateCreated(new Date());
        CommentVO comment = new CommentVO();
        comment.setAuthorUsername("Michal");
        comment.setText("Kdo z politiku neopsal diplomku?");
        t.setComments(new ArrayList<CommentVO>(){{add(comment);}});
        topics.add(t);
      */
    }
    @Override
    public void createTopic(TopicEntity topic) {
        for(TopicEntity entity : topics)
            if(entity.getName().equals(topic.getName()))
                return;
        topic.setDateCreated(new Date());
        topics.add(topic);
    }

    @Override
    public void deleteTopic(String name) {
        for (Iterator<TopicEntity> topicIterator = topics.iterator(); topicIterator.hasNext(); ) {
            TopicEntity topic = topicIterator.next();
            if (topic.getName().equals(name)) {
                topicIterator.remove();
                System.out.println("Deleting topic with name: " + name);
            }
        }
    }

    public List<TopicEntity> getTopics(String searchText, String sortBy, String sortOrder) {
        if(sortBy == null)
            sortBy = "name"; //sorted by name by default
        if(sortBy.equals("date"))
        {
            if(sortOrder != null) {
                if(sortOrder.equals("asc"))
                    sortTopicsByDateAsc();
                else if(sortOrder.equals("desc"))
                    sortTopicsByDateDesc();
            }
            else sortTopicsByDateAsc();//default sort is ascending
        }
        else if(sortBy.equals("name"))
        {
            if(sortOrder != null) {
                if (sortOrder.equals("asc"))
                    sortTopicsAlphabeticallAsc();
                if (sortOrder.equals("desc"))
                    sortTopicsAlphabeticallDesc();
            }
            else sortTopicsAlphabeticallAsc();//default sort is ascending
        }

        List<TopicEntity> result = new ArrayList<>(topics.size());
        if(searchText != null)
        {
            result = searchTopicsWithText(searchText);
        }
        else{

            result = topics;

        }

        return result;
    }


    @Override
    public void addCommentToTopic(String topicName, CommentVO comment) {
        topics.forEach( topic -> {if(topic.getName().equals(topicName)) {
            topic.getComments().add(comment);
            System.out.println("Adding comment to topic: " + topicName);
        }
        }
        );

    }

    public void sortTopicsAlphabeticallAsc() {
        Collections.sort(topics, (TopicEntity lhs, TopicEntity rhs)->lhs.getName().compareTo(rhs.getName()));
        System.out.println("Sorting topics by name ascending order.");
    }

    public void sortTopicsAlphabeticallDesc() {
        Collections.sort(topics, (TopicEntity lhs, TopicEntity rhs)->rhs.getName().compareTo(lhs.getName()));
        System.out.println("Sorting topics by name descending order.");
    }

    public void sortTopicsByDateAsc() {
        Collections.sort(topics, (TopicEntity lhs, TopicEntity rhs)->lhs.getDateCreated().compareTo(rhs.getDateCreated()));
        System.out.println("Sorting topics by date asc.");
    }

    public void sortTopicsByDateDesc() {
        Collections.sort(topics, (TopicEntity lhs, TopicEntity rhs)->rhs.getDateCreated().compareTo(lhs.getDateCreated()));
        System.out.println("Sorting topics by date desc.");
    }

    public List<TopicEntity> searchTopicsWithText(String text) {
        List<TopicEntity> result = new ArrayList<>();
        for(TopicEntity topic : topics)
        {
            if(topic.getText().toLowerCase().contains(text.toLowerCase()) || topic.getName().toLowerCase().contains(text.toLowerCase()))
            {
                result.add(topic);
            }
        }
        System.out.println("Searching topic by text: " + text);
        return result;
    }

    @Override
    public List<CommentVO> getAllComments(String name) {
        for(TopicEntity topicEntity : topics)
            if(topicEntity.getName().equals(name))
                return topicEntity.getComments();
        return new ArrayList<>();
    }
}
