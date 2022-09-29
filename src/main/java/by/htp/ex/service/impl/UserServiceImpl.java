package by.htp.ex.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.entity.UserInfo;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import by.htp.ex.util.validation.DataValidation;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private IUserDAO userDAO;

	private final DataValidation.Builder validBuilder = new DataValidation.Builder();
	private static final String messageInvalideAuthData = "invalid authorization data";
	private static final String messageInvalideRegData = "invalid registration data";

	@Override
	@Transactional
	public UserInfo signIn(String login, String password) throws ServiceException {
		if (!validBuilder.checkLogin(login).checkPassword(password).generateResult().isResult()) {
			throw new ServiceException(messageInvalideAuthData);
		}
		try {
			return userDAO.logination(login, password);

		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public boolean registration(UserInfo user) throws ServiceException {
		if (!validBuilder.checkRegData(user).generateResult().isResult()) {
			throw new ServiceException(messageInvalideRegData);
		}
		try {
			return userDAO.registration(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
