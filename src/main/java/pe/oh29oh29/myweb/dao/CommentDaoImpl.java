package pe.oh29oh29.myweb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.oh29oh29.myweb.mapper.CommentMapper;
import pe.oh29oh29.myweb.model.Comment;
import pe.oh29oh29.myweb.model.CommentExample;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired CommentMapper commentMapper;
	
	@Override
	public int insertComment(Comment comment) {
		return commentMapper.insertSelective(comment);
	}

	@Override
	public List<Comment> readComments(CommentExample example) {
		return commentMapper.selectByExample(example);
	}

	@Override
	public int updateComment(Comment comment) {
		return commentMapper.updateByPrimaryKeySelective(comment);
	}

	@Override
	public int deleteComment(CommentExample example) {
		return commentMapper.deleteByExample(example);
	}

	@Override
	public int deleteAllComments() {
		return commentMapper.deleteByExample(null);
	}

}
