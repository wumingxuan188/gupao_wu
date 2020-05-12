package com.chain.builder;

import com.chain.Member;

public class LoginService {
    public static void login(String name, String pass) {

        Handle.Builder builder = new Handle.Builder();
        builder.addHandel(new ValidateHandler()).addHandel(new LoginHandler()).addHandel(new AuthHandler()).built(new Member(name, pass));
    }
}
