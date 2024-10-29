package com.zero.activiti.controller;

import com.zero.activiti.service.ActivityConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    ActivityConsumerService activityConsumerService;

    @Autowired
    public ActivityController(ActivityConsumerService activityConsumerService) {
        this.activityConsumerService = activityConsumerService;
    }

    @GetMapping(value="/")
    public boolean startActivityDemo(){
        return activityConsumerService.startActivityDemo();
    }

}