package com.personal.project.hec_life.model;

/**
 * Created by Ha Truong on 3/1/2018.
 * This is a HECLife
 * into the com.personal.project.hec_life.model
 */

public class Video {
    private String videoID;
    private String videoName;
    private String videoDescription;
    private String videoUploadTime;
    private String videoViews;
    private String videoImageThumnailDefault;
    private String videoImageThumnailMedium;
    private String videoImageThumnailLarge;

    public String getVideoID() {
        return videoID;
    }

    public Video setVideoID(String videoID) {
        this.videoID = videoID;
        return this;
    }

    public String getVideoName() {
        return videoName;
    }

    public Video setVideoName(String videoName) {
        this.videoName = videoName;
        return this;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public Video setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
        return this;
    }

    public String getVideoUploadTime() {
        return videoUploadTime;
    }

    public Video setVideoUploadTime(String videoUploadTime) {
        this.videoUploadTime = videoUploadTime;
        return this;
    }

    public String getVideoViews() {
        return videoViews;
    }

    public Video setVideoViews(String videoViews) {
        this.videoViews = videoViews;
        return this;
    }

    public String getVideoImageThumnailDefault() {
        return videoImageThumnailDefault;
    }

    public Video setVideoImageThumnailDefault(String videoImageThumnailDefault) {
        this.videoImageThumnailDefault = videoImageThumnailDefault;
        return this;
    }

    public String getVideoImageThumnailMedium() {
        return videoImageThumnailMedium;
    }

    public Video setVideoImageThumnailMedium(String videoImageThumnailMedium) {
        this.videoImageThumnailMedium = videoImageThumnailMedium;
        return this;
    }

    public String getVideoImageThumnailLarge() {
        return videoImageThumnailLarge;
    }

    public Video setVideoImageThumnailLarge(String videoImageThumnailLarge) {
        this.videoImageThumnailLarge = videoImageThumnailLarge;
        return this;
    }
}
