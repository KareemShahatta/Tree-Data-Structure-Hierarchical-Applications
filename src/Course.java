public class Course
{
    private String name;
    private int id;
    private boolean isCategory;

    public Course(String name, int id, boolean isCategory)
    {
        this.id= id;
        this.name = name;
        this.isCategory = isCategory;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isCategory(){return isCategory;}
}
