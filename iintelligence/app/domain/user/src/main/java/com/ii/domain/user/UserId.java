package com.ii.domain.user;

import com.ii.domain.base.Entity;

import java.util.Objects;

/**
 * Created by liyou on 17/4/24.
 */
public class UserId implements Entity<UserId> {

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

    @Override
    public boolean sameIdentityAs(UserId other) {
        if(this == other || Objects.equals(uid, other.uid()))
            return true;
        return false;
    }

    public UserId() {
        //for ORM
    }
}
