package com.ranchuanyin.schoolcat.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName school_animal
 */
@TableName(value = "school_animal")
@Data
public class SchoolAnimal implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 猫Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    private String place;
    /**
     *
     */
    private String relationship;
    /**
     *
     */
    private String image;
    /**
     *
     */
    private String animalName;
    /**
     *
     */
    private String animalCharacter;
    private double score;
}