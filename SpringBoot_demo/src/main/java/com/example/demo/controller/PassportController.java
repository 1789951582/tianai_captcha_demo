package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.dto.ReqDto;
import com.example.demo.service.PassportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/passport")
@Slf4j
public class PassportController {

    @Autowired
    PassportService passportService;

    @PostMapping("get_captcha")
    public Result getCaptcha(){
        try{
            return Result.successResponse(passportService.getCaptcha());
        }
        catch (Exception e){
            log.error(e.getMessage());
            return Result.FailResponse("发生错误");
        }
    }

    @PostMapping("captcha")
    public Result captcha(@RequestBody ReqDto reqDto){
        try{
            return Result.successResponse(passportService.captcha(reqDto));
        }catch (Exception e){
            log.error(e.getMessage());
            return Result.FailResponse("发生错误");
        }
    }

}
