/* PACKAGE PATH */ 
package edu.csus.csc131.euc.view.panels;

/* Library Imports */ 
import javax.swing.*; 
import java.awt.*; 

public class MainPanel extends Panel {
    /* CONSTANTS */ 
    // Size per Element 
    private final Dimension PANEL_SIZE = new Dimension(412, 825); 
    private final Dimension BUTTON_SIZE = new Dimension(412, 60); 
    private final Dimension LOGO_BG_SIZE = new Dimension(412, 175);

    // Padding per Element 
    private final Insets BUTTON_PADDING = new Insets(0, 0, 0, 0); 
    private final Insets TITLE_PADDING = new Insets(90, 0, 95, 0);
    private final Insets LOGO_PADDING = new Insets(142, 0, 0, 0);


    /* Elements for the Main Frame */
    private JButton importjsonbutton, manualinputbutton, viewcalcbutton;

    /* Labels */ 
    private JLabel titleapp; 

    /* Logo Image */ 
    JLabel logo = new JLabel(new ImageIcon("src\\main\\assets\\mainpanelres\\Logo.png"));

    // Constructor 
    public MainPanel(){ 
        /* Setting attributes for this Panel */ 
        setBackgroundColor(2, 29, 62);
        setPanelSize(PANEL_SIZE); 

        /* Initializers */ 
        initializePanel(); 
        initializeComponents(); 
        intializeConstraints(); 
    }

    // Getters
    public JButton getImportJsonButton(){ return this.importjsonbutton; }
    public JButton getManualInputButton(){ return this.manualinputbutton; }
    public JButton getViewCalcButton(){ return this.viewcalcbutton; }

    /* Functions for Main Panel */ 

    // Intialize All Components 
    public void initializeComponents(){
        /* Instantiate all components of panel here */ 
        // Instantiate Buttons
        this.importjsonbutton = new JButton(new ImageIcon("src\\main\\assets\\mainpanelres\\ImportButton.png")); 
        this.manualinputbutton = new JButton(new ImageIcon("src\\main\\assets\\mainpanelres\\ManualButton.png")); 
        this.viewcalcbutton = new JButton(new ImageIcon("src\\main\\assets\\mainpanelres\\ViewCalcButton.png")); 

        // Instantiate Labels
        this.titleapp = new JLabel("<html><center>Electricity Usage <br />Calculator</center></html>"); 
    }

    // Initializes Constraints for GridBag Layout 
    public void intializeConstraints(){

        // Import JSON Button constraints 
        setButtonConstraints(this.importjsonbutton, 0, 1, BUTTON_SIZE, BUTTON_PADDING);        


        /* IMPORT JSON BUTTON ATTRIBUTES */ 
        // Removes Focus Border
        importjsonbutton.setFocusPainted(false);

        // Rollover and Select Icon will be the same
        importjsonbutton.setRolloverIcon(new ImageIcon("src\\main\\assets\\mainpanelres\\ImportSelected.png") );
        importjsonbutton.setSelectedIcon(new ImageIcon("src\\main\\assets\\mainpanelres\\ImportCurrSelected.png") ); 
        

        // Set opacity of button 
        importjsonbutton.setOpaque(true);
        importjsonbutton.setContentAreaFilled(false);
        importjsonbutton.setBorderPainted(false);

        // Manual Input Button constraints 
        setButtonConstraints(this.manualinputbutton, 0, 2, BUTTON_SIZE, BUTTON_PADDING);

        /* MANUAL INPUT BUTTON ATTRIBUTES */ 
        // Removes Focus Border
        manualinputbutton.setFocusPainted(false); 

        // Rollover and Select Icon will be the same
        manualinputbutton.setRolloverIcon(new ImageIcon("src\\main\\assets\\mainpanelres\\ManualSelected.png"));
        manualinputbutton.setSelectedIcon(new ImageIcon("src\\main\\assets\\mainpanelres\\ManualCurrSelected.png"));

        // Set opacity of button 
        manualinputbutton.setOpaque(true);
        manualinputbutton.setContentAreaFilled(false);
        manualinputbutton.setBorderPainted(false);

        // View Calculate Button constraints 
        setButtonConstraints(this.viewcalcbutton, 0, 3, BUTTON_SIZE, BUTTON_PADDING);

        /* VIEW CALCULATE BUTTON ATTRIBUTES */ 
        // Removes Focus Border
        viewcalcbutton.setFocusPainted(false); 

        // Rollover and Select Icon will be the same 
        viewcalcbutton.setRolloverIcon(new ImageIcon("src\\main\\assets\\mainpanelres\\ViewCalcSelected.png"));
        viewcalcbutton.setSelectedIcon(new ImageIcon("src\\main\\assets\\mainpanelres\\ViewCalcCurrSelected.png"));

        // Set opacity of button 
        viewcalcbutton.setOpaque(true);
        viewcalcbutton.setContentAreaFilled(false);
        viewcalcbutton.setBorderPainted(false);

        // titleapp Label constraints 
        setLabelConstraints(titleapp, 0, 0, null, TITLE_PADDING);

        /* TITLE APP LABEL ATTRIBUTES */ 
        // Set Font and Size 
        titleapp.setForeground(Color.WHITE); 
        titleapp.setFont(new Font("Poppins", Font.BOLD, 40));

        // Label for Bottom Of Main Panel 
        setLabelConstraints(logo, 0, 4, LOGO_BG_SIZE, LOGO_PADDING);

        /* LOGO LABEL ATTRIBUTES */ 
        // Set Background color for Logo 
        logo.setBackground(new Color(23, 104, 172));
        logo.setOpaque(true);
        

    }


}
