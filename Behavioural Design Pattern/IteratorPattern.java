import java.util.*;

class Video {
    private String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class YouTubePlaylist {
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
    }

    public List<Video> getVideos() {
        return videos;
    }
}

interface PlaylistIterator {
    boolean hasNext();

    Video next();
}

class YouTubePlaylistIterator implements PlaylistIterator {
    private List<Video> videos;
    private int position;

    public YouTubePlaylistIterator(List<Video> videos) {
        this.videos = videos;
        position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    @Override
    public Video next() {
        return hasNext() ? videos.get(position++) : null;
    }
}

public class IteratorPattern {
    public static void main(String[] args) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("LLD"));
        playlist.addVideo(new Video("System Design"));

        PlaylistIterator iterator = new YouTubePlaylistIterator(playlist.getVideos());

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }
}
