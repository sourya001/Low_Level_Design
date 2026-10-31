import java.util.*;

interface VideoDownloader {
    String downloadVideo(String videoURL);
}

class RealVideoDownloader implements VideoDownloader {
    @Override
    public String downloadVideo(String videoUrl) {
        System.out.println("Downloading video from URL: " + videoUrl);
        return "Video content from " + videoUrl;
    }
}

// PROXY
class CachedVideoDownloader implements VideoDownloader {
    private RealVideoDownloader realVideoDownloader;
    private Map<String, String> cache;

    public CachedVideoDownloader() {
        this.realVideoDownloader = new RealVideoDownloader();
        this.cache = new HashMap<>();
    }

    @Override
    public String downloadVideo(String videoUrl) {
        if (cache.containsKey(videoUrl)) {
            System.out.println("Returning cached video for: " + videoUrl);
            return cache.get(videoUrl);
        }

        System.out.println("Cache miss. Downloading...");
        String video = realVideoDownloader.downloadVideo(videoUrl);
        cache.put(videoUrl, video);
        return video;
    }
}

public class Proxy {
    public static void main(String[] args) {
        VideoDownloader cacheVideoDownloader = new CachedVideoDownloader();
        System.out.println("User 1 tries to download the video.");
        cacheVideoDownloader.downloadVideo("https://video.com/proxy-pattern");

        System.out.println();

        System.out.println("User 2 tries to download the same video again.");
        cacheVideoDownloader.downloadVideo("https://video.com/proxy-pattern");

        System.out.println();

        System.out.println("User 3 tries to download the same video again.");
        cacheVideoDownloader.downloadVideo("https://video.com/proxy-pattern-2");
    }
}