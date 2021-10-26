package com.github.obarbier.user.cmd.api.dto;

import com.github.obarbier.user.core.dto.BaseResponse;

public class RegisterUserResponse  extends BaseResponse {
    private  String  id;

    public RegisterUserResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
