package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cultureLearning.cultureLearning.domain.Comment;
import cultureLearning.cultureLearning.services.CommentInterface;
@Service("comment")
public class CommentServiceImplemantation implements CommentServiceInerterface {
    @Autowired
	private CommentInterface co;
    @Transactional
	public void createComment(Comment c) {
		// TODO Auto-generated method stub
co.createComment(c);
	}
@Transactional
	public void deleteComment(Comment c) {
		// TODO Auto-generated method stub
co.deleteComment(c);
	}
	@Transactional
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return co.findAll();
	}

}
