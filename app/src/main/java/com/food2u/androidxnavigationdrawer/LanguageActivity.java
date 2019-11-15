package com.food2u.androidxnavigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyLanguage(this,"my");
        setContentView(R.layout.activity_language);
    }

    private void applyLanguage(Activity activity, String language)
    {
        Locale locale= new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration= activity.getBaseContext().getResources().getConfiguration();
        configuration.setLocale(locale);
        activity.getBaseContext().getResources().updateConfiguration(configuration,activity.getBaseContext().getResources().getDisplayMetrics());
        activity.getBaseContext().getApplicationContext().getResources().updateConfiguration(configuration,activity.getBaseContext().getResources().getDisplayMetrics());
    }
}
