package pkg2d.drawingapplication;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JColorChooser;

/**
 *
 * @author MauriLitvak
 */
public class CreateJFrame extends JFrame
{
    //Initialization of JFrame components
    private final JLabel shape;
    private final Panel panel;
    private final JPanel UpperJPanel;
    private final JButton undo, clear, firstColorButton, secondColorButton;
    private final JComboBox shapes;
    private final JCheckBox filled;
    private final JCheckBox dashed;
    private final JCheckBox gradient;
    private final JPanel LowerJPanel;
    private final JLabel lineWidth;
    private final JLabel dashLength;
    private final JTextField lineWidthField;
    private final JTextField dashLengthField;
    private final JLabel statusLabel;
    private final JPanel Padder;
    public Color firstColor = Color.BLACK;
    public Color secondColor = Color.BLACK;
    
    //Initializing shape options string array
    private final String shapeOptions[]= {"Line","Rectangle","Oval"};
    
    //Constructor for the JFrame
    public CreateJFrame()
    {
        super("2D Drawing Application");

        //JLabel
        statusLabel = new JLabel("");
        lineWidth = new JLabel("Line width:");
        dashLength = new JLabel("Dash Length:");
        shape = new JLabel("Shape:");
        
        //JButtons
        undo = new JButton("Undo");
        clear = new JButton("Clear");
        firstColorButton = new JButton("First Color");
        secondColorButton = new JButton("Second Color");
        
        ActionListener firstColorChooser = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Color firstSelectedColor = JColorChooser.showDialog(null, "Choose 1st color", panel.getCurrentColor());
                panel.setCurrentShapeColor(firstSelectedColor);
            }
        };
        
        ActionListener secondColorChooser = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                Color secondSelectedColor = JColorChooser.showDialog(null, "Choose 2nd color", panel.getCurrentGradientColor());
                panel.setCurrentShapeGradientColor(secondSelectedColor);
            }
        };
        
        firstColorButton.addActionListener(firstColorChooser);
        secondColorButton.addActionListener(secondColorChooser);
        
        //Adds combo boxs
        shapes = new JComboBox(shapeOptions);
        
        //JCheckBoxes
        filled = new JCheckBox("Filled");
        dashed = new JCheckBox("Dashed");
        gradient = new JCheckBox("Use Gradient");
        
        //JTextField
        lineWidthField = new JTextField("1", 5);
        dashLengthField = new JTextField("10", 5);
        
        //JPanel Tray for the above components
        UpperJPanel = new JPanel();
        UpperJPanel.setLayout(new FlowLayout()); //sets padding between s in gridlayout
        
        LowerJPanel = new JPanel();
        LowerJPanel.setLayout(new FlowLayout());
        
        Padder = new JPanel();
        Padder.setLayout(new GridLayout(2,1)); //sets padding around the edges
        
        // add s to JPanel
        UpperJPanel.add(undo);
        UpperJPanel.add(clear);
        LowerJPanel.add(gradient);
        LowerJPanel.add(firstColorButton);
        LowerJPanel.add(secondColorButton);
        UpperJPanel.add(shape);
        UpperJPanel.add(shapes);                 
        UpperJPanel.add(filled);
        LowerJPanel.add(lineWidth);
        LowerJPanel.add(lineWidthField);
        LowerJPanel.add(dashLength);
        LowerJPanel.add(dashLengthField);
        LowerJPanel.add(dashed);
        
        // add JPanel to Padder
        Padder.add(UpperJPanel);
        Padder.add(LowerJPanel);
        
        //add Padder and panel to JFrame
        add(Padder, BorderLayout.NORTH);
        panel = new Panel(statusLabel);
        
        add(panel, BorderLayout.CENTER);
        
        // create new ButtonHandler for button event handling
        ButtonHandler buttonHandler = new ButtonHandler();
        undo.addActionListener(buttonHandler);
        clear.addActionListener(buttonHandler);
        
        //create handlers for combobox and checkbox
        ItemListenerHandler handler = new ItemListenerHandler();
        shapes.addItemListener(handler);
        filled.addItemListener(handler);
        gradient.addItemListener(handler);
        dashed.addItemListener(handler);
        //handler for text field
        TextFieldHandler thandler = new TextFieldHandler();
        lineWidthField.addActionListener(thandler);
        dashLengthField.addActionListener(thandler);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setVisible(true);
    }

    //TextField Handling 
    private class TextFieldHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String string = "";
            int getUserInput;
            
            if (event.getSource() == lineWidthField)
            {
                string = event.getActionCommand(); 
                
                try
                {
                    getUserInput = Integer.parseInt(string.trim());
                    panel.setOutWidth(getUserInput);
                }
                
                catch (NumberFormatException ex)//variable not used
                {
                    System.out.println("Please enter a valid number.");
                }
            }
            
            if (event.getSource() == dashLengthField)
            {

                string = event.getActionCommand(); 
                
                try
                {
                    getUserInput = Integer.parseInt(string.trim());
                    panel.setNumDashes(getUserInput);
                }
                
                catch (NumberFormatException ex)//variable not used...again just syntax
                {
                    System.out.println("Please enter a valid number.");
                }
            }
        }
    }
    
    private class ButtonHandler implements ActionListener
    {
        //Handling for button events
        // handles button events
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (event.getActionCommand().equals("Undo"))
            {
                panel.clearLastShape();
            }
            
            else if (event.getActionCommand().equals("Clear"))
            {
                panel.clearDrawing();
            }
        }
    }
    
    private class ItemListenerHandler implements ItemListener
    {
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            // process filled checkbox events
            if (event.getSource() == filled)
            {
                boolean checkFill=filled.isSelected();
                panel.setCurrentShapeFilled(checkFill);
            }
            
            if (event.getSource() == gradient)
            {
                boolean checkFill2=gradient.isSelected();
                panel.setNeedGradientFilled(checkFill2);
            }
            
            if (event.getSource() == dashed)
            {
                boolean checkFill3=dashed.isSelected();
                panel.setDash(checkFill3);
            }
            
            // determine whether combo box selected
            if (event.getStateChange() == ItemEvent.SELECTED)
            {
                //if event source is combo box shapes pass in index selected
                if ( event.getSource() == shapes)
                {
                    panel.setCurrentShapeType(shapes.getSelectedIndex());
                }
            }//end of if statement
        }//end of @Override statement
    }//end of implimentation of Listener
}//end of public class CreateJFrame
