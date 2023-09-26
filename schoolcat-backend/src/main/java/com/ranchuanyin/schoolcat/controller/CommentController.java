package com.ranchuanyin.schoolcat.controller;

import com.ranchuanyin.schoolcat.dto.CommentDTO;
import com.ranchuanyin.schoolcat.dto.WriteACommentDTO;
import com.ranchuanyin.schoolcat.service.ScCommentService;
import com.ranchuanyin.schoolcat.units.RestBean;
import com.ranchuanyin.schoolcat.util.RedisCache;
import com.ranchuanyin.schoolcat.vo.CommentVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cat/comment")
public class CommentController {

    @Resource
    ScCommentService scCommentService;

    @Resource
    RedisCache redisCache;

    @PostMapping("commentList")
    public RestBean<List<CommentVO>> commentList(CommentDTO commentDTO) {
        return scCommentService.commentList(commentDTO);
    }

    @PostMapping("send")
    public RestBean<String> sendComment(WriteACommentDTO writeACommentDTO) {
        return scCommentService.sendComment(writeACommentDTO);
    }
}
