import java.io.FileNotFoundException;
import java.lang.*;

import java.io.*;
import java.util.Scanner;
import javax.swing.JFrame;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.activity.Activity;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.layout.CircleLayout;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Edge;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.render.ShapeRenderer;
import prefuse.util.FontLib;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.data.io.TableReader;
public class Play_node 
{
	
	public int[][] make_table(Graph g)
	{
		Table r2 = g.getNodeTable();
		int[][] nodeinfo = new int[g.getNodeCount()][6];
		for(int i=0;i<g.getNodeCount();i++)
		{
			for(int j=0;j<6;j++)
			{
				nodeinfo[i][j]=0;
				
			}
			nodeinfo[i][4]=g.getInDegree(i);
			nodeinfo[i][5]=g.getOutDegree(i);
		}
		for(int i=0;i<g.getEdgeCount() ;i++ )
		{
			int src = g.getSourceNode(i);
			int trgt = g.getTargetNode(i);//System.out.println(r2.get(src,"Value"));
			int s1 =  (Integer) r2.get(src,"Value");
			int s2 =  (Integer) r2.get(trgt,"Value");
			if(s1==(s2)) 
				{
					nodeinfo[src][2]++;
				}
			else 
				{
					nodeinfo[src][3]++;
				}
			if(s2==(0))
			{
				nodeinfo[src][0]++;
				
			}
			if(s2==(1))
			{
				nodeinfo[src][1]++;
				
			}	
		}
		//for(int i=0;i<g.getNodeCount();i++)
		//{
			//for(int j=0;j<10;j++)
			//{
			//	System.out.print(nodeinfo[i][j] + " , ");
				
		//}
			//System.out.println();
		//}
		return nodeinfo;
	}
	public void max_lcr(Graph g)
	{
		Table r2 = g.getNodeTable();
		int h_nl=0;
		int ndeg_out=0;
		int h_nc=0;
		int same=0;
		int diff=0;
		int deg_out=0;
		int nh_nl=0;
		int deg_in=0;
		int nh_nc=0;
		int nsame=0;
		int ndiff=0;
		int ndeg_in=0;
		//int[][] nodeinfo = new int[g.getNodeCount()][6];
		int[][] nodeinfo = make_table(g);
		for(int i=0; i<g.getNodeCount();i++)
		{
			if(nh_nl<nodeinfo[i][0])
			{				
				h_nl=i;
				nh_nl=nodeinfo[i][0];	
			}			
			if(nh_nc<nodeinfo[i][1])
			{
				h_nc=i;
				nh_nc=nodeinfo[i][1];			
			}		
			if(nsame<nodeinfo[i][2])
			{
				same=i;
				nsame=nodeinfo[i][2];
			}
			if(ndiff<nodeinfo[i][3])
			{
				diff=i;
				ndiff=nodeinfo[i][3];
			}
			if(ndeg_in<nodeinfo[i][4])
			{
				deg_in=i;
				ndeg_in=nodeinfo[i][4];
			}
			if(ndeg_out<nodeinfo[i][5])
			{
				deg_out=i;
				ndeg_out=nodeinfo[i][5];
			}
		}
		String s1 =   r2.getString(h_nl,0);
		String s2 =   r2.getString(deg_out,0);
		String s3 =   r2.getString(h_nc,0);
		String s4 =   r2.getString(same,0);
		String s5 =   r2.getString(diff,0);
		String s6 =   r2.getString(deg_in,0);
		
		
		System.out.println("Max Edges in 0 = "+s1+" with number = "+ nh_nl);
		System.out.println("Max Out_Deg = "+s2+" with number = "+ ndeg_out);
		System.out.println("Max Edges in 1 = "+s3+" with number = "+ nh_nc);
		System.out.println("Max Edges in same = "+s4+" with number "+ nsame);
		System.out.println("Max Edges in diff = "+ s5 + " with number "+ ndiff);
		System.out.println("Max In_Degree = "+s6+" with number "+ ndeg_in);
		
	
	}
	public void min_lcr(Graph g)
	{
		Table r2 = g.getNodeTable();
		int h_nl=0;
		int ndeg_out=0;
		int h_nc=0;
		int same=0;
		int diff=0;
		int deg_out=0;
		int nh_nl=0;
		int deg_in=0;
		int nh_nc=0;
		int nsame=0;
		int ndiff=0;
		int ndeg_in=0;
		//int[][] nodeinfo = new int[g.getNodeCount()][6];
		int[][] nodeinfo = make_table(g);
		for(int i=0; i<g.getNodeCount();i++)
		{
			if(nh_nl>nodeinfo[i][0])
			{				
				h_nl=i;
				nh_nl=nodeinfo[i][0];	
			}			
			if(nh_nc>nodeinfo[i][1])
			{
				h_nc=i;
				nh_nc=nodeinfo[i][1];			
			}		
			if(nsame>nodeinfo[i][2])
			{
				same=i;
				nsame=nodeinfo[i][2];
			}
			if(ndiff>nodeinfo[i][3])
			{
				diff=i;
				ndiff=nodeinfo[i][3];
			}
			if(ndeg_in>nodeinfo[i][4])
			{
				deg_in=i;
				ndeg_in=nodeinfo[i][4];
			}
			if(ndeg_out>nodeinfo[i][5])
			{
				deg_out=i;
				ndeg_out=nodeinfo[i][5];
			}
		}
		String s1 =   r2.getString(h_nl,0);
		String s2 =   r2.getString(deg_out,0);
		String s3 =   r2.getString(h_nc,0);
		String s4 =   r2.getString(same,0);
		String s5 =   r2.getString(diff,0);
		String s6 =   r2.getString(deg_in,0);
		
		
		System.out.println("Min Edges in 0 = "+s1+" with number = "+ nh_nl);
		System.out.println("Min Out_Deg = "+s2+" with number = "+ ndeg_out);
		System.out.println("Min Edges in 1 = "+s3+" with number = "+ nh_nc);
		System.out.println("Min Edges in same = "+s4+" with number "+ nsame);
		System.out.println("Min Edges in diff = "+ s5 + " with number "+ ndiff);
		System.out.println("Min In_Degree = "+s6+" with number "+ ndeg_in);
	}
}
