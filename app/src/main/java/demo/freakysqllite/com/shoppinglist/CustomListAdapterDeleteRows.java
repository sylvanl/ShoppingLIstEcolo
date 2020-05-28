package demo.freakysqllite.com.shoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapterDeleteRows extends BaseAdapter {

    Context c;
    ArrayList<ItemModel> items;

    public CustomListAdapterDeleteRows(Context c, ArrayList<ItemModel> items) {
        this.c = c;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view=LayoutInflater.from(c).inflate(R.layout.listviewdelete_row,viewGroup,false);
        }

        TextView mtextView1 = (TextView) view.findViewById(R.id.textView1);
        TextView mtextView2 = (TextView) view.findViewById(R.id.textView2);
        Button deleteBtn = (Button) view.findViewById(R.id.button1);

        final ItemModel item= (ItemModel) this.getItem(i);
        mtextView1.setText(item.getItemname());
        mtextView2.setText(item.getItemquantity());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ItemsDatabaseAdapter.deleteEntry(item.getID());
                items.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}