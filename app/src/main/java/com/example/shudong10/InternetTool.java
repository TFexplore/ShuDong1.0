package com.example.shudong10;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


import static android.content.ContentValues.TAG;

public class InternetTool {
    private boolean netstatus;
    private static InternetTool INSTANSE;
    private onMsgReturnedListener mlistener;

    private final String mServerIp = "192.168.43.45";
    private final int mServerPort = 8987;
    private InetAddress mSerAddress;
    private DatagramSocket recSocket;
   // private SocketAddress socketAddress;
    private boolean isListening;
    DatagramPacket recPacket;

    private MyViewModle myViewModle;

    public MyViewModle getMyViewModle() {
        return myViewModle;
    }

    public void setMyViewModle(MyViewModle myViewModle) {
        this.myViewModle = myViewModle;
    }

    public static InternetTool getInternetTool(){
        if (INSTANSE == null){
            INSTANSE= new InternetTool();
        }
        return INSTANSE;
    }
    public void setMlistener(onMsgReturnedListener mlistener) {
        this.mlistener = mlistener;
    }
    private InternetTool() {

        try {
            mSerAddress = InetAddress.getByName(mServerIp);
           recSocket = new DatagramSocket(null);
           recSocket.bind(new InetSocketAddress(Integer.parseInt("8088")));
          // socketAddress=new InetSocketAddress(mSerAddress,mServerPort);
         //  conSocket.bind(socketAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setNetstatus(boolean netstatus) {
        this.netstatus = netstatus;
    }

    public boolean getisListening() {
        return isListening;
    }

    public void setListening(boolean listening) {
        isListening = listening;
    }

    public interface onMsgReturnedListener {
        void onMsgReturned(String msg);
        void onError(Exception ex);
    }
    public void recListenUDP() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Log.d(TAG, "run: " + isListening);
                    do {
                        byte[] bytesMsg = new byte[1024];
                        recPacket = new DatagramPacket(bytesMsg, bytesMsg.length);
                        recSocket.receive(recPacket);
                        String recMsg = new String(recPacket.getData(), 0, recPacket.getLength());
                        Log.d(TAG, "run: " + recMsg);
                        MsgDeal.MsgDeal(recMsg,mlistener);


                    } while (isListening);
                    recSocket.close();
                } catch (Exception e) {
                    Log.d(TAG, "run: " + e);
                }
            }
        }.start();

    }
    public void serConnect(){
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                    while (netstatus){
                        Thread.sleep(10000);
                        MsgEntity msgEntity=MsgEntity.getMsgEntity();
                        msgEntity.setType(-1);

                        sendMsg(MsgEntity.toJsonString(msgEntity));

                    }

                } catch (Exception e) {

                }
            }
        }.start();
    }

    public void sendMsg(String msg) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    byte[] bytesMsg = msg.getBytes();
                    DatagramSocket sendSocket = new DatagramSocket();
                    DatagramPacket sendPacket = new DatagramPacket(bytesMsg, bytesMsg.length, mSerAddress, mServerPort);
                    sendSocket.send(sendPacket);
                    sendSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
   /* static void MsgDeal(String msg,onMsgReturnedListener listener) {
        MsgEntity msgEntity = MsgEntity.getMsgEntity(msg);
        switch (msgEntity.getType()){
            case 20:

                break;
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
        }
        listener.onMsgReturned(msg);

    }*/

}
