package com.goldenchef.company;

import android.support.annotation.NonNull;

/**
 * Created by luo-hao on 2017-03-14.
 */

public interface  BasePresenter<T extends BaseView> {

    void attachView(@NonNull T t);

    void detachView();

}
