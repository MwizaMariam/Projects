package cultureLearning.cultureLearning.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import cultureLearning.cultureLearning.domain.Video;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VideoModel2 {
	private List<Video> videos;
	private Video video;
	
	
	
	@Init
	public void initialization(@QueryParam("id") Integer id) {
		videos = new ArrayList<Video>();
		/* ((ArrayList<Video>)videos).add(new Video(0, "video", "video.mp4")); */
		((ArrayList<Video>)videos).add(new Video(0, "Ikeshamvugo ku mata", "Amata.mp4"));
		((ArrayList<Video>)videos).add(new Video(1, "Ikeshamvugo  kw'isekuru,ingobyi,ingoma ", "Ikeshamvugo ingobyi.mp4"));
		
		  ((ArrayList<Video>)videos).add(new Video(2, "ikeshamvugo ku mwami,amata,igisabo,inka,gukama,urusyo",
		  "ntibavuga.mp4"));
		   ((ArrayList<Video>)videos).add(new Video(3,
		  "inkshamvugo k", "IREME.mp4"));
		   /*
		 * ((ArrayList<Video>)videos).add(new Video(4, "Uko batwaraga Umugeni",
		 * "umugeni.mp4"));
		 */
		/*
		 * ((ArrayList<Video>)videos).add(new Video(5, "Dusigasire Ikinyarwanda",
		 * "DUSIGASIRE IKINYARWANDA.mp4"));
		 */
		
		if(id!=null) {
			video = videos.get(id);
		}
		
	}
	
	
	
	public boolean isVideoSet() {
		return video!=null;
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
