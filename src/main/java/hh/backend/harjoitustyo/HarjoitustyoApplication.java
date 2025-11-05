package hh.backend.harjoitustyo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hh.backend.harjoitustyo.domain.Album;
import hh.backend.harjoitustyo.domain.AlbumRepository;
import hh.backend.harjoitustyo.domain.Artist;
import hh.backend.harjoitustyo.domain.ArtistRepository;
import hh.backend.harjoitustyo.domain.Song;
import hh.backend.harjoitustyo.domain.SongRepository;
import hh.backend.harjoitustyo.domain.User;
import hh.backend.harjoitustyo.domain.UserRepository;

@SpringBootApplication
public class HarjoitustyoApplication {

	private static final Logger log = LoggerFactory.getLogger(HarjoitustyoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}

	@Bean
	public CommandLineRunner DatanLisays(ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository) {

			return (args) -> {
			log.info("Lisätty Gaga");
			Artist gaga = new Artist("Lady Gaga", "Pop");
            artistRepository.save(gaga);

			Album mayhem = new Album("MAYHEM", 2025, gaga);
	
			mayhem.getSongs().add(new Song("Disease", 3.49, mayhem));
			mayhem.getSongs().add(new Song("Abracadabra", 3.43, mayhem));
			mayhem.getSongs().add(new Song("LoveDrug", 3.13, mayhem));
			mayhem.getSongs().add(new Song("Killah", 3.30, mayhem));
			mayhem.getSongs().add(new Song("Shadow of A Man", 3.19, mayhem));

			gaga.getAlbums().add(mayhem);
			albumRepository.save(mayhem);

			Album chromatica = new Album("Chromatica", 2020, gaga);
			
			chromatica.getSongs().add(new Song("Rain On Me", 3.02, chromatica));
			chromatica.getSongs().add(new Song("911", 2.52, chromatica));
			chromatica.getSongs().add(new Song("Replay", 3.06, chromatica));
			chromatica.getSongs().add(new Song("Stupid Love", 3.13, chromatica));

			gaga.getAlbums().add(chromatica);
			albumRepository.save(chromatica);

			Album joanne = new Album("Joanne", 2016, gaga);

			joanne.getSongs().add(new Song("John Wayne", 2.54, joanne));
			joanne.getSongs().add(new Song("Diamond Heart", 3.30, joanne));
			joanne.getSongs().add(new Song("Dancin' In Circles", 3.27, joanne));
			joanne.getSongs().add(new Song("Perfect Illusion", 3.02, joanne));
			joanne.getSongs().add(new Song("A-YO", 3.27, joanne));

			gaga.getAlbums().add(joanne);
			albumRepository.save(joanne);

			Album artpop = new Album("ARTPOP", 2013, gaga);

			artpop.getSongs().add(new Song("Aura", 3.55, artpop));
			artpop.getSongs().add(new Song("Venus", 3.53, artpop));
			artpop.getSongs().add(new Song("G.U.Y", 3.52, artpop));
			artpop.getSongs().add(new Song("MANiCURE", 3.19, artpop));
			artpop.getSongs().add(new Song("Donatella", 4.24, artpop));
			artpop.getSongs().add(new Song("Mary Jane Holland", 4.37, artpop));
			artpop.getSongs().add(new Song("Applause", 3.32, artpop));

			gaga.getAlbums().add(artpop);
			albumRepository.save(artpop);

			Album bornthisway = new Album("Born This Way", 2011, gaga);
		
			bornthisway.getSongs().add(new Song("Marry The Night", 3.24, bornthisway));
			bornthisway.getSongs().add(new Song("Born This Way", 4.20, bornthisway));
			bornthisway.getSongs().add(new Song("Government Hooker", 4.14, bornthisway));
			bornthisway.getSongs().add(new Song("Judas", 4.09, bornthisway));
			bornthisway.getSongs().add(new Song("Americano", 4.06, bornthisway));
			bornthisway.getSongs().add(new Song("Scheibe", 3.45, bornthisway));
			bornthisway.getSongs().add(new Song("Bloody Mary", 4.04, bornthisway));
			bornthisway.getSongs().add(new Song("Heavy Metal Lover", 4.12, bornthisway));
			bornthisway.getSongs().add(new Song("Electric Chapel", 4.12, bornthisway));

			gaga.getAlbums().add(bornthisway);
			albumRepository.save(bornthisway);

			Album fame = new Album("The Fame", 2008, gaga);

			fame.getSongs().add(new Song("Just Dance", 4.01, fame));
			fame.getSongs().add(new Song("LoveGame", 3.36, fame));
			fame.getSongs().add(new Song("Paparazzi", 3.28, fame));
			fame.getSongs().add(new Song("Poker Face", 3.57, fame));
			fame.getSongs().add(new Song("StarStruck", 3.37, fame));

			gaga.getAlbums().add(fame);
			albumRepository.save(fame);

			artistRepository.save(gaga);

			Artist marina = new Artist("Marina", "Pop");
            artistRepository.save(marina);

			Album thefamilyjewels = new Album("The Family Jewels", 2009, marina);

			thefamilyjewels.getSongs().add(new Song("Are you satisfied?", 3.18, thefamilyjewels));
			thefamilyjewels.getSongs().add(new Song("Shampain", 3.11, thefamilyjewels));
			thefamilyjewels.getSongs().add(new Song("I am not a robot", 3.34, thefamilyjewels));
			thefamilyjewels.getSongs().add(new Song("Girls", 3.26, thefamilyjewels));
			thefamilyjewels.getSongs().add(new Song("Hollywood", 3.50, thefamilyjewels));
			thefamilyjewels.getSongs().add(new Song("The outsider", 3.14, thefamilyjewels));
			thefamilyjewels.getSongs().add(new Song("Hermit the frog", 3.33, thefamilyjewels));
			thefamilyjewels.getSongs().add(new Song("Oh no!", 3.00, thefamilyjewels));

			marina.getAlbums().add(thefamilyjewels);
			albumRepository.save(thefamilyjewels);

			Album electraheart = new Album("Electra Heart", 2012, marina);

			electraheart.getSongs().add(new Song("Bubblegum bitch", 2.34, electraheart));
			electraheart.getSongs().add(new Song("Primadonna", 3.41, electraheart));
			electraheart.getSongs().add(new Song("Power & control", 3.46, electraheart));
			electraheart.getSongs().add(new Song("How to be a heartbreaker", 3.41, electraheart));

			marina.getAlbums().add(electraheart);
			albumRepository.save(electraheart);

			Album ancientdreams = new Album("Ancient dreams in a modern land", 2022, marina);

			ancientdreams.getSongs().add(new Song("Ancient dreams on a modern land", 3.26, ancientdreams));
			ancientdreams.getSongs().add(new Song("Venus fly trap", 2.38, ancientdreams));
			ancientdreams.getSongs().add(new Song("Man's world", 3.27, ancientdreams));
			ancientdreams.getSongs().add(new Song("Happy loner", 3.13, ancientdreams));

			marina.getAlbums().add(ancientdreams);
			albumRepository.save(ancientdreams);

			Album pop = new Album("Princess of power", 2025, marina);

			pop.getSongs().add(new Song("Butterfly", 4.25, pop));
			pop.getSongs().add(new Song("Cuntissimo", 4.00, pop));
			pop.getSongs().add(new Song("Cupid's girl", 3.28, pop));
			pop.getSongs().add(new Song("Digital fantasy", 3.19, pop));
			pop.getSongs().add(new Song("Final boss", 3.10, pop));

			marina.getAlbums().add(pop);
			albumRepository.save(pop);

			artistRepository.save(marina);

			Artist reol = new Artist("Reol", "Jpop");
			artistRepository.save(reol);

			Album sigma = new Album("Sigma", 2016, reol);

			sigma.getSongs().add(new Song("Give Me a Break Stop Now", 3.41, sigma));
			sigma.getSongs().add(new Song("Lunatic", 3.17, sigma));
			sigma.getSongs().add(new Song("DetaramE KiddinG", 3.17, sigma));
			sigma.getSongs().add(new Song("Summer Horror Party", 4.14, sigma));
			sigma.getSongs().add(new Song("404 not found", 4.04, sigma));
			
			reol.getAlbums().add(sigma);
			albumRepository.save(sigma);

			Album jijitsujo = new Album("Jijitsujo", 2018, reol);

			jijitsujo.getSongs().add(new Song("SAISAKI", 3.37, jijitsujo));
			jijitsujo.getSongs().add(new Song("Bon-no-yu-gi", 3.12, jijitsujo));
			jijitsujo.getSongs().add(new Song("Shinku old rose", 4.08, jijitsujo));

			reol.getAlbums().add(jijitsujo);
			albumRepository.save(jijitsujo);

			Album thesixs = new Album("The sixth sense", 2021, reol);

			thesixs.getSongs().add(new Song("The sixth sense", 3.11, thesixs));
			thesixs.getSongs().add(new Song("Q?", 3.14, thesixs));
			thesixs.getSongs().add(new Song("Ms.Control", 3.10, thesixs));
			thesixs.getSongs().add(new Song("White midnight", 4.06, thesixs));
			thesixs.getSongs().add(new Song("mutant", 4.08, thesixs));
			thesixs.getSongs().add(new Song("Boy", 4.11, thesixs));

			reol.getAlbums().add(thesixs);
			albumRepository.save(thesixs);

			Album blackbox = new Album("Black Box", 2023, reol);

			blackbox.getSongs().add(new Song("Final call", 3.19, blackbox));
			blackbox.getSongs().add(new Song("Scorpion", 2.56, blackbox));
			blackbox.getSongs().add(new Song("Glitter", 3.14, blackbox));
			blackbox.getSongs().add(new Song("secret trip", 3.33, blackbox));
			blackbox.getSongs().add(new Song("Edge", 3.29, blackbox));
			blackbox.getSongs().add(new Song("DDD", 2.54, blackbox));

			reol.getAlbums().add(blackbox);
			albumRepository.save(blackbox);

			artistRepository.save(reol);
		};
	}

