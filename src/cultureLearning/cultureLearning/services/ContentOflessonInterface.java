package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.ContentOfLesson;

public interface ContentOflessonInterface {

	public void createContent(ContentOfLesson c);
	public void deleteContent(ContentOfLesson c);
	public void updateContent(ContentOfLesson c);
	public List<ContentOfLesson>findAllContent();
	public ContentOfLesson findById(int id);
	public List<ContentOfLesson>findImiganiMigufi();
	public List<ContentOfLesson>findImiganiMiremire();
	public List<ContentOfLesson>findIbisakuzo();
}
