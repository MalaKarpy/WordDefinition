import java.util.ArrayList;

public class Word {
  private static ArrayList<Word> instances = new ArrayList<Word>();

  private String mName;
  private int mId;
  private ArrayList<Definition> mDefinitionList = new ArrayList<Definition>();

  public Word(String name) {
    mName = name;
    instances.add(this);
    mId = instances.size();
    mDefinitionList = new ArrayList<Definition>();
  }

  public String getName() {
    return mName;
  }

  public int getId() {
    return mId;
  }

  public ArrayList<Definition> getDefinition() {
    return mDefinitionList;
  }

  public void addDefinition(Definition definition) {
    mDefinitionList.add(definition);
  }

  public static ArrayList<Word> all() {
    return instances;
  }

  public static void clear() {
    instances.clear();
  }

  public static Word find(int id) {
    try {
      return instances.get(id - 1);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

}
