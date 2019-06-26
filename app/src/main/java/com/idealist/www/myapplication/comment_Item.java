package com.idealist.www.myapplication;

/**
 * Created by 박종원 on 2018-03-05.
 */

public class comment_Item {
    String id;
    String text;


    public comment_Item(String id,String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {return id;}
    public String getText() {return text;}


}