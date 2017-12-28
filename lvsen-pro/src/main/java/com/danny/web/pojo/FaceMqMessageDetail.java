package com.danny.web.pojo;

public class FaceMqMessageDetail {
    private Long personId;
    private String birthday;
    private String createTime;
    private String idcard;
    private String memo;
    private String name;
    private String sex;
    private String nation;
    private String libName;
    private String targetImage;
    private Long libId;
    private int score;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public String getTargetImage() {
        return targetImage;
    }

    public void setTargetImage(String targetImage) {
        this.targetImage = targetImage;
    }

    public Long getLibId() {
        return libId;
    }

    public void setLibId(Long libId) {
        this.libId = libId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
