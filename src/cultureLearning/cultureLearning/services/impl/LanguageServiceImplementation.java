package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cultureLearning.cultureLearning.domain.Culture;
import cultureLearning.cultureLearning.domain.Language;
import cultureLearning.cultureLearning.services.Languageinterface;
@Service("language")
public class LanguageServiceImplementation implements LanguageServiceInterface {
	
	@Autowired
	
	private Languageinterface language;
	
	@Override
	@Transactional
	public void registerLanguage(Language u) {
		// TODO Auto-generated method stub
		language.registerLanguage(u);
	}

	@Override
	@Transactional
	public void deleteLanguage(Language u) {
		// TODO Auto-generated method stub
		language.deleteLanguage(u);
	}

	@Override
	@Transactional
	public List<Language> findAllLanguage() {
		// TODO Auto-generated method stub
		return language.findAllLanguage();
	}

	@Override
	@Transactional
	public void update(Language u) {
		// TODO Auto-generated method stub
		language.update(u);
	}

	@Override
	@Transactional
	public Language findById(String id) {
		// TODO Auto-generated method stub
		return language.findById(id);
	}

	@Override
	public List<Language> ViewCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	
}