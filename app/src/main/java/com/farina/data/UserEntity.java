package com.farina.data;

/**
 * created by farinaZhang on 2016-05-26
 * the class is for save user data and state
*/

public class UserEntity {
    private  int id;
    private  String nikeName; //昵称
    private  String iconPath =null;
    private  String countNumber; //用户账号
    private  String phoneNumber;
    private  String mailNumber;
    private  String qqNumber;
    private  String weixinNumber;

    public int getId(){
        return id;
    }
    public void setId(int value){
        id = value;
    }
    public String getNikeName(){
        return nikeName;
    }
    public void setNikeName(String value){
        nikeName = value;
    }
    public String getIconPath(){
        return iconPath;
    }
    public void setIconPath(String value){
        iconPath = value;
    }
    public String getCountNumber(){
        return countNumber;
    }
    public void setCountNumber(String value){
        countNumber = value;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String value){
        phoneNumber = value;
    }
    public String getMailNumber(){
        return mailNumber;
    }
    public void setMailNumber(String value){
        mailNumber = value;
    }
    public String getQQNumber(){
        return qqNumber;
    }
    public void setQQNumber(String value){
        qqNumber = value;
    }
    public String getWeixinNumber(){
        return weixinNumber;
    }
    public void setWeixinNumber(String value){
        weixinNumber =value;
    }

}