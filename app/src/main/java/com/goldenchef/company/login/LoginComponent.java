package com.goldenchef.company.login;

import com.goldenchef.company.injector.PreActivity;
import com.goldenchef.company.injector.component.AppComponent;

import dagger.Component;

/**
 * Created by luo-hao on 2017-03-16.
 */
@PreActivity
@Component(dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}
