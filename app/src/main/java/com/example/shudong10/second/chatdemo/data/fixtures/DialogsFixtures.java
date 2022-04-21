package com.example.shudong10.second.chatdemo.data.fixtures;

import com.example.shudong10.second.chatdemo.data.model.Dialog;
import com.example.shudong10.second.chatdemo.data.model.Message;
import com.example.shudong10.second.chatdemo.data.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * Created by Anton Bevza on 07.09.16.
 */
public final class DialogsFixtures extends FixturesData {
    private DialogsFixtures() {
        throw new AssertionError();
    }

    public static ArrayList<Dialog> getDialogs() {//会话列表
        ArrayList<Dialog> chats = new ArrayList<>();



        return chats;
    }

    private static Dialog getDialog(int i, Date lastMessageCreatedAt) {
        ArrayList<User> users = getUsers();
        return new Dialog(//返回会话列表
                getRandomId(),
                users.size() > 1 ? groupChatTitles.get(users.size() - 2) : users.get(0).getName(),
                users.size() > 1 ? groupChatImages.get(users.size() - 2) : getRandomAvatar(),
                users,
                getMessage(lastMessageCreatedAt),
                i < 3 ? 3 - i : 0);
    }

    private static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(getUser());

        return users;
    }

    private static User getUser() {//返回对话用户
        User user =new User();

        return user;
    }

    private static Message getMessage(final Date date) {
        return new Message(
                getRandomId(),
                getUser(),
                getRandomMessage(),
                date);
    }
}
