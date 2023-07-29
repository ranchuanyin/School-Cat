package entity;

import lombok.Data;

/**
 * 领养记录
 */
@Data
public class AdoptionLogs extends Adoptions{

    /**
     * 宠物信息
     */
    private Animal animal;

    /**
     * 领养人信息
     */
    private User user;
}
