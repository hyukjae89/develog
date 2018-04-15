package pe.oh29oh29.myweb.dao;

import java.util.List;

import pe.oh29oh29.myweb.model.Comment;
import pe.oh29oh29.myweb.model.CommentExample;

public interface CommentDao {
	
	public int insertComment(Comment comment);
	
	public List<Comment> readComments(CommentExample example);
	
	public int updateComment(Comment comment);
	
	public int deleteComment(CommentExample example);
	
	public int deleteAllComments();
}
