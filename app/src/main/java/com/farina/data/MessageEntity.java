package com.farina.data;

/**
 * Created by FarinaZhang on 2016/7/21.
 */
public class MessageEntity {
    private int id;
    private String message;
    private int fromId;
    private int toId;
    private String type;
    private String time;

    private MessageEntity entity=null;

    public MessageEntity getInstance(){
        if(entity ==null){
            entity = new MessageEntity();
        }
        return entity;
    }

    public void setId(int value){
        id=value;
    }
    public int getId(){
        return id;
    }
    public void setMessageStr(String value){
        message = value;
    }
    public String getMessageStr(){
        return message;
    }

    public void setFromId(int value){
        fromId=value;
    }
    public int getFromId(){
        return fromId;
    }

    public void setToId(int value){
        toId=value;
    }
    public int getToId(){
        return toId;
    }

    public void setType(String value){
        type=value;
    }
    public String getType(){
        return type;
    }

    public void setTime(String value){
        time=value;
    }
    public String getTime(){
        return time;
    }


}
