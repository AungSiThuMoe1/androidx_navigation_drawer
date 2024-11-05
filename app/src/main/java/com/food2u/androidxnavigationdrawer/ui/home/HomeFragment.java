package com.food2u.androidxnavigationdrawer.ui.home;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.food2u.androidxnavigationdrawer.LanguageActivity;
import com.food2u.androidxnavigationdrawer.R;
import com.food2u.androidxnavigationdrawer.musicService;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button btnStart,btnStop;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
       btnStart=root.findViewById(R.id.btn_start);
       btnStop=root.findViewById(R.id.btn_stop);

       btnStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getActivity(), musicService.class);
               getActivity().startService(i);

           }
       });

       btnStop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getActivity(),musicService.class);
               getActivity().stopService(i);

           }
       });



        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i=new Intent(getActivity(), LanguageActivity.class);
                startActivity(i);
               // getActivity().finish();
            }
        });
        return root;
    }

    BroadcastReceiver receiveSMS = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("OnCreate Messages");
            System.out.println(intent.getAction());
            System.out.println("sms " + intent.getStringExtra("sms"));
            System.out.println("sms number " + intent.getStringExtra("smsnumber"));
           /* if (OTPPhoneno.trim().equalsIgnoreCase(intent.getStringExtra("smsnumber"))) {
                String otpsms = intent.getStringExtra("sms");
                String[] otpsmsarray = otpsms.split(":");
                otpsms = otpsmsarray[1].trim().substring(0, OTP.length());
                edt_otp.setText(otpsms);
                if (edt_otp.getText().toString().equals(OTP)) {
                    if (ActivityCompat.checkSelfPermission(PhoneNumberLoginActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }

                    String simserialnumber = telephonyManager.getSimSerialNumber();
                    SharedPreferences sp = getSharedPreferences("food2uorder", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("ContactNo", edt_phone_number.getText().toString().trim());
                    editor.putString("SimSerialNumber", simserialnumber);
                    editor.apply();
                }

            }*/
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiveSMS, new IntentFilter("otp"));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiveSMS);
    }
}