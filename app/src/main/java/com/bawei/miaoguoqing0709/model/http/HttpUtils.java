package com.bawei.miaoguoqing0709.model.http;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtils {
    private static HttpUtils httpUtils;
    private HttpUtils(){

    }
    public static HttpUtils getInstance(){
        if (httpUtils==null){
            return new HttpUtils();
        }
        return httpUtils;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data= (String) msg.obj;
            callBack.onsuccess(data);
        }
    };
    private CallBack callBack;
    private HttpURLConnection connection;

    public void getData(final String strUrl, CallBack back){
        this.callBack=back;
        new Thread(new Runnable() {



            @Override
            public void run() {
                try {
                    URL url = new URL(strUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    int code = connection.getResponseCode();
                    if (code==200){
                        InputStream stream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                        StringBuffer buffer = new StringBuffer();
                        String str="";
                        while ((str=reader.readLine())!=null){
                            buffer.append(str);
                        }
                        Message message = handler.obtainMessage();
                        message.obj = buffer.toString();
                        Log.e("aa", "onsuccess: "+buffer.toString());
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
