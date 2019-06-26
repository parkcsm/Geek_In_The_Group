package com.idealist.www.myapplication.Item;

import android.net.Uri;

/**
 * Created by 박종원 on 2018-02-25.
 */

public class ShareItem {


    String uriString;
    String id;
    String postText;
   public boolean isUserLike;
   public int postLikeCount;

    String userName;
    String Comment;
    String postImgUrl;





    public ShareItem(String uriString, String id, String postText,boolean isUserLike ,int postLikeCount) {
        this.uriString = uriString;
        this.id = id;
        this.postText = postText;
        this.isUserLike = isUserLike;
        this.postLikeCount = postLikeCount;
    }




    public Uri getUri() {return Uri.parse(uriString);}
    public String getUriString() {return uriString;}

    public String getId() {
        return id;
    }

    public String getPostText() {
        return postText;
    }

    public boolean getisUserLike() {
        return isUserLike;
    }

    public int getPostLikeCount() {
        return postLikeCount;
    }

    public void setPostchecked(boolean ischecked){ this.isUserLike = ischecked;}

}
