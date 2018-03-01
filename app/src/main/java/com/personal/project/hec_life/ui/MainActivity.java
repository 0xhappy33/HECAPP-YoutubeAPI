package com.personal.project.hec_life.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.personal.project.hec_life.R;
import com.personal.project.hec_life.adapter.VideoAdapter;
import com.personal.project.hec_life.model.Video;
import com.personal.project.hec_life.utils.DataUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String API_KEY = "AIzaSyAp8By69Sj9osJ_tEGTMLVvsRih4kNDan0";
    private ListView lvListVideos;
    private ArrayList<Video> arrVideos = new ArrayList<>();
    private VideoAdapter videoAdapter;
    private ProgressBar prgbLoadingVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadDataFromServer();
        setEventForListViewVideo();
    }

    private void setEventForListViewVideo() {
        lvListVideos.setOnItemClickListener(this);
    }

    private void loadDataFromServer() {
        prgbLoadingVideo.setVisibility(View.VISIBLE);
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                String channelID = "UCOEMEjL1GBJ41uCWur5VXWQ";
                String url = "https://www.googleapis.com/youtube/v3/search?key="
                        + API_KEY + "@channelId="
                        + channelID
                        + "&part=snippet, id&order=date&maxResults=50";
                String result = DataUtils.getDataFromURL(url);
                Message msg = new Message();
                msg.obj = result;
                // send message to handler
                netWorkHandler.sendMessage(msg);
            }
        };
        thread.start();
    }
    @SuppressLint("HandlerLeak")
    Handler netWorkHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = msg.obj.toString();

            // hide progress bar
            prgbLoadingVideo.setVisibility(View.GONE);
            lvListVideos.setVisibility(View.VISIBLE);
            ArrayList<Video> videos = parserData(result);
            // update all video
            arrVideos.addAll(videos);
            if (videoAdapter != null){
                videoAdapter = new VideoAdapter(MainActivity.this, arrVideos);
                lvListVideos.setAdapter(videoAdapter);
            }else{
                videoAdapter.notifyDataSetChanged();
            }
        }
    };

    private ArrayList<Video> parserData(String result) {
        ArrayList<Video> arrVideos = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(result);
            JSONArray arrItems = root.getJSONArray("items");
            for (int i = 0; i < arrItems.length(); i++){
                JSONObject videoObject = (JSONObject) arrItems.get(i);
                Video video = new Video();
                if(videoObject.getJSONObject("id").has("videoId")){
                    video.setVideoID(videoObject.getJSONObject("id").getString("videoId"));
                    JSONObject snippet = videoObject.getJSONObject("snippet");
                    video.setVideoName(snippet.getString("title"));
                    video.setVideoDescription(snippet.getString("description"));
                    video.setVideoUploadTime(snippet.getString("publishedAt"));

                    JSONObject thumbnail = snippet.getJSONObject("thumbnails");
                    video.setVideoImageThumnailDefault(thumbnail.getJSONObject("default").getString("url"));
                    video.setVideoImageThumnailMedium(thumbnail.getJSONObject("medium").getString("url"));
                    video.setVideoImageThumnailLarge(thumbnail.getJSONObject("high").getString("url"));
                    arrVideos.add(video);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrVideos;
    }

    private void initView() {
        lvListVideos = findViewById(R.id.lvListVideos);
        prgbLoadingVideo = findViewById(R.id.pgbLoadVideo);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
