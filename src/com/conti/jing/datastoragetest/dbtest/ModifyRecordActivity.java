
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

public class ModifyRecordActivity extends Activity implements OnClickListener {
    private EditText mSubjectEditText;
    private EditText mDescriptionEditText;
    private Button mUpdateButton;
    private Button mDeleteButton;

    private DatabaseManager mDatabaseManager;
    private long _id;

    private String mCurrentId;
    private String mCurrentSubject;
    private String mCurrentDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.layout_modify_record_activity);
        getCurrentRecord();
        initDatabase();
        initView();
        initEvent();
    }

    private void getCurrentRecord() {
        Intent intent = getIntent();
        mCurrentId = intent.getStringExtra("id");
        mCurrentSubject = intent.getStringExtra("subject");
        mCurrentDescription = intent.getStringExtra("description");
    }

    private void initDatabase() {
        mDatabaseManager = new DatabaseManager(this);
        mDatabaseManager.open();
        _id = Long.parseLong(mCurrentId);
    }

    private void initEvent() {
        mUpdateButton.setOnClickListener(this);
        mDeleteButton.setOnClickListener(this);
    }

    private void initView() {
        mSubjectEditText = (EditText) findViewById(R.id.edit_subject_modify);
        mSubjectEditText.setText(mCurrentSubject);
        mDescriptionEditText = (EditText) findViewById(R.id.edit_description_modify);
        mDescriptionEditText.setText(mCurrentDescription);
        mUpdateButton = (Button) findViewById(R.id.button_update_record);
        mDeleteButton = (Button) findViewById(R.id.button_delete_record);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_update_record:
                String subject = mSubjectEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();
                mDatabaseManager.update(_id, subject, description);
                returnMainActivity();
                break;
            case R.id.button_delete_record:
                mDatabaseManager.delete(_id);
                returnMainActivity();
                break;
            default:
                break;
        }
    }

    private void returnMainActivity() {
        Intent intent = new Intent(this, DatabaseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        mDatabaseManager.close();
    }
}
