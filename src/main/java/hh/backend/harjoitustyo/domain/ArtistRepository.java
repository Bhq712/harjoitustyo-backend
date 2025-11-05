package hh.backend.harjoitustyo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    // find artists by name
    List<Artist> findByName(String name);

   
}
