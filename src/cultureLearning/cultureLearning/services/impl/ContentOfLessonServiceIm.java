package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cultureLearning.cultureLearning.domain.ContentOfLesson;
import cultureLearning.cultureLearning.services.ContentOflessonInterface;
@Service("content")
public class ContentOfLessonServiceIm implements IContentOfLesson {
@Autowired
	private ContentOflessonInterface dao;
@Transactional
	public void createContent(ContentOfLesson c) {
		// TODO Auto-generated method stub
dao.createContent(c);
	}
@Transactional
	public void deleteContent(ContentOfLesson c) {
		// TODO Auto-generated method stub
dao.deleteContent(c);
	}
@Transactional
	public void updateContent(ContentOfLesson c) {
		// TODO Auto-generated method stub
dao.updateContent(c);
	}
@Transactional
	public List<ContentOfLesson> findAllContent() {
		// TODO Auto-generated method stub
		return dao.findAllContent();
	}
@Transactional
public ContentOfLesson findById(int id) {
	// TODO Auto-generated method stub
	return dao.findById(id);
}
@Transactional
public List<ContentOfLesson> findImiganiMigufi() {
	// TODO Auto-generated method stub
	return dao.findImiganiMigufi();
}@Transactional
public List<ContentOfLesson> findImiganiMiremire() {
	// TODO Auto-generated method stub
	return dao.findImiganiMiremire();
}
@Transactional
public List<ContentOfLesson> findIbisakuzo() {
	// TODO Auto-generated method stub
	return dao.findIbisakuzo();
}

}
