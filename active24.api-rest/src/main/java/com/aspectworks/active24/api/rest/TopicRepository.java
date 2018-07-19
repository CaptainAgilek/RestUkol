package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.TopicEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TopicRepository extends JpaRepository<TopicEntity,Long> {
    //Query creation from method names
    TopicEntity findByName(String name);
    List<TopicEntity> findByTextContainingIgnoreCaseOrNameContainingIgnoreCase(String searchText,String sameSearchText, Sort sort);
}
