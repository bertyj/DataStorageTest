
package com.conti.jing.datastoragetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button mSharedPreferencesButton;
    private Button mInternalStorageButton;
    private Button mExternalStorageButton;
    private Button mSQLiteDatabaseButton;
    private Button mNetworkConnectionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);
        initView();
        initEvent();
    }

    private void initEvent() {
        mSharedPreferencesButton.setOnClickListener(this);
        mInternalStorageButton.setOnClickListener(this);
        mExternalStorageButton.setOnClickListener(this);
        mSQLiteDatabaseButton.setOnClickListener(this);
        mNetworkConnectionButton.setOnClickListener(this);
    }

    private void initView() {
        mSharedPreferencesButton = (Button) findViewById(R.id.button_shared_preferences);
        mInternalStorageButton = (Button) findViewById(R.id.button_internal_storage);
        mExternalStorageButton = (Button) findViewById(R.id.button_external_storage);
        mSQLiteDatabaseButton = (Button) findViewById(R.id.button_sqlite_database);
        mNetworkConnectionButton = (Button) findViewById(R.id.button_network_connection);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_shared_preferences:
                startActivity(new Intent(this, SharedPreferencesActivity.class));
                break;
            case R.id.button_internal_storage:
                startActivity(new Intent(this, InternalStorageActivity.class));
                break;
            case R.id.button_external_storage:
                startActivity(new Intent(this, ExternalStorageActivity.class));
                break;
            case R.id.button_sqlite_database:
                startActivity(new Intent(this, SQLiteDatabaseActivity.class));
                break;
            case R.id.button_network_connection:
                startActivity(new Intent(this, NetworkConnectionActivity.class));
                break;
            default:
                break;
        }
    }
}
