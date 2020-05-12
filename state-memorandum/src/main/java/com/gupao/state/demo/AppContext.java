package com.gupao.state.demo;

public class AppContext {

    public static final UserState  UNLOGIN_STATE=new UnLoginState();
    public static final UserState  LOGIN_STATE=new LoginState();
    private static  UserState current_state ;
    {
        UNLOGIN_STATE.setAppContext(this);
        LOGIN_STATE.setAppContext(this);
        current_state=UNLOGIN_STATE;

    }
    public void setCurrent_state(UserState current_state) {
        AppContext.current_state = current_state;
        current_state.setAppContext(this);
    }
    //收藏
    public   void collect(){
        current_state.collect();
    }

    public static UserState getCurrent_state() {
        return current_state;
    }

    public   void comment(String data){
        current_state.comment(data);
    }

}
