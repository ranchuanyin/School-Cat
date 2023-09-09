package com.ranchuanyin.schoolcat.controller;

import com.ranchuanyin.schoolcat.units.OSSUnit;
import com.ranchuanyin.schoolcat.units.RestBean;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/cat/upload")
public class FileUploadController {
    @Resource
    private OSSUnit ossUnit;

    @PostMapping("/avatar-img")
    public RestBean<String> uploadImage(MultipartFile file) {
        log.info(file.getOriginalFilename());
        String uploadUrl = ossUnit.upload(file);
        return RestBean.success("修改成功", uploadUrl);
    }
}
