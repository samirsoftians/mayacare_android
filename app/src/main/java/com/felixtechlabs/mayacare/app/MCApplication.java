package com.felixtechlabs.mayacare.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Application class managing initialisations
 * Created by rohan on 20/6/17.
 */

public class MCApplication extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
