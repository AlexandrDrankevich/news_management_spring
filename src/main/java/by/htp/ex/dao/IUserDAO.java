package by.htp.ex.dao;

import by.htp.ex.entity.NewUserInfo;

public interface IUserDAO {
    boolean logination(String login, String password) throws DaoException;

    boolean registration(NewUserInfo user) throws DaoException;

    String getRole(String login) throws DaoException;
}
