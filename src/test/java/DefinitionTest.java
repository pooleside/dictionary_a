import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class DefinitionTest {


  @Test
  public void definition_instantiatesCorrectly_true(){
    Definition myDefinition = new Definition("garden tool");
    assertEquals(true, myDefinition instanceof Definition); //checking if an instance of the class will show up, therefore true.
  }

  @Test
  public void definition_instantiatesWithDescription_true() {
    Definition myDefinition = new Definition ("garden tool");
    assertEquals("garden tool", myDefinition.getDescription());
  }
  @Test
  public void isCompleted_isFalseAfterInstantiaon_false() {
    Definition myDefinition = new Definition("garden tool");
    assertEquals(false, myDefinition.isCompleted());
  }


  // @Test
  // public void all_returnsAllInstancesOfWord_true() {
  //   Word firstWord = new Word("garden");
  //   Word secondWord = new Word("flower");
  //   assertTrue(Word.getAll().contains(firstWord));
  //   assertTrue(Word.getAll().contains(secondWord));
  // }

  @Test
  public void newId_DefinitionInstantiateWithAnID_true() {
    Definition myDefinition = new Definition("garden tool");
    assertEquals(Definition.getAll().size(), myDefinition.getId());
  }

  @Test
  public void find_returnsWordWithSameId_secondWord() {
    Word firstWord = new Word("garden");
    Word secondWord = new Word("flower");
    assertEquals(Word.find(secondWord.getId()), secondWord);
  }

  @Test
  public void find_returnsNullWhenNoDefinitionFound_null() {
    assertTrue(Definition.find(999) == null);
  }

  @Test
  public void clear_emptiesAllDefinitionsFromArrayList() {
    Definition myDefinition = new Definition("garden tool");
    Definition.clear();
    assertEquals(Definition.getAll().size(), 0);
  }
  @Rule
  public ClearRule clearRule = new ClearRule();


}
