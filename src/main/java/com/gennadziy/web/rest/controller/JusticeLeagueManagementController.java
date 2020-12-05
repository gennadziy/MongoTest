package com.gennadziy.web.rest.controller;

import javax.validation.Valid;

import com.gennadziy.dao.TestDao;
import com.gennadziy.service.TestService;
import com.gennadziy.dao.JusticeLeagueRepository;
import com.gennadziy.domain.JusticeLeagueMemberDetail;
import com.gennadziy.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gennadziy.constants.MessageConstants;
import com.gennadziy.service.JusticeLeagueMemberService;
import com.gennadziy.web.dto.JusticeLeagueMemberDTO;
import com.gennadziy.web.dto.ResponseDTO;

import java.util.List;

/**
 * This class exposes the REST API for the system.
 *
 * @author
 */
@RestController
public class JusticeLeagueManagementController {

    @Autowired
    private JusticeLeagueMemberService memberService;

    @Autowired
    private JusticeLeagueRepository justiceLeagueRepository;

    @Autowired
    private TestDao testDao;

    @Autowired
    private TestService testService;

    /**
     * This method will be used to add justice league members to the system.
     *
     * @param justiceLeagueMember the justice league member to add.
     * @return an instance of {@link ResponseDTO} which will notify whether
     * adding the member was successful.
     */
    @ResponseBody
    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE} )
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseDTO addJusticeLeagueMember(@Valid @RequestBody JusticeLeagueMemberDTO justiceLeagueMember) {
        ResponseDTO responseDTO = new ResponseDTO(ResponseDTO.Status.GOVNO,
                MessageConstants.MEMBER_ADDED_SUCCESSFULLY);
        try {
            memberService.addMember(justiceLeagueMember);
        } catch (Exception e) {
            responseDTO.setStatus(ResponseDTO.Status.FAIL);
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @PostMapping("/add")
    public JusticeLeagueMemberDetail addToDb(JusticeLeagueMemberDetail test) {
        return justiceLeagueRepository.save(test);
    }

    @GetMapping("/all")
    public List<JusticeLeagueMemberDetail> getAll() {
        return justiceLeagueRepository.findAll();
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<JusticeLeagueMemberDetail> getAll1(@PathVariable String id) {
        return ResponseEntity.ok().body(justiceLeagueRepository.findOne(id));
    }

    @GetMapping("/all1")
    public List<Test> getAll12() throws InterruptedException {
        return testService.getAlll();
    }

    @GetMapping("/all1/{id}")
    public ResponseEntity<Test> getAll123(@PathVariable String id) {
        return ResponseEntity.ok().body(testDao.findOne(id));
    }

}
