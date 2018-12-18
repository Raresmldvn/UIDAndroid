package com.example.rares.testandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rares.testandroid.chat.DisplayMessage;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<DisplayMessage> {

    public List<DisplayMessage> list;
    private Context context;

    public ChatAdapter(Context context, List<DisplayMessage> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myRow = (convertView == null) ?
                inflater.inflate(R.layout.message, parent, false) : convertView;
        DisplayMessage element = getItem(position);
        ((TextView) myRow.findViewById(R.id.sender)).setText(element.getName());
        ((TextView) myRow.findViewById(R.id.text)).setText(element.getMessage());
        ((TextView) myRow.findViewById(R.id.msgDate)).setText(element.getDate().toString());
        return myRow;
    }
}
