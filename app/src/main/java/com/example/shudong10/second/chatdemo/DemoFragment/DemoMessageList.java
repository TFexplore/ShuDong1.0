package com.example.shudong10.second.chatdemo.DemoFragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shudong10.R;
import com.example.shudong10.second.chatdemo.data.fixtures.MessagesFixtures;
import com.example.shudong10.second.chatdemo.data.model.Message;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public abstract class DemoMessageList extends Fragment
        implements MessagesListAdapter.SelectionListener,
        MessagesListAdapter.OnLoadMoreListener{
    private static final int TOTAL_MESSAGES_COUNT = 100;

    protected final String senderId = "0";
    protected ImageLoader imageLoader;
    protected MessagesListAdapter<Message> messagesAdapter;

    private Date lastLoadedDate;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageLoader = (imageView, url, payload) -> Picasso.get().load(url).into(imageView);//聊天图片加载
    }
    @Override
    public void onStart() {
        super.onStart();
        messagesAdapter.addToStart(MessagesFixtures.getTextMessage(), true);
        messagesAdapter.disableSelectionMode();
    }



    @Override
    public void onLoadMore(int page, int totalItemsCount) {
        Log.i("TAG", "onLoadMore: " + page + " " + totalItemsCount);
        if (totalItemsCount < TOTAL_MESSAGES_COUNT) {
            loadMessages();
        }
    }



    protected void loadMessages() {
        //imitation of internet connection
        new Handler().postDelayed(() -> {
            ArrayList<Message> messages = MessagesFixtures.getMessages(lastLoadedDate);
            lastLoadedDate = messages.get(messages.size() - 1).getCreatedAt();
            messagesAdapter.addToEnd(messages, false);
        }, 1000);
    }

    private MessagesListAdapter.Formatter<Message> getMessageStringFormatter() {
        return message -> {
            String createdAt = new SimpleDateFormat("MMM d, EEE 'at' h:mm a", Locale.getDefault())
                    .format(message.getCreatedAt());

            String text = message.getText();
            if (text == null) text = "[attachment]";

            return String.format(Locale.getDefault(), "%s: %s (%s)",
                    message.getUser().getName(), text, createdAt);
        };
    }
}
