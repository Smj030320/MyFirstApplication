package com.jnu.student;

import android.content.Context;

import java.util.ArrayList;

// 存储任务使用的持久化接口
public interface TaskRepository {
    // 获得所有的任务
    ArrayList<MyTask> loadTaskItems(Context context,String fileName);
    // 获得指定类型的任务
    ArrayList<MyTask> loadTaskItems(Context context,String fileName,String type);
    // 获得指定类型和标签的任务
    ArrayList<MyTask> loadTaskItems(Context context,String fileName,String type,String tag);
    // 保存数据
    void saveTaskItems(Context context,String fileName,ArrayList<MyTask> taskData);
}