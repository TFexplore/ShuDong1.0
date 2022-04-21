package com.example.shudong10.first;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shudong10.MsgEntity;
import com.example.shudong10.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    List<MsgEntity> msgEntities=new ArrayList<>();

    public RecyclerAdapter() {
     msgEntities.add(new MsgEntity("吃瓜群众","启动已安装的程序仅仅是最基础的功能，uTools 最大的特点就是拥有强大的插件系统。\n" +
             "\n" +
             "现在已有 100+ 的插件供你选择，每个插件解决一个具体场景的问题，简洁美观、即用即走。",144,"今天12:21"));
     msgEntities.add(new MsgEntity("热心网友","输入 插件中心 进入插件管理，你就可以根据自己的需求挑选安装，组合成自己最称手的工具合集，为各种日常操作提供便利，不断产生的新插件，也将为你带来无限可能。",
             187,"今天15:22"));
        msgEntities.add(new MsgEntity("热心网友","输入 插件中心 进入插件管理，你就可以根据自己的需求挑选安装，组合成自己最称手的工具合集，为各种日常操作提供便利，不断产生的新插件，也将为你带来无限可能。",
                187,"今天15:22"));

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView;
        itemView=layoutInflater.inflate(R.layout.cell_normal,parent,false);

        final MyViewHolder holder=new MyViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
       MsgEntity msgEntity=msgEntities.get(position);
       holder.itemView.setTag(R.id.word_key,msgEntity);
       holder.name.setText(msgEntity.getName());
       holder.data.setText(msgEntity.getNum());
       holder.zan.setText(String.valueOf(msgEntity.getValue()));
       holder.content.setText(msgEntity.getMsg());
        Log.d(TAG, "onBindViewHolder: "+getItemCount());
    }



    @Override
    public int getItemCount() {
        return msgEntities.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{//数据库表格字段与控键关联
     TextView name,data,zan,content;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name=itemView.findViewById(R.id.userName);
            this.data=itemView.findViewById(R.id.data);
            this.zan=itemView.findViewById(R.id.dianzan);
            this.content=itemView.findViewById(R.id.content);

        }
    }
}
