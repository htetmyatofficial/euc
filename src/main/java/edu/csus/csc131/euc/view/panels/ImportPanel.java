/* PACKAGE PATH */
package edu.csus.csc131.euc.view.panels;

/* Library Imports */
import javax.swing.*;
import java.awt.*;

/* Local Imports */
import edu.csus.csc131.euc.view.renders.*;
import edu.csus.csc131.euc.libraries.filedrop.*;

public class ImportPanel extends Panel {
    /* CONSTANTS */
    // Size Per Element
    private static final Dimension IMPORT_FIELD_DIMENSIONS = new Dimension(700, 50);
    private static final Dimension BROWSE_BUTTON_DIMENSIONS = new Dimension(165, 50);
    private static final Dimension IMPORT_BUTTON_DIMENSIONS = new Dimension(410,75);
    private final Dimension ADD_NEW_FILE_BUTTON_DIMENSIONS  = new Dimension(165, 50);
    private static final Dimension DRAG_FILE_PANEL_DIMENSIONS = new Dimension(700, 332);

    // Padding
    private final Insets IMPORT_FIELD_PADDING  = new Insets(0,0,0,0);
    private final Insets BROWSE_BUTTON_PADDING  = new Insets(0,0,0,0);
    private final Insets IMPORT_BUTTON_PADDING  = new Insets(10,0,0,0);
    private final Insets ADD_NEW_FILE_BUTTON_PADDING  = new Insets(10,0,0,0);
    private final Insets DRAGBOX_PADDING = new Insets(0,0,0,0);

    /* Instance Variables */
    private JButton importbutton, browsebutton, addnewfile;
    private JTextField importfield;
    private DragBoxDraw dragbox;

    // Constructor
    public ImportPanel() {
        /* Intializers for this Panel */
        setBackgroundColor(Color.WHITE);

        initializePanel();
        initializeComponents();
        intializeConstraints();
    }

    // Setters
    public void setImportField(String t) { this.importfield = new JTextField(t); }

    // Getters
    public JButton getImportButton() { return this.importbutton; }
    public JButton getBrowseButton() { return this.browsebutton; }
    public JButton getAddNewFileButton() { return this.addnewfile; }
    public JTextField getImportField() { return this.importfield; }


    // Intialize All Components
    public void initializeComponents(){
        /* Instantiate all components of panel here */
        // import field
        this.importfield = new JTextField("Enter file path here, or browse. . .");
        this.importfield.setFont(new Font("Poppins", Font.PLAIN, 15)); 

        // browse button
        this.browsebutton = new JButton(new ImageIcon("src\\main\\assets\\importjsonres\\browsebutton.png"));

        // Drag Box
        this.dragbox = new DragBoxDraw(DRAG_FILE_PANEL_DIMENSIONS);

        // add new file button
        this.addnewfile = new JButton(new ImageIcon("src\\main\\assets\\importjsonres\\addnewfile.png"));

        // import button
        this.importbutton = new JButton(new ImageIcon("src\\main\\assets\\importjsonres\\submitfile.png"));
    }

    // Initializes Constraints for GridBag Layout
    public void intializeConstraints(){
        // importfield constraints
        setTextFieldConstraints(this.importfield, 0, 0, IMPORT_FIELD_DIMENSIONS, IMPORT_FIELD_PADDING);

        // browse button constraints
        setButtonConstraints(this.browsebutton, 1, 0, BROWSE_BUTTON_DIMENSIONS, BROWSE_BUTTON_PADDING);
        /*BROWSE BUTTON ATTRIBUTES */
        // Removes Focus Border
        browsebutton.setFocusPainted(false);

        // Set opacity of button
        browsebutton.setOpaque(true);
        browsebutton.setContentAreaFilled(false);
        browsebutton.setBorderPainted(false);

        // Rollover and Select Icon
        browsebutton.setRolloverIcon(new ImageIcon("src\\main\\assets\\importjsonres\\browsebuttonroll.png"));
        browsebutton.setSelectedIcon(new ImageIcon("src\\main\\assets\\importjsonres\\browseclicked.png"));

        // dragbox constraints
        setDragBoxConstraints(dragbox, 0, 1, DRAGBOX_PADDING);

        // importbutton Constraints
        setButtonConstraints(this.importbutton, 0, 2, IMPORT_BUTTON_DIMENSIONS, IMPORT_BUTTON_PADDING);

        // without dragbox
        // setButtonConstraints(this.importbutton, 0, 1, IMPORT_BUTTON_DIMENSIONS, IMPORT_BUTTON_PADDING);

        /*IMPORT BUTTON ATTRIBUTES */
        // Removes Focus Border
        importbutton.setFocusPainted(false);

        // Set opacity of button
        importbutton.setOpaque(true);
        importbutton.setContentAreaFilled(false);
        importbutton.setBorderPainted(false);

        // Rollover and Select Icon
        importbutton.setRolloverIcon(new ImageIcon("src\\main\\assets\\importjsonres\\submitfileroll.png"));
        importbutton.setSelectedIcon(new ImageIcon("src\\main\\assets\\importjsonres\\resubmitfile.png"));

        // add new file button constraints
        setButtonConstraints(this.addnewfile, 1, 2, ADD_NEW_FILE_BUTTON_DIMENSIONS, ADD_NEW_FILE_BUTTON_PADDING);
        // Removes Focus Border
        addnewfile.setFocusPainted(false);

        // Set opacity of button
        addnewfile.setOpaque(true);
        addnewfile.setContentAreaFilled(false);
        addnewfile.setBorderPainted(false);

        // Rollover and Select Icon will be the same
        addnewfile.setRolloverIcon(new ImageIcon("src\\main\\assets\\importjsonres\\addnewfileroll.png"));

    new FileDrop(dragbox, importfield , new FileDrop.Listener()
    {   public void filesDropped( java.io.File[] files )
        {
            try
            {
                importfield.setText(files[0].getCanonicalPath());
            }   // end try
            catch( java.io.IOException e ) {}
        }   // end filesDropped
    });
}
}
