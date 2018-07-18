package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TopicRepository extends JpaRepository<TopicEntity,Long> {
    //Query creation from method names
    TopicEntity findByName(String name);
}
