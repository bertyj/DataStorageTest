
package com.conti.jing.datastoragetest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Shared Preferences: Store private primitive data in key-value pairs.
 * The SharedPreferences class provides a general framework that allows you to save and retrieve
 * persistent key-value pairs of primitive data types. You can use SharedPreferences to save any
 * primitive data: booleans, floats, ints, longs, and strings. This data will persist across user
 * sessions (even if your application is killed).
 * A SharedPreferences object points to a file containing key-value pairs and provides simple
 * methods to read and write them. Each SharedPreferences file is managed by the framework and can
 * be private or shared.
 */
public class SharedPreferencesActivity extends Activity {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shared_preferences_activity);
        initView();
        initSharedPreference();
    }

    private void initSharedPreference() {
        mSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(getString(R.string.saved_high_score), "90");
        mEditor.commit();

        // To retrieve values from a shared preferences file, call methods such as getInt() and
        // getString(), providing the key for the value you want, and optionally a default value to
        // return if the value isn't present.
        String defaultValue = getResources().getString(R.string.saved_high_score_default);
        Toast.makeText(this, "R.string.saved_high_score_default: " + defaultValue, Toast.LENGTH_SHORT).show();
        String highScore = mSharedPreferences.getString(getString(R.string.saved_high_score), defaultValue);
        Toast.makeText(this, "R.string.saved_high_score: " + highScore, Toast.LENGTH_SHORT).show();
    }

    private void initView() {}

}
