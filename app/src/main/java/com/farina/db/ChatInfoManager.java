package com.farina.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.farina.data.FriendEntity;
import com.farina.data.FriendListData;
import com.farina.data.GroupEntity;
import com.farina.data.MessageEntity;
import com.farina.data.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FarinaZhang on 2016/7/14.
 */
public class ChatInfoManager {
    private Context mContext;
    private ChatDBhelper mDBHelper;

    //table user string
    private final String USER_TABLE_NAME="users";
    private final String USER_ID="id";
    private final String USER_COUNT_NUMBER="countNumber";
    private final String USER_NIKENAME="nikeName";
    private final String USER_ICON_PATH="iconPath";
    private final String USER_PHONE_NUMBER="phoneNumber";
    private final String USER_MAIL_ADDR="mailAddr";
    private final String USER_QQ_NUMBER="qqNumber";
    private final String USER_WEIXIN_NUMBER="weixinNumber";

    //table friends string
    private final String FRIEND_TABLE_NAME="friends";
    private final String FRIEND_ID="id";
    private final String FRIEND_HOST_ID="hostId";
    private final String FRIEND_GROUP_ID="groupId";
    private final String FRIEND_NAME="friendName";
    private final String FRIEND_ICON_PATH="iconPath";
    private final String FRIEND_BELIVE="beLive";

    //table groups string
    private final String GROUP_TABLE_NAME="groups";
    private final String GROUP_ID="id";
    private final String GROUP_HOST_ID="hostId";
    private final String GROUP_NAME="groupName";

    //table messages string
    private final String MESSAGE_TABLE_NAME="messages";
    private final String MESSAGE_ID="id";
    private final String MESSAGE_MESSAGE="message";
    private final String MESSAGE_FROM_ID="fromId";
    private final String MESSAGE_TO_ID="toId";
    private final String MESSAGE_READED="readed";
    private final String MESSAGE_TYPE="type";
    private final String MESSAGE_TIME="time";

