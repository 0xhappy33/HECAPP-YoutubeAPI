package com.personal.project.hec_life.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.project.hec_life.R;
import com.personal.project.hec_life.model.Video;
import com.personal.project.hec_life.utils.DataUtils;

import java.util.ArrayList;

/**
 * Created by Ha Truong on 3/1/2018.
 * This is a HECLife
 * into the com.personal.project.hec_life.adapter
 */

public class VideoAdapter extends BaseAdapter{

    private ArrayList<Video> arrVideo = new ArrayList<>();
    private Context context;
    private DisplayMetrics metrics;

    public VideoAdapter(Context context, ArrayList<Video> arrVideo) {
        this.context = context;
        this.arrVideo = arrVideo;
        metrics = context.getResources().getDisplayMetrics();
    }

    @Override
    public int getCount() {
        return arrVideo.size();
    }

    @Override
    public Object getItem(int position) {
        return arrVideo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.item_view_list_videos, parent, false);
        TextView txtVideoName = rowView.findViewById(R.id.txtNameVideo);
        TextView txtVideoTime = rowView.findViewById(R.id.txtTimeVideo);
        ImageView imgThumb = rowView.findViewById(R.id.imgVideoPicture);

        txtVideoName.setText(arrVideo.get(i).toString());
        txtVideoTime.setText(arrVideo.get(i).toString().substring(0, 10));
        // view thumbnail
        if (metrics.density <= 1.0f){
            DataUtils.setBitmapToImage(arrVideo.get(i).getVideoImageThumnailDefault(), imgThumb);
        }else{
            if(metrics.density <= 1.5f){
                DataUtils.setBitmapToImage(arrVideo.get(i).getVideoImageThumnailMedium(), imgThumb);
            }else{
                DataUtils.setBitmapToImage(arrVideo.get(i).getVideoImageThumnailLarge(), imgThumb);
            }
        }
        return rowView;
    }
}
