
public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create several Movie objects
		Movie french = new Movie("/Users/stevenyuan/Documents/dzx/birthday/french.mp4",
				"The wishes in French", "French", "Youtube");
		//Try create Movie object with unacceptable format
		try {
			Movie error = new Movie("/Users/stevenyuan/Documents/dzx/birthday/french.mp3",
						"The wishes in French", "French", "Youtube");
			error.getTitle();
		}
		catch (AssertionError e){
			System.out.println("Successfully deal with the wrong format");
		}
		Movie japanese = new Movie("/Users/stevenyuan/Documents/dzx/birthday/japanese.mp4",
				"The wishes in Japanese", "Japanese", "Youtube");
		Movie surprise = new Movie("/Users/stevenyuan/Documents/dzx/birthday/surprise.mp4",
				"Surprise", "Chinese", "Tencent");
		Movie cut = new Movie("/Users/stevenyuan/Documents/dzx/birthday/love.mp4",
				"Cut", "Chinese", "Youtube");
		Movie finalCut = new Movie("/Users/stevenyuan/Documents/dzx/birthday/final.mp4",
				"Final", "Chinese", "iMovie");
		//Try create some WatchList object and some methods
		WatchList loves = new WatchList("loves");
		loves.add(french);
		loves.add(japanese);
		WatchList parts = new WatchList("parts");
		parts.add(surprise);
		parts.add(cut);
		WatchList finalVersion = new WatchList("final");
		finalVersion.add(finalCut);
		System.out.println(french.getValidity());
		System.out.println(french.getTitle());
		System.out.println(french.getStudio().toString());
		System.out.println(french.getLanguage().toString());
		System.out.println(french.getFormat());
		french.addPair("Addition", "This is the version in French");
		french.addPair("I love you", "Je t'aime");
		french.modifyPair("Addition", "lalalala");
		french.removePair("I love you");
		System.out.println(loves.getName());
		loves.setName("newLoves");
		System.out.println(loves.getName());
		System.out.println(loves.validMovies());
		Movie invalid = new Movie("/lala.mp4", "invalid", "heyhey", "dutch");
		loves.add(invalid);
		System.out.println(loves.validMovies());
		System.out.println(parts.allLanguages().toString());
		System.out.println(parts.allStudios().toString());
		try {
			WatchList newLove = new WatchList("parts");
			newLove.accessAll();
		}
		catch (AssertionError e){
			System.out.println("Unable to create two watch list with same name");
		}
		System.out.println(loves.accessAll().toString());
		loves.watchOne();
		System.out.println(loves.accessAll().toString());
		//Test the library
		Library test = new Library("Library1");
		test.addMovie(french);
		test.addMovie(japanese);
		test.addMovie(surprise);
		test.addMovie(cut);
		test.addMovie(finalCut);
		test.addList(loves);
		test.addList(parts);
		test.addList(finalVersion);
		Movie sameFrench = new Movie("/Users/stevenyuan/Documents/dzx/birthday/french.mp4",
				"The wishes in French", "French", "Youtube");
		try {
			loves.add(sameFrench);
			loves.add(sameFrench);
		}
		catch (AssertionError e) {
			System.out.println("Unable to add same Movie to a watch list");
		}
		try {
			test.addMovie(sameFrench);
		}
		catch (AssertionError e) {
			System.out.println("Unable to add the movie with same info into a Library");
		}
	}

}
