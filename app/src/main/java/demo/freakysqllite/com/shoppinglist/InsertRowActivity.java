package demo.freakysqllite.com.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static xdroid.toaster.Toaster.toast;

public class InsertRowActivity extends AppCompatActivity {

    private TextView mItemName;
    private TextView mItemQuantity;
    private Button insertRowFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_row);

        insertRowFrom = (Button) findViewById(R.id.insertRowFrom);
        mItemName = (TextView) findViewById(R.id.itemNameTxt);
        mItemQuantity = (TextView) findViewById(R.id.itemQuantityTxt);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Insert New Row in SQLite");
        }
    }

    public void insertRow(View view) {

        TextView itemNameTxtView = findViewById(R.id.itemNameTxt);
        TextView itemQuantityTxtView = findViewById(R.id.itemQuantityTxt);

        if(itemNameTxtView.getText().toString().trim().equals("")
                || itemQuantityTxtView.getText().toString().trim().equals("")){
            toast("Please Fill All Fields ");
        }else{
            ItemsDatabaseAdapter.insertEntry(itemNameTxtView.getText().toString().trim(),itemQuantityTxtView.getText().toString());
            Intent myIntent = new Intent(InsertRowActivity.this, MainActivity.class);
            InsertRowActivity.this.startActivity(myIntent);
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
