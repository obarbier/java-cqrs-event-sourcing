package com.github.obarbier.user.query.api.dto;

import com.github.obarbier.user.core.dto.BaseResponse;
import com.github.obarbier.user.core.models.User;

import java.util.ArrayList;
import java.util.List;


public class UserLookupResponse extends BaseResponse {
    private List<User> user;

    public  UserLookupResponse(String message){
        super(message);
    }

    public UserLookupResponse(String message, User user){
        super(message);
        this.user = new ArrayList<>();
        this.user.add(user);
    }

    public UserLookupResponse(User user){
        super(null);
        this.user = new ArrayList<>();
        this.user.add(user);
    }

    public UserLookupResponse(List<User> user){
        super(null);
        this.user = user;
    }

    public  List<User> getUsers(){
        return this.user;
    }

    public void setUser(List<User> users){
        this.user = users;
    }
}
