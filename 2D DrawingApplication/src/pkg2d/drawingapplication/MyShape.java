package pkg2d.drawingapplication;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author MauriLitvak
 */
public abstract class MyShape 
{
    //initialization of private variables for abstract class MyShape
    private int x1, x2, y1, y2;
    private Color color1, color2;
    private boolean gradient;
    private boolean dash;
    private float outWidth;
    private float numDashes;
    
    //Constructor
    public MyShape()
    {
        x1=0;
        y1=0;
        x2=0;
        y2=0;
        color1=Color.BLACK;
        color2 = Color.BLACK;
        gradient = false;
        outWidth = 10;
        dash = false;
        numDashes = 10;
    }
    
    
    public MyShape(int x1, int y1, int x2, int y2, Color color1, Color color2, boolean gradient, float outlineWidth, boolean dash, float numDashes)
    {
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
        this.color1=color1;
        this.color2=color2;
        this.gradient = gradient;
        outWidth = outlineWidth;
        this.dash = dash;
        this.numDashes = numDashes;
    }
    //Mutators
    
    //Coordinate Mutator Methods
    
    //Setter Methods
    public void setX1(int x)
    {
        this.x1 = x;
    }

    public void setY1(int y)
    {
        this.y1 = y;
    }    
    
    public void setX2(int x)
    {
        this.x2 = x;
    }
      
    public void setY2(int y)
    {
        this.y2 = y;
    }
    // (end of coordinate setters)
    
    //Getter Methods (Coordinates)
    public int getX1()
    {
        return x1;
    }

    public int getY1()
    {
        return y1;
    }
        
    public int getX2()
    {
        return x2;
    }
        
    public int getY2()
    {
        return y2;
    }
    // (end of coordinate getters)
    // (end of coordinate mutators)
        
    //Color Mutator Methods
    
    //Setter Method (color)
    public void setColor1(Color color)
    {
        this.color1 = color;
    }
    
    public Color getColor1()
    {
        return color1;
    }
    //
    
    //Setter Method (color)
    public void setColor2(Color color)
    {
        this.color2 = color;
    }
    
    public Color getColor2()
    {
        return color2;
    }
    
    
    public boolean getGradient()
    {
        return gradient;
    }
    //
    
    public void setWidth(int width)
    {
        outWidth = width;
    }
    
    public float getOutWidth()
    {
        return outWidth;
    }
    
    public void setDash(boolean dash)
    {
        this.dash = dash;
    }

    public boolean getDash()
    {
        return dash;
    }
       
    public void setNumDashes(float dashes)
    {
        numDashes = dashes;
        
    }    
    
    public float getNumDashes()
    {
        return numDashes;
    }
    // (end of color mutators)
    
    // (end of mutators)
    
    //Abstract methodology for drawing shapes
    abstract public void draw(Graphics2D g);
    
}//end of abstract class MyShape
