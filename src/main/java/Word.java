import java.util.ArrayList;


public class Word {
  private static ArrayList<Word> instances = new ArrayList<Word>();
//these are the properties (buckets) where the class stores data
  private String mName;
  private int mId;
  private ArrayList<Definition> mDefinitions;

  //This is the constructor.  It creates a new Word instance.
  public Word (String name) {
    mName = name;  //Filling the properties with parameter value
    instances.add(this); //adding itself to a list.  When we are working inside an object we use keyword 'this' to reference that object
    mId = instances.size(); //instances is the list.  Size is how many items are in the list.  Example: If there are 5 instances it will have any ID of 5
    mDefinitions = new ArrayList<Definition>();                        //Using the count as the ID at the time of creation.  You add it and then say "how big is it?"
  }
      //Method section
      //Method that gets the string from the properties.
      public String getName() {
        return mName; // do not put = because just returning a variable that is
                        //already filled by the constructor.  This is why we
                        //have a constructor.  We have created our getDescription
                        //method.
      }

      public int getId() {
        return mId;
      }

      public static ArrayList<Word> getAll() {
        return instances;
      }

      public  ArrayList<Definition> getDefinitions() {
        return mDefinitions;
      }

      public void addDefinition(Definition d) {
        mDefinitions.add(d);
      }

      public static Word find(int id) {          //the try-catch work flow.  Why do we use this?
        try{                                     //a way to catch user input mistakes and avoid error screen
          return instances.get(id -1);           //We tell java to 'try' to run a block of code and then to
        } catch (IndexOutOfBoundsException e) {  //catch certain exceptions and run some other code instead
          return null;                           //we use -1 because ArrayLists use the 0 index.  We will get
        }                                        //an IndexOutOfBoundsException and return null (nothing)
      }                                          //When we catch an exception we are creating an Excepion object
                                                //If you wanted to look at them(the mistakes the application in catching) use System.out.println(e.getStackTrace) in place of return null
      public static void clear () {
        instances.clear();
      }

}
