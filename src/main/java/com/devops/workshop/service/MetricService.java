package com.devops.workshop.service;

import com.devops.workshop.dto.Workshop;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetricService {

    @Timed(value = "for.time", description = "Time taken to return for")
    public List<Workshop> getDataWithFor(Integer max){
        List<Workshop> workshopList = new ArrayList<>();

        for (int i = 0; i < max; i++) {
            workshopList.add(new Workshop(i,"Workshop-" + i, "https://workshop."+i+".com"));
        }

        return workshopList;
    }

    @Timed(value = "while.time", description = "Time taken to return foreach")
    public List<Workshop> getDataWithWhile(Integer max){
        List<Workshop> workshopList = new ArrayList<>();
        Integer index = 0;
        Integer test = 1;
        while (index < max){
            workshopList.add(new Workshop(index, "Workshop-"+index, "https://workshop."+index+".com"));
        }

        return workshopList;
    }

}
