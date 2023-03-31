package app.lab3albums.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.lab3albums.entity.Album;
import app.lab3albums.service.AlbumService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class Initializer {
    private final AlbumService albumService;

    @Autowired
    public Initializer(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostConstruct
    @Transactional
    synchronized void init() {
        Album rebelyell = Album.builder()
                .name("Rebel Yell")
                .artist("Billy Idol")
                .releaseYear(1983)
                .length("38:22")
                .build();
        Album heavenorlasvegas = Album.builder()
                .name("Heaven or Las Vegas")
                .artist("Cocteau Twins")
                .releaseYear(1990)
                .length("37:24")
                .build();
        Album theooz = Album.builder()
                .name("The OOZ")
                .artist("King Krule")
                .releaseYear(2017)
                .length("66:0")
                .build();

        albumService.create(rebelyell);
        albumService.create(heavenorlasvegas);
        albumService.create(theooz);
    }
}
