package by.htp.ex.dao;

import by.htp.ex.bean.News;

import java.util.List;

public interface INewsDAO {
    List<News> getList() throws NewsDAOException;

    List<News> getLatestsList(int count) throws NewsDAOException;

    News fetchById(int id) throws NewsDAOException;

    void addNews(News news, String login) throws NewsDAOException;

    void updateNews(News news, String login) throws NewsDAOException;

    void deleteNews(String[] idNews) throws NewsDAOException;
}
