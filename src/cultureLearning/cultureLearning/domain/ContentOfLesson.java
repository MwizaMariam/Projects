package cultureLearning.cultureLearning.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ContentOfLesson {
	@Id
private String contentTitle;
@Column(columnDefinition ="TEXT" )
private String contentBody;
@ManyToOne
private  Lesson lesson;

@ManyToOne
private ContentBasedLesson contentBasedLessson;



public String getContentTitle() {
	return contentTitle;
}
public void setContentTitle(String contentTitle) {
	this.contentTitle = contentTitle;
}
public String getContentBody() {
	return contentBody;
}
public void setContentBody(String contentBody) {
	this.contentBody = contentBody;
}
public Lesson getLesson() {
	return lesson;
}
public void setLesson(Lesson lesson) {
	this.lesson = lesson;
}

public ContentBasedLesson getContentBasedLessson() {
	return contentBasedLessson;
}

public void setContentBasedLessson(ContentBasedLesson contentBasedLessson) {
	this.contentBasedLessson = contentBasedLessson;
}

}
