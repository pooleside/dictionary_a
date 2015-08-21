import java.util.ArrayList;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Dictionary");
      //pageSource means html
  }

  @Test
  public void wordIsCreatedTest(){
    goTo("http://localhost:4567/");//going to the home page-indicated by one slash / after server name
    fill("#description").with("garden");  //fill the input that has the ID of "description" with "Call home" on home.vtl page
    submit(".btn"); //on home.vtl page find type= "submit" with class btn.  The dot signifies "look for a class" Example line...<button type="submit" class="btn">GO!</button>
    assertThat(pageSource()).contains("Your word");
    //assertThat means "look for", pageSource is html
  }

  @Test
  public void wordIsDisplayedTest() {
  goTo("http://localhost:4567/");
  fill("#description").with("garden");
  submit(".btn");
  click("a", withText("Go Back"));
  assertThat(pageSource()).contains("garden");
  }

  @Test
  public void multipleWordsAreDisplayedTest(){
  goTo("http://localhost:4567/");
  fill("#description").with("garden");
  submit(".btn");
  click("a", withText("Go Back"));  //Use 'click 'when you want to click a link.  "a" is referring to the html anchor tag.
  fill("#description").with("Buy flower");
  submit(".btn");
  click("a", withText("Go Back"));
  assertThat(pageSource()).contains("garden");
  assertThat(pageSource()).contains("flower");
  }

}
