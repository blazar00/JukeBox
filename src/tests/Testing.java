package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import model.Playlist;
import model.Registry;
import model.Song;
import model.User;

public class Testing {
	
    @Test
	public void TestSong() {
		Song testing = new Song("Hello, Goodbye", "songfiles/hello.mp3", "The Beatles", 85);
		assertEquals(testing.getName(), "Hello, Goodbye");
		assertEquals(testing.getPath(), "songfiles/hello.mp3");
		assertEquals(testing.getArtist(), "The Beatles");
		assertEquals(testing.getTime(), 85);
	}
	
    @Test 
    public void TestPlaylist() {
    	Playlist testing = new Playlist();
    	Song Loping = new Song("LopingSting", "songfiles/LopingSting.mp3", "Kevin MacLeod", 5);
    	testing.addSong(Loping);
    	assertEquals(testing.getSong(0), Loping);
    	testing.addToQueue("LopingSting");
    	assertEquals(testing.find("LopingSting"), Loping);
    	//need to test play function
    }
    
    @Test 
    public void TestRegistry() {
    	Registry testing = new Registry();
    	testing.addUser("Chris", "1");   	
    	assertEquals(testing.search("Chris", "wrongpass"),null);
    	User account = testing.search("Chris", "1");
    	assertEquals(account.getAccountName(), "Chris");
    	assertEquals(account.getPassword(), "1");
    	account.setAccountName("NewUser");
    	account.setPassword("NewPassword");
    	assertEquals(account.getAccountName(),"NewUser");
    	assertEquals(account.getPassword(), "NewPassword");
    	assertEquals(account.getSongsPlayed(),0);
    	account.playedSong();
    	assertEquals(account.getSongsPlayed(),1);
    }
	
}