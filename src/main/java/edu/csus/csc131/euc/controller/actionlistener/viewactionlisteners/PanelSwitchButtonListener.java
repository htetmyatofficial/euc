/* PACKAGE PATH */
package edu.csus.csc131.euc.controller.actionlistener.viewactionlisteners;

/* Library Imports */
// AWT Imports
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
// Swing Imports
import javax.swing.JButton;

/* Local Imports */
// Local MVC Imports
import edu.csus.csc131.euc.view.View;

public class PanelSwitchButtonListener implements ActionListener {

    // Instance Variables
    private View view;

    // Button Instances
    private JButton ib;
    private JButton mb;
    private JButton vcb;
    private String panelname;

    public PanelSwitchButtonListener(View v, String pn) {
        this.view = v;
        this.panelname = pn;

        // Get References to All MP Buttons
        this.ib = v.getMainPanel().getImportJsonButton();
        this.mb = v.getMainPanel().getManualInputButton();
        this.vcb = v.getMainPanel().getViewCalcButton();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout panels = (CardLayout) view.getPanels().getLayout();

        // Do this if Current Button is NOT Selected
        if (!((AbstractButton) e.getSource()).isSelected()) {
            // Select
            ((AbstractButton) e.getSource()).setSelected(true);

            // Deselect All Other Buttons
            if(e.getSource() == ib){
                mb.setSelected(false);
                vcb.setSelected(false);
            }else if(e.getSource() == mb){
                ib.setSelected(false);
                vcb.setSelected(false);
            }else if(e.getSource() == vcb){
                ib.setSelected(false);
                mb.setSelected(false);
            }
            // Display Panel
            panels.show(view.getPanels(), this.panelname );
        }
        // If Current Button is Already Selected
        else if(((AbstractButton) e.getSource()).isSelected()) {
            // Deselect
            ((AbstractButton) e.getSource()).setSelected(false);
            
            // Display Blank Screen
            // panels.show(view.getPanels(), "Blank" );

            // By default View and Calculate is then Selected 
            vcb.setSelected(true); 

            // Display Default Screen 
            panels.show(view.getPanels(), "View & Calculate Panel");
        }
    }

}