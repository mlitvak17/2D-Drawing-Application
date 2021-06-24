package pkg2d.drawingapplication;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author MauriLitvak
 */
public abstract class MyBoundedShape extends MyShape
{
    private boolean fill;
    
    //public constructor with no parameters and calls this when no parameters are given...sets fill to false
    public MyBoundedShape()
    {
        super();
        fill = false;
    }
    
    //public overload constructor for passing through the values to the given parameters
    public MyBoundedShape(int x1, int y1, int x2, int y2, Color color1, Color color2, boolean gradient, boolean fill, float outlineWidth, boolean dash, float numDashes)
    {
        super(x1, x2, y1, y2, color1, color2, gradient, outlineWidth, dash, numDashes);
        this.fill=fill;
    }
    
    //Mutators
    public void setFill(boolean fill)
    {
        this.fill = fill;
    }
    
    //Upper left x value
    public int getUpperLeftX()
    {
        return Math.min(getX1(), getX2());
    }
    
    //Upper Left y value
    public int getUpperLeftY()
    {
        return Math.min(getY1(), getY2());
    }
    
    //Get Width of shape
    public int getWidth()
    {
        return Math.abs(getX1()-getX2());
    }
    
    //get height
    public int getHeight()
    {
        return Math.abs(getY1()-getY2());
    }
    
    public boolean getFill()
    {
        return fill;
    }

    //overriding the abstract method for draw
    @Override
    abstract public void draw(Graphics2D g);
}
