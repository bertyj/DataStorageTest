
package com.conti.jing.datastoragetest.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.conti.jing.datastoragetest.R;
import com.conti.jing.datastoragetest.dbutils.DatabaseManager;

public class AddRecordActivity extends Activity implements OnClickListener {
    private EditText mSubjectEditText;
    private EditText mDescriptionEditText;
    private Button mAddRecordButton;

    private DatabaseManager mDatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.layout_add_record_activity);
        initDatabase();
        initView();
        initEvent();
    }

    private void initDatabase() {
        mDatabaseManager = new DatabaseManager(this);
        mDatabaseManager.open();
    }

    private void initEvent() {
        mAddRecordButton.setOnClickListener(this);
    }

    private void initView() {
        mSubjectEditText = (EditText) findViewById(R.id.edit_subject);
        mDescriptionEditText = (EditText) findViewById(R.id.edit_description);
        mAddRecordButton = (Button) findViewById(R.id.button_add_record);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_add_record:
                String subject = mSubjectEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();
                mDatabaseManager.insert(subject, description);
                Intent intent = new Intent(this, DatabaseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
