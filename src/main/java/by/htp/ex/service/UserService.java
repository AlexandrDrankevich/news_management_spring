package by.htp.ex.service;

import by.htp.ex.entity.UserInfo;

public interface UserService {

    UserInfo signIn(String login, String password) throws ServiceException;

    boolean registration(UserInfo user) throws ServiceException;
}
