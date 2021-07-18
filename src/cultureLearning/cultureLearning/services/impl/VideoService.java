package cultureLearning.cultureLearning.services.impl;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cultureLearning.cultureLearning.domain.Video;
import cultureLearning.cultureLearning.services.VideoDao;

@Service("video")
public class VideoService implements VideoInterfaceServiceI {
	@Autowired
private VideoDao dao;
@Transactional
	@Override
	public void createVideo(Video v) {
		// TODO Auto-generated method stub
		dao.createVideo(v);
	
		
	}
	@Transactional
	@Override
	public void deleteVideo(Video v) {
		// TODO Auto-generated method stub
		dao.deleteVideo(v);
	}
	@Transactional
	@Override
	public void UpdateVideo(Video v) {
		// TODO Auto-generated method stub
		dao.UpdateVideo(v);
	}
@Transactional
	@Override
	public List<Video> listVideo() {
		// TODO Auto-generated method stub
		return dao.listVideo();
	}
@Transactional
	@Override
	public Video findVideo(String url) {
		// TODO Auto-generated method stub
		return dao.findVideo(url);
	}
	

}
