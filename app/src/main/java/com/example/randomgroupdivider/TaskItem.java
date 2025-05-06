package com.example.randomgroupdivider;

import java.util.List;

public class TaskItem {
    String taskName;
    List<String> members;

    public TaskItem(String taskName, List<String> members) {
        this.taskName = taskName;
        this.members = members;
    }

    public String getTaskName() {
        return taskName;
    }

    public List<String> getMembers() {
        return members;
    }
}
