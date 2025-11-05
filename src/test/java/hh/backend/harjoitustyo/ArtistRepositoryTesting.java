package hh.backend.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.backend.harjoitustyo.domain.Artist;
import hh.backend.harjoitustyo.domain.ArtistRepository;

@DataJpaTest
public class ArtistRepositoryTesting {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void createNewArtist() {
        Artist artist = new Artist("Metallica", "Heavy Metal");
        artistRepository.save(artist);

        assertThat(artist.getArtistId()).isNotNull();
        assertThat(artistRepository.findByName("Metallica")).isNotEmpty();
    }

    @Test
    public void findByNameShouldReturnArtist() {
        Artist artist = new Artist("Pink Floyd", "Progressive Rock");
        artistRepository.save(artist);

        List<Artist> result = artistRepository.findByName("Pink Floyd");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getGenre()).isEqualTo("Progressive Rock");
    }

    @Test
    public void deleteArtistShouldRemoveItFromDatabase() {
        Artist artist = new Artist("Nirvana", "Grunge");
        artistRepository.save(artist);

        Long id = artist.getArtistId();
        artistRepository.deleteById(id);

        assertThat(artistRepository.findById(id)).isEmpty();
    }
}

