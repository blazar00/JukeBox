package tests;
import static org.junit.Assert.*;

import org.junit.Test;

public class SongTest {
  
  // Using Test Driven Design to come up with a Song Type for Jukebox

  @Test
  public void testCanPlay() {
    Song song = new Song ("Pokemon Capture", 5, "Pikachu", "Capture.mp3");
    song.PlayMe();
    song.PlayMe();
    assertEquals(2, song.timePlayedToday())
    assertTrue(song.canBePlayedToday());
    song.PlayMe();
    assertFalse(song.canBePlayedToday());
   
    song.pretendItsTomorrow();
    assertEquals(0, song.timePlayedToday())
    assertTrue(song.canBePlayedToday());
    song.PlayMe();
    song.PlayMe();
    song.PlayMe();
    assertEquals(3, song.timePlayedToday())
    assertFalse(song.canBePlayedToday());
    
        
  }

}
