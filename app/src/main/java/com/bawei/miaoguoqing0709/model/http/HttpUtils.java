package com.bawei.miaoguoqing0709.model.http;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtils {
    private static HttpUtils httpUtils;
    private HttpURLConnection connection;
    private HttpURLConnection connection1;

    private HttpUtils(){

    }
    public static HttpUtils getInstance(){
        if (httpUtils==null){
            return new HttpUtils();
        }
        return httpUtils;
    }

    public String getData(String strUrl){
        try {
            URL url = new URL(strUrl);
            connection1 = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code==200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();
                String str="";
                while ((str=bufferedReader.readLine())!=null){
                    buffer.append(str);
                }
                connection.disconnect();
                bufferedReader.close();
                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return "";
    }
    public void getAsyncTask(String strUrl, final GetBack callBack){
        new AsyncTask<String,Integer,String>(){
            @Override
            protected String doInBackground(String... strings) {
                return getData(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                callBack.getData(s);
            }
        }.execute(strUrl);
    }
    public interface GetBack{
        void getData(String s);
    }
}
