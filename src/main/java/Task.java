import java.time.LocalDateTime;
import java.util.ArrayList;


public class Task {
  private static ArrayList<Task> instances = new ArrayList<Task>();
//these are the properties (buckets) where the class stores data
  private String mDescription;
  private LocalDateTime mCreatedAt;
  private boolean mCompleted;
  private int mId;

  //This is the constructor.  It creates a new Rectangle instance.
  public Task (String description) {
    mDescription = description;  //Filling the properties with parameter value
    mCreatedAt = LocalDateTime.now();  //build in method
    mCompleted = false;  //giving it an initial value
    instances.add(this); //adding itself to a list
    mId = instances.size(); //instances is the list.  Size is how many items are in the list.  Example: If there are 5 instances it will have any ID of 5
                            //Using the count as the ID at the time of creation.  You add it and then say "how big is it?"
  }
      //Method section
      //Method that gets the string from the properties.
      public String getDescription() {
        return mDescription; // do not put = because just returning a variable that is
                        //already filled by the constructor.  This is why we
                        //have a constructor.  We have created our getDescription
                        //method.
      }

      public LocalDateTime getCreatedAt() {
        return mCreatedAt;
      }

      public boolean isCompleted() {
        return mCompleted;
      }

      public int getId() {
        return mId;
      }

      public void completeTask () {
        mCompleted = true;
      }

      public static Task find(int id) {
        try{
          return instances.get(id -1);
        } catch (IndexOutOfBoundsException e) {
          return null;
        }
      }

      public static void clear () {
        instances.clear();
      }
}
