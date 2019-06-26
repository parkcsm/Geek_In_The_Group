package com.idealist.www.myapplication.Item;

/**
 * Created by 박종원 on 2018-02-06.
 */

public class FriendItem {

    private String name;
    private String major;
    private String mail;
    private String phonenumber;
    private boolean ischecked;


    public FriendItem(String name, String major, String mail, String phonenumber){
        this.name = name;
        this.major = major;
        this.mail = mail;
        this.phonenumber = phonenumber;
    }

    public String getName(){ return name;}

    public String getMajor(){return major;}

    public String getMail(){return mail;}

    public String getPhonenumber(){return phonenumber;}

    public boolean ischecked() { return ischecked;}

    public void setIschecked(boolean ischecked){ this.ischecked = ischecked;}


}
