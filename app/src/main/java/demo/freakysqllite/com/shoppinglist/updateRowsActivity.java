package demo.freakysqllite.com.shoppinglist;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import org.json.JSONException;
import java.util.ArrayList;

public class updateRowsActivity extends AppCompatActivity {
    static ListView listView ;
    ArrayList<ItemModel> items=new ArrayList<>();
    static CustomListAdapterUpdateRows updateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_rows);
        try {
            items = ItemsDatabaseAdapter.getRows();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        updateAdapter = new CustomListAdapterUpdateRows(this, items);
        listView = (ListView) findViewById(R.id.listupdateviewID);
        listView.setAdapter(updateAdapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Update Row in SQLite");
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
