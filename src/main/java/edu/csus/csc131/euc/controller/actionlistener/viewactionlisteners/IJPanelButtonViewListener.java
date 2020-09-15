/* PACKAGE PATH */
package edu.csus.csc131.euc.controller.actionlistener.viewactionlisteners;

/* Library Imports */
// AWT Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// IO Imports
import java.io.File;

// Swing Imports
import javax.swing.AbstractButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/* Local Imports */
// Local MVC Imports
import edu.csus.csc131.euc.view.View;

public class IJPanelButtonViewListener implements ActionListener{
    // Instance variable of the view
    private View view;

    // For Browse button Purposes
    private File file;

    public IJPanelButtonViewListener(View v){ this.view = v; }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If Selected
        if(!((AbstractButton)e.getSource()).isSelected())
        {
            if(((AbstractButton)e.getSource()) == view.getImportPanel().getImportButton()){
                if(view.getImportPanel().getImportField().getText().equals("Enter file path here, or browse. . .")){
                    return;
                }
            }
            // Set it to true
            ((AbstractButton)e.getSource()).setSelected(true);
        }

        // If the button being selected is the browse button then find file and display it
        if( ((AbstractButton)e.getSource()) == view.getImportPanel().getBrowseButton() ){
            JFileChooser chooser = new JFileChooser();

            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Locate JSON FILE to import");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setMultiSelectionEnabled(false);
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON File", "json"));

            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
                System.out.println("getSelectedFile(): "+ chooser.getSelectedFile());
                System.out.println("getAbsolutePath(): "+ file.getAbsolutePath());

                // Set new import field text
                view.getImportPanel().getImportField().setText("" + chooser.getSelectedFile());
            } else if (returnVal == JFileChooser.CANCEL_OPTION) {
                System.out.println("Cancel Option ");
            } else {
                System.out.println("No Selection or Error "); // Technically dont need this anymore
                file = null;
                ((AbstractButton)e.getSource()).setSelected(false);
            }
        }

        // If reset button was selected then reset view
        if( ((AbstractButton)e.getSource()) == view.getImportPanel().getAddNewFileButton() ){
            // Set Browse and Submit to not selected
            view.getImportPanel().getBrowseButton().setSelected(false);
            view.getImportPanel().getImportButton().setSelected(false);

            // Reset textfield
            view.getImportPanel().getImportField().setText("Enter file path here, or browse. . .");
        }


    }
}