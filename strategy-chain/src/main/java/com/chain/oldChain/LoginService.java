package com.chain.oldChain;

import com.chain.Member;

public class LoginService {
    public static void login(String name,String pass){


        ValidateHandler validateHandler = new ValidateHandler();
        LoginHandler loginHandler = new LoginHandler();
        AuthHandler authHandler = new AuthHandler();
        validateHandler.next(loginHandler);
        loginHandler.next(authHandler);
        validateHandler.doPerform(new Member(name,pass));
    }
}
