package com.github.obarbier.user.cmd.api.security;

public interface PasswordEncoder {

    String hashPassword(String password);
}
