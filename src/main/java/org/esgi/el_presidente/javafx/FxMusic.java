package org.esgi.el_presidente.javafx;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

public class FxMusic {

    private MediaPlayer player;
    private final List<Media> playlist;

    public FxMusic() {
        playlist = new ArrayList<>();
    }

    public void startMusicPlayer() {
        if (player != null) {
            player.stop();
        }
        playlist.clear();
        loadPlaylist();
        playMusic(0);
    }

    private void loadPlaylist() {
        for (FxMusicList fxmusic : FxMusicList.values()) {
            try {
                Media media = new Media(getClass().getResource(fxmusic.getPath()).toString());
                playlist.add(media);
            } catch (Exception e) {
                System.out.println("Can't read sound files : " + fxmusic.getPath());
                return;
            }
        }
    }

    private void playMusic(int i) {
        if (playlist.size() == 0) {
            return;
        }
        player = new MediaPlayer(playlist.get(i));
        int nextI = (i + 1) % playlist.size();
        player.setOnEndOfMedia(() -> playMusic(nextI));
        player.setAutoPlay(true);
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public void resume() {
        player.play();
    }
}
