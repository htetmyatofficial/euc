/* PACKAGE PATH */
package edu.csus.csc131.euc.view;

/* Local Imports */
import edu.csus.csc131.euc.view.panels.*;

/* Library Imports */
import javax.swing.*;
import java.awt.*;

// ----------------------------------------- //
// View Class: Manages UI View of App        //
// ----------------------------------------- //


public class View{
    /* Create Instance of View Frame */
    private JFrame frame;

    // Size of frame
    // private Dimension size = new Dimension(1440,810);
    // Size of frame
    private final Dimension FRAME_SIZE = new Dimension(1440,810);
    private final Dimension CARD_PANELS_SIZE = new Dimension(1013, 786);

    /* Declare each Panel */

    // JPanel to hold all other panels
    private JPanel panels = new JPanel(new CardLayout());

    // Each Panel View
    private MainPanel mainpanel;

    // In Panels
    private ManualInputPanel manualinputpanel;
    private ViewCalculatePanel viewcalculatepanel;
    private ImportPanel importpanel;


    // Construtor
    public View(){
        // This sets the title and new instance of the Frame
        this.frame = new JFrame("Electicity Usage Calculator");

        GridBagLayout layout = new GridBagLayout();
        this.frame.setLayout(layout);

        // Default size for the main frame and sizeable options of frame
        this.frame.setMinimumSize(FRAME_SIZE);
        this.frame.setSize(FRAME_SIZE);
        this.frame.setResizable(false);

        // Set Close operation for the main frame
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Instatiate all panels
        this.mainpanel = new MainPanel();
        this.manualinputpanel = new ManualInputPanel();
        this.viewcalculatepanel = new ViewCalculatePanel();
        this.importpanel = new ImportPanel();

        // Add Necessary Panels to Panels

        // Blank Panel
        // panels.add("Blank", new JPanel());

        panels.add("View & Calculate Panel", this.viewcalculatepanel.getPanel());
        panels.add("Manual Input Panel", this.manualinputpanel.getPanel());
        panels.add("Import JSON Panel", this.importpanel.getPanel());

        panels.setPreferredSize(CARD_PANELS_SIZE);

        // Set the position for each panel on main view
        this.frame.getContentPane().add( this.mainpanel.getPanel() );
        this.frame.getContentPane().add( this.panels );
        // Set the position for each panel on main view
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        this.frame.getContentPane().add( this.mainpanel.getPanel(), c );

        c = new GridBagConstraints();
        c.weightx = c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        this.frame.getContentPane().add( this.panels, c );


    }

    // Setters
    public void setFrame(JFrame f ) { this.frame = f; }

    // Getters
    public JFrame getFrame() { return this.frame; }
    public MainPanel getMainPanel(){ return this.mainpanel; }
    public ViewCalculatePanel getViewCalculatePanel(){ return this.viewcalculatepanel; }
    public ImportPanel getImportPanel(){ return this.importpanel; }
    public ManualInputPanel getManualInputPanel(){ return this.manualinputpanel; }
    public JPanel getPanels(){ return this.panels; }

}