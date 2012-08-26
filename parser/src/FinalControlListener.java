
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.ControlAdapter;
import prefuse.controls.Control;
import prefuse.controls.DragControl;
import prefuse.controls.NeighborHighlightControl;
import prefuse.controls.PanControl;
import prefuse.controls.WheelZoomControl;
import prefuse.controls.ZoomControl;
import prefuse.controls.ZoomToFitControl;
import prefuse.data.Graph;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;

public class FinalControlListener extends ControlAdapter implements Control  {

	public void itemClicked(VisualItem item, MouseEvent e) 
	{
		if(item instanceof NodeItem)
		{
			
			//int neutral,left,congress=0;
			String NAME = ((String) item.get("Name"));
			int STAND =  (Integer) item.get("Value");
			int itemno = item.getRow();
			JPopupMenu jpub = new JPopupMenu();
			jpub.add("id no.: " +(itemno+1));
			jpub.add("Name: " + NAME);
			jpub.add("Value: " + STAND);
			int nodedegree_in = ((NodeItem) item).getInDegree()  ;
			int nodedegree_out = ((NodeItem) item).getOutDegree() ;
			jpub.add("In_degree: " + nodedegree_in);
			jpub.add("Out_degree: " + nodedegree_out);
			Graph graph =  ((NodeItem) item).getGraph();
	    	int edgecount = graph.getEdgeCount();
		    //int nodecount = graph.getNodeCount();
	    	Play_node p= new Play_node();
			int[][] tab = p.make_table(graph);
		

			jpub.add("Left: " +tab[itemno][0] );
			//jpub.add("Neutral: " + tab[itemno][edgecount+2] );
			jpub.add("Cons: " + tab[itemno][1]);
			
			jpub.show(e.getComponent(),1,1);
			
			
			
			
			

		}

		
		
		
		
		
		
		
	}

}

