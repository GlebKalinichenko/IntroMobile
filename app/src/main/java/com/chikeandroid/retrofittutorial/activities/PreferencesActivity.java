package com.chikeandroid.retrofittutorial.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.chikeandroid.retrofittutorial.R;
import com.chikeandroid.retrofittutorial.constants.Constants;
import com.chikeandroid.retrofittutorial.helper.SharedPreferencesManager;
import com.chikeandroid.retrofittutorial.service.ArifmeticService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreferencesActivity extends AppCompatActivity {
    private final String LOG_TAG = this.getClass().getCanonicalName();
    @BindView(R.id.green_check_box)
    public RadioButton greenCheckBox;
    @BindView(R.id.red_check_box)
    public RadioButton redCheckBox;
    @BindView(R.id.start_service)
    public Button startServiceButton;
    @BindView(R.id.stop_service)
    public Button stopServiceButton;
    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        ButterKnife.bind(this);
        sharedPreferencesManager = new SharedPreferencesManager(PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()));
        greenCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    sharedPreferencesManager.writeStringPreference(Constants.COLOR_MODE, "green");

                String color = (String) sharedPreferencesManager.readStringPreference(Constants.COLOR_MODE, "red");
                Log.d(LOG_TAG, color);
            }
        });

        redCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    sharedPreferencesManager.writeStringPreference(Constants.COLOR_MODE, "red");

                String color = (String) sharedPreferencesManager.readStringPreference(Constants.COLOR_MODE, "red");
                Log.d(LOG_TAG, color);
            }
        });
    }

    @OnClick(R.id.start_service)
    public void startServiceClicked() {
        Intent intent = new Intent(this, ArifmeticService.class);
        startService(intent);
    }

    @OnClick(R.id.stop_service
    )
    public void stopServiceClicked() {
        Intent intent = new Intent(this, ArifmeticService.class);
        stopService(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
    }
}
