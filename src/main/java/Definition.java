import java.util.ArrayList;

public class Definition {

  private static ArrayList<Definition> instances = new ArrayList<Definition>();

  private String mDescription;
  private int mId;

  public Definition(String description) {
    mDescription = description;
    mId = instances.size();
    instances.add(this);
  }

  public String getDescription() {
      return mDescription;
    }


    public int getId() {
      return mId;
    }


    public static ArrayList<Definition> getAll() {
      return instances;
    }

    public static Definition find(int id) {
      try {
        return instances.get(id - 1);
      } catch (IndexOutOfBoundsException e) {
        return null;
      }
    }

    public static void clear() {
      instances.clear();
    }


}
