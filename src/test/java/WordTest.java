import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class WordTest {


  @Test
  public void Word_instantiatesCorrectly_true(){
    Word myWord = new Word("dynamite");
    assertEquals(true, myWord instanceof Word); //checking if an instance of the class will show up, therefore true.
  }

  @Test
  public void word_instantiatesWithDescription_true() {
    Word myWord = new Word ("garden");
    assertEquals("garden", myWord.getName());
  }

  @Test
  public void getName_returnsName_true() {
    Word myWord = new Word("garden");
    assertEquals("garden", myWord.getName());
  }


  @Test
  public void all_returnsAllInstancesOfWord_true() {
    Word firstWord = new Word("garden");
    Word secondWord = new Word("flower");
    assertTrue(Word.getAll().contains(firstWord));
    assertTrue(Word.getAll().contains(secondWord));
  }

  @Test
  public void newId_wordsInstantiateWithAnID_true() {
    Word myWord = new Word("garden");
    assertEquals(Word.getAll().size(), myWord.getId());
  }

  @Test
  public void find_returnsWordWithSameId_secondWord() {
    Word firstWord = new Word("garden");
    Word secondWord = new Word("flower");
    assertEquals(Word.find(secondWord.getId()), secondWord);
  }

  @Test
  public void find_returnsNullWhenNoWordFound_null() {
    assertTrue(Word.find(999) == null);
  }

  @Test
  public void clear_emptiesAllWordsFromArrayList() {
    Word myWord = new Word("garden");
    Word.clear();
    assertEquals(Word.getAll().size(), 0);
  }
  @Rule
  public ClearRule clearRule = new ClearRule();


}
