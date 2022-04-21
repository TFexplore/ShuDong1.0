package com.example.shudong10.UITool;

import android.widget.TextView;

import android.widget.TextView;

    public class textTool {


        private static TextView tv;
        private static String s;
        private static int length;
        private static long time;
        private static long delay;
        static int n = 0;
        private static int nn;


        public textTool(TextView tv, String s, long time,long delay) {
            this.tv = tv;//textview
            this.s = s;//字符串
            this.time = time;//间隔时间
            this.length = s.length();
            this.delay=delay;
            startTv(n);//开启线程
        }


        public static void startTv(final int n) {

            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final String stv = s.substring(0, n);//截取要填充的字符串
                                tv.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv.setText(stv);
                                    }
                                });
                                Thread.sleep(time);//休息片刻
                                nn = n + 1;//n+1；多截取一个
                                if (nn <= length) {//如果还有汉字，那么继续开启线程，相当于递归的感觉
                                    startTv(nn);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }


                        }
                    }

            ).start();


        }
    }