package com.goldenchef.company.main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseActivity;
import com.goldenchef.company.find.FindFragment;
import com.goldenchef.company.home.HomeFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.message.MessageListFragment;
import com.goldenchef.company.person.PersonFragment;
import com.goldenchef.company.utils.SharedPreferencesUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    private static final int RC_EXTERNAL_STORAGE = 0x01;
    private static final int RC_WRITE_EXTERNAL_STORAGE = 0x04;
    private static final int RC_RECORD_AUDIO = 0x02;
    private static final int RC_CAMERA = 0x03;
    private static final int RC_READ_PHONE_STATE = 0x05;

    @BindView(R.id.main_btn_dachu)
    View main_btn_dachu;

    @BindView(R.id.main_btn_shangpu)
    View main_btn_shangpu;

    @BindView(R.id.main_btn_xiaoxi)
    View main_btn_xiaoxi;

    @BindView(R.id.main_btn_faxian)
    View main_btn_faxian;

    @BindView(R.id.main_btn_wode)
    View main_btn_wode;

    FragmentManager mFragmentManager;

    HomeFragment mHomeFragment;
    PersonFragment mPersonFragment;
    MessageListFragment mMessageListFragment;
    FindFragment mFindFragment;

    private Fragment mOldFragment, mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initEasemob();
        requestPermission();
    }

    @Override
    protected void initInjector(AppComponent appComponent) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mFragmentManager = getSupportFragmentManager();
        mHomeFragment = new HomeFragment();
        mMessageListFragment = new MessageListFragment();
        mPersonFragment = new PersonFragment();
        mFindFragment = new FindFragment();

        switchFragment(new Fragment(), mHomeFragment);
        main_btn_dachu.setSelected(true);
    }

    @OnClick({R.id.main_btn_dachu, R.id.main_btn_shangpu, R.id.main_btn_xiaoxi, R.id.main_btn_faxian, R.id.main_btn_wode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_dachu:
                //大厨
//                StatusBarCompat.compat(this, getResources().getColor(R.color.colorPrimaryDark));
                switchFragment(mCurrentFragment, mHomeFragment);

                selectedView(main_btn_dachu);
                break;
            case R.id.main_btn_shangpu:
                //商铺

                selectedView(main_btn_shangpu);
                break;

            case R.id.main_btn_xiaoxi:
                //消息
                switchFragment(mCurrentFragment, mMessageListFragment);

                selectedView(main_btn_xiaoxi);
                break;

            case R.id.main_btn_faxian:
                //发现
                switchFragment(mCurrentFragment, mFindFragment);

                selectedView(main_btn_faxian);
                break;
            case R.id.main_btn_wode:
                //我的
//                StatusBarCompat.compat(this, getResources().getColor(R.color.title_red));
                switchFragment(mCurrentFragment, mPersonFragment);

                selectedView(main_btn_wode);
                break;

        }
    }

    /**
     * 切换Fragment
     *
     * @param from
     * @param to
     */
    private void switchFragment(Fragment from, Fragment to) {
        if (to == null)
            return;

        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
            if (!to.isAdded())
                mTransaction.hide(from).add(R.id.main_layout_content, to);
            else
                mTransaction.hide(from).show(to);
            mTransaction.commit();
        }
    }

    private void selectedView(View view) {
        clearSelected();
        view.setSelected(true);
    }

    private void clearSelected() {
        main_btn_dachu.setSelected(false);
        main_btn_shangpu.setSelected(false);
        main_btn_faxian.setSelected(false);
        main_btn_xiaoxi.setSelected(false);
        main_btn_wode.setSelected(false);
    }

    /**
     * 初始化环信
     */
    private void initEasemob() {
        EMClient.getInstance().login((String) SharedPreferencesUtil.getPreference(this, "username"), "123456", new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.e("TAG", "登录成功");
            }

            @Override
            public void onError(int i, String s) {
                Log.e("TAG", "登录失败" + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
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

    private void requestPermission() {
        requestExternalStorage();
        requestCamera();
        requestRecordAudio();
        requestWriteExternalStorage();
        requestReadPhoneState();
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

    @AfterPermissionGranted(RC_READ_PHONE_STATE)
    public boolean requestReadPhoneState() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_PHONE_STATE)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, "", RC_READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);
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
