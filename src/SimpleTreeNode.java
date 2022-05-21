import java.util.*;

public class SimpleTreeNode<T>{
    private T data = null;  // name or other data about node
    private List<SimpleTreeNode> children = new ArrayList<>();
    private SimpleTreeNode parent = null;

    public SimpleTreeNode(T data) {
        this.data = data;
    }

    public void addChild(SimpleTreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        SimpleTreeNode<T> newChild = new SimpleTreeNode<>(data);
        this.addChild(newChild);
    }

    public void addChildren(List<SimpleTreeNode> children) {
        for(SimpleTreeNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<SimpleTreeNode> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(SimpleTreeNode parent) {
        this.parent = parent;
    }

    public SimpleTreeNode getParent() {
        return parent;
    }

    public  String getParents(SimpleTreeNode node)
    {
        Deque stack = new LinkedList<SimpleTreeNode>();
        String path="";
        SimpleTreeNode parentNode = node.getParent();
        while (parentNode != null)
        {
            stack.push(parentNode);
            parentNode = parentNode.getParent();
        }
        Iterator<SimpleTreeNode> itr = stack.iterator();
        while (itr.hasNext()) {
            path = path + ((SimpleTreeNode) itr.next()).data + ":";
        }
        return path;
    }

    public int childCount()
    {
        return (int)getChildren().stream().count();
    }

    //  Recursive methods to print multi-level tree
    public void printChildren(SimpleTreeNode node)
    {
        if (node.parent != null)
        {
            Course course = (Course) node.getData();

            if(course.isCategory())
            {
                System.out.println("------{ " + course.getName() + " }------");
            }
            else
            {
                System.out.println("Course Name: " + course.getName() + " Course ID: " + course.getId());
            }

            if (node.children.size() != 0)
            {
                for(Object child : node.getChildren())
                {
                    SimpleTreeNode subCourse = (SimpleTreeNode) child;
                    if (subCourse.children.size() == 0)
                    {
                        Course courseChild = (Course) subCourse.getData();
                        System.out.println("Course Name: " + courseChild.getName() + " Course ID: " + courseChild.getId());
                    } else {
                        printChildren(subCourse);
                    }
                }
            }
        }
        //       System.out.println(node.data);
    }

    public static void main(String[] args)
    {
        SimpleTreeNode<Course> root = new SimpleTreeNode<Course>(new Course("Course", 000 , true));

        SimpleTreeNode<Course> computerScience = new SimpleTreeNode<>(new Course("ComputerScience", 000 , true));
        computerScience.addChild(new Course("CS:100", 100 ,false));
        computerScience.addChild(new Course("CS:101", 101 ,false));
        SimpleTreeNode<Course> computerScienceAdvanced = new SimpleTreeNode<>(new Course("CS:200", 200 ,false));
        computerScienceAdvanced.addChild(new Course("CS:201", 201 ,false));
        computerScienceAdvanced.addChild(new Course("CS:202", 202 ,false));
        computerScience.addChild(computerScienceAdvanced);


        SimpleTreeNode<Course> english = new SimpleTreeNode<>(new Course("English", 000 , true));
        SimpleTreeNode<Course> englishAdvanced = new SimpleTreeNode<>(new Course("ENG:100", 100 ,false));
        englishAdvanced.addChild(new Course("ENG:201", 201 ,false));
        englishAdvanced.addChild(new Course("ENG:202", 202 ,false));
        english.addChild(englishAdvanced);


        SimpleTreeNode<Course> mathematics = new SimpleTreeNode<>(new Course("Mathematics", 000 , true));
        SimpleTreeNode<Course> simpleMathematics = new SimpleTreeNode<>(new Course("MAT:050", 50 ,false));
        simpleMathematics.addChild(new Course("MAT:065", 65 ,false));
        SimpleTreeNode<Course> advancedMathematics = new SimpleTreeNode<>(new Course("MAT:100", 100 ,false));
        advancedMathematics.addChild(new Course("MAT:120", 120 ,false));
        SimpleTreeNode<Course> advancedMathematicsMore = new SimpleTreeNode<>(new Course("MAT:161", 161 ,false));
        advancedMathematicsMore.addChild(new Course("MAT:162", 162 ,false));
        advancedMathematics.addChild(advancedMathematicsMore);
        mathematics.addChild(simpleMathematics);
        mathematics.addChild( advancedMathematics);


        root.addChild(computerScience);
        root.addChild(english);
        root.addChild(mathematics);

        for (SimpleTreeNode child: root.getChildren())
        {
            root.printChildren(child);
        }
    }
}
