package by.htp.ex.dao;

import by.htp.ex.entity.UserInfo;

public interface IUserDAO {
	UserInfo logination(String login, String password) throws DaoException;

    boolean registration(UserInfo user) throws DaoException;

}
