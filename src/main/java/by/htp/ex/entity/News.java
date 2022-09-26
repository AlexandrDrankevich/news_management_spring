package by.htp.ex.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int idNews;
	@Column(name = "title")
	private String title;
	@Column(name = "brief")
	private String briefNews;
	@Column(name = "content")
	private String content;
	@Column(name = "date")
	private java.sql.Date newsDate;

	public News() {
	}

	public Integer getIdNews() {
		return idNews;
	}

	public void setIdNews(Integer idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefNews() {
		return briefNews;
	}

	public void setBriefNews(String briefNews) {
		this.briefNews = briefNews;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public java.sql.Date getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(java.sql.Date newsDate) {
		this.newsDate = newsDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		News news = (News) o;
		return idNews == news.idNews && Objects.equals(title, news.title) && Objects.equals(briefNews, news.briefNews)
				&& Objects.equals(content, news.content) && Objects.equals(newsDate, news.newsDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNews, title, briefNews, content, newsDate);
	}

	@Override
	public String toString() {
		return "News{" + "idNews=" + idNews + ", title='" + title + '\'' + ", briefNews='" + briefNews + '\''
				+ ", content='" + content + '\'' + ", newsDate='" + newsDate + '\'' + '}';
	}
}
