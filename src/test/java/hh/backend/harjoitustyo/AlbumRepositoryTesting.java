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

@DataJpaTest
public class AlbumRepositoryTesting {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void createNewAlbum() {
        Artist artist = new Artist("Queen", "Rock");
        artistRepository.save(artist);

        Album album = new Album("A Night at the Opera", 1975, artist);
        albumRepository.save(album);

        assertThat(album.getAlbumId()).isNotNull();
        assertThat(albumRepository.findByTitle("A Night at the Opera")).isNotEmpty();
    }

    @Test
    public void findByTitleShouldReturnAlbum() {
        Artist artist = new Artist("AC/DC", "Rock");
        artistRepository.save(artist);

        Album album = new Album("Back in Black", 1980, artist);
        albumRepository.save(album);

        List<Album> result = albumRepository.findByTitle("Back in Black");
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getReleaseYear()).isEqualTo(1980);
    }

    @Test
    public void deleteAlbumShouldRemoveItFromDatabase() {
        Artist artist = new Artist("The Beatles", "Rock");
        artistRepository.save(artist);

        Album album = new Album("Abbey Road", 1969, artist);
        albumRepository.save(album);

        Long id = album.getAlbumId();
        albumRepository.deleteById(id);

        assertThat(albumRepository.findById(id)).isEmpty();
    }
}
