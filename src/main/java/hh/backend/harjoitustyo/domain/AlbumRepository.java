package hh.backend.harjoitustyo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	// find albums by title
	List<Album> findByTitle(String title);

	// find albums that belong to a given artist
	List<Album> findByArtist(Artist artist);

	// find albums by artist id
	List<Album> findByArtistArtistId(Long artistId);
}
