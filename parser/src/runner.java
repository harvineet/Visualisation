import java.io.*;
import prefuse.data.Graph;
import prefuse.data.Table;

public class runner 
{
	public static void main(String[] args) throws Exception 
	{
		int ocount_left=0;
		int ocount_right=0;
		Imp parser = new Imp("polblogs.gml");		
		Graph g1 = parser.read();
		parser.give_vis(g1);
		for(int i=0; i< g1.getEdgeCount();i++)
		{
			Table r2 = g1.getNodeTable();
			int src = g1.getSourceNode(i);
			int trgt = g1.getTargetNode(i);
			int s =  (Integer) r2.get(src,"Value");
			int s2 =  (Integer) r2.get(trgt,"Value");
			if (s==(s2))
			{
				if (s==(0))
				{
					ocount_left++;
				}
				if (s==(1))
				{
					ocount_right++;
				}
			}
		}
		New_Class n1 = new New_Class();
		n1.node_cal(g1);
		float ol_den= (float)(ocount_left)/(n1.ncount_left);
	    float or_den= (float)(ocount_right)/(n1.ncount_right);
	    
	    System.out.println("left density = " + ol_den);
	    System.out.println("right density = " + or_den);
	    
	    float b1 = ocount_right+ ocount_left;
	    float b2= g1.getEdgeCount();
		float oAff_ratio= (float)(b1/b2);
		System.out.println("Aff ratio in original graph is = "+ oAff_ratio);
		/*Imp parser2 =new Imp("polblogs.gml");
		Graph g2 = parser2.read();
		int num_edges = g2.getEdgeCount();
		for(int i=0; i< num_edges;i++)
		{
			g2.removeEdge(i);
			
		}*/
		File file = new File("randomgraph_blogs.csv");
		Writer output = null;
		output = new BufferedWriter(new FileWriter(file));
		output.write("%graph id,same_affiliation_ratio,clustering_coeff_total,clustering_coeff_same\n");
		for(int i=0; i<30; i++)
		{
			New_Class n = new New_Class();
			
			Imp parser2 =new Imp("polblogs.gml");
			Graph g2 = parser2.read();
			int num_edges = g2.getEdgeCount();
			for(int j=0; j< num_edges;j++)
			{
				g2.removeEdge(j);
				
			}
			//System.out.println(g2.getEdgeCount());
			n.create_random(g2, num_edges);
			for(int j=0; j< num_edges;j++)
			{
				g2.removeEdge(j);
				
			}
			//System.out.println(n.count_right);
		    int same_affiliation= n.count_right+ n.count_left;
		    double Aff_ratio;
		    //System.out.println(g2.getEdgeCount()+"@");
		    Aff_ratio = (double)(same_affiliation)/(num_edges);

		    New_Class n2 = new New_Class();
		    Cal_triad c= new Cal_triad();
		    
		    double f=c.triad(n2.create_random(g2, num_edges));
		    output.write(i+","+Aff_ratio+","+f+","+c.same_triad_ratio+"\n");
		}
		output.close();
		
		Play_node pn= new Play_node();
		pn.max_lcr(g1);
		
		New_Class n = new New_Class();
		n.node_cal(g1);
		
		Cal_triad ct= new Cal_triad();
		double org_ct= ct.triad(g1);
		double same_ct = ct.same_triad_ratio;
		System.out.println("clustering coefficient of original dataset =" +org_ct);
		System.out.println("Same clustering coefficient of original dataset =" +same_ct);	
	}
}