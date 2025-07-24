package com.typing_test_app.typing_app.interfaces;

import com.typing_test_app.typing_app.model.User;


public interface UserService {
    public boolean saveUser(User user);

    public boolean userAuthenticate(User user);

    public User userLogout(User user);
}
