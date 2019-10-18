package com.lonecode.mymoviecatalogue3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

public class SettingActivity extends AppCompatActivity {
    private Globals g = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RadioButton rbEnglish = findViewById(R.id.rbEnglish);
        RadioButton rbIndonesia = findViewById(R.id.rbIndonesia);

        String language = g.getLanguage();

        switch (language) {
            case "en-US":
                rbEnglish.setChecked(true);
                break;

            case "id-ID":
                rbIndonesia.setChecked(true);
                break;
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Setting");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onRadioButtonClicked(View view) {
        // is the button now checked ?
        boolean checked = ((RadioButton) view).isChecked();

        // check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbEnglish:
                if (checked) {
                    g.setLanguage("en-US");
                }

                break;

            case R.id.rbIndonesia:
                if (checked) {
                    g.setLanguage("id-ID");
                }

                break;
        }
    }
}
