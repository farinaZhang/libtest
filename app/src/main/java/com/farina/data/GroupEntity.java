package com.farina.data;

/**
 * Created by FarinaZhang on 2016/7/5.
 */
public class GroupEntity {
    private int id;
    private int hostId;
    private String groupName;


    private GroupEntity entity=null;

    public GroupEntity getInstance(){
        if(entity ==null){
            entity = new GroupEntity();
        }
        return entity;
    }

    public void setId(int value){
        id=value;
    }
    public int getId(){
        return id;
    }
    public void setGroupName(String value){
        groupName = value;
    }
    public String getName(){
        return groupName;
    }
    public void setHostId(int value){
        hostId=value;
    }
    public int getHostId(){
        return hostId;
    }

}
