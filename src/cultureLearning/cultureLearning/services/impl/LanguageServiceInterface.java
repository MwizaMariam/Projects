package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Language;

public interface LanguageServiceInterface {
	public void registerLanguage(Language u);
	public void deleteLanguage(Language u);
	public List<Language>findAllLanguage();
	public void update(Language u);
	public Language findById(String id);
	public  List<Language>ViewCourse();

}
