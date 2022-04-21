package com.example.shudong10;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static android.content.ContentValues.TAG;

public class Internet {


    public Internet()  {

    }
     public static void sendUdpData(String Msg, Handler myHandler,onMsgReturnedListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    DatagramSocket socket = new DatagramSocket(8888);
                    byte[] rec=new byte[1024];
                    byte[] bytesToSend=Msg.getBytes();
                    //IP是服务器地址
                    InetAddress serverAddress = InetAddress.getByName("fe80::51bc:8f6f:b961:5fb3%7");
                    //Log.d(TAG, "run: "+serverAddress);
                    //PORT是服务器端口号
                    int serverPort = 8987;
                    InetAddress myAddress =InetAddress.getByName("192.168.43.45");


                    socket.setSoTimeout(8000);

                    //sendPacket发送的数据包
                    DatagramPacket sendPacket = new DatagramPacket(bytesToSend,bytesToSend.length,serverAddress,serverPort);
                    //receivePacket返回的数据包
                    DatagramPacket receivePacket = new DatagramPacket(rec,rec.length);

                    int tries = 0;
                    boolean receivedResponse = false;
                    do{
                        socket.send(sendPacket);
                        try{
                            socket.receive(receivePacket);
                            if(!receivePacket.getAddress().equals(serverAddress)){
                                throw new IOException("接收到未知来源的包");
                            }
                            receivedResponse = true;
                        }catch(InterruptedIOException e){
                            tries +=1;
                            Log.i("TAG","Received:"+"Time out,"+(5-tries));
                        }
                        Log.d(TAG, "server: "+sendPacket.getAddress()+" "+sendPacket.getPort());
                    }while((!receivedResponse)&&(tries < 5));

                    if(receivedResponse){
                        //在这里可以拿到服务器返回的数据
                        //byte[] ackbyte="ACK".getBytes();
                        //DatagramPacket ackPacket = new DatagramPacket(ackbyte,ackbyte.length,serverAddress,serverPort);
                       //socket.send(ackPacket);
                        String receiveData=new String(rec,0,receivePacket.getLength());
                        Internet.MsgDeal(receiveData,myHandler,listener);
                        Log.i("TAG","返回数据:"+receiveData);

                    }else{
                        Log.i("TAG","NO response -- giving up");
                    }
                    socket.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public interface onMsgReturnedListener {
        void onMsgReturned(MsgEntity msgEntity);

        void onError(Exception ex);
    }
    static void MsgDeal(String msg,Handler myHandler,onMsgReturnedListener listener){
        Message message=myHandler.obtainMessage();
        MsgEntity msgEntity=new Gson().fromJson(msg,MsgEntity.class);
        switch (msgEntity.getType()){
            case 0:
                if (msgEntity.getValue()==-1){
                    message.arg1=-1;
                }else if (msgEntity.getValue()==0){
                    message.arg1=0;
                }else {
                    listener.onMsgReturned(msgEntity);
                    message.arg1=1;
                }
                break;
            case 1:
                if (msgEntity.getValue()==1) {
                    listener.onMsgReturned(msgEntity);
                    message.arg1 = 1;
                }
                else message.arg1=0;
                break;

        }

       myHandler.sendMessage(message);
    }

}

