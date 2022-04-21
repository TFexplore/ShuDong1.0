package com.example.shudong10.second.chatdemo.DemoFragment;

import android.os.Bundle;
import android.renderscript.Sampler;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shudong10.second.chatdemo.data.model.Dialog;
import com.example.shudong10.second.chatdemo.utils.AppUtils;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

public abstract class DemoDialogs extends Fragment
        implements DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog>{
    protected ImageLoader imageLoader;
    protected DialogsListAdapter<Dialog> dialogsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageLoader = (imageView, url, payload) -> Picasso.get().load(Integer.parseInt(url)).into(imageView);//头像加载new File(url),加载本地图片
    }

    @Override
    public void onDialogClick(Dialog dialog) {
        AppUtils.showToast(
                requireActivity(),
                dialog.getDialogName(),
                false);
    }

    @Override
    public void onDialogLongClick(Dialog dialog) {

    }

    public abstract void onPointerCaptureChanged(boolean hasCapture);
}
