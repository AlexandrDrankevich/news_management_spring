package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.util.date.DateUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {

    private static final String userRole = "guest";
    private static final String rolesColumnLabelTitle = "title";
    private static final String usersColumnLabelPassword = "password";
    private static final int saltLength = 30;

    private static final String authorizeDataSelection = "SELECT * FROM users WHERE login=?";

    @Override
    public boolean logination(String login, String password) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(authorizeDataSelection)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return checkPassword(connection, login, password);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return false;
    }

    private static final String getuserRole = "SELECT roles.title FROM users inner join roles on users.roles_id=roles.id where users.login=? ";

    public String getRole(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(getuserRole)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(rolesColumnLabelTitle);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return userRole;
    }

    private static final String insertRegistrationData = "INSERT INTO users(login,password,registration_date,name,surname,birthday) values (?,?,?,?,?,?)";

    @Override
    public boolean registration(NewUserInfo user) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(insertRegistrationData)) {
            if (isloginExist(connection, user.getLogin())) {
                return false;
            }
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setDate(3, DateUtil.getDate());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());
            ps.setDate(6, DateUtil.convertStrToDate(user.getBirthday()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return true;
    }

    private static final String getPassword = "SELECT password FROM users WHERE login=?";

    private boolean checkPassword(Connection connection, String login, String password) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(getPassword)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String hashPasswordDataBase = rs.getString(usersColumnLabelPassword);
            String hashPassword = BCrypt.hashpw(password, hashPasswordDataBase.substring(0, saltLength));
            return hashPasswordDataBase.equals(hashPassword);
        }
    }

    private static final String getlogin = "SELECT login FROM users WHERE login=?";

    private boolean isloginExist(Connection connection, String login) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(getlogin)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

}
