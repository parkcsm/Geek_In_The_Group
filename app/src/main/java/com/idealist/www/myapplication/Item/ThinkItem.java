package com.idealist.www.myapplication.Item;

/**
 * Created by 박종원 on 2018-02-07.
 */

public class ThinkItem {

    private String year;
    private String month;
    private String day;
    private String opinion;
    private String source;
    private boolean numberischecked;
    private boolean shareischecked;


    public ThinkItem(String year, String month, String day, String opinion, String source){
        this.year = year;
        this.month = month;
        this.day = day;
        this.opinion = opinion;
        this.source = source;
    }


    public boolean numberischecked() { return numberischecked;}

    public boolean shareischecked() { return shareischecked;}


    public void setNumberischecked(boolean numberischecked){ this.numberischecked = numberischecked;}

    public void setShareischecked(boolean shareischecked){ this.shareischecked = shareischecked;}

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getOpinion() {
        return opinion;
    }

    public String getSource() {
        return source;
    }
}
