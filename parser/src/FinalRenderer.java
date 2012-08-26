import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import prefuse.render.AbstractShapeRenderer;
import prefuse.visual.VisualItem;

public class FinalRenderer extends AbstractShapeRenderer
{
	//protected RectangularShape m_box = new Rectangle2D.Double();
	protected Ellipse2D m_box = new Ellipse2D.Double();
	
	@Override
	protected Shape getRawShape(VisualItem item) 
	{	
		int x=0;
		if(item.get("Value").equals(0)) {x=10;}
		if(item.get("Value").equals(1)) {x=15;}
		//if(item.get("Stand").equals("n")) {x=10;}
		
		m_box.setFrame(item.getX(), item.getY(),x,x);

		return m_box;
	}
}