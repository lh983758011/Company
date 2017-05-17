package com.goldenchef.company.find;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.goldenchef.company.R;
import com.goldenchef.company.api.ToStringConverterFactory;
import com.goldenchef.company.common.BaseFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.Utils;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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

                                long fileSize = file.length();
                                byte[] result = Base64.encode(stringBuffer.toString().getBytes("UTF-8"), Base64.DEFAULT);
                                String resultStr = new String(result, "UTF-8");
                                String deviceId = Utils.getDeviceID(mContext);


                                createTestApi().testVoice((int) fileSize, deviceId, resultStr)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Action1<String>() {
                                            @Override
                                            public void call(String o) {
                                                Log.e("TAG", "result:" + o);
                                                Toast.makeText(mContext, "result:" + o, Toast.LENGTH_SHORT).show();
                                            }
                                        }, new Action1<Throwable>() {
                                            @Override
                                            public void call(Throwable throwable) {
                                                Log.e("TAG", "throwable:" + throwable);
                                                Toast.makeText(mContext, "result:" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

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


    public interface TestApi {

        String BASE_URL = "http://139.129.35.116:3000/voice/";

        @FormUrlEncoded
        @POST(BASE_URL)
        Observable<String> testVoice(@Field("len") Integer len, @Field("cuid") String cuid, @Field("speech") String speech);

    }

    private TestApi createTestApi() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(TestApi.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(new ToStringConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit.create(TestApi.class);
    }

    @Override
    public void initData() {

    }
}
