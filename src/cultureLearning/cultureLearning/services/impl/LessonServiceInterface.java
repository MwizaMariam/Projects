package cultureLearning.cultureLearning.services.impl;

import java.util.List;

import cultureLearning.cultureLearning.domain.Course;
import cultureLearning.cultureLearning.domain.Lesson;

public interface LessonServiceInterface {
	public void registerLesson(Lesson u);
	public void deleteLesson(Lesson u);
	public void deleteById(String code);
	public List<Lesson>findAllLesson();
	public void update(Lesson u);
	public Lesson findById(String id);
	public  List<Lesson>ViewCourse();

	/* public List <Lesson> findLessonByChapter(); */
	public List<Lesson> ingombajwi();
	/*
	 * public List<Lesson> itondeYinyugutiZikinyarwanda(); public List<Lesson>
	 * ibihekane(); public List<Lesson> inshinga(); public List<Lesson> interuro();
	 * public List<Lesson> indamukanyo(); public List<Lesson> amakuru(); public
	 * List<Lesson> Kwivuga(); public List<Lesson> Kuyoboza(); public List<Lesson>
	 * gusabaUbufasha(); public List<Lesson> amasano(); public List<Lesson>
	 * ibinyazina(); public List<Lesson> ibiceByumubiri(); public List<Lesson>
	 * ibiribwa(); public List<Lesson> kubara(); public List<Lesson>
	 * iminsiyicyumweru(); public List<Lesson> amasaha(); public List<Lesson>
	 * imyambaro(); public List<Lesson> inyamaswa(); public List<Lesson> imyuga();
	 * public List<Lesson> urugwiroNubutabazi(); public List<Lesson> imyadagaduro();
	 * public List<Lesson> murugo(); public List<Lesson> mubiro(); public
	 * List<Lesson> urugendo(); public List<Lesson> guhaha(); public List<Lesson>
	 * gutumiraAbantu(); public List<Lesson> intekozamazina(); public List<Lesson>
	 * guteka(); public List<Lesson> ibikoreshoByomugikoni(); public List<Lesson>
	 * ibyungo(); public List<Lesson> amagamboAbaza(); public List<Lesson>
	 * ibikoreshoByubwubatsi(); public List<Lesson> ntera(); public List<Lesson>
	 * inshingano(); public List<Lesson> amazinaYamabara(); public List<Lesson>
	 * ibiheByoMurwanda(); public List<Lesson> ubuyobozi(); public List<Lesson>
	 * gutwaraAbantuNibintu();
	 * 
	 * public List<Lesson> ingiro(); public List<Lesson> indango(); public
	 * List<Lesson> ubumweNubwinshi(); public List<Lesson> ikigombero(); public
	 * List<Lesson> imvugoIziguyeNitaziguye(); public List<Lesson>
	 * inzegoZubutegetsiMuRwanda(); public List<Lesson> ibyoUkundaNibyoUdakunda();
	 * public List<Lesson> KubaraInkuru(); public List<Lesson> amarangamutima();
	 * public List<Lesson> ingera(); public List<Lesson> inyigana(); public
	 * List<Lesson> impuzanyito(); public List<Lesson> imbusane(); public
	 * List<Lesson> ibaruwa();
	 * 
	 */
}
