import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.util.Arrays;
public class JGraphX2 extends JFrame
{

    private static final long serialVersionUID = -2707712944901661771L;
    SimpleTreeNode<Course> root = createSimpleTreeNode();

    public void fillGraphFromModel( mxGraph graph)  {
        //   graphUpdate();

        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();

        try
        {
            Course rootCourse = createSimpleTreeNode().getData();
            Object vRoot = graph.insertVertex(parent, null, rootCourse.getName(), 80, 00, 80, 30);

            CreateGraphPoints(graph, parent, vRoot, createSimpleTreeNode());
            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setUseBoundingBox(false);
            layout.execute(parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
    }

    public void CreateGraphPoints(mxGraph graph, Object parent, Object vRoot, SimpleTreeNode<Course> parentNode)
    {
        for(SimpleTreeNode child : parentNode.getChildren())
        {
            Course course = (Course) child.getData();
            Object meRoot = graph.insertVertex(parent, null, course.getName() , 80, 0, 80, 30);
            graph.insertEdge(parent, null, "", vRoot, meRoot);


            if(child.childCount() > 0)
            {
                // Recursively draw nodes
                CreateGraphPoints(graph, parent, meRoot, child);
            }
        }
    }


    public JGraphX2()
    {
        super("Simple Tree Node");
        mxGraph graph = new mxGraph();
        fillGraphFromModel(graph);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public SimpleTreeNode<Course> createSimpleTreeNode()
    {
        root = new SimpleTreeNode<Course>(new Course("Course", 000, true));


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


        for (SimpleTreeNode child: root.getChildren()) {
            root.printChildren(child);
        }
        return root;
    }


    // Backup not used
    public void graphUpdate() {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try
        {
            //Notice that the parent is the default parent...
            //The hierarchical structure looks great but I cannot collapse/expand the tree.
            Object vDogsRoot = graph.insertVertex(parent, null, "Whale", 80, 0, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "Shar Pei", 0, 0, 80, 30);
            Object v3 = graph.insertVertex(parent, null, "Pug", 0, 0, 80, 30);
            Object v4 = graph.insertVertex(parent, null, "Cocker Spaniel", 0, 0, 80, 30);
            Object v5 = graph.insertVertex(parent, null, "Pit Bull", 0, 0, 80, 30);
            Object v6 = graph.insertVertex(parent, null, "Chihuahua", 0, 0, 80, 30);
            Object v7 = graph.insertVertex(null, null, "GrandPuppy", 0,0,90,30);
            Object v8 = graph.insertVertex(null, null, "GrandPuppy2", 0,0,90,30);
            Object v9 = graph.insertVertex(null, null, "GrandPuppy3", 0,0,90,30);
            graph.insertEdge(parent, null, "", vDogsRoot, v2);
            graph.insertEdge(parent, null, "", vDogsRoot, v3);
            graph.insertEdge(parent, null, "", vDogsRoot, v4);
            graph.insertEdge(parent, null, "", vDogsRoot, v5);
            graph.insertEdge(parent, null, "", vDogsRoot, v6);
            graph.insertEdge(v6, null, "", v6, v7);
            graph.insertEdge(v6, null, "", v6, v8);
            graph.insertEdge(v6, null, "", v6, v9);

            mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
            layout.setUseBoundingBox(false);

            layout.execute(parent);
        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public static void main(String[] args)
    {
        JGraphX2 frame = new JGraphX2();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 420);
        frame.setVisible(true);
    }
}