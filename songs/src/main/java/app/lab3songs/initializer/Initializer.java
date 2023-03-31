package app.lab3songs.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.lab3songs.entity.Album;
import app.lab3songs.entity.Song;
import app.lab3songs.service.AlbumService;
import app.lab3songs.service.SongService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class Initializer {
    private final AlbumService albumService;
    private final SongService songService;

    @Autowired
    public Initializer(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @PostConstruct
    @Transactional
    synchronized void init() {
        Album rebelyell = Album.builder().build();
        Album heavenorlasvegas = Album.builder().build();
        Album theooz = Album.builder().build();

        albumService.create(rebelyell);
        albumService.create(heavenorlasvegas);
        albumService.create(theooz);

        Song rebel_yell = Song.builder()
                .name("Rebel Yell")
                .length("4:48")
                .rate(6)
                .album(rebelyell)
                .build();
        Song eyesWithoutAFace = Song.builder()
                .name("Eyes Without A Face")
                .length("4:59")
                .rate(10)
                .album(rebelyell)
                .build();
        Song iceblinkluck = Song.builder()
                .name("Iceblink Luck")
                .length("5:53")
                .rate(7)
                .album(heavenorlasvegas)
                .build();
        Song halfmanhalfshark = Song.builder()
                .name("Half Man Half Shark")
                .length("5:02")
                .rate(8)
                .album(theooz)
                .build();
        Song logos = Song.builder()
                .name("Logos")
                .length("3:50")
                .rate(9)
                .album(theooz)
                .build();

        songService.create(rebel_yell);
        songService.create(eyesWithoutAFace);
        songService.create(iceblinkluck);
        songService.create(halfmanhalfshark);
        songService.create(logos);
    }
}
