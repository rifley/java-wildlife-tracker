import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class RangerTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void ranger_instantiatesCorrectly_false() {
    Ranger testRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    assertEquals(true, testRanger instanceof Ranger);
  }

  @Test
  public void getName_rangerInstantiatesWithName_Todd() {
    Ranger testRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    assertEquals("Todd", testRanger.getName());
  }

  @Test
  public void equals_returnsTrueIfNameIsTheSame_false() {
    Ranger firstRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    Ranger anotherRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    assertTrue(firstRanger.equals(anotherRanger));
  }

  @Test
  public void save_assignsIdToObjectAndSavesObjectToDatabase() {
    Ranger testRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    testRanger.save();
    Ranger savedRanger = Ranger.all().get(0);
    assertEquals(testRanger.getId(), savedRanger.getId());
  }

  @Test
  public void all_returnsAllInstancesOfRanger_false() {
    Ranger firstRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    firstRanger.save();
    Ranger secondRanger = new Ranger("Todds Brother", 25, "Jodd@lost.com", "5022211111");
    secondRanger.save();
    assertEquals(true, Ranger.all().get(0).equals(firstRanger));
    assertEquals(true, Ranger.all().get(1).equals(secondRanger));
  }

  @Test
  public void find_returnsRangerWithSameId_secondRanger() {
    Ranger firstRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    firstRanger.save();
    Ranger secondRanger = new Ranger("Todds Brother", 25, "Jodd@lost.com", "5022211111");
    secondRanger.save();
    assertEquals(Ranger.find(secondRanger.getId()), secondRanger);
  }

  @Test
  public void delete_deletesRangerFromDatabase_0() {
    Ranger testRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    testRanger.save();
    testRanger.delete();
    assertEquals(0, Ranger.all().size());
  }

  public void updateName_updatesRangerNameInDatabase_String() {
    Ranger testRanger = new Ranger("Todd", 24, "todd@lost.com", "5022211122");
    testRanger.save();
    testRanger.updateName("Toad");
    assertEquals("Toad", testRanger.getName());
  }

  @Test
  public void find_returnsNullWhenNoRangerFound_null() {
    assertTrue(Ranger.find(999) == null);
  }

}
