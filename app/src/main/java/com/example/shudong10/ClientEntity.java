package com.example.shudong10;

import java.util.ArrayList;

public class ClientEntity {
    private String user;
    private String pwd;
    private String name;
    private String email;
    private int sex;

    private int lastTime;//签到时间
    private long lastData;//连续签到天数
    private int lv;//等级
    private int jifen;//积分
    private int exp;//经验

    private ArrayList<String> dialogs;//会话id
    public ClientEntity(){

    }
    public ClientEntity(String user, String pwd, String name, String email, int sex, int lastTime, long lastData, int lv, int jifen, int exp, ArrayList<String> dialogs) {
        this.user = user;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.lastTime = lastTime;
        this.lastData = lastData;
        this.lv = lv;
        this.jifen = jifen;
        this.exp = exp;
        this.dialogs = dialogs;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public long getLastData() {
        return lastData;
    }

    public void setLastData(long lastData) {
        this.lastData = lastData;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getJifen() {
        return jifen;
    }

    public void setJifen(int jifen) {
        this.jifen = jifen;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public ArrayList<String> getDialogs() {
        return dialogs;
    }

    public void setDialogs(ArrayList<String> dialogs) {
        this.dialogs = dialogs;
    }
}
