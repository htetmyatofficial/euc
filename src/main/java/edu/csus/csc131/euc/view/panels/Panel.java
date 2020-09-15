/* PACKAGE PATH */
package edu.csus.csc131.euc.view.panels;

/* Library Imports */
import javax.swing.*;

// import org.graalvm.compiler.hotspot.nodes.DimensionsNode;

import java.awt.*;

/* Local Imports */
import edu.csus.csc131.euc.view.renders.*;

public class Panel {
    /* Panel for this class */
    private JPanel panel;

    /* Set Constraints variable for GridBagLayout */
    private GridBagLayout gb = new GridBagLayout();
    private GridBagConstraints c = new GridBagConstraints();

    /* Constant Colors */
    private final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    private Color BACKGROUND_COLOR;

    public Panel() {
        initializePanel();
    }

    /* Functions for Manual Input Panel */
    // Initialize Panel
    public void initializePanel(){
        /* Panel Initializations */
        panel = new JPanel(gb);

        // Set Panel Color
        if(this.BACKGROUND_COLOR == null) {
            this.panel.setBackground(this.DEFAULT_BACKGROUND_COLOR);
        }
        else
            this.panel.setBackground(this.BACKGROUND_COLOR);

    }

    // Setters
    public void setBackgroundColor(Color c) { this.BACKGROUND_COLOR = c; }
    public void setPanelSize(Dimension s){ panel.setSize(s); }

    // Overloaded with RGB
    public void setBackgroundColor(int r, int g, int b) {
        Color c = new Color(r, g, b);
        this.BACKGROUND_COLOR = c;
    }

    // If all elements are using the same padding
    public void setGlobalGridbagInsets(Insets i){ c.insets = i; }

    // Getters
    public JPanel getPanel(){ return this.panel; }
    public GridBagConstraints getGridbagConstraints(){ return this.c; }
    public GridBagLayout getGridBagLayout(){ return this.gb; }

    /* Functions for Panel */
    public void setButtonConstraints(JButton button, int gridx, int gridy, Dimension preferredSize, Insets inset) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();

        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        // Setting the preferred size of the button
        if( preferredSize != null ){ button.setPreferredSize(preferredSize); }

        // Setting the padding
        if( inset != null ){  c.insets = inset; }

