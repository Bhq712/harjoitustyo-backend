package hh.backend.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.backend.harjoitustyo.domain.Album;
import hh.backend.harjoitustyo.domain.AlbumRepository;
import hh.backend.harjoitustyo.domain.Artist;
import hh.backend.harjoitustyo.domain.ArtistRepository;
import hh.backend.harjoitustyo.domain.Song;
import hh.backend.harjoitustyo.domain.SongRepository;

@DataJpaTest
public class SongRepositoryTesting {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void createNewSong() {
        Artist artist = new Artist("Guns N' Roses", "Hard Rock");
        artistRepository.save(artist);

        Album album = new Album("Appetite for Destruction", 1987, artist);
        albumRepository.save(album);

        Song song = new Song("Welcome to the Jungle", 4.35, album);
        songRepository.save(song);

        assertThat(song.getSongId()).isNotNull();
        assertThat(songRepository.findByTitle("Welcome to the Jungle")).isNotEmpty();
    }

    @Test
    public void findByTitleShouldReturnSong() {
        Artist artist = new Artist("Eagles", "Rock");
        artistRepository.save(artist);

        Album album = new Album("Hotel California", 1976, artist);
        albumRepository.save(album);

        Song song = new Song("Hotel California", 6.30, album);
        songRepository.save(song);

        List<Song> result = songRepository.findByTitle("Hotel California");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDuration()).isEqualTo(6.30);
    }

    @Test
    public void deleteSongShouldRemoveItFromDatabase() {
        Artist artist = new Artist("Bon Jovi", "Rock");
        artistRepository.save(artist);

        Album album = new Album("Slippery When Wet", 1986, artist);
        albumRepository.save(album);

        Song song = new Song("Livin' on a Prayer", 4.09, album);
        songRepository.save(song);

        Long id = song.getSongId();
        songRepository.deleteById(id);

        assertThat(songRepository.findById(id)).isEmpty();
    }
}
