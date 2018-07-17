package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicVO;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;

@Service
public class TopicServiceImpl implements TopicServiceInterface {
    List<TopicVO> topics = new ArrayList<>();

    public TopicServiceImpl() {
        TopicVO t = new TopicVO();
        t.setName("Politika v CR");
        t.setText("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi scelerisque luctus velit. Integer lacinia. Vivamus luctus egestas leo. Aenean id metus id velit ullamcorper pulvinar. Mauris tincidunt sem sed arcu. Pellentesque pretium lectus id turpis. Phasellus rhoncus. Sed convallis magna eu sem. Donec iaculis gravida nulla. Vivamus luctus egestas leo. Nullam eget nisl. Nulla non lectus sed nisl molestie malesuada. Aliquam erat volutpat. Nunc tincidunt ante vitae massa. Aenean placerat. Morbi leo mi, nonummy eget tristique non, rhoncus non leo.");
        t.setDateCreated(new Date());
        CommentVO c = new CommentVO();
        c.setAuthorUsername("Karel");
        c.setText("Nas pan premier je...");
        t.setComments(new ArrayList<CommentVO>(){{add(c);}});
        topics.add(t);
        t = new TopicVO();
        t.setName("Babis opsal diplomku");
        t.setText("Kralici, capi hnizdo,...");
        t.setDateCreated(new Date());
        CommentVO comment = new CommentVO();
        comment.setAuthorUsername("Michal");
        comment.setText("Kdo z politiku neopsal diplomku?");
        t.setComments(new ArrayList<CommentVO>(){{add(comment);}});
        topics.add(t);
    }
    @Override
    public void createTopic(TopicVO topic) {
        topic.setDateCreated(new Date());
        topics.add(topic);
    }

    @Override
    public void deleteTopic(String name) {
        for (Iterator<TopicVO> topicIterator = topics.iterator(); topicIterator.hasNext(); ) {
            TopicVO topic = topicIterator.next();
            if (topic.getName().equals(name)) {
                topicIterator.remove();
                System.out.println("Deleting topic with name: " + name);
            }
        }
    }

    public List<TopicVO> getAllTopics(String searchText, String sortBy, String sortOrder) {
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

        List<TopicVO> result = new ArrayList<>();
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

    @Override
    public void sortTopicsAlphabeticallAsc() {
        Collections.sort(topics, (TopicVO lhs, TopicVO rhs)->lhs.getName().compareTo(rhs.getName()));
        System.out.println("Sorting topics by name ascending order.");
    }

    @Override
    public void sortTopicsAlphabeticallDesc() {
        Collections.sort(topics, (TopicVO lhs, TopicVO rhs)->rhs.getName().compareTo(lhs.getName()));
        System.out.println("Sorting topics by name descending order.");
    }

    @Override
    public void sortTopicsByDateAsc() {
        Collections.sort(topics, (TopicVO lhs, TopicVO rhs)->lhs.getDateCreated().compareTo(rhs.getDateCreated()));
        System.out.println("Sorting topics by date asc.");
    }

    @Override
    public void sortTopicsByDateDesc() {
        Collections.sort(topics, (TopicVO lhs, TopicVO rhs)->rhs.getDateCreated().compareTo(lhs.getDateCreated()));
        System.out.println("Sorting topics by date desc.");
    }

    @Override
    public List<TopicVO> searchTopicsWithText(String text) {
        List<TopicVO> result = new ArrayList<>();
        for(TopicVO topic : topics)
        {
            if(topic.getText().toLowerCase().contains(text.toLowerCase()) || topic.getName().toLowerCase().contains(text.toLowerCase()))
            {
                result.add(topic);
            }
        }
        System.out.println("Searching topic by text: " + text);
        return result;
    }
}
