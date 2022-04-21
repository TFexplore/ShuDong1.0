package com.example.shudong10;

public class MsgDeal {

    static void MsgDeal(String msg, InternetTool.onMsgReturnedListener listener) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                MsgEntity msgEntity = MsgEntity.getMsgEntity(msg);
                switch (msgEntity.getType()){
                    case 20://匹配
                        if (msgEntity.getValue()==1){//匹配成功
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                        }
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

            }
        }.start();


    }
}
