package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.entity.News;

public interface INewsDAO {
    List<News> getList() throws NewsDAOException;

    List<News> getLatestsList(int count) throws NewsDAOException;

    News fetchById(int id) throws NewsDAOException;

    void saveUpdateNews(News news) throws NewsDAOException;

    void deleteNews(String[] idNews) throws NewsDAOException;
}
