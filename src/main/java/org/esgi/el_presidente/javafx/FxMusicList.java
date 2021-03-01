package org.esgi.el_presidente.javafx;

public enum FxMusicList {
    GLORIOUS_MORNING("/music/glorious-morning.mp3"),
    GLORIOUS_MORNING_2("/music/glorious-morning-2.mp3");

    private final String path;

    FxMusicList(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
