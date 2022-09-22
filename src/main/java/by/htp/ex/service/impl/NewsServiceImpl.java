package by.htp.ex.service.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class NewsServiceImpl implements NewsService {

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	private List<Integer> pageCount;
	private String newsCount = "5";

	@Override
	public List<News> latestList(int count) throws ServiceException {
		try {
			List<News> latestNews = newsDAO.getLatestsList(count);
			if (latestNews.isEmpty()) {
				latestNews = null;
			}
			return latestNews;
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list(Integer pageNumber, String newsCount) throws ServiceException {
		if (newsCount != null) {
			this.newsCount = newsCount;
		}
		try {
			List<News> allNewsList = newsDAO.getList();
			pageCount = createPageCountList(allNewsList);
			return getNewsOnPage(allNewsList, pageNumber);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id) throws ServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(News news, String login) throws ServiceException {
		try {
			newsDAO.addNews(news, login);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(String[] idNews) throws ServiceException {
		try {
			newsDAO.deleteNews(idNews);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(News news, String login) throws ServiceException {
		try {
			newsDAO.updateNews(news, login);
		} catch (NewsDAOException e) {
			throw new ServiceException(e);
		}
	}

	private List<Integer> createPageCountList(List<News> allNewsList) {
		double numberNews = Double.parseDouble(newsCount);
		int number = (int) (Math.ceil(allNewsList.size() / numberNews));
		List<Integer> pageCount = new ArrayList<Integer>();
		for (int i = 1; i <= number; i++) {
			pageCount.add(i);
		}
		return pageCount;
	}

	private List<News> getNewsOnPage(List<News> allNewsList, Integer pageNumber) {
		int numberNews = Integer.valueOf(newsCount);
		List<News> newsListOnPage = new ArrayList<News>();
		if (allNewsList.isEmpty()) {
			return null;
		}
		int startNews = pageNumber * numberNews - numberNews;
		int finishNews = pageNumber * numberNews;
		if (finishNews > allNewsList.size()) {
			finishNews = allNewsList.size();
		}
		for (int i = startNews; i < finishNews; i++) {
			newsListOnPage.add(allNewsList.get(i));
		}
		if (newsListOnPage.isEmpty() && pageNumber > 1) {
			return getNewsOnPage(allNewsList, pageNumber - 1);
		}
		return newsListOnPage;

	}

	@Override
	public List<Integer> getPageCount() {
		return pageCount;
	}
}
