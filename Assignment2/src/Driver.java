//The required Client code to show some scenarios from the users
import java.io.File;

public class Driver {
	
	public static void main(String[] args) {
		//Create several Movie objects
		File m1 = new File("../Avengers.mp4");
		Movie avenger = new Movie(m1, "Avengers", "English", "Marvel");
		File m2 = new File("../Avengers2.mp4");
		Movie avenger2 = new Movie(m2, "Avengers2", "English", "Marvel");
		File m3 = new File("../Avengers3.mp4");
		Movie avenger3 = new Movie(m3, "Avengers3", "English", "Marvel");
		File m4 = new File("../Wolf-Warrior.mp4");
		Movie wolf = new Movie(m4, "Wolf-Warrior", "Chinese", "Deng Feng");
		File m5 = new File("../Wolf-Warrior2.mp4");
		Movie wolf2 = new Movie(m5, "Wolf-Warrior2", "Chinese", "Deng Feng");
		File m6 = new File("../Flipped.mp4");
		Movie flipped = new Movie(m6, "Flipped", "English", "Warner");
		
		//Set the sequels of the movies
		avenger.setNext(avenger2);
		avenger2.setNext(avenger3);
		avenger2.setPrevious(avenger);
		avenger3.setPrevious(avenger2);
		wolf.setNext(wolf2);
		wolf2.setPrevious(wolf);
		
		//Create several Episode objects
		File ep1 = new File("../Singer-EP1.mp4");
		Episode episode1 = new Episode(ep1, 1, "Singer EP1", "Chinese", "Hunan");
		File ep2 = new File("../Singer-EP2.mp4");
		Episode episode2 = new Episode(ep2, 2, "Singer EP2", "Chinese", "Hunan");
		File ep3 = new File("../Singer-EP3.mp4");
		Episode episode3 = new Episode(ep3, 3, "Singer EP3", "Chinese", "Hunan");
		
		//Set the sequence of Episodes
		episode1.setNext(episode2);
		episode2.setNext(episode3);
		episode2.setPrevious(episode1);
		episode3.setPrevious(episode3);
		
		//Create a TV Show
		TVShow singer = new TVShow("Singer");
		singer.add(episode1);
		singer.add(episode2);
		singer.add(episode3);
		
		//Create several watch lists
		WatchList marvel = new WatchList("Marvel");
		marvel.add(avenger);
		marvel.add(avenger2);
		marvel.add(avenger3);
		
		WatchList war = new WatchList("War movies");
		war.add(wolf);
		war.add(wolf2);
		
		WatchList love = new WatchList("Romantic Movies");
		love.add(flipped);
		
		WatchList show = new WatchList("All of Singer");
		show.add(episode1);
		show.add(episode2);
		show.add(episode3);
		show.add(singer);
		
		WatchList all = new WatchList("All availiable resources");
		all.add(avenger);
		all.add(avenger2);
		all.add(avenger3);
		all.add(wolf);
		all.add(wolf2);
		all.add(flipped);
		all.add(episode1);
		all.add(episode2);
		all.add(episode3);
		all.add(singer);
		
		//Create a Library
		Library stevenyuan = new Library("Resources of Steven Yuan");
		stevenyuan.addMovie(avenger);
		stevenyuan.addMovie(avenger2);
		stevenyuan.addMovie(avenger3);
		stevenyuan.addMovie(wolf);
		stevenyuan.addMovie(wolf2);
		stevenyuan.addMovie(flipped);
		stevenyuan.addEpisode(episode1);
		stevenyuan.addEpisode(episode2);
		stevenyuan.addEpisode(episode3);
		stevenyuan.addTVShow(singer);
		stevenyuan.addList(marvel);
		stevenyuan.addList(war);
		stevenyuan.addList(love);
		stevenyuan.addList(show);
		stevenyuan.addList(all);
		
		//The following parts is required by the description, generating at least two watch lists
		
		//Generate some random watch list from the library by the algorithm specified by clients
		WatchList w1 = stevenyuan.generateList("5 random watchable things", new ByRandomNum(5));
		WatchList w2 = stevenyuan.generateList("English watchable things", new ByLanguage("English"));
		WatchList w3 = stevenyuan.generateList("Deng Feng's movies", new ByStudio("Deng Feng"));
		System.out.println(w1);
		System.out.println(w2);
		System.out.println(w3);
	}

}
