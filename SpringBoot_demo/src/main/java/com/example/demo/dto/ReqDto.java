package com.example.demo.dto;

import cloud.tianai.captcha.validator.common.model.dto.ImageCaptchaTrack;
import lombok.Data;

@Data
public class ReqDto {
    private String token;
    private String codeId;
    private ImageCaptchaTrack datas;
}
