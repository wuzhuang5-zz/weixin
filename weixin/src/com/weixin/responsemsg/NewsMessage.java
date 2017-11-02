package com.weixin.responsemsg;

import java.util.List;

/**
 * 图文消息
 * @author wz
 */
public class NewsMessage extends BaseMessage{
	//图文消息个数，限制为10条以内
	private int ArticleCount;
	//多条图文消息，默认第一个item为大图
	private List<Article> Article;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticle() {
		return Article;
	}
	public void setArticle(List<Article> article) {
		Article = article;
	}
	
}
	
