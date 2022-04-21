package com.example.shudong10;

public class MyData {
    private ClientEntity clientEntity;
    private long lastData;
    private int lv;
    private int jifen;
    private int lastTime;

    public MyData(ClientEntity clientEntity, long lastData, int lv, int jifen, int lastTime) {
        this.clientEntity = clientEntity;
        this.lastData = lastData;
        this.lv = lv;
        this.jifen = jifen;
        this.lastTime = lastTime;
    }
    public MyData(){

    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
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

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }
}
