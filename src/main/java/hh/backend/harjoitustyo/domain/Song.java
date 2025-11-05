package hh.backend.harjoitustyo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity

public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //attribuutit

    private Long songId;

    @NotBlank(message = "Title is mandatory")
    private String title;
    private double duration;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

    //konstruktorit
    
    public Song(String title, double duration, Album album) {
        this.title = title;
        this.duration = duration;
        this.album = album;
    }

    public Song() {}

    //Getit ja setit
    
    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Song [songId=" + songId + ", title=" + title + ", duration=" + duration + ", album=" + album + "]";
    }
    

}