        // Adding the button constraints to the main panel
        panel.add(button, c);
    }

    public void setTextFieldConstraints(JTextField text, int gridx, int gridy, Dimension preferredSize, Insets inset) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();

        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        // Setting the preferred size of the button
        if( preferredSize != null ){ text.setPreferredSize(preferredSize); }

        // Setting the padding
        if( inset != null ){  c.insets = inset; }

        // Adding the button constraints to the main panel
        panel.add(text, c);
    }

    public void setScrollPaneConstraints(JScrollPane scrollpane, int gridx, int gridy, Dimension preferredSize, Insets inset) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();

        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        // Setting the preferred size of the button
        if( preferredSize != null ){ scrollpane.setPreferredSize(preferredSize); }

        // Setting the padding
        if( inset != null ){  c.insets = inset; }

        // Adding the button constraints to the main panel
        panel.add(scrollpane, c);
    }


    public void setScrollBarConstraints(JScrollBar scrollbar, int gridx, int gridy, Dimension preferredSize, Insets inset) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();

        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        // Setting the preferred size of the button
        if( preferredSize != null ){ scrollbar.setPreferredSize(preferredSize); }

        // Setting the padding
        if( inset != null ){  c.insets = inset; }

        // Adding the button constraints to the main panel
        panel.add(scrollbar, c);
    }

    public void setLabelConstraints(JLabel label, int gridx, int gridy, Dimension preferredSize, Insets inset) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();

        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        // Setting the preferred size of the button
        if( preferredSize != null ){ label.setPreferredSize(preferredSize); }

        // Setting the padding
        if( inset != null ){  c.insets = inset; }

        // Adding the button constraints to the main panel
        panel.add(label, c);
    }

    public void setConstraints(JComponent comp, int gridx, int gridy, Dimension preferredSize, Insets inset) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();

        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        // Setting the preferred size of the button
        if( preferredSize != null ){ comp.setPreferredSize(preferredSize); }

        // Setting the padding
        if( inset != null ){  c.insets = inset; }

        // Adding the button constraints to the main panel
        panel.add(comp, c);

    }

    //with gridwidth
    public void setViewCalConstraints(JComponent pane, JComponent comp, double weight, int anchor, int gridwidth, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();

        c.weightx = c.weighty = weight;
        c.anchor = anchor; 
        c.gridwidth = gridwidth; 
        c.gridx = gridx; 
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }
        pane.add(comp, c); 
    }

    //without gridwidth and wieghts are together
    public void setVeiewCalConstraints(JComponent pane, JComponent comp, double weight, int anchor, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();

        c.weightx = c.weighty = weight;
        c.anchor = anchor; 
        c.gridx = gridx; 
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }
        pane.add(comp, c); 
    }

    //without gridwidth and wieghts are together and with fill
    public void setVeiewCalConstraints(JComponent pane, JComponent comp, int fill, double weight, int anchor, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();

        c.fill = fill;
        c.weightx = c.weighty = weight;
        c.anchor = anchor; 
        c.gridx = gridx; 
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }
        pane.add(comp, c); 
    }

    public void setVeiewCalConstraints(JComponent pane, JComponent comp, int fill, double weightx, double weighty, int anchor, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();

        c.fill = fill;
        c.weightx = weightx;
        c.weighty = weighty;
        c.anchor = anchor; 
        c.gridx = gridx; 
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }
        pane.add(comp, c); 
    }



    //with gridwidth and weights are seperate
    public void setVeiewCalConstraints(JComponent pane, JComponent comp, double weightx, double weighty, int anchor, int gridwidth, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();

        c.weightx = weightx;
        c.weighty = weighty;
        c.anchor = anchor; 
        c.gridwidth = gridwidth; 
        c.gridx = gridx; 
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }
        pane.add(comp, c); 
    }

    //without gridwidth
    public void setViewCalConstraints(JComponent pane, JComponent comp, double weightx, double weighty, int anchor, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();

        c.weightx = weightx;
        c.weighty = weighty;
        c.anchor = anchor; 
        c.gridx = gridx; 
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }
        pane.add(comp, c); 
    }

    public void setViewCalculateFont(JComponent pane, JComponent comp, int gridx, int gridy, int style, int size, Insets inset) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();
        c.weightx = c.weighty = 1.0;
        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }

        comp.setFont(new Font("Poppins", style, size));
        pane.add(comp, c);
    }

    // For no weight
    public void setViewCalculateFont(JComponent pane, JComponent comp, int gridx, int gridy, int style, int size, Insets inset, Boolean isWeight) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();
        // c.weightx = c.weighty = 1.0;
        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }

        comp.setFont(new Font("Poppins", style, size));
        pane.add(comp, c);
    }

    // Override for alignment 
    public void setViewCalculateFont(JComponent pane, JComponent comp, int gridx, int gridy, int style, int size, Insets inset, String alignment) {
        // Set new GridBagConstraints per element
        c = new GridBagConstraints();
        c.weightx = c.weighty = 1.0;
        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        if(alignment == "east"){
            c.anchor = GridBagConstraints.EAST; 
        }else{
            c.anchor = GridBagConstraints.WEST; 
        }

        if( inset != null ){  c.insets = inset; }

        comp.setFont(new Font("Poppins", style, size));
        pane.add(comp, c);
    }


    public void setDragBoxConstraints(DragBoxDraw dragbox, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();

        // Setting the X-Axis and Y-Axis
        c.gridx = gridx;
        c.gridy = gridy;

        // Setting the padding
        if( inset != null ){  c.insets = inset; }

        // Adding the button constraints to the main panel
        this.getPanel().add(dragbox, c);
    }


    public void createPanel(JComponent pane, JComponent comp, int fill, int anchor, int gridx, int gridy) {
        c = new GridBagConstraints();
        c.fill = fill;
        c.anchor = anchor;
        c.weightx = c.weighty = 1.0;
        c.gridx = gridx; 
        c.gridy = gridy;
        pane.add(comp);
    }

    public void createPanel(JComponent pane, JComponent comp, Dimension d, int fill, int anchor, int gridx, int gridy) {
        c = new GridBagConstraints();
        c.fill = fill;
        c.anchor = anchor;
        c.weightx = c.weighty = 1.0;
        c.gridx = gridx; 
        c.gridy = gridy;
        pane.add(comp);
    }


    public void createPanel(JComponent pane, JComponent comp, int fill, int anchor, int gridx, int gridy, Insets i) {
        c = new GridBagConstraints();
        c.fill = fill;
        c.anchor = anchor;
        c.weightx = c.weighty = 1.0;
        c.gridx = gridx; 
        c.gridy = gridy;
        c.insets = i;
        pane.add(comp);
    }


    public void createPanel(JComponent pane, JComponent comp, Dimension d, int fill, int anchor, int gridx, int gridy, Insets i) {
        comp.setMinimumSize(d);
        comp.setSize(d);
        comp.setPreferredSize(d);
        c = new GridBagConstraints();
        c.fill = fill;
        c.anchor = anchor;
        c.weightx = c.weighty = 1.0;
        c.gridx = gridx; 
        c.gridy = gridy;
        c.insets = i;
        pane.add(comp);
    }

    // with jlabel explicitly 
    public void setPanelContraints(JComponent pane, JLabel comp, int fill, int anchor, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();
        c.fill = fill;
        c.anchor = anchor;
        c.weightx = c.weighty = 1.0;
        c.gridx = gridx; 
        c.gridy = gridy;

         // Setting the padding
         if( inset != null ){  c.insets = inset; }
        pane.add(comp, c);
    }

    public void setPanelContraints(JComponent pane, JComponent comp, int fill, int anchor, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();
        c.fill = fill;
        c.anchor = anchor;
        c.weightx = c.weighty = 1.0;
        c.gridx = gridx; 
        c.gridy = gridy;

         // Setting the padding
         if( inset != null ){  c.insets = inset; }
        pane.add(comp, c);
    }

    // no weight and gridwidth 3 
    public void setPanelContraints(JComponent pane, JComponent comp, int fill, int anchor, int gridx, int gridy, Insets inset, int gridwidth) {
        c = new GridBagConstraints();
        c.fill = fill;
        c.anchor = anchor;
        // c.weightx = c.weighty = 0.5;
        c.gridwidth = 3; 
        c.gridx = gridx; 
        c.gridy = gridy;

         // Setting the padding
         if( inset != null ){  c.insets = inset; }
        pane.add(comp, c);
    }


    // for no weight
    public void setPanelContraints(JComponent pane, JComponent comp, int fill, int anchor, int gridx, int gridy, Insets inset, Boolean isWeight) {
        c = new GridBagConstraints();
        if(fill > 0){
            c.fill = fill;
        }
        c.anchor = anchor;
        // c.weightx = c.weighty = 0;
        c.gridx = gridx; 
        c.gridy = gridy;

         // Setting the padding
         if( inset != null ){  c.insets = inset; }
        pane.add(comp, c);
    }

    // for no weight, + gridwidth
    public void setPanelContraints(JComponent pane, JComponent comp, int fill, int anchor, int gridx, int gridy, Insets inset, Boolean isWeight, int gridwidth) {
        c = new GridBagConstraints();
        if(fill > 0){
            c.fill = fill;
        }
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.anchor = anchor;
        // c.weightx = c.weighty = 0;
        c.gridx = gridx; 
        c.gridy = gridy;

         // Setting the padding
         if( inset != null ){  c.insets = inset; }
        pane.add(comp, c);
    }

    public void setRightArrow(JComponent pane, JComponent comp, int fill, int anchor, double weightx, double weighty, int gridx, int gridy, Insets inset) {
        c = new GridBagConstraints();
        c.fill = fill;
        c.anchor = anchor; 
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx; 
        c.gridy = gridy;

        if( inset != null ){  c.insets = inset; }
        pane.add(comp, c); 
    }

    /* START NEW FUNCTIONS */ 
}
