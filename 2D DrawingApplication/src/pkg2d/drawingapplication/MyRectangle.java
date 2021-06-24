package pkg2d.drawingapplication;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

/**
 *
 * @author MauriLitvak
 */
public class MyRectangle extends MyBoundedShape
{
    public MyRectangle()
    {
        super();
    }
    
    public MyRectangle(int x1, int x2, int y1, int y2, Color color1, Color color2, boolean gradient, boolean fill, float outlineWidth, boolean dash, float numDashes)
    {
        super(x1, y1, x2, y2, color1, color2, gradient, fill, outlineWidth, dash, numDashes);
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        GradientPaint gPaint = new GradientPaint(getX1(), getY1(), getColor1(),getX2(), getY2(), getColor2());
        g.setColor(getColor1());
        
        if (getDash())
        {
            float[] dashes = {getNumDashes()};
            g.setStroke(new BasicStroke(getOutWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
        }
        
        else
        {
            g.setStroke(new BasicStroke(getOutWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        }
        
        if (getFill() && getGradient())
        {
            g.setPaint(gPaint);
            g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        
        else if (getFill())
        {
            g.fillRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        
        else if (getGradient())
        {
            g.setPaint(gPaint);
            g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        
        else
        {
            g.drawRect(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        g.setStroke(new BasicStroke());
    }//end of @Override Statement
}//end of public class MyRectangle
