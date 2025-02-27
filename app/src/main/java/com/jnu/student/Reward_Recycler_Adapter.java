package com.jnu.student;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Reward_Recycler_Adapter extends RecyclerView.Adapter<Reward_Recycler_Adapter.MyViewHolder>{
    private Context context;
    private ArrayList<MyReward> rewardList;
    private Reward_Repository rewardRepository;
    private final String FILE_Reward_NAME = "rewardData.ser";
    public Reward_Recycler_Adapter(Context context, ArrayList<MyReward> rewardList) {
        this.context = context;
        this.rewardList = rewardList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.reward_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyReward myReward = rewardList.get(position);
        holder.rewardTitle.setText(myReward.getRewardTitle());
        boolean isOnce = false;
        if(myReward.getRewardType().equals("单次")){
            holder.rewardFinish.setText(myReward.getRewardFinish()+"/1");
            isOnce = true;
        }
        else{
            holder.rewardFinish.setText(myReward.getRewardFinish()+"/∞");
            isOnce = false;
        }

        holder.rewardSub.setText("-"+myReward.getRewardPoint());
        boolean finalIsOnce = isOnce;
        holder.rewardSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 改变数据
                if((!finalIsOnce) || (finalIsOnce && myReward.getRewardFinish() == 0)){
                    rewardRepository = new Reward_Repository_Lmpl();
                    ArrayList<MyReward> rewardAllList = rewardRepository.loadRewardItems(context.getApplicationContext(), FILE_Reward_NAME);
                    for(MyReward reward:rewardAllList){
                        if(reward.getRewardTime().equals(myReward.getRewardTime())){
                            reward.setRewardFinish(reward.getRewardFinish()+1);
                            rewardRepository.saveRewardItems(context, FILE_Reward_NAME,rewardAllList);
                            // 发布广播
                            Intent intent = new Intent("MY_CUSTOM_ACTION");
                            intent.putExtra("data", "invalidate");
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        }
                    }
                    myReward.setRewardFinish(myReward.getRewardFinish()+1);
                    if(myReward.getRewardType().equals("单次")){
                        holder.rewardFinish.setText(myReward.getRewardFinish()+"/1");
                    }
                    else{
                        holder.rewardFinish.setText(myReward.getRewardFinish()+"/∞");
                    }
                }
                // 创建 AlphaAnimation 对象，设置透明度从 1 到 0，再从 0 到 1，实现闪烁效果
                Animation animation = new AlphaAnimation(1, 0);
                animation.setDuration(300); // 设置动画时长
                animation.setRepeatMode(Animation.REVERSE); // 设置反向重复
                animation.setRepeatCount(1); // 设置重复次数

                // 设置动画监听器，确保在动画结束后恢复 TextView 的透明度
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // 在动画开始时可以添加一些处理逻辑
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // 在动画结束时将透明度恢复到初始状态
                        holder.rewardSub.setAlpha(1.0f);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // 在动画重复时可以添加一些处理逻辑
                    }
                });
                // 启动动画
                holder.rewardSub.startAnimation(animation);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rewardList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView rewardTitle;
        TextView rewardFinish;
        TextView rewardSub;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rewardTitle = itemView.findViewById(R.id.Reward_Item_Title);
            rewardFinish = itemView.findViewById(R.id.Reward_Num_Finish);
            rewardSub = itemView.findViewById(R.id.Reward_Num_Sub);
            // 设置监听者为自己
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(3,0,this.getAdapterPosition(),"修改");
            menu.add(3,1,this.getAdapterPosition(),"删除");
        }
    }
}
