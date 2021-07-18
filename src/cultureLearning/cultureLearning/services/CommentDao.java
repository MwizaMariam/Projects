package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cultureLearning.cultureLearning.domain.Comment;
@Repository
public class CommentDao implements CommentInterface {
@Autowired
private SessionFactory session;

public void createComment(Comment c) {
	// TODO Auto-generated method stub
	session.getCurrentSession().save(c);
}

public void deleteComment(Comment c) {
	// TODO Auto-generated method stub
	session.getCurrentSession().delete(c);
}

@SuppressWarnings("unchecked")
public List<Comment> findAll() {
	// TODO Auto-generated method stub
	return session.getCurrentSession().createQuery("from  Comment").list();
}

	
}
