package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Culture;

public interface CultureInterfaceService {
	public void registerCulture(Culture u);
	public void deleteCulture(Culture u);
	public List<Culture>findAllCulture();
	public void update(Culture u);
	public Culture findById(String id);
	public  List<Culture>ViewCourse();
}
