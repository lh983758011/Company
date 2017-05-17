package com.goldenchef.company.find;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;

/**
 * Created by luo-hao on 2017/5/17.
 */

public class FindFragment extends BaseFragment {

    @BindView(R.id.btn_voice)
    View btn_voice;
    @BindView(R.id.find_voice_recorder)
    EaseVoiceRecorderView find_voice_recorder;


    @Override
    public int initContentView() {
        return R.layout.fragment_find;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initInjector(AppComponent appComponent) {

    }

    @Override
    public void initUI() {
        showContent();
        btn_voice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                find_voice_recorder.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() {

                    @Override
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        Log.e("TAG", "voiceFilePath:" + voiceFilePath + ", voiceTimeLength:" + voiceTimeLength);
                        File file = new File(voiceFilePath);
                        if (file.exists()) {
                            FileInputStream fileInputStream = null;
                            try {
                                fileInputStream = new FileInputStream(file);
                                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

                                StringBuffer stringBuffer = new StringBuffer();
                                byte[] buffer = new byte[1024];
                                int length = 0;
                                String itemStr = null;
                                while ((length = bufferedInputStream.read(buffer)) != -1) {
                                    itemStr = new String(buffer, 0, length);
                                    stringBuffer.append(itemStr);
                                }

                                int oldStrlength = stringBuffer.toString().length();

                                byte[] result = Base64.encode(stringBuffer.toString().getBytes("UTF-8"), Base64.DEFAULT);

                                Log.e("TAG", "oldStr:" + stringBuffer.toString() + "\n" + ", newStr:" + new String(result, "UTF-8"));

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                });

                return false;
            }
        });
    }


    @Override
    public void initData() {

    }
}
