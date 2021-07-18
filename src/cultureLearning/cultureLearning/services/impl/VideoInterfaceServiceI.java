package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Video;

public interface VideoInterfaceServiceI {
	public void createVideo(Video v);
	public void deleteVideo(Video v);
	public void UpdateVideo(Video v);
	public List<Video> listVideo();
	public Video findVideo(String url);
}
