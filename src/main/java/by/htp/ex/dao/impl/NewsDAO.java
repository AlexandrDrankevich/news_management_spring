package by.htp.ex.dao.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import by.htp.ex.util.date.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO implements INewsDAO {

    private static final String newsColumnLabelId = "id";
    private static final String newsColumnLabelTitle = "title";
    private static final String newsColumnLabelBrief = "brief";
    private static final String newsColumnLabelContent = "content";
    private static final String newsColumnLabelDate = "date";

    public static final String latestNews = "SELECT * FROM news order by date DESC limit ?";

    @Override
    public List<News> getLatestsList(int count) throws NewsDAOException {
        List<News> result = new ArrayList<News>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(latestNews)) {
            ps.setInt(1, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new News(rs.getInt(newsColumnLabelId), rs.getString(newsColumnLabelTitle), rs.getString(newsColumnLabelBrief),
                        rs.getString(newsColumnLabelContent), DateUtil.convertDateToStr(rs.getDate(newsColumnLabelDate))));
            }
        } catch (SQLException e) {
            throw new NewsDAOException(e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
        return result;
    }

    public static final String allNews = "SELECT * FROM news order by date DESC";

    @Override
    public List<News> getList() throws NewsDAOException {
        List<News> result = new ArrayList<News>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(allNews);
            while (rs.next()) {
                result.add(new News(rs.getInt(newsColumnLabelId), rs.getString(newsColumnLabelTitle), rs.getString(newsColumnLabelBrief),
                        rs.getString(newsColumnLabelContent), DateUtil.convertDateToStr(rs.getDate(newsColumnLabelDate))));
            }
        } catch (SQLException e) {
            throw new NewsDAOException(e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
        return result;
    }

    public static final String newsById = "SELECT * FROM news where id=?";

    @Override
    public News fetchById(int id) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(newsById)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new News(rs.getInt(newsColumnLabelId), rs.getString(newsColumnLabelTitle), rs.getString(newsColumnLabelBrief), rs.getString(newsColumnLabelContent),
                    DateUtil.convertDateToStr(rs.getDate(newsColumnLabelDate)));
        } catch (SQLException e) {
            throw new NewsDAOException(e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }

    private static final String addNews = "INSERT INTO news(title, brief,content,date,reporter_id) values(?,?,?,?,?)";

    @Override
    public void addNews(News news, String login) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(addNews, Statement.RETURN_GENERATED_KEYS)) {
            int userId = getUserId(connection, login);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getBriefNews());
            ps.setString(3, news.getContent());
            ps.setDate(4, DateUtil.convertStrToDate(news.getNewsDate()));
            ps.setInt(5, userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int idNews = rs.getInt(1);
            news.setIdNews(idNews);
        } catch (SQLException e) {
            throw new NewsDAOException(e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }

    private static final String updateNews = "UPDATE news SET title=?, brief=?,content=?,date=?,reporter_id=? WHERE id=?";

    @Override
    public void updateNews(News news, String login) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(updateNews)) {
            int userId = getUserId(connection, login);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getBriefNews());
            ps.setString(3, news.getContent());
            ps.setDate(4, DateUtil.convertStrToDate(news.getNewsDate()));
            ps.setInt(5, userId);
            ps.setInt(6, news.getIdNews());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new NewsDAOException(e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }

    public static final String deleteNewsById = "DELETE  FROM news where id=?;";

    @Override
    public void deleteNews(String[] idNews) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(deleteNewsById)) {
            for (String id : idNews) {
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new NewsDAOException(e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }

    private static final String getUserId = "SELECT id FROM users WHERE login=?";

    private int getUserId(Connection connection, String login) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(getUserId)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }
}
