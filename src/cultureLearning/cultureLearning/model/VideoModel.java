package cultureLearning.cultureLearning.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import cultureLearning.cultureLearning.domain.Video;
import cultureLearning.cultureLearning.services.impl.VideoService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VideoModel {
	private List<Video> videos;
	@Autowired
	private Video video;
	@WireVariable("video")
	private VideoService videoservice;
	@Autowired
	private ListModelList<Video> listOfVideo;

	@Init
	public void initialization(@QueryParam("id") Integer id) {
		videos = new ArrayList<Video>();
		listOfVideo = new ListModelList<Video>(videoservice.listVideo());
		video = new Video();

		((ArrayList<Video>) videos).add(new Video(2, "Duhugukirwe no kunoza ururimi", "NOZA.mp4"));
		((ArrayList<Video>) videos).add(new Video(3, "Dore Imyambarire ya kera  n'ibisobanuro byayo", "Imyambaro.mp4"));

		((ArrayList<Video>) videos).add(new Video(4, "Uko batwaraga Umugeni", "umugeni.mp4"));

		((ArrayList<Video>) videos).add(new Video(5, "Dusigasire Ikinyarwanda", "DUSIGASIREIKINYARWANDA.mp4"));

//		if (id != null) {
//			video = videos.get(id);
//		}

	}

	@Command("save")
	public void createVideolink() {
		try {
			videoservice.createVideo(video);
			Clients.showNotification("Ubitse neza umuyobora wa videwo yawe");
		} catch (Exception e) { // TODO: handle exception
			Clients.showNotification("kubika umuyoboro ntibyakunze" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Command("delete")
	public void deleteVideo(@BindingParam("del") Video video) {
		try {
			videoservice.deleteVideo(video);
			Clients.showNotification("videwo yawe yasibitse");
		} catch (Exception e) {

			Clients.showNotification("gusiba video ntibyakunze" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Command("update")
	public void updateVideolink(@BindingParam("up") Video video) {
		try {
			videoservice.UpdateVideo(video);
			Clients.showNotification("Ivugururwa ryagenze neza");
		} catch (Exception e) {
			// TODO: handle exception
			Clients.showNotification("Ivugururwa ntabwo ryagenze neza" + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean isVideoSet() {
		return video != null;
	}

	public VideoService getVideoservice() {
		return videoservice;
	}

	public void setVideoservice(VideoService videoservice) {
		this.videoservice = videoservice;
	}

	public ListModelList<Video> getListOfVideo() {
		return listOfVideo;
	}

	public void setListOfVideo(ListModelList<Video> listOfVideo) {
		this.listOfVideo = listOfVideo;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public List<Video> getVideos() {
		return videos;
	}

}
