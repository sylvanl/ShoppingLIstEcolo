package demo.freakysqllite.com.shoppinglist;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONException;
import java.util.ArrayList;

public class deleteRowsActivity extends AppCompatActivity {
    ListView listView ;
    ArrayList<ItemModel> items=new ArrayList<>();
    static CustomListAdapterDeleteRows deleteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_rows);
        try {
            items = ItemsDatabaseAdapter.getRows();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        deleteAdapter = new CustomListAdapterDeleteRows(this, items);
        listView = (ListView) findViewById(R.id.listviewdeleteID);
        listView.setAdapter(deleteAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Delete Row from SQLite");
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
