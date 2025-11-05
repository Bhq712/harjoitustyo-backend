package hh.backend.harjoitustyo.domain;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity

public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    //attribuutit

    private Long artistId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Genre is mandatory")
    private String genre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist", orphanRemoval = true)
    @JsonIgnore
    private List<Album> albums = new ArrayList<>();


    //konstruktorit
    
    public Artist(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

     public Artist() {}

    //getit ja setit
    
     public Long getArtistId() {
         return artistId;
     }

     public void setArtistId(Long artistId) {
         this.artistId = artistId;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getGenre() {
         return genre;
     }

     public void setGenre(String genre) {
         this.genre = genre;
     }

     public List<Album> getAlbums() {
         return albums;
     }

     public void setAlbums(List<Album> albums) {
         this.albums = albums;
     }

     //toString

     @Override
     public String toString() {
        return "Artist [artistId=" + artistId + ", name=" + name + ", genre=" + genre + "]";
     }


}
