package cultureLearning.cultureLearning.services;

import java.util.List;

import cultureLearning.cultureLearning.domain.Language;

public interface Languageinterface {
	public void registerLanguage(Language u);
	public void deleteLanguage(Language u);
	public List<Language>findAllLanguage();
	public void update(Language u);
	public Language findById(String id);
	public  List<Language>ViewCourse();
}
