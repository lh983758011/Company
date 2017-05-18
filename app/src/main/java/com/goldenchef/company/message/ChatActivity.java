package com.goldenchef.company.message;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.goldenchef.company.R;
import com.hyphenate.easeui.EaseConstant;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by luo-hao on 2017/5/17.
 * <p>
 * 环信聊天界面
 */

public class ChatActivity extends FragmentActivity implements EasyPermissions.PermissionCallbacks {

    private static final int RC_EXTERNAL_STORAGE = 0x01;
    private static final int RC_WRITE_EXTERNAL_STORAGE = 0x04;
    private static final int RC_RECORD_AUDIO = 0x02;
    private static final int RC_CAMERA = 0x03;

    private ChatFragment mChatFragment;
    private String mToChatUsername;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置顶部状态栏颜色
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_chat);

        //user or group id
        mChatFragment = new ChatFragment();
        mToChatUsername = getIntent().getExtras().getString(EaseConstant.EXTRA_USER_ID);
        //set arguments
        mChatFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().add(R.id.content_chat, mChatFragment).commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // enter to chat activity when click notification bar, here make sure only one chat activiy
        String username = intent.getStringExtra("userId");
        if (mToChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        mChatFragment.onBackPressed();
    }

    public String getToChatUsername() {
        return mToChatUsername;
    }

    @AfterPermissionGranted(RC_EXTERNAL_STORAGE)
    public boolean requestExternalStorage() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, "", RC_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
            return false;
        }
    }

    @AfterPermissionGranted(RC_WRITE_EXTERNAL_STORAGE)
    public boolean requestWriteExternalStorage() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, "", RC_WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            return false;
        }
    }

    @AfterPermissionGranted(RC_RECORD_AUDIO)
    public boolean requestRecordAudio() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.RECORD_AUDIO)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, "", RC_RECORD_AUDIO, Manifest.permission.RECORD_AUDIO);
            return false;
        }
    }

    @AfterPermissionGranted(RC_CAMERA)
    public boolean requestCamera() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, "", RC_CAMERA, Manifest.permission.CAMERA);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //成功
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        //失败
        if (requestCode == RC_EXTERNAL_STORAGE) {
            //读取文件
            showConfirmDialog(this, "没有权限, 你需要去设置中开启读取手机存储权限.", "去设置", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                    finish();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        } else if (requestCode == RC_WRITE_EXTERNAL_STORAGE) {
            //打开麦克风
            showConfirmDialog(this, "没有权限, 你需要去设置中开启写入手机存储权限.", "去设置", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                    finish();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        } else if (requestCode == RC_RECORD_AUDIO) {
            //打开麦克风
            showConfirmDialog(this, "没有权限, 你需要去设置中开启麦克风权限.", "去设置", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                    finish();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        } else if (requestCode == RC_CAMERA) {
            //打开相机
            showConfirmDialog(this, "没有权限, 你需要去设置中开启相机权限.", "去设置", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                    finish();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
    }

    public static AlertDialog.Builder showConfirmDialog(Context context, String message, String
            okString, String cancelString,
                                                        DialogInterface.OnClickListener
                                                                okClickListener,
                                                        DialogInterface.OnClickListener
                                                                cancelClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton(okString, okClickListener);
        builder.setNegativeButton(cancelString, cancelClickListener);
        builder.setCancelable(false);
        return builder;
    }
}
