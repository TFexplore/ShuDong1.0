package com.example.shudong10;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.google.gson.Gson;

public class MyViewModle extends AndroidViewModel {
    MutableLiveData<ClientEntity> mycilent;
    public ClientEntity clientEntity;
    public int onWait;
    public int type;
    public Boolean dialog;
    SavedStateHandle handle;
    public MyViewModle(@NonNull Application application,SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        onWait=-1;
        type=-1;
        dialog=true;
    }

    void load() {
        SharedPreferences shp=getApplication().getSharedPreferences("my_data", Context.MODE_PRIVATE);
        String jsonData=shp.getString("my_data","");
        Gson gson=new Gson();
        MutableLiveData<ClientEntity> clientEntity =new MutableLiveData<>();
        clientEntity.setValue(gson.fromJson(jsonData, ClientEntity.class));
        this.mycilent=clientEntity;
        this.clientEntity=gson.fromJson(jsonData, ClientEntity.class);
        handle.set("my_data", "1");
    }
    void save(){
        SharedPreferences shp=getApplication().getSharedPreferences("my_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=shp.edit();
        ClientEntity clientEntity=mycilent.getValue();
        Gson gson=new Gson();
        String jsonData=gson.toJson(clientEntity);
        editor.putString("my_data",jsonData);
        editor.apply();
    }

    public MutableLiveData<ClientEntity> getMycilent() {
        return mycilent;
    }

    public void setClient(ClientEntity client){
        handle.set("my_data",client);
    }
}
