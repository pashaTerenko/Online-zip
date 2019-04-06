package com.terenko.service;

import com.terenko.model.CustomUser;

public interface UserService {
    CustomUser getUserByLogin(String login);
    boolean existsByLogin(String login);
    void addUser(CustomUser customUser);

}
