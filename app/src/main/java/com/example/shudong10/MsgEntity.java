package com.example.shudong10;

import com.google.gson.Gson;

public class MsgEntity {
    private String user;
    private String pwd;
    private String name;
    private int sex;
    private String email;

    private int type;
    private int value;
    private String num;
    private String msg;
    private String otheruser;

    //private transient static MsgEntity msgEntity;


    public static MsgEntity getMsgEntity(){

        return new MsgEntity();
    }

   public  MsgEntity(String name,String msg,int value,String data){
        this.name=name;
        this.msg=msg;
        this.value=value;
        this.num=data;

   }


    private MsgEntity() {

    }
    public static String toJsonString(MsgEntity msgEntity){
        Gson gson=new Gson();
        return gson.toJson(msgEntity);
    }
    public static MsgEntity getMsgEntity(String jsonMsg){
        Gson gson=new Gson();
        return gson.fromJson(jsonMsg,MsgEntity.class);
    }
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOtheruser() {
        return otheruser;
    }

    public void setOtheruser(String otheruser) {
        this.otheruser = otheruser;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    void setSex(int sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMsg() {
        return msg;
    }

    public int getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }

    public int getType() {
        return type;
    }
}
