package pe.oh29oh29.myweb.service;

import java.util.List;

import pe.oh29oh29.myweb.model.Comment;

public interface CommentService {

	public void writeComment(Comment comment);
	
	public List<Comment> readComments(String postIdx);
	
	public void modifyComment(Comment comment);
	
	public void removeComment(String idx);
		
}
