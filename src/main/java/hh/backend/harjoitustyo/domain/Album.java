package hh.backend.harjoitustyo.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity

public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    

 //attribuutit

    private Long albumId;

    @NotBlank(message = "Title is mandatory")
    private String title;
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name= "artistId")
    private Artist artist;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs = new ArrayList<>();

    //konstruktorit
    public Album(String title, int releaseYear, Artist artist) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    public Album() {}

    //getit ja setit    

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }


    //toString

    @Override
    public String toString() {
        return "Album [albumId=" + albumId + ", title=" + title + ", releaseYear=" + releaseYear + ", artist=" + artist
                + "]";
    }
    

}
