
package com.conti.jing.datastoragetest.dbtest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.conti.jing.datastoragetest.R;
import com.conti.jing.datastoragetest.dbutils.DatabaseHelper;
import com.conti.jing.datastoragetest.dbutils.DatabaseManager;

public class DatabaseActivity extends Activity implements OnItemClickListener {
    private DatabaseManager mDatabaseManager;
    private Cursor mCursor;

    private TextView mEmptyTextView;
    private ListView mListView;
    private SimpleCursorAdapter mSimpleCursorAdapter;

    private final String[] FROM = new String[] {
            DatabaseHelper._ID,
            DatabaseHelper.TODO_SUBJECT,
            DatabaseHelper.TODO_DESCRIPTION
    };
    private final int[] TO = new int[] {
            R.id.text_id,
            R.id.text_subject,
            R.id.text_description
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_database_activity);
        initDatabase();
        initView();
        initEvent();
    }

    private void initEvent() {
        mListView.setOnItemClickListener(this);
    }

    private void initView() {
        mEmptyTextView = (TextView) findViewById(R.id.text_empty);
        mListView = (ListView) findViewById(R.id.list_view_all_data);
        mListView.setEmptyView(mEmptyTextView);
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.layout_list_item, mCursor, FROM, TO, 0);
        mSimpleCursorAdapter.notifyDataSetChanged();
        mListView.setAdapter(mSimpleCursorAdapter);
    }

    private void initDatabase() {
        mDatabaseManager = new DatabaseManager(this);
        mDatabaseManager.open();
        mCursor = mDatabaseManager.fetch();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_database_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_add_record:
                Intent intent = new Intent(this, AddRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_export_records:
                exportRecords(mCursor);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exportRecords(Cursor cursor) {}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
        TextView idTextView = (TextView) view.findViewById(R.id.text_id);
        TextView subjectTextView = (TextView) view.findViewById(R.id.text_subject);
        TextView descriptionTextView = (TextView) view.findViewById(R.id.text_description);
        String id = idTextView.getText().toString();
        String subject = subjectTextView.getText().toString();
        String description = descriptionTextView.getText().toString();
        Intent intent = new Intent(this, ModifyRecordActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("subject", subject);
        intent.putExtra("description", description);
        startActivity(intent);
    }

}
