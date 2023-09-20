package com.ranchuanyin.schoolcat.controller;

import com.ranchuanyin.schoolcat.dto.CommentDTO;
import com.ranchuanyin.schoolcat.service.ScCommentService;
import com.ranchuanyin.schoolcat.units.RestBean;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cat/comment")
public class CommentController {

    @Resource
    ScCommentService scCommentService;
    @GetMapping("commentList")
    public RestBean commentList(@RequestBody CommentDTO commentDTO){
        return scCommentService.commentList(commentDTO);
    }
}
