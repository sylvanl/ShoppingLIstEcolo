package demo.freakysqllite.com.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;

import java.util.ArrayList;

import static xdroid.toaster.Toaster.toast;


public class ItemsDatabaseAdapter {
    static ArrayList<ItemModel> items=new ArrayList<>();
    static final String DATABASE_NAME = "ItemsDatabase.db";
    static final String TABLE_NAME = "ITEMS";
    static final int DATABASE_VERSION = 1;
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table "+TABLE_NAME+"( ID integer primary key autoincrement,item_name  text,item_quantity  text); ";
    private static final String TAG = "ItemsDatabaseAdapter:";

    // Variable to hold the database instance
    public static SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private static DataBaseHelper dbHelper;
    public  ItemsDatabaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method to open the Database
    public  ItemsDatabaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // Method to close the Database
    public void close()
    {
        db.close();
    }

    // method returns an Instance of the Database
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    // method to insert a record in Table
    public static String insertEntry(String item_name, String item_quantity)
    {

        try {

            ContentValues newValues = new ContentValues();
            // Assign values for each column.
            newValues.put("item_name", item_name);
            newValues.put("item_quantity", item_quantity);

            // Insert the row into your table
            db = dbHelper.getWritableDatabase();
            long result=db.insert(TABLE_NAME, null, newValues);
            Log.i("Row Insert Result ", String.valueOf(result));
            toast("Item Info Saved! Total Row Count is "+getRowCount());
            db.close();

        }catch(Exception ex) {

        }
        return "ok";
    }

    // method to get all Rows Saved in Table
    public static ArrayList<ItemModel> getRows() throws JSONException {

        items.clear();
        ItemModel item;
        db=dbHelper.getReadableDatabase();
        Cursor projCursor = db.query(TABLE_NAME, null, null,null, null, null, null,null);
        while (projCursor.moveToNext()) {

            item=new ItemModel();
            item.setID(projCursor.getString(projCursor.getColumnIndex("ID")));
            item.setItemname(projCursor.getString(projCursor.getColumnIndex("item_name")));
            item.setItemquantity(projCursor.getString(projCursor.getColumnIndex("item_quantity")));
            items.add(item);
        }
        projCursor.close();
        return items;
    }

    // method to delete a Record in Tbale using Primary Key Here it is ID
    public static int deleteEntry(String ID)
    {
        String where="ID=?";
        int numberOFEntriesDeleted= db.delete(TABLE_NAME, where, new String[]{ID}) ;
        toast("Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted);
        return numberOFEntriesDeleted;
    }

    // method to get Count of Total Rows in Table
    public static int getRowCount()
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
        toast("Row Count is "+cursor.getCount());
        db.close();
        return cursor.getCount();
    }

    // method to Truncate/ Remove All Rows in Table
    public static void truncateTable()
    {
        db=dbHelper.getReadableDatabase();
        db.delete(TABLE_NAME, "1", null);
        db.close();
        toast("Table Data Truncated!");
    }

    // method to Update an Existing Row in Table
    public static void  updateEntry(String ID,String Itemname, String Itemquantity)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("item_name", Itemname);
        updatedValues.put("item_quantity", Itemquantity);
        String where="ID = ?";
        db=dbHelper.getReadableDatabase();
        db.update(TABLE_NAME,updatedValues, where, new String[]{ID});
        db.close();
        toast("Row Updated!");
    }

}