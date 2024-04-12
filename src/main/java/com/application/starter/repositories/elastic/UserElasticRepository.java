package com.application.starter.repositories.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.application.starter.model.User;

public interface UserElasticRepository extends ElasticsearchRepository<User, String> {
          
}   
