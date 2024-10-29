package com.zero.activiti.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.zero.activiti.service.ActivityConsumerService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityConsumerServiceImpl implements ActivityConsumerService {

    RuntimeService runtimeService;
    TaskService taskService;

    @Autowired
    public ActivityConsumerServiceImpl(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    @Override
    public boolean startActivityDemo() {
        System.out.println("method startActivityDemo begin....");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("apply", "zhangsan");
        map.put("approve", "lisi");
        //flow start
//        RuntimeService runtimeService = new RuntimeServiceImpl();
        ExecutionEntity pi1 = (ExecutionEntity) runtimeService.startProcessInstanceByKey("leave", map);
        String processId = pi1.getId();
        pi1.getExecutions().forEach(row->{
            String taskId =  row.getTasks().get(0).getId();
//            TaskService taskService = new TaskServiceImpl();
            taskService.complete(taskId, map);//complete fist step
            Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
            String taskId2 = task.getId();
            map.put("pass", false);
            taskService.complete(taskId2, map);//refuse apply
            System.out.println("method startActivityDemo end....");
        });

        return false;
    }
}