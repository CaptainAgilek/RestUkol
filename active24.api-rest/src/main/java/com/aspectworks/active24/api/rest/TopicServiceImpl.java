package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLOutput;
import java.util.*;
@Service
public class TopicServiceImpl implements TopicServiceInterface {
    @Autowired
    private TopicRepository tr;
   // @PersistenceContext
   // EntityManager em;

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
        topic.setDateCreated(new Date());
        tr.save(topic);
    }

    @Override
    public void deleteTopic(String name) {
         tr.delete(tr.findByName(name));
         System.out.println("Deleting topic with name: " + name);
    }

    public List<TopicEntity> getTopics(String searchText, String sortBy, String sortOrder) {
        List<TopicEntity> result;
        if(searchText != null)
        {
            result = searchTopicsWithText(searchText);
        }
        else{

            result = tr.findAll();

        }
        if(sortBy == null)
            sortBy = "name"; //sorted by name by default
        if(sortBy.equals("date"))
        {
            if(sortOrder != null) {
                if(sortOrder.equals("asc"))
                    return sortTopicsByDateAsc(result);
                else if(sortOrder.equals("desc"))
                    return sortTopicsByDateDesc(result);
            }
            else return sortTopicsByDateAsc(result);//default sort is ascending
        }
        else if(sortBy.equals("name"))
        {
            if(sortOrder != null) {
                if (sortOrder.equals("asc"))
                    return sortTopicsAlphabeticallAsc(result);
                if (sortOrder.equals("desc"))
                    return sortTopicsAlphabeticallDesc(result);
            }
            else return sortTopicsAlphabeticallAsc(result);//default sort is ascending
        }


        return result;
    }


    @Override
    public void addCommentToTopic(String topicName, CommentVO comment) {
        TopicEntity entity = tr.findByName(topicName);
        entity.getComments().add(comment);
        tr.save(entity);
    }

    public List<TopicEntity> sortTopicsAlphabeticallAsc(List<TopicEntity> list) {
        List<TopicEntity> result = list;
        Collections.sort(result, (TopicEntity lhs, TopicEntity rhs)->lhs.getName().compareTo(rhs.getName()));
        System.out.println("Sorting topics by name ascending order.");
        return result;
    }

    public List<TopicEntity> sortTopicsAlphabeticallDesc(List<TopicEntity> list) {
        List<TopicEntity> result = list;
        Collections.sort(result, (TopicEntity lhs, TopicEntity rhs)->rhs.getName().compareTo(lhs.getName()));
        System.out.println("Sorting topics by name descending order.");
        return result;
    }

    public List<TopicEntity> sortTopicsByDateAsc(List<TopicEntity> list) {
        List<TopicEntity> result = list;
        Collections.sort(result, (TopicEntity lhs, TopicEntity rhs)->lhs.getDateCreated().compareTo(rhs.getDateCreated()));
        System.out.println("Sorting topics by date asc.");
        return result;
    }

    public List<TopicEntity> sortTopicsByDateDesc(List<TopicEntity> list) {
        List<TopicEntity> result = list;
        Collections.sort(result, (TopicEntity lhs, TopicEntity rhs)->rhs.getDateCreated().compareTo(lhs.getDateCreated()));
        System.out.println("Sorting topics by date desc.");
        return result;
    }

    public List<TopicEntity> searchTopicsWithText(String text) {
        System.out.println("Searching topic by text: " + text);
        return tr.findByTextContaining(text);
    }

    @Override
    public List<CommentVO> getAllComments(String name) {
        TopicEntity entity = tr.findByName(name);
        System.out.println(entity.getName());
        return entity.getComments();

    }
}
