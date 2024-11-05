package com.food2u.androidxnavigationdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ConfigurationInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity {
    private Locale myLocale;
    TextView txtView;
    Button btnEnglish,btnMyanmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // applyLanguage(this,"my");
        setContentView(R.layout.activity_language);
        txtView=findViewById(R.id.textview);
        btnEnglish=findViewById(R.id.btnEnglish);
        btnMyanmar=findViewById(R.id.btnMyanmar);
        changeLang("ak");
        btnMyanmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLang("ak");
            }
        });
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLang("en");
            }
        });
        loadLocale();

    }

    /*private void applyLanguage(Activity activity, String language)
    {
        Locale locale= new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration= activity.getBaseContext().getResources().getConfiguration();
        configuration.setLocale(locale);
        activity.getBaseContext().getResources().updateConfiguration(configuration,activity.getBaseContext().getResources().getDisplayMetrics());
        activity.getBaseContext().getApplicationContext().getResources().updateConfiguration(configuration,activity.getBaseContext().getResources().getDisplayMetrics());
    }*/

   // Changing the language in the application:
    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    //    getBaseContext().getApplicationContext().getResources().updateConfiguration(config, getBaseContext().getApplicationContext().getResources().getDisplayMetrics());
        updateTexts();
    }

   // Save the current locale:
    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }

    //Loading a saved locale:
    public void loadLocale()
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }

   // Updating the UI elements of the current screen (you need to update only the screen in which a change of locale):
    private void updateTexts()
    {
       /* txt_hello.setText(R.string.hello_world);
        btn_en.setText(R.string.btn_en);
        btn_ru.setText(R.string.btn_ru);
        btn_fr.setText(R.string.btn_fr);
        btn_de.setText(R.string.btn_de);*/
       txtView.setText(getString(R.string.menu_home));
    }

}
