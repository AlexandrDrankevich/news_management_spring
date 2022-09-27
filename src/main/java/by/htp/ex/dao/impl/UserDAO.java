package by.htp.ex.dao.impl;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;

import by.htp.ex.entity.UserInfo;
import by.htp.ex.util.date.DateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAO implements IUserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	private static final int saltLength = 30;

	@Override
	public UserInfo logination(String login, String password) throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<UserInfo> query = currentSession.createQuery("from UserInfo v where v.login=:login",
					UserInfo.class);
			query.setParameter("login", login);
			UserInfo user=query.uniqueResult();
			if (user != null) {
			return user=checkUserByPassword(user, password);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return null;
	}


	@Override
	public boolean registration(UserInfo user) throws DaoException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			if (isloginExist(currentSession, user.getLogin())) {
				return false;
			}
			System.out.println(user.getId());
			currentSession.saveOrUpdate(user);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	private UserInfo checkUserByPassword(UserInfo user, String password) {
		String hashPasswordDataBase=user.getPassword();
		String hashPassword = BCrypt.hashpw(password, hashPasswordDataBase.substring(0, saltLength));
		if( hashPasswordDataBase.equals(hashPassword)) {
			return user;
		}
		return null;
	}

	private boolean isloginExist(Session currentSession, String login) {
		Query<UserInfo> query = currentSession.createQuery("from UserInfo v where v.login=:login",
				UserInfo.class);
		query.setParameter("login", login);
		return query.uniqueResult() != null;

	}
}
