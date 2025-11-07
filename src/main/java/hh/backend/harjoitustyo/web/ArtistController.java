package hh.backend.harjoitustyo.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.transaction.annotation.Transactional;

import hh.backend.harjoitustyo.domain.Album;
import hh.backend.harjoitustyo.domain.AlbumRepository;
import hh.backend.harjoitustyo.domain.Artist;
import hh.backend.harjoitustyo.domain.ArtistRepository;
import hh.backend.harjoitustyo.domain.Song;
import hh.backend.harjoitustyo.domain.SongRepository;
import jakarta.validation.Valid;

@Controller
public class ArtistController {

    ArtistRepository artistRepository;
    AlbumRepository albumRepository;
    SongRepository songRepository;

    ArtistController (ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

   @GetMapping("/musiclist")
    public String musicList(Model model) {
    List<Artist> artists = artistRepository.findAll();

    for (Artist artist : artists) {
        List<Album> albums = albumRepository.findByArtist(artist); 
        for (Album album : albums) {
            List<Song> songs = songRepository.findByAlbum(album); 
            album.setSongs(songs); 
        }
        artist.setAlbums(albums); 
    }

    model.addAttribute("artists", artists);
    return "musiclist"; //musiclist.html
}

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addartist")
    public String showAddArtistForm(Model model) {
    Artist artist = new Artist();
    Album album = new Album();
    album.setTitle("");
    album.setReleaseYear(0);
    album.setSongs(new ArrayList<>());
    album.getSongs().add(new Song());
    album.getSongs().add(new Song());
    artist.setAlbums(new ArrayList<>());
    artist.getAlbums().add(album);

    model.addAttribute("artist", artist);
    return "addartist";
}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addartist")
    public String saveArtist(@Valid @ModelAttribute Artist artist, BindingResult result) {

    // jos validointi epäonnistuu, näytetään lomake uudelleen
    if (result.hasErrors()) {
        return "addartist";
    }

    // asetetaan relatiot albumien ja biisien kanssa
    if (artist.getAlbums() != null) {
        for (Album album : artist.getAlbums()) {
            album.setArtist(artist);
            if (album.getSongs() != null) {
                List<Song> cleaned = album.getSongs().stream()
                    .filter(s -> s.getTitle() != null && !s.getTitle().isBlank())
                    .peek(s -> s.setAlbum(album))
                    .collect(Collectors.toList());
                album.setSongs(cleaned);
            }
        }
    }

    artistRepository.save(artist);
    return "redirect:/musiclist";
}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/deleteartist/{id}")
    @Transactional
    public String deleteArtist(@PathVariable("id") Long artistId) {
    artistRepository.deleteById(artistId);
    return "redirect:/musiclist";
}

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editartist/{id}")
    public String showEditArtistForm(@PathVariable("id") Long artistId, Model model) {
    Artist artist = artistRepository.findById(artistId).orElse(null);
    if (artist == null) {
        return "redirect:/musiclist";
    }

    // haetaan albumit ja biisit
    List<Album> albums = albumRepository.findByArtist(artist);
    for (Album album : albums) {
        List<Song> songs = songRepository.findByAlbum(album);
        album.setSongs(songs);
    }
    artist.setAlbums(albums);

    model.addAttribute("artist", artist);
    return "editartist"; // luodaan tämä html
}

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/editartist")
    @Transactional
    public String updateArtist(@Valid @ModelAttribute Artist artist, BindingResult result) {
    if (result.hasErrors()) {
        return "editartist"; // palaa lomakkeelle virheilmoitusten kanssa
    }

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

    artistRepository.save(artist);
    return "redirect:/musiclist";
}

}
