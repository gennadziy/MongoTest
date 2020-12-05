package com.gennadziy.service.impl;

import com.gennadziy.constants.MessageConstants;
import com.gennadziy.dao.TestDao;
import com.gennadziy.domain.Test;
import com.gennadziy.exception.JusticeLeagueManagementException;
import com.gennadziy.service.TestService;
import com.gennadziy.web.dto.TestDTO;
import com.gennadziy.web.transformer.DTOToDomainTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public void addTest(TestDTO testDTO) {
        Test test = testDao.findByTest(testDTO.getName());
        if (test != null) {
            throw new JusticeLeagueManagementException(MessageConstants.ErrorMessages.MEMBER_ALREDY_EXISTS);
        }
        Test memberToPersist1 = DTOToDomainTransformer.transformTest(testDTO);
        testDao.insert(memberToPersist1);

    }
    @Override
    @Cacheable("test")
    public List<Test> getAlll() throws InterruptedException {
        Thread.sleep(5000);
        return testDao.findAll();
    }
}
