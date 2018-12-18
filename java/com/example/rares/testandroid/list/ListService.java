package com.example.rares.testandroid.list;

import java.util.ArrayList;
import java.util.List;

public class ListService {

    private static ListService instance;
    public static ListService getInstance()
    {
        if(instance==null) {
            instance = new ListService();
        }
        return instance;
    }

    List<Element> list;

    public List<Element> getOriginalList()
    {
        list = new ArrayList<Element>();
        Element e1 = new Element(1, "Shawshank redemption", "What a great movie to look at!", false);
        Element e2 = new Element(2, "Pulp fiction", "Is this a good movie anyway!", false);
        Element e3 = new Element(3, "Troy", "Why did Achilles have to die!", false);
        Element e4 = new Element(4, "Moonlight", "Black people were still a thing.", false);
        Element e5 = new Element(5, "The Revenant", "Leo finally crawls for the Oscar!", false);
        list.add(e1); list.add(e2); list.add(e3); list.add(e4); list.add(e5);
        return list;
    }

    public List<Element> getList()
    {
        if(list==null) {
            getOriginalList();
        }
        return list;
    }

    public void setFavourite(int position, boolean value) {
        this.list.get(position).setFavorite(value);
    }
}
