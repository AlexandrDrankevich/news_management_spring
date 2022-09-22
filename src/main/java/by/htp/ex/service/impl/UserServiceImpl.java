package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.UserService;
import by.htp.ex.util.validation.DataValidation;

public class UserServiceImpl implements UserService {

    private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();

    private final DataValidation.Builder validBuilder = new DataValidation.Builder();
    private static final String userRole = "guest";
    private static final String messageInvalideAuthData = "invalid authorization data";
    private static final String messageInvalideRegData = "invalid registration data";


    @Override
    public String signIn(String login, String password) throws ServiceException {
        if (!validBuilder.checkLogin(login).checkPassword(password).generateResult().isResult()) {
            throw new ServiceException(messageInvalideAuthData);
        }
        try {
            if (userDAO.logination(login, password)) {
                return userDAO.getRole(login);
            } else {
                return userRole;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(NewUserInfo user) throws ServiceException {
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
