package com.jnu.student;

import java.io.Serializable;

public class MyReward implements Serializable {
    private String rewardTime;
    private String rewardTitle;
    private int rewardPoint;
    private int rewardFinish;
    private String rewardType;
    private String rewardTag;

    public MyReward() {
    }

    public MyReward(String rewardTime, String rewardTitle, int rewardPoint, int rewardFinish, String rewardType, String rewardTag) {
        this.rewardTime = rewardTime;
        this.rewardTitle = rewardTitle;
        this.rewardPoint = rewardPoint;
        this.rewardFinish = rewardFinish;
        this.rewardType = rewardType;
        this.rewardTag = rewardTag;
    }

    public String getRewardTime() {
        return rewardTime;
    }

    public void setRewardTime(String rewardTime) {
        this.rewardTime = rewardTime;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public int getRewardFinish() {
        return rewardFinish;
    }

    public void setRewardFinish(int rewardFinish) {
        this.rewardFinish = rewardFinish;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getRewardTag() {
        return rewardTag;
    }

    public void setRewardTag(String rewardTag) {
        this.rewardTag = rewardTag;
    }

    @Override
    public String toString() {
        return "MyReward{" +
                "rewardTime='" + rewardTime + '\'' +
                ", rewardTitle='" + rewardTitle + '\'' +
                ", rewardPoint=" + rewardPoint +
                ", rewardFinish=" + rewardFinish +
                ", rewardType='" + rewardType + '\'' +
                ", rewardTag='" + rewardTag + '\'' +
                '}';
    }
}