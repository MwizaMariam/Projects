package cultureLearning.cultureLearning.services.impl;

public interface NotificationService {
	public void sendEmail(String email, String subject, String text) throws Exception;
	public void sendEmail(String email, String subject, String text, String attachmentPath) throws Exception;
}
