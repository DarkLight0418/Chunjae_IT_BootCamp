package com.gotit.board;

public class PostService {
	private PostDAO dao;
	
	private static final PostService instance = new PostService();

	private PostService() {
		dao = new PostDAO();
	}
	
	public static PostService getInstance() {
		return instance;
	}
}
