import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    //this method puts the home page out in the layout.vtl template
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/home.vtl");//We are putting the home.vtl template into the root "/" route
      model.put("tasks", request.session().attribute("tasks"));  //When you open home, if there are any tasks in the session it will display them
      return new ModelAndView(model, layout);  //returning to Spark
    }, new VelocityTemplateEngine());

    //We are now making a second page that displays results of home page(root page /)
    post("/tasks", (request,response) -> {  //"tasks" is our route and new page
    HashMap<String, Object> model = new HashMap<String, Object>();//created a HashMap called model
    ArrayList<Task> tasks = request.session().attribute("tasks"); //Pulling the item "tasks" out of session storage and putting them in an ArrayList of multiple task objects we've called "tasks"

    if (tasks == null)  {
      tasks = new ArrayList<Task>(); //If the ArrayList is equal to null, nothing was put into the list.  It will then create a new ArrayList and equal it to "tasks".  Then it will put it in the session.
      request.session().attribute("tasks", tasks);  // See above note for more info. The 'session' stores information. Allows us to create a list without losing information
    }

    String description = request.queryParams("description"); // This brings in what was typed text box with the name "description" from home.vtl or root route
    Task newTask = new Task(description);                   //Example line..<input id="description" name="description" class="form-control" type="text"/>
                                          //When we take in "buy groceries" in the description text box we put it in the String description. Then we need to create a place to store the description.  We create a new instance of the class called "Task". The name our instance is 'nt'

    tasks.add(newTask);              //Now we are storing the tasks each time they are create                                             //anything coming in from a text input on a website is a string.
    model.put("template", "templates/success.vtl");//put in my HashMap a success.vtl template called "template"
    return new ModelAndView(model, layout);
  },new VelocityTemplateEngine());


  }//end of main method
}//end of class
