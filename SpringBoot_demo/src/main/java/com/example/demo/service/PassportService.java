package com.example.demo.service;

import cloud.tianai.captcha.application.vo.CaptchaResponse;
import cloud.tianai.captcha.application.vo.ImageCaptchaVO;
import com.example.demo.dto.ReqDto;

public interface PassportService {
    CaptchaResponse<ImageCaptchaVO> getCaptcha();

    boolean captcha(ReqDto reqDto);
}
