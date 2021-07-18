package cultureLearning.cultureLearning.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@DiscriminatorValue("ContentBased")
public class ContentBasedLesson extends Lesson {
	
	private String title;
	@Column(columnDefinition ="TEXT" )
	private String content;
	@OneToMany(mappedBy = "contentBasedLessson")
	private List<ContentOfLesson> contentLesson;
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public String getTitle() {
		return title;
	}
	public List<ContentOfLesson> getContentLesson() {
		return contentLesson;
	}
	public void setContentLesson(List<ContentOfLesson> contentLesson) {
		this.contentLesson = contentLesson;
	}
	
}
