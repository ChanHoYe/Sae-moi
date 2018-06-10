package com.three_eung.saemoi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    private String device_version;
    private Button version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        setSupportActionBar((Toolbar) findViewById(R.id.infoToolbar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("정보");

        Button licence = (Button) findViewById(R.id.info_licence);
        licence.setOnClickListener(this);

        version = (Button) findViewById(R.id.info_version);

        try {
            device_version = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        VersionChecker versionChecker = new VersionChecker(this);
        versionChecker.execute(getPackageName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_licence:
                startActivity(new Intent(InfoActivity.this, LicenseActivity.class));
                break;
            case R.id.info_version:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                break;
        }
    }

    class VersionChecker extends AsyncTask<String, Void, String> {
        private Context mContext;

        public VersionChecker(Context context) {
            mContext = context;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Document doc = Jsoup.connect("https://play.google.com/store/apps/details?id=" + params[0]).get();

                Elements Version = doc.select(".htlgb");

                String VersionMarket = "";

                for (int i = 0; i < 5 ; i++) {
                    VersionMarket = Version.get(i).text();
                    if (Pattern.matches("^[0-9]{1}.[0-9]{1}.[0-9]{1}$", VersionMarket)) {
                        break;
                    }
                }

                return VersionMarket;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.compareTo(device_version) > 0) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(device_version);
                buffer.append(" -> ");
                buffer.append(s);
                buffer.append(" 업데이트 하러 가기.");
                version.setText(buffer.toString());
                version.setOnClickListener((InfoActivity)mContext);
            } else {
                StringBuffer buffer = new StringBuffer();
                buffer.append("앱이 최신버전입니다.");
                version.setText(buffer.toString());
                version.setClickable(false);
            }
        }
    }
}