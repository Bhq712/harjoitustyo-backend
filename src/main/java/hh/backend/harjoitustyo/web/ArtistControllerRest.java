package hh.backend.harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hh.backend.harjoitustyo.domain.Album;
import hh.backend.harjoitustyo.domain.AlbumRepository;
import hh.backend.harjoitustyo.domain.Artist;
import hh.backend.harjoitustyo.domain.ArtistRepository;
import hh.backend.harjoitustyo.domain.Song;
import hh.backend.harjoitustyo.domain.SongRepository;
import jakarta.transaction.Transactional;

@CrossOrigin
@RestController
public class ArtistControllerRest {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public ArtistControllerRest(ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    //Hae kaikki artistit
    @GetMapping("/api/artists")
    public List<Artist> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        for (Artist artist : artists) {
            List<Album> albums = albumRepository.findByArtist(artist);
            for (Album album : albums) {
                List<Song> songs = songRepository.findByAlbum(album);
                album.setSongs(songs);
            }
            artist.setAlbums(albums);
        }
        return artists;
    }

    //Hae yksi artisti ID:n perusteella
    @GetMapping("/api/artists/{id}")
    public Optional<Artist> getArtistById(@PathVariable("id") Long id) {
        Optional<Artist> artistOpt = artistRepository.findById(id);
        artistOpt.ifPresent(artist -> {
            List<Album> albums = albumRepository.findByArtist(artist);
            for (Album album : albums) {
                List<Song> songs = songRepository.findByAlbum(album);
                album.setSongs(songs);
            }
            artist.setAlbums(albums);
        });
        return artistOpt;
    }

    //Lisää uusi artisti + albumit + biisit
     @PostMapping("/api/artists")
    @PreAuthorize("hasRole('ADMIN')")
    public Artist createArtist(@RequestBody Artist artist) {
        if (artist.getAlbums() != null) {
            for (Album album : artist.getAlbums()) {
                album.setArtist(artist);
                if (album.getSongs() != null) {
                    for (Song song : album.getSongs()) {
                        song.setAlbum(album);
                    }
                }
            }
        }
        return artistRepository.save(artist);
    }

    // Muokkaa artistia
    @PutMapping("/api/artists/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Artist updateArtist(@PathVariable("id") Long id, @RequestBody Artist updatedArtist) {
        Artist existingArtist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        existingArtist.setName(updatedArtist.getName());
        existingArtist.setGenre(updatedArtist.getGenre());

        albumRepository.deleteAll(albumRepository.findByArtist(existingArtist));

        if (updatedArtist.getAlbums() != null) {
            for (Album album : updatedArtist.getAlbums()) {
                album.setArtist(existingArtist);
                if (album.getSongs() != null) {
                    for (Song song : album.getSongs()) {
                        song.setAlbum(album);
                    }
                }
                albumRepository.save(album);
            }
        }

        return artistRepository.save(existingArtist);
    }

    // Poista artisti
    @DeleteMapping("/api/artists/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteArtist(@PathVariable("id") Long id) {
        artistRepository.deleteById(id);
    }
}