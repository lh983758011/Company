package com.goldenchef.company.injector.component;

import com.goldenchef.company.api.ApiService;
import com.goldenchef.company.injector.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by luo-hao on 2017-03-15.
 */
@Singleton
@Component(modules = {ApiModule.class})
public interface AppComponent {

   ApiService getService();

}
