package com.gennadziy.dao;

import com.gennadziy.domain.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TestDao extends MongoRepository<Test, String> {

    @Query("{ 'name' : {$regex: ?0, $options: 'i' }}")
    Test findByTest(final String superHeroName);
}
