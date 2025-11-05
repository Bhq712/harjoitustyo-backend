package hh.backend.harjoitustyo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
	// find songs by title
	List<Song> findByTitle(String title);

	// find songs that belong to a given album
	List<Song> findByAlbum(Album album);

	// find songs by album id
	List<Song> findByAlbumAlbumId(Long albumId);
}
