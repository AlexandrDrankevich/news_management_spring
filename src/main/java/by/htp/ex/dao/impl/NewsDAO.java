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

	@Override
	public List<News> getLatestsList(int count) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<News> theQuery = currentSession.createQuery("from News order by date desc", News.class);
		theQuery.setMaxResults(count);
		List<News> news = theQuery.getResultList();
		return news;
	}

	@Override
	public List<News> getList() throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<News> theQuery = currentSession.createQuery("from News order by date desc", News.class);
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
	public void saveUpdateNews(News news) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(news);

	}

	@Override
	public void deleteNews(String[] idNews) throws NewsDAOException {
		Session currentSession = sessionFactory.getCurrentSession();
		for (String id : idNews) {
			Query theQuery = currentSession.createQuery("delete from News where idNews=:id");
			theQuery.setParameter("id", Integer.parseInt(id));
			theQuery.executeUpdate();
		}
	}


}
