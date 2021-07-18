package cultureLearning.cultureLearning.services;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cultureLearning.cultureLearning.domain.Video;
import cultureLearning.cultureLearning.exception.DuplicateNid;
@Repository
public class VideoDao implements VideoInterfaceService {

	@Autowired
	private SessionFactory session;
	@Override
	public void createVideo(Video v) {
		if (findVideo(v.getUrl())!=null) {
			throw new DuplicateNid();
		}
		// TODO Auto-generated method stub
		session.getCurrentSession().save(v);
	}

	@Override
	public void deleteVideo(Video v) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(v);
	}

	@Override
	public void UpdateVideo(Video v) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(v);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Video> listVideo() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Video").list();
	}

	@Override
	public Video findVideo(String url) {
		// TODO Auto-generated method stub
		return (Video) session.getCurrentSession().createCriteria(Video.class).add(Restrictions.eq("url", url)).uniqueResult();
	}

}
