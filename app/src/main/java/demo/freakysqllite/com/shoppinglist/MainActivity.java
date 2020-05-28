package demo.freakysqllite.com.shoppinglist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ItemsDatabaseAdapter usersDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create the instance of Databse
        usersDatabaseAdapter=new ItemsDatabaseAdapter(getApplicationContext());
    }

    //open activity to Insert new rows in table
    public void insertRowActivity(View view) {
        Intent myIntent = new Intent(MainActivity.this, InsertRowActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    //Open activity to update rows
    public void updateRowView(View view) {
        Intent myIntent = new Intent(MainActivity.this, updateRowsActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    //call method to show rows count in Toast
    //public void rowCount(View view) {
    //    ItemsDatabaseAdapter.getRowCount();
    //}

    //Open activity to delete rows
    public void deleteRowActivity(View view) {
        Intent myIntent = new Intent(MainActivity.this, deleteRowsActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}
