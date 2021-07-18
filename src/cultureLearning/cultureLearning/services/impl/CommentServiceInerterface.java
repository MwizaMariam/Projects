package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Comment;

public interface CommentServiceInerterface {
	public void createComment(Comment c);
	public void deleteComment(Comment c);
	public List<Comment>findAll();
}
