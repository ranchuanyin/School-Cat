package com.schoolcat.req;

import com.schoolcat.entity.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 宠物页码查询条件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalQueryListReq {
    private Integer page;
    private Integer pageSize;
    private Animal animal;
    private Integer userType;

    /**
     * toString：1_5_1
     * @return 页码_页码大小
     */
    @Override
    public String toString(){
        return page+"_"+pageSize+"_"+userType;
    }
}
