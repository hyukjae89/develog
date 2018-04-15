package pe.oh29oh29.myweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.oh29oh29.myweb.common.Utils;
import pe.oh29oh29.myweb.dao.CommentDao;
import pe.oh29oh29.myweb.model.Comment;
import pe.oh29oh29.myweb.model.CommentExample;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired CommentDao commentDao;
	
	@Override
	public void writeComment(Comment comment) {
		comment.setIdx(Utils.generateIdx());
		comment.setRegDate(Utils.generateNowGMTDate());
		commentDao.insertComment(comment);
	}

	@Override
	public List<Comment> readComments(String postIdx) {
		CommentExample example = new CommentExample();
		example.createCriteria().andPostIdxEqualTo(postIdx);
		return commentDao.readComments(example);
	}

	@Override
	public void modifyComment(Comment comment) {
		commentDao.updateComment(comment);
	}

	@Override
	public void removeComment(String idx) {
		CommentExample example = new CommentExample();
		example.createCriteria().andIdxEqualTo(idx);
		commentDao.deleteComment(example);
	}

}
