package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Comment;

public interface CommentInterface {

	public void createComment(Comment c);
	public void deleteComment(Comment c);
	public List<Comment>findAll();
	
}
