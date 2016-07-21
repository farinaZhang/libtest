package com.farina.data;

/**
 * Created by FarinaZhang on 2016/7/21.
 */
public class FriendEntity {
    private int id;
    private String name;
    private String  icnPath;
    private int hostId;
    private int groupId;
    private boolean beLive;

    private FriendEntity entity=null;

    public FriendEntity getInstance(){
        if(entity ==null){
            entity = new FriendEntity();
        }
        return entity;
    }

    public void setId(int value){
        id=value;
    }
    public int getId(){
        return id;
    }
    public void setName(String value){
        name = value;
    }
    public String getName(){
        return name;
    }

    public void setIcnPath(String value){
        icnPath=value;
    }
    public String getIcnPath(){
        return icnPath;
    }

    public void setHostId(int value){
        hostId=value;
    }
    public int getHostId(){
        return hostId;
    }

    public void setgroupId(int value){
        groupId=value;
    }
    public int getgroupId(){
        return groupId;
    }

    public void setBeLive(boolean value){
        beLive=value;
    }
    public boolean getBeLive(){
        return beLive;
    }


}
