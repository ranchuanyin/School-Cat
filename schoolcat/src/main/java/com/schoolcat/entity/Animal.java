package com.schoolcat.entity;

import lombok.Data;


@Data
public class Animal  {

    private Integer id;

    /*
    * 动物名字
    * */
    private String animalName;


    /*
    * 动物性格
    * */
    private String animalCharacter;

    /*
    * 出没点
    * */
    private String place;

    /*
    * 关系网
    * */
    private String relationship;

    /*
    * 图片
    * */
    private String image;

}
