package com.goldenchef.company.injector.module;

import com.goldenchef.company.api.ApiService;
import com.goldenchef.company.api.ToStringConverterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import static com.goldenchef.company.api.ApiService.BASE_URL;

/**
 * Created by luo-hao on 2017-03-16.
 */
@Module
public class ApiModule {

    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    public ApiService provideApiService(OkHttpClient okHttpClient) {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(new ToStringConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit.create(ApiService.class);
    }

}
