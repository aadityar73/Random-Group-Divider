package com.example.randomgroupdivider;

import java.util.List;

public class GroupItem {
    private String groupName;
    private List<String> members;

    public GroupItem(String groupName, List<String> members) {
        this.groupName = groupName;
        this.members = members;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getMembers() {
        return members;
    }
}
