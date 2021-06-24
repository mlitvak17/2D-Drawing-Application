package pkg2d.drawingapplication;

import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author MauriLitvak
 */
public class Panel extends JPanel
{
    private List<MyShape> myShapes; //dynamic stack of shapes
    private List<MyShape> clearedShapes; //dynamic stack of cleared shapes from undo
    
    //current Shape variables
    private boolean needGradient;
    private int outlineWidth;
    private boolean dash;
    private float numDashes;
    private int currentShapeType; //0 for line, 1 for rect, 2 for oval
    private MyShape currentShapeObject; //stores the current shape object
    private Color currentShapeColor; //current shape color
    private boolean currentShapeFilled; //determine whether shape is filled or not
    private Color currentShapeGradientColor;
    
    JLabel statusLabel; //status label for mouse coordinates

    public Panel(JLabel statusLabel){
        
        myShapes = new List<MyShape>(); //initialize myShapes dynamic stack
        clearedShapes = new List<MyShape>(); //initialize clearedShapes dynamic stack
        
        //Initialize current Shape variables
        currentShapeType=0;
        currentShapeObject=null;
        currentShapeColor=Color.BLACK;
        currentShapeGradientColor = Color.BLACK;
        currentShapeFilled=false;
        needGradient = false;
        outlineWidth = 1;
        dash = false;
        numDashes = 10;
        
        this.statusLabel = statusLabel; //Initialize statusLabel
        
        setLayout(new BorderLayout()); //sets layout to border layout; default is flow layout
        setBackground( Color.WHITE ); //sets background color of panel to white
        add( statusLabel, BorderLayout.SOUTH );  //adds a statuslabel to the south border
        
        // event handling for mouse and mouse motion events
        MouseHandler handler = new MouseHandler();                                    
        addMouseListener(handler);
        addMouseMotionListener(handler); 
    }
    
    //Calls the draw method for the existing shapes.
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 =  (Graphics2D) g;
        super.paintComponent(g);
        
        // draw the shapes
        ArrayList<MyShape> shapeArray=myShapes.getArray();
        for ( int counter=shapeArray.size()-1; counter>=0; counter-- )
           shapeArray.get(counter).draw(g2);
        
        //draws the current Shape Object if it is not null
        if (currentShapeObject!=null)
            currentShapeObject.draw(g2);
    }
    //Clear the last shape drawn and calls repaint() to redraw the panel if clearedShapes is not empty
    public void clearLastShape()
    {
        if (! myShapes.isEmpty())
        {
            clearedShapes.addFront(myShapes.removeFront());
            repaint();
        }
    }
    //Removes all of the shapes in current drawing.
    public void clearDrawing()
    {
        myShapes.makeEmpty();
        clearedShapes.makeEmpty();
        repaint();
    }
            
    public void setCurrentShapeType(int type)
    {
        currentShapeType=type;
    }
    
    //Sets the currentShapeColor
    public void setCurrentShapeColor(Color color)
    {
        currentShapeColor=color;
    }
    
    public Color getCurrentColor()
    {
        return currentShapeColor;
    }
    
    //Sets the boolean currentShapeFilled to boolean filled passed in.
    public void setCurrentShapeFilled(boolean filled)
    {
        currentShapeFilled=filled;
    }
    
    public void setCurrentShapeGradientColor(Color color)
    {
        currentShapeGradientColor=color;
    }

    //SETS THE BOOLEAN FOR IF GRADIENT NEED APPLY
    public void setNeedGradientFilled(boolean gradient)
    {
        needGradient = gradient;
    }
    
    public Color getCurrentGradientColor()
    {
        return currentShapeGradientColor;
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
    
    public void setOutWidth(int width)
    {
        outlineWidth = width;
    }

    public int getOutWidth()
    {
        return outlineWidth;
    }
    
    //Private inner class that implements MouseAdapter and does event handling for mouse events.
    private class MouseHandler extends MouseAdapter 
    {
        //When mouse is pressed draw a shape object based on type, color and filled.
        public void mousePressed( MouseEvent event )
        {
            switch (currentShapeType) //0 for line, 1 for rect, 2 for oval
            {
                case 0:
                    currentShapeObject= new MyLine(event.getX(), event.getY(), event.getX(), event.getY(), currentShapeColor, currentShapeGradientColor, needGradient, outlineWidth, dash, numDashes);
                    break;
                case 1:
                    currentShapeObject= new MyRectangle(event.getX(), event.getY(), event.getX(), event.getY(), currentShapeColor, currentShapeGradientColor, needGradient, currentShapeFilled, outlineWidth, dash, numDashes);
                    break;
                case 2:
                    currentShapeObject= new MyOval(event.getX(), event.getY(), event.getX(), event.getY(),currentShapeColor, currentShapeGradientColor, needGradient,currentShapeFilled, outlineWidth,dash, numDashes);
                    break;        
            }
        }
        
        //When mouse is released set currentShapeObject's x2 & y2 to mouse pos.
        public void mouseReleased(MouseEvent event)
        {
            //sets currentShapeObject x2 & Y2
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            myShapes.addFront(currentShapeObject); //addFront currentShapeObject onto myShapes
            
            currentShapeObject=null; //sets currentShapeObject to null
            clearedShapes.makeEmpty(); //clears clearedShapes
            repaint();     
        }
        
        //This method gets the mouse pos when it is moving and sets it to statusLabel.
        public void mouseMoved(MouseEvent event)
        {
            statusLabel.setText(String.format("(%d,%d)",event.getX(),event.getY()));
        } // end method mouseMoved
        
        //This method gets the mouse position when it is dragging and sets x2 & y2 of current shape to the mouse pos
        public void mouseDragged(MouseEvent event)
        {
            //sets currentShapeObject x2 & Y2
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            //sets statusLabel to current mouse position
            statusLabel.setText(String.format("(%d,%d)",event.getX(),event.getY()));
            repaint();
        }//end of method
    }//end of private class MouseHandler
}//end of public class DrawingPanel
