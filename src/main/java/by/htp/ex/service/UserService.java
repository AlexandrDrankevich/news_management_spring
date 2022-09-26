package by.htp.ex.service;

import by.htp.ex.entity.NewUserInfo;

public interface UserService {

    String signIn(String login, String password) throws ServiceException;

    boolean registration(NewUserInfo user) throws ServiceException;
}
