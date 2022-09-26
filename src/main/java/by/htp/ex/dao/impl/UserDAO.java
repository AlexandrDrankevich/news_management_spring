package by.htp.ex.dao.impl;

import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;

import by.htp.ex.entity.NewUserInfo;
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
	public boolean logination(String login, String password) throws DaoException {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Query<NewUserInfo> query = currentSession.createQuery("from NewUserInfo v where v.login=:login",
					NewUserInfo.class);
			query.setParameter("login", login);
			if (query.uniqueResult() != null) {
				query.uniqueResult().getPassword();
				return checkPassword(query.uniqueResult().getPassword(), password);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return false;
	}

	

	public String getRole(String login) throws DaoException {
		String userRole="admin";
		try {
			
			userRole="admin";
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return userRole;
	}

	@Override
	public boolean registration(NewUserInfo user) throws DaoException {
		Session currentSession = sessionFactory.getCurrentSession();
		try {
			if (isloginExist(currentSession, user.getLogin())) {
				return false;
			}
			currentSession.save(user);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return true;
	}

	private boolean checkPassword(String hashPasswordDataBase, String password) {
		String hashPassword = BCrypt.hashpw(password, hashPasswordDataBase.substring(0, saltLength));
		return hashPasswordDataBase.equals(hashPassword);
	}

	private boolean isloginExist(Session currentSession, String login) {
		Query<NewUserInfo> query = currentSession.createQuery("from NewUserInfo v where v.login=:login",
				NewUserInfo.class);
		query.setParameter("login", login);
		return query.uniqueResult() != null;

	}
}
