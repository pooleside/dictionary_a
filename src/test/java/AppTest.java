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
      assertThat(pageSource()).contains("Task");
      //pageSource means html
  }

  @Test
  public void taskIsCreatedTest(){
    goTo("http://localhost:4567/");//going to the home page-indicated by one slash / after server name
    fill("#description").with("Call home");  //fill the input that has the ID of "description" with "Call home" on home.vtl page
    submit(".btn"); //on home.vtl page find type= "submit" with class btn.  The dot signifies "look for a class" Example line...<button type="submit" class="btn">GO!</button>
    assertThat(pageSource()).contains("Your task");
    //assertThat means "look for", pageSource is html
  }

  @Test
  public void taskIsDisplayedTest() {
  goTo("http://localhost:4567/");
  fill("#description").with("Call home");
  submit(".btn");
  click("a", withText("Go Back"));
  assertThat(pageSource()).contains("Call home");
  }

  @Test
  public void multipleTasksAreDisplayedTest(){
  goTo("http://localhost:4567/");
  fill("#description").with("Call home");
  submit(".btn");
  click("a", withText("Go Back"));  //Use 'click 'when you want to click a link.  "a" is referring to the html anchor tag.
  fill("#description").with("Buy groceries");
  submit(".btn");
  click("a", withText("Go Back"));
  assertThat(pageSource()).contains("Call home");
  assertThat(pageSource()).contains("Buy groceries");
  }

}
