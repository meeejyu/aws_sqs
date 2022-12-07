package com.sqs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sqs.dto.UserDto;
import com.sqs.service.MainService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    
    private final MainService mainService;

    @PostMapping("/send")
    @ResponseBody
    public String match(@RequestBody UserDto userMatchDto) {
        // 메세지 보내기
        mainService.sendMessage(userMatchDto);

        return "OK";
    }
    
    // 메세지 불러오기
    @GetMapping(value="/getmessage")
    @ResponseBody
    public String getMessage() {

        mainService.getUserMessage();
        return "OK";
    }

}