	@Bean
	public CommandLineRunner initUsers(UserRepository userRepository) {
		return (args) -> {
			//tämä tarkistaa onko käyttäjiä olemassa
			if (userRepository.findByUsername("user") == null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			log.info("Creat users with BCrypt hashed passwords");

			//luodaan peruskäyttäjä, joka saa katsella musiikkilistaa

			User user = new User();
			user.setUsername("katselija");
			user.setPassword(passwordEncoder.encode("katselija"));
			user.setRole("KATSELIJA");
			userRepository.save(user);

			//luodaan admin, joka voi myös katselun lisäksi editoida ja poistaa tietoja

			User admin = new User();
			admin.setUsername("yllapitaja");
			admin.setPassword(passwordEncoder.encode("yllapitaja"));
			admin.setRole("ADMIN");
			userRepository.save(admin);

			log.info("Users created");
			} else {
				log.info("Users exist already");
			}
		};
	}

	/*kun haluat h2-consolessa listata kaikki:
	SELECT 
    a.artist_id AS Artist_ID,
    a.name AS Artist_Name,
    a.genre AS Genre,
    al.album_id AS Album_ID,
    al.title AS Album_Title,
    al.release_year AS Release_Year,
    s.song_id AS Song_ID,
    s.title AS Song_Title,
    s.duration AS Duration
FROM artist a
LEFT JOIN album al ON a.artist_id = al.artist_id
LEFT JOIN song s ON al.album_id = s.album_id
ORDER BY a.artist_id, al.album_id, s.song_id; 

Jos haluat lisätä esim artistin:
INSERT INTO ARTIST (NAME, GENRE)
VALUES ('Metallica', 'Metal');

Albumin lisäys:
INSERT INTO ALBUM (TITLE, RELEASE_YEAR, ARTIST_ID)
VALUES ('Ride the Lightning', 1984, 1);

biisin lisäys:
INSERT INTO SONG (TITLE, DURATION, ALBUM_ID)
VALUES ('Fade to Black', 6.55, 1);

poisto:

-- Poista ensin biisit, jotka kuuluvat Metallican albumeihin
DELETE FROM SONG
WHERE ALBUM_ID IN (
  SELECT ALBUM_ID FROM ALBUM WHERE ARTIST_ID IN (
    SELECT ARTIST_ID FROM ARTIST WHERE NAME = 'Metallica'
  )
);

-- Poista Metallican albumit
DELETE FROM ALBUM
WHERE ARTIST_ID IN (
  SELECT ARTIST_ID FROM ARTIST WHERE NAME = 'Metallica'
);

-- Poista lopuksi artisti
DELETE FROM ARTIST
WHERE NAME = 'Metallica';
*/



}
