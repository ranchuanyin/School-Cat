package entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("school-cat")
@Data
public class Animal  {

    private Integer animalId;

    /**
     * 动物名字
     * */
    private String animalName;


    /**
     * 动物性格
     * */
    private String animalCharacter;

    /**
     * 出没点
     * */
    private String place;

    /**
     * 关系网
     * */
    private String relationship;

    /**
     * 图片
     * */
    private String image;

    /**
     * 宠物状态：0待领养，1被领养
     */
    private Integer animalStatus;

}
