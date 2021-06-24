package pkg2d.drawingapplication;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;

/**
 *
 * @author MauriLitvak
 */
public class MyOval extends MyBoundedShape
{
    public MyOval()
    {
        super();
    }
    
    public MyOval(int x1, int x2, int y1, int y2, Color color1, Color color2, boolean gradient, boolean fill, int outlineWidth, boolean dash, float numDashes)
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
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        
        else if (getFill())
        {
            g.fillOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        
        else if (getGradient())
        {
            g.setPaint(gPaint);
            g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
        
        else
        {
            g.drawOval(getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight());
        }
    }//end of @Override statement
}//end of public class MyOval