   public ChatInfoManager(Context context){
       mContext = context;
       mDBHelper = ChatDBhelper.getInstance(mContext);

   }
    public void addUser(UserEntity user){

        SQLiteDatabase db = null;
        try {
            db = mDBHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USER_ID,user.getId());
            values.put(USER_COUNT_NUMBER,user.getCountNumber());
            values.put(USER_NIKENAME,user.getNikeName());
            values.put(USER_ICON_PATH,user.getIconPath());
            values.put(USER_PHONE_NUMBER,user.getPhoneNumber());
            values.put(USER_MAIL_ADDR,user.getMailNumber());
            values.put(USER_QQ_NUMBER,user.getQQNumber());
            values.put(USER_WEIXIN_NUMBER,user.getWeixinNumber());
            db.insert(USER_TABLE_NAME, null, values);

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        } finally {
            db.close();
        }
    }

    public UserEntity getUserData(int index){
        UserEntity user= new UserEntity();
        SQLiteDatabase db =null;
        Cursor cursor = null;

        try {
            db = mDBHelper.getReadableDatabase();
            cursor = db.query(USER_TABLE_NAME, null, USER_ID + "=" + index, null, null, null, null);
            if (cursor != null) {
                user.setId(index);
                user.setCountNumber(cursor.getString(cursor.getColumnIndex(USER_COUNT_NUMBER)));
                user.setNikeName(cursor.getString(cursor.getColumnIndex(USER_NIKENAME)));
                user.setIconPath(cursor.getString(cursor.getColumnIndex(USER_ICON_PATH)));
                user.setPhoneNumber(cursor.getString(cursor.getColumnIndex(USER_PHONE_NUMBER)));
                user.setMailNumber(cursor.getString(cursor.getColumnIndex(USER_MAIL_ADDR)));
                user.setQQNumber(cursor.getString(cursor.getColumnIndex(USER_QQ_NUMBER)));
                user.setWeixinNumber(cursor.getString(cursor.getColumnIndex(USER_WEIXIN_NUMBER)));
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return user;
    }
    public List<UserEntity> getUserList(){
        List<UserEntity> list =new ArrayList<UserEntity>();
        SQLiteDatabase db =null;
        Cursor cursor=null;

        try{
            db=mDBHelper.getReadableDatabase();
            cursor=db.query(USER_TABLE_NAME,new String[]{USER_ID,USER_COUNT_NUMBER,USER_NIKENAME,USER_ICON_PATH},null,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()) {
                    UserEntity  user= new UserEntity();

                    user.setId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
                    user.setCountNumber(cursor.getString(cursor.getColumnIndex(USER_COUNT_NUMBER)));
                    user.setNikeName(cursor.getString(cursor.getColumnIndex(USER_NIKENAME)));
                    user.setIconPath(cursor.getString(cursor.getColumnIndex(USER_ICON_PATH)));
                    list.add(user);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return list;

    }

    public void addFriend(FriendEntity friend){
        SQLiteDatabase db=null;

        try{
            db=mDBHelper.getWritableDatabase();
            if(db==null)return ;

            ContentValues values = new ContentValues();
            values.put(FRIEND_ID,friend.getId());
            values.put(FRIEND_HOST_ID,friend.getHostId());
            values.put(FRIEND_GROUP_ID,friend.getgroupId());
            values.put(FRIEND_NAME,friend.getName());
            values.put(FRIEND_ICON_PATH,friend.getIcnPath());
            values.put(FRIEND_BELIVE,friend.getBeLive());

            db.insert(FRIEND_TABLE_NAME,null,values);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public List<FriendListData> getFriendList(){
        SQLiteDatabase db=null;
        List<FriendListData> friendList=new ArrayList<FriendListData>();
        List<GroupEntity> groupList= getGroupList();

        try{
            db = mDBHelper.getWritableDatabase();
            if (db == null) return null;
            for(int i=0;i<groupList.size();i++) {
                FriendListData friendGroup= new FriendListData();
                String groupName = groupList.get(i).getName();
                friendGroup.setGroupName(groupName);

                List<FriendEntity> groupChild=new ArrayList<FriendEntity>();

                Cursor cursor = db.query(FRIEND_TABLE_NAME, null, GROUP_NAME+"="+groupName, null, null, FRIEND_ID + "ASC", null);
                while (cursor.moveToNext()) {
                    FriendEntity entity = new FriendEntity();
                    entity.setId(cursor.getInt(cursor.getColumnIndex(FRIEND_ID)));
                    entity.setgroupId(cursor.getInt(cursor.getColumnIndex(FRIEND_GROUP_ID)));
                    entity.setHostId(cursor.getInt(cursor.getColumnIndex(FRIEND_HOST_ID)));
                    entity.setName(cursor.getString(cursor.getColumnIndex(FRIEND_NAME)));
                    entity.setIcnPath(cursor.getString(cursor.getColumnIndex(FRIEND_ICON_PATH)));
                    String beLive = cursor.getString(cursor.getColumnIndex(FRIEND_BELIVE));
                    entity.setBeLive(beLive.equals("1"));
                    groupChild.add(entity);
                }
                friendGroup.setFriendChildList(groupChild);
                friendList.add(friendGroup);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
        return friendList;
    }

    public List<GroupEntity> getGroupList(){
        List<GroupEntity> groupList = null;
        SQLiteDatabase db=null;

        try{
            db= mDBHelper.getReadableDatabase();
            Cursor cursor =db.query(GROUP_TABLE_NAME,null,null,null,null,null,null);
            while(cursor.moveToNext()){
                GroupEntity entity =new GroupEntity();
                entity.setId(cursor.getInt(cursor.getColumnIndex(GROUP_ID)));
                entity.setHostId(cursor.getInt(cursor.getColumnIndex(GROUP_HOST_ID)));
                entity.setGroupName(cursor.getString(cursor.getColumnIndex(GROUP_NAME)));

                groupList.add(entity);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
        return groupList;
    }

    public void addGroupMember(GroupEntity group){
        SQLiteDatabase db=null;

        try{
            db=mDBHelper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(GROUP_ID,group.getId());
            values.put(GROUP_HOST_ID,group.getHostId());
            values.put(GROUP_NAME,group.getName());
            db.insert(GROUP_TABLE_NAME,null,values);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

    public List<MessageEntity> getMessageList(String loadId){
        List<MessageEntity> list = new ArrayList<MessageEntity>();
        SQLiteDatabase db=null;
        Cursor cursor=null;
        try{
            db=mDBHelper.getReadableDatabase();

            cursor=db.query(MESSAGE_TABLE_NAME,null,
                    MESSAGE_FROM_ID+"=?,"+MESSAGE_TO_ID+"=?",
                    new String[]{loadId},
                    null,
                    loadId,MESSAGE_TIME+"DES",null);
            while(cursor.moveToNext()){
                MessageEntity entity =new MessageEntity();
                entity.setId(cursor.getInt(cursor.getColumnIndex(MESSAGE_ID)));
                entity.setFromId(cursor.getInt(cursor.getColumnIndex(MESSAGE_FROM_ID)));
                entity.setToId(cursor.getInt(cursor.getColumnIndex(MESSAGE_TO_ID)));
                entity.setMessageStr(cursor.getString(cursor.getColumnIndex(MESSAGE_MESSAGE)));
                entity.setType(cursor.getString(cursor.getColumnIndex(MESSAGE_TYPE)));
                entity.setTime(cursor.getString(cursor.getColumnIndex(MESSAGE_TIME)));

                list.add(entity);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }

        return list;
    }

    public void setMessageItem(MessageEntity message){
        SQLiteDatabase db=null;

        try{
            db=mDBHelper.getWritableDatabase();
            if(db==null)return;

            ContentValues values = new ContentValues();
            values.put(MESSAGE_ID,message.getId());
            values.put(MESSAGE_FROM_ID,message.getFromId());
            values.put(MESSAGE_TO_ID,message.getToId());
            values.put(MESSAGE_MESSAGE,message.getMessageStr());
            values.put(MESSAGE_TYPE,message.getType());
            values.put(MESSAGE_TIME,message.getTime());
            db.insert(MESSAGE_TABLE_NAME,null,values);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }
    }

}
