package com.ranchuanyin.schoolcat.controller;

import com.ranchuanyin.schoolcat.domain.SchoolAnimal;
import com.ranchuanyin.schoolcat.service.SchoolAnimalService;
import com.ranchuanyin.schoolcat.units.RestBean;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/cat/cat")
public class SchoolCatController {
    @Resource
    private SchoolAnimalService schoolAnimalService;

    @GetMapping("/{page}")
    public RestBean<List<SchoolAnimal>> paginationQuery(@PathVariable int page
    ) {
        return RestBean.success(schoolAnimalService.paginationQuery(page));
    }

    @GetMapping("/num")
    public RestBean<Long> getNum() {
        return RestBean.success(schoolAnimalService.getNums());
    }

}
