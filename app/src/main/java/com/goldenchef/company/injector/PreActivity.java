package com.goldenchef.company.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by luo-hao on 2017-03-15.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PreActivity {
}
