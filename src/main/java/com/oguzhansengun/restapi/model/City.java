package com.oguzhansengun.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String id;
    private String name;
    private Date createDate;
}
