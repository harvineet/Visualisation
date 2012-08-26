import javax.xml.soap.Node;

import prefuse.data.Graph;
import prefuse.data.Table;


public class Cal_triad {
	
	public int same_triad=0;
	public float same_triad_ratio=0;
	
	public float triad (Graph g) {
		int num_triad=0;
		Table r2 = g.getNodeTable();
		for (int i=0; i< g.getEdgeCount();i++) {
			int source_node= g.getSourceNode(i);
			int target_node= g.getTargetNode(i);
			for (int j=0; j< g.getEdgeCount();j++) {
				if(g.getSourceNode(j)==target_node && g.getEdge(g.getNode(g.getTargetNode(j)), g.getNode(source_node)) !=null) {
						int s1 =  (Integer) r2.get(source_node,"Value");
						int s2 =  (Integer) r2.get(target_node,"Value");
						int s3 =  (Integer) r2.get(g.getTargetNode(j),"Value");
						if(s1==(s2) && s2==(s3)) {
							same_triad++;
						}
						num_triad++;
				}
			}
		}
		//System.out.println("number of triads =" + num_triad);
		//System.out.println(same_triad+","+ num_triad);
		int nc2= g.getNodeCount()*(g.getNodeCount()-1)/2;
		same_triad_ratio= (float)(same_triad)/(nc2);
		//System.out.println("clustering coefficient =" + (float)(num_triad)/(nc2));
		return((float)(num_triad)/(nc2));
	}

}
