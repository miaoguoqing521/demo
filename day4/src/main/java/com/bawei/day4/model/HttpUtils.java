package com.bawei.day4.model;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
            String da= (String) msg.obj;
            callback.ondata(da);
        }
    };
    private CallBack callback;
    public void getData(final String strUrl, CallBack back){
        this.callback=back;
        new Thread(new Runnable() {

            private HttpURLConnection connection;

            @Override
            public void run() {
                try {
                    URL url = new URL(strUrl);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
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
                        message.obj=buffer.toString();
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
