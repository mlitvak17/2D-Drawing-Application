package pkg2d.drawingapplication;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.GradientPaint;

/**
 *
 * @author MauriLitvak
 */
public class MyLine extends MyShape
{
    
    public MyLine()
    {
        super();
    }
    
    public MyLine(int x1, int x2, int y1, int y2, Color color1, Color color2, boolean gradient, float outlineWidth, boolean dash, float numDashes)
    {
        super(x1, x2, y1, y2, color1, color2, gradient, outlineWidth, dash, numDashes);
        
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        GradientPaint gPaint = new GradientPaint(getX1(), getY1(), getColor1(),getX2(), getY2(), getColor2());
        g.setColor(getColor1());
        if (getGradient())
        {
            g.setPaint(gPaint);
        }
        else
        {
            g.setPaint(getColor1());
        }
        if (getDash())
        {
            float[] dashes = {getNumDashes()};
            g.setStroke(new BasicStroke(getOutWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
        }
        else
        {
            g.setStroke(new BasicStroke(getOutWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        }
        g.drawLine(getX1(), getY1(), getX2(), getY2());
        
    }
}
