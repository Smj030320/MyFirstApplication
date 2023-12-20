package com.jnu.student;

import android.content.Context;

import java.util.ArrayList;

public interface Reward_Repository {
    ArrayList<MyReward> loadRewardItems(Context context, String fileName);
    // 获得指定类型的任务
    void saveRewardItems(Context context, String fileName, ArrayList<MyReward> rewardData);
}