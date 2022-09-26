package by.htp.ex.dao.impl;

import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;

import by.htp.ex.entity.News;
import by.htp.ex.util.date.DateUtil;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDAO implements INewsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
			
    private static final String newsColumnLabelId = "id";
    private static final String newsColumnLabelTitle = "title";
    private static final String newsColumnLabelBrief = "brief";
    private static final String newsColumnLabelContent = "content";
    private static final String newsColumnLabelDate = "date";
    
    

    @Override
    public List<News> getLatestsList(int count) throws NewsDAOException {
    	Session currentSession = sessionFactory.getCurrentSession();
    	Query<News> theQuery = 
				currentSession.createQuery("from News order by date desc", News.class);
    	theQuery.setMaxResults(count);
			List<News> news = theQuery.getResultList();
		return news;
    }

    @Override
    public List<News> getList() throws NewsDAOException {
    	Session currentSession = sessionFactory.getCurrentSession();
    	Query<News> theQuery = 
				currentSession.createQuery("from News order by date desc", News.class);
  			List<News> result = theQuery.getResultList();
        return result;
    }

  

    @Override
    public News fetchById(int id) throws NewsDAOException {
        try {
        	Session currentSession = sessionFactory.getCurrentSession();
        	return currentSession.get(News.class, id);
                
        } catch (Exception e) {
            throw new NewsDAOException(e);
        }
    }

    

    @Override
    public void addNews(News news, String login) throws NewsDAOException {
    	System.out.println(news.getIdNews());
	Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(news);
		System.out.println(news.getIdNews());
    	
    }
   /* 
    * private static final String addNews = "INSERT INTO news(title, brief,content,date,reporter_id) values(?,?,?,?,?)";
    * public void addNews(News news, String login) throws NewsDAOException {
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
    }*/

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
