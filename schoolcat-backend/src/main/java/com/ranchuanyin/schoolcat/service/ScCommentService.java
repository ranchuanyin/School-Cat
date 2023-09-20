package com.ranchuanyin.schoolcat.service;

import com.ranchuanyin.schoolcat.domain.ScComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ranchuanyin.schoolcat.dto.CommentDTO;
import com.ranchuanyin.schoolcat.units.RestBean;

/**
* @author 33716
* @description 针对表【sc_comment】的数据库操作Service
* @createDate 2023-09-20 16:45:01
*/
public interface ScCommentService extends IService<ScComment> {

    RestBean commentList(CommentDTO commentDTO);
}
