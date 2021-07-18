package cultureLearning.cultureLearning.services;


import java.util.List;

import cultureLearning.cultureLearning.domain.Video;

public interface VideoInterfaceService {
public void createVideo(Video v);
public void deleteVideo(Video v);
public void UpdateVideo(Video v);
public List<Video> listVideo();
public Video findVideo(String url);
}
