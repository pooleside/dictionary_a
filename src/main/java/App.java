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
      model.put("words", request.session().attribute("words"));
      model.put("template", "templates/home.vtl");//We are putting the home.vtl template into the root "/" route
      return new ModelAndView(model, layout);  //returning to Spark
    }, new VelocityTemplateEngine());
//
//
    get("/words", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      model.put("words", Word.getAll());  //When you open home, if there are any tasks in the session it will display them
      model.put("template", "templates/words.vtl");//We are putting the home.vtl template into the root "/" rout
      return new ModelAndView(model, layout);  //returning to Spark
    }, new VelocityTemplateEngine());

    get("words/new", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/word-form.vtl");//We are putting the home.vtl template into the root "/" rout
      return new ModelAndView(model, layout);  //returning to Spark
    }, new VelocityTemplateEngine());

//     //We are now making a second page that displays results of home page(root page /)
    post("/words", (request,response) -> {  //"tasks" is our route and new page
    HashMap<String, Object> model = new HashMap<String, Object>();//created a HashMap called model
    String name = request.queryParams("name"); // This brings in what was typed text box with the name "description" from home.vtl or root route
    Word newWord = new Word(name);                   //Example line..<input id="description" name="description" class="form-control" type="text"/>
                                                        //When we take in "garden" in the description text box we put it in the String description. Then we need to create a place to store the description.  We create a new instance of the class called "Task". The name our instance is 'nt'
                                                    //anything coming in from a text input on a website is a string.
    model.put("template", "templates/success.vtl");//put in my HashMap a success.vtl template called "template"
    return new ModelAndView(model, layout);
  },new VelocityTemplateEngine());

  get("/words/:id", (request,response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  Word word = Word.find(Integer.parseInt(request.params(":id"))); //This will be a string, turning into integer. Then using word-form.find method from instances and save it into a Word object. Putting that Word object into our model
  model.put("word", word);
  model.put("template", "templates/word-definitions-form.vtl");//word-definitions-form
  return new ModelAndView(model, layout);
  },new VelocityTemplateEngine());

  get("/words/:id/definitions/new", (request,response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  Word word = Word.find(Integer.parseInt(request.params(":id"))); //This will be a string, turning into integer. Then using word-form.find method from instances and save it into a Word object. Putting that Word object into our model
  ArrayList<Definition> definitions = word.getDefinitions();
  model.put("word", word);
  model.put("definition", definitions);
  model.put("template", "templates/word.vtl");//words.vtl
  return new ModelAndView(model, layout);
  },new VelocityTemplateEngine());

  get("/definitions", (request, response) -> {
    HashMap<String, Object> model = new HashMap<String, Object>();

    model.put("definitions", Definition.getAll());  //When you open home, if there are any tasks in the session it will display them
    model.put("template", "templates/word.vtl");//We are putting the home.vtl template into the root "/" rout
    return new ModelAndView(model, layout);  //returning to Spark
  }, new VelocityTemplateEngine());

  post("/definitions", (request,response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  Word word = Word.find(Integer.parseInt(request.queryParams("wordId")));
  // Word word = Word.find(Integer.parseInt(request.queryParams("wordId"))); //This will be a string, turning into integer. Then using word-form.find method from instances and save it into a Word object. Putting that Word object into our model
  // ArrayList<Definition> definitions = word.getDefinitions();
  //
  // if(definitions == null) {
  //   definitions = new ArrayList<Definition>();
  //   request.session().attribute("definitions", definitions);
  // }
  //Word word = new Word("Honkey");
  String name = request.queryParams("name");
  Definition newDefinition = new Definition(name);
  word.addDefinition(newDefinition);


  model.put("word", word);
  model.put("def", newDefinition);
  model.put("template", "templates/word.vtl");
  return new ModelAndView(model, layout);
},new VelocityTemplateEngine());


  }//end of main method
}//end of class
