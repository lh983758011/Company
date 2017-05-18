package com.goldenchef.company.find;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.goldenchef.company.R;
import com.goldenchef.company.common.BaseFragment;
import com.goldenchef.company.injector.component.AppComponent;
import com.goldenchef.company.utils.Utils;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressImageButton;
import butterknife.BindView;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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
    @BindView(R.id.btn_id)
    CircularProgressImageButton btn_id;


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

        btn_id.startAnimation();

        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_id.startAnimation();
            }
        });


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
                                byte[] buffer = new byte[(int) file.length()];
                                fileInputStream.read(buffer);

                                int fileSize = buffer.length;
                                String resultStr = Base64.encodeToString(buffer, Base64.NO_WRAP);

                                String deviceId = Utils.getDeviceID(mContext);

                                createTestApi().testVoice(fileSize, deviceId, resultStr)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Action1<Object>() {
                                            @Override
                                            public void call(Object o) {
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
                            } catch (UnsupportedEncodingException e) {
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
        Observable<Object> testVoice(@Field("len") Integer len, @Field("cuid") String cuid, @Field("speech") String speech);

    }

    private TestApi createTestApi() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(TestApi.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit.create(TestApi.class);
    }

    @Override
    public void initData() {

    }
}
