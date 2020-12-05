package com.gennadziy.service;

import com.gennadziy.web.dto.TestDTO;
import com.gennadziy.domain.Test;

import java.util.List;

public interface TestService {

    /**
     * This method will add a new member to the system.
     *
     * @param testDTO an instance of {@link TestDTO} with the member
     *                details.
     */
    void addTest(final TestDTO testDTO);
     List<Test> getAlll() throws InterruptedException;
}
