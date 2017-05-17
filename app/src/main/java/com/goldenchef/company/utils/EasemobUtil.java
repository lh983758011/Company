package com.goldenchef.company.utils;

import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * Created by luo-hao on 2017/5/16.
 */

public class EasemobUtil {

    //注册
    public static boolean registUser(String username, String password) {
        try {
            EMClient.getInstance().createAccount(username, password);
            return true;
        } catch (HyphenateException e) {
            e.printStackTrace();
            return false;
        }
    }

    //登录
    public static void login(String userName, String password) {
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });
    }

    //登出（异步）
    public static void logout() {
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.d("main", "登出聊天服务器成功！");
            }

            @Override
            public void onError(int i, String s) {
                Log.d("main", "登出聊天服务器失败！");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }

    //更换昵称
    public static void updateName(String name) {
        EMClient.getInstance().updateCurrentUserNick(name);
    }

    //添加连接监听器
    public static void addConnectListener() {
        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected(int error) {
                if (error == EMError.USER_REMOVED) {
                    // 显示帐号已经被移除
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    // 显示帐号在其他设备登录
                } else {

                }
            }
        });
    }

}
