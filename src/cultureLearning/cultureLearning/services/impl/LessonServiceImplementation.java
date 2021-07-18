package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Lesson;
import cultureLearning.cultureLearning.services.LessonInterface;
import cultureLearning.cultureLearning.services.LessonDao;

@Service("Lesson")
public class LessonServiceImplementation implements LessonServiceInterface {

	@Autowired
	private LessonDao dao;
	
	@Transactional
	public void registerLesson(Lesson u) {
		// TODO Auto-generated method stub
		dao.registerLesson(u);
	}

	@Transactional

	public void deleteLesson(Lesson u) {
		// TODO Auto-generated method stub
		dao.deleteLesson(u);
	}

	@Transactional
	public List<Lesson> findAllLesson() {
		// TODO Auto-generated method stub
		return dao.findAllLesson();
	}

	@Transactional
	public void update(Lesson u) {
		// TODO Auto-generated method stub
		dao.update(u);
	}

	@Transactional
	public Lesson findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);

	}

	public LessonInterface getDao() {
		return dao;
	}

	public void setDao(LessonDao dao) {
		this.dao = dao;
	}

	@Transactional
	public List<Lesson> ViewCourse() {
		// TODO Auto-generated method stub
		return dao.ViewCourse();
	}

	@Override
	@Transactional
	public void deleteById(String code) {
		// TODO Auto-generated method stub
		dao.deleteLessonById(code);
	}

	/*
	 * @Override
	 * 
	 * @Transactional public List<Lesson> findLessonByChapter() { // TODO
	 * Auto-generated method stub return dao.findLessonByChapter(); }
	 */

	@Override
	@Transactional
	public List<Lesson> ingombajwi() {
		// TODO Auto-generated method stub
		return dao.ingombajwi();
	}

	/*
	 * @Override
	 * 
	 * @Transactional public List<Lesson> itondeYinyugutiZikinyarwanda() { // TODO
	 * Auto-generated method stub return dao.itondeYinyugutiZikinyarwanda(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibihekane() { // TODO Auto-generated
	 * method stub return dao.ibihekane(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> inshinga() { // TODO Auto-generated method
	 * stub return dao.inshinga(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> interuro() { // TODO Auto-generated method
	 * stub return dao.interuro(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> indamukanyo() { // TODO Auto-generated
	 * method stub return dao.indamukanyo(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> amakuru() { // TODO Auto-generated method
	 * stub return dao.amakuru(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> Kwivuga() { // TODO Auto-generated method
	 * stub return dao.Kwivuga(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> Kuyoboza() { // TODO Auto-generated method
	 * stub return dao.Kuyoboza(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> gusabaUbufasha() { // TODO Auto-generated
	 * method stub return dao.gusabaUbufasha(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> amasano() { // TODO Auto-generated method
	 * stub return dao.amasano(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibinyazina() { // TODO Auto-generated
	 * method stub return dao.ibinyazina(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibiceByumubiri() { // TODO Auto-generated
	 * method stub return dao.ibiceByumubiri(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibiribwa() { // TODO Auto-generated method
	 * stub return dao.ibiribwa(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> kubara() { // TODO Auto-generated method
	 * stub return dao.kubara(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> iminsiyicyumweru() { // TODO
	 * Auto-generated method stub return dao.iminsiyicyumweru(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> amasaha() { // TODO Auto-generated method
	 * stub return dao.amasaha(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> imyambaro() { // TODO Auto-generated
	 * method stub return dao.imyambaro(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> inyamaswa() { // TODO Auto-generated
	 * method stub return dao.inyamaswa(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> imyuga() { // TODO Auto-generated method
	 * stub return dao.imyuga(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> urugwiroNubutabazi() { // TODO
	 * Auto-generated method stub return dao.urugwiroNubutabazi(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> imyadagaduro() { // TODO Auto-generated
	 * method stub return dao.imyadagaduro(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> murugo() { // TODO Auto-generated method
	 * stub return dao.murugo(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> mubiro() { // TODO Auto-generated method
	 * stub return dao.mubiro(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> urugendo() { // TODO Auto-generated method
	 * stub return dao.urugendo(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> guhaha() { // TODO Auto-generated method
	 * stub return dao.guhaha(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> gutumiraAbantu() { // TODO Auto-generated
	 * method stub return dao.gutumiraAbantu(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> intekozamazina() { // TODO Auto-generated
	 * method stub return dao.intekozamazina(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> guteka() { // TODO Auto-generated method
	 * stub return dao.guteka(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibikoreshoByomugikoni() { // TODO
	 * Auto-generated method stub return dao.ibikoreshoByomugikoni(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibyungo() { // TODO Auto-generated method
	 * stub return dao.ibyungo(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> amagamboAbaza() { // TODO Auto-generated
	 * method stub return dao.amagamboAbaza(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibikoreshoByubwubatsi() { // TODO
	 * Auto-generated method stub return dao.ibikoreshoByubwubatsi(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ntera() { // TODO Auto-generated method
	 * stub return dao.ntera(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> inshingano() { // TODO Auto-generated
	 * method stub return dao.inshingano(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> amazinaYamabara() { // TODO Auto-generated
	 * method stub return dao.amazinaYamabara(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibiheByoMurwanda() { // TODO
	 * Auto-generated method stub return dao.ibiheByoMurwanda(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ubuyobozi() { // TODO Auto-generated
	 * method stub return dao.ubuyobozi(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> gutwaraAbantuNibintu() { // TODO
	 * Auto-generated method stub return dao.gutwaraAbantuNibintu(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ingiro() { // TODO Auto-generated method
	 * stub return dao.ingiro(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> indango() { // TODO Auto-generated method
	 * stub return dao.indango(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ubumweNubwinshi() { // TODO Auto-generated
	 * method stub return dao.ubumweNubwinshi(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ikigombero() { // TODO Auto-generated
	 * method stub return dao.ikigombero(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> imvugoIziguyeNitaziguye() { // TODO
	 * Auto-generated method stub return dao.imvugoIziguyeNitaziguye(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> inzegoZubutegetsiMuRwanda() { // TODO
	 * Auto-generated method stub return dao.inzegoZubutegetsiMuRwanda(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibyoUkundaNibyoUdakunda() { // TODO
	 * Auto-generated method stub return dao.ibyoUkundaNibyoUdakunda(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> KubaraInkuru() { // TODO Auto-generated
	 * method stub return dao.KubaraInkuru(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> amarangamutima() { // TODO Auto-generated
	 * method stub return dao.amarangamutima(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ingera() { // TODO Auto-generated method
	 * stub return dao.ingera(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> inyigana() { // TODO Auto-generated method
	 * stub return dao.inyigana(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> impuzanyito() { // TODO Auto-generated
	 * method stub return dao.impuzanyito(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> imbusane() { // TODO Auto-generated method
	 * stub return dao.imbusane(); }
	 * 
	 * @Override
	 * 
	 * @Transactional public List<Lesson> ibaruwa() { // TODO Auto-generated method
	 * stub return dao.ibaruwa(); }
	 * 
	 */
	
	
}
