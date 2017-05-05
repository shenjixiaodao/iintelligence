package com.ii.domain.user;


import com.ii.domain.base.ValueObject;


/**
 * Created by liyou on 17/4/24.
 */
public class UserId implements ValueObject<UserId> {

    private String uid;

    public UserId(String uid) {
        this.uid = uid;
    }

    public void uid(String uid){
        this.uid = uid;
    }
    public String uid(){
        return this.uid;
    }



    public UserId() {
        //for ORM
    }

    @Override
    public boolean sameValueAs(UserId other) {
        return other != null && this.uid.equals(other.uid);
    }
}
