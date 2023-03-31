package app.lab3songs.service;

import app.lab3songs.entity.Album;
import app.lab3songs.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.lab3songs.entity.Song;
import app.lab3songs.repository.SongRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    @Autowired
    public SongService(SongRepository repository, AlbumRepository albumRepository) {
        this.songRepository = repository;
        this.albumRepository = albumRepository;
    }

    public Optional<Song> find(int id) {
        return songRepository.findById(id);
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    @Transactional
    public List<Song> findAllByAlbumId(int albumId) {
        return songRepository.findAllByAlbumId(albumId);
    }

    @Transactional
    public Song create(Song song) {
        return songRepository.save(song);
    }

    @Transactional
    public void update(int id, Song song) {
        find(id).ifPresentOrElse(
                (original) -> {
                    original.setName(song.getName());
                    original.setLength(song.getLength());
                    original.setRate(song.getRate());
                },
                () -> {
                    throw new IllegalArgumentException("Cannot update song");
                }
        );
    }

    @Transactional
    public void delete(int id) {
        songRepository.deleteById(id);
    }

    @Transactional
    public void addAlbumToSong(int albumId, Song song) {
        Optional<Album> album = albumRepository.findById(albumId);
        album.ifPresentOrElse(
                song::setAlbum,
                () -> {
                    throw new IllegalArgumentException("Cannot add song to album");
                });
    }
}
