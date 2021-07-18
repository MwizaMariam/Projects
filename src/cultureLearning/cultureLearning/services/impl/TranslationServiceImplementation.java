package cultureLearning.cultureLearning.services.impl;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.*;

@Service("TranslationService")
public class TranslationServiceImplementation implements TranslationService {
	
	public String translateKinyarwandaToEnglish(String text) throws Exception {
		Translate translator = TranslateOptions.newBuilder().setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("/Inter/src/helical-button-238512-80a758b55445.json"))).build().getService();
		return translator.translate(text,Translate.TranslateOption.sourceLanguage("rw"),Translate.TranslateOption.targetLanguage("en")).getTranslatedText();
	}

	public String translateEnglishToKinyarwanda(String text) throws Exception {
		Translate translator = TranslateOptions.newBuilder().setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("/Inter/src/helical-button-238512-80a758b55445.json"))).build().getService();
		return translator.translate(text,Translate.TranslateOption.sourceLanguage("en"),Translate.TranslateOption.targetLanguage("rw")).getTranslatedText();
	}

}
