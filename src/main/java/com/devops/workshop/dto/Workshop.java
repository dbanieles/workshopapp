package com.devops.workshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Workshop {

    private Integer workshopId;

    private String workshopName;

    private String workshopUrl;
}
