package com.example.rares.testandroid;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rares.testandroid.list.Element;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Element> {

    public List<Element> list;
    private Context context;

    public ListAdapter(Context context, List<Element> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        LayoutInflater	inflater	=
                (LayoutInflater)	context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myRow	=	(convertView	==	null)	?
               inflater.inflate(R.layout.element,	parent, false)	:	convertView;
        Element element = getItem(position);
        ((TextView)myRow.findViewById(R.id.elementName)).setText(element.getName());
        ((TextView)myRow.findViewById(R.id.elementDescription)).setText(element.getDescription());
        if(element.isFavorite()) {
            ((ImageView)myRow.findViewById(R.id.elementFavourite)).setImageResource(R.drawable.star);
        } else {
            ((ImageView)myRow.findViewById(R.id.elementFavourite)).setImageResource(0);
        }
        return myRow;
    }


}
