//$Id$
package com.lld.structural_pattern;

import java.util.HashMap;
import java.util.Map;


//Dummy Video Object stores the meta data of downloading video
class Video{
	
	private String name;
	//Meta Data 
	Video(String name){
		this.name = name;
	}
	
}

interface VideoDownloader{
	Video getVideo(String videoName);
}


class VideoDownloaderImplementation implements VideoDownloader{

	@Override
	public Video getVideo(String videoName) {
		
		System.out.println("Connecting to the video server");
		System.out.println("Downloading Video");
		System.out.println("Getting Meta Object of video");
		System.out.println("Requested Video : "+videoName);
		return new Video(videoName);
	}
	
}

class ProxyVideoDownloaderImplementation implements VideoDownloader{
	
	private final Map<String,Video> videoCache = new HashMap<>();
	private final VideoDownloaderImplementation videoObj = new VideoDownloaderImplementation();

	@Override
	public Video getVideo(String videoName) {
		// TODO Auto-generated method stub
		
		if(!videoCache.containsKey(videoName)) {
			videoCache.put(videoName, videoObj.getVideo(videoName));
		}
		return videoCache.get(videoName);
	}
	
	
}



public class ProxyPatternCachingObjClient {

	public static void main(String... strings) {
		ProxyVideoDownloaderImplementation videoDownloader = new ProxyVideoDownloaderImplementation();
		videoDownloader.getVideo("system design tutorial");
		videoDownloader.getVideo("datastructures tutorial");
		videoDownloader.getVideo("HLD tutorial");
		videoDownloader.getVideo("system design tutorial");
		videoDownloader.getVideo("HLD tutorial");
	}

}
