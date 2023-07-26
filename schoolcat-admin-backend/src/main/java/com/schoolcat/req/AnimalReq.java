package com.schoolcat.req;

import lombok.Data;

@Data
public class AnimalReq extends PageReq{

    private String animalName;

    private String place;

}
