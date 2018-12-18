package com.example.rares.testandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rares.testandroid.chat.DisplayMessage;
import com.example.rares.testandroid.fruit.Fruit;

import org.w3c.dom.Text;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    public List<Fruit> list;
    private Context context;

    public FruitAdapter(Context context, List<Fruit> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myRow = (convertView == null) ?
                inflater.inflate(R.layout.fruit_element, parent, false) : convertView;
        Fruit element = getItem(position);
        ((TextView) myRow.findViewById(R.id.fruitName)).setText(element.getName());
        ((TextView)myRow.findViewById(R.id.price)).setText(Float.toString(element.getPrice()));
        ((EditText)myRow.findViewById(R.id.quantity)).setText(Integer.toString(element.getQuantity()));
        CheckBox box = ((CheckBox)myRow.findViewById(R.id.fruitChecked));
        box.setChecked(element.isChecked());
        return myRow;
    }
}