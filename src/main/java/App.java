import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      ArrayList<Word> words = new ArrayList<Word>();
      if (words == null) {
        words = new ArrayList<Word>();
        //request.session().attribute("word", word);
      }

      model.put("words", Word.all());
      model.put("template", "templates/words.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/words", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/addWord-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      // ArrayList<ToDoCategory> categories = request.session().attribute("categories");
      // if (categories == null) {
      //   categories  = new ArrayList<ToDoCategory>();
      //   request.session().attribute("categories", categories);
      // }
      String input = request.queryParams("name");
      Word newWord = new Word(input);
      model.put("words", Word.all());

      //categories.add(newToDoCategory);
      //System.out.println(input);
      //model.put("newToDoCategory", newToDoCategory);

      //model.put("categories", categories);
      //model.put("name",input);
      model.put("template", "templates/words.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/definitions/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.params(":id")));
      ArrayList<Definition> definitions = word.getDefinition();
      model.put("word", word);
      //model.put("definitions", definitions);
      model.put("template", "templates/add-definitions-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/words/:id/definitions", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Word word = Word.find(Integer.parseInt(request.params(":id")));

      //ArrayList<Definition> definitions = new ArrayList<Definition>();
      ArrayList<Definition> definitions = word.getDefinition();
      // if (definitions == null) {
      //   definitions = new ArrayList<Definition>();
      //   //request.session().attribute("word", word);
      // }



      //Word word = Word.find(Integer.parseInt(request.params(":id")));
      //ArrayList<Definition> definitions = word.getDefinition();
      model.put("word", word);
      model.put("definitions", definitions);
      model.put("template", "templates/definitions.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/definitions", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      Word inputWord = Word.find(Integer.parseInt(request.queryParams("wordId")));
      ArrayList<Definition> definitions = inputWord.getDefinition();
      //inputWord.getDefinitions();

      if (definitions == null) {
        //System.out.println(inputWord);
        definitions = new ArrayList<Definition>();
      }

      String definition = request.queryParams("definitionName");
      Definition newDefinition = new Definition(definition);

      definitions.add(newDefinition);
      //System.out.println(definitions.size());

      model.put("definitions", definitions);
      model.put("word", inputWord);
      model.put("template", "templates/definitions.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }

}
