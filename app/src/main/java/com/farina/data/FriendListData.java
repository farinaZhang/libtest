package com.farina.data;

import java.util.List;

/**
 * Created by FarinaZhang on 2016/7/21.
 */
public class FriendListData {
    private String groupName;
    private List<FriendEntity> member;

    private static FriendListData listData=null;
    public static FriendListData getInstance(){
        if(listData==null){
            listData = new FriendListData();
        }
        return listData;
    }

    public void setGroupName(String value){
        groupName = value;
    }
    public String getGroupName(){
        return groupName;
    }
    public void setFriendChildList(List<FriendEntity> list){
        member = list;
    }
    public List<FriendEntity> getFriendChildList(){
        return member;
    }
}
