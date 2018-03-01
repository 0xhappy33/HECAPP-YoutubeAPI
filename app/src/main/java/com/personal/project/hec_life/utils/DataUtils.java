package com.personal.project.hec_life.utils;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.URL;


/**
 * Created by Ha Truong on 3/1/2018.
 * This is a HECLife
 * into the com.personal.project.hec_life.utils
 */

public class DataUtils {

    public static String getDataFromURL(String url){
        String result = null;
        int CONNECT_TIMEOUT = 6000;
        int SOCKET_TIMEOUT = 60000;

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SOCKET_TIMEOUT);

        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpGet httpGet = new HttpGet(url);
        try{
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null){
                InputStream inputStream = httpResponse.getEntity().getContent();
                result = convertSteamToString(inputStream);
            }
        }catch (ConnectTimeoutException ex){
            result = "Connect time out";
        } catch (SocketException e) {
            result = "Socket time out";
        } catch (IOException e) {
            result = e.getMessage();
        }
        return result;
    }

    private static String convertSteamToString(InputStream inputStream) {
        String line = "";
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            while ((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static Drawable loadImageFromWebOperations(String url){
        try{
            InputStream inputStream = (InputStream) new URL(url).getContent();
            return Drawable.createFromStream(inputStream, "src name");
        }catch (Exception e) {
            System.out.print("Exception "+ e);
            return null;
        }
    }

    public static void setBitmapToImage(final String url, final ImageView imageView){
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Drawable flag = (Drawable) message.obj;
                if (flag != null){
                    imageView.setImageDrawable(flag);
                }
                return false;
            }
        });
        try{
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Drawable drawable = DataUtils.loadImageFromWebOperations(url);
                    Message msg = new Message();
                    msg.obj = drawable;
                    handler.sendMessage(msg);
                }
            });
            thread.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
