import java.io.File;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create several Movie objects
		File fr = new File("/Users/stevenyuan/Documents/dzx/birthday/french.mp4");
		Movie french = new Movie(fr,
				"The wishes in French", "French", "Youtube");
		//Try create Movie object with unacceptable format
		try {
			File er = new File("/Users/stevenyuan/Documents/dzx/birthday/french.mp3");
			Movie error = new Movie(er,
						"The wishes in French", "French", "Youtube");
			error.getTitle();
		}
		catch (AssertionError e){
			System.out.println("Successfully deal with the wrong format");
		}
		File ja = new File("/Users/stevenyuan/Documents/dzx/birthday/japanese.mp4");
		Movie japanese = new Movie(ja,
				"The wishes in Japanese", "Japanese", "Youtube");
		File su = new File("/Users/stevenyuan/Documents/dzx/birthday/surprise.mp4");
		Movie surprise = new Movie(su,
				"Surprise", "Chinese", "Tencent");
		File cu = new File("/Users/stevenyuan/Documents/dzx/birthday/love.mp4");
		Movie cut = new Movie(cu,
				"Cut", "Chinese", "Youtube");
		File fi = new File("/Users/stevenyuan/Documents/dzx/birthday/final.mp4");
		Movie finalCut = new Movie(fi,
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
		File in = new File("/lala.mp4");
		Movie invalid = new Movie(in, "invalid", "heyhey", "dutch");
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
		File sa = new File("/Users/stevenyuan/Documents/dzx/birthday/french.mp4");
		Movie sameFrench = new Movie(sa,
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
