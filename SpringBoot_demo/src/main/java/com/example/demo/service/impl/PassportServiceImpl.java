package com.example.demo.service.impl;

import cloud.tianai.captcha.application.ImageCaptchaApplication;
import cloud.tianai.captcha.application.vo.CaptchaResponse;
import cloud.tianai.captcha.application.vo.ImageCaptchaVO;
import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.common.response.ApiResponse;
import com.example.demo.dto.ReqDto;
import com.example.demo.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportServiceImpl implements PassportService {

    @Autowired
    private ImageCaptchaApplication imageCaptchaApplication;
    @Override
    public CaptchaResponse<ImageCaptchaVO> getCaptcha() {
        return imageCaptchaApplication.generateCaptcha(CaptchaTypeConstant.SLIDER);
    }

    @Override
    public boolean captcha(ReqDto reqDto) {
        ApiResponse<?> match = imageCaptchaApplication.matching(reqDto.getCodeId(), reqDto.getDatas());
        return match.isSuccess();
    }
}
