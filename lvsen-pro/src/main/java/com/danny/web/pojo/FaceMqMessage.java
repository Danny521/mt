package com.danny.web.pojo;

public class FaceMqMessage {
    private Long _id;
    private String alarmId;
    private Long cameraId;
    private String cameraName;
    private String captureImage;
    private String panoramaImage;
    private String absTime;
    private String captureTime;
    private String creater;
    private String orgids;
    private String detail;
    private int alarmLevel;
    private int alarmType;
    private int alarmStatus;
    private int dealStatus;
    private Long taskId;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;
    }

    public Long getCameraId() {
        return cameraId;
    }

    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getCaptureImage() {
        return captureImage;
    }

    public void setCaptureImage(String captureImage) {
        this.captureImage = captureImage;
    }

    public String getPanoramaImage() {
        return panoramaImage;
    }

    public void setPanoramaImage(String panoramaImage) {
        this.panoramaImage = panoramaImage;
    }

    public String getAbsTime() {
        return absTime;
    }

    public void setAbsTime(String absTime) {
        this.absTime = absTime;
    }

    public String getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(String captureTime) {
        this.captureTime = captureTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getOrgids() {
        return orgids;
    }

    public void setOrgids(String orgids) {
        this.orgids = orgids;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public int getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    public int getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(int alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public int getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(int dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
