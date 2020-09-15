/* PACKAGE PATH */
package edu.csus.csc131.euc.controller;

/* Library Imports */
// AWT Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Swing Imports
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// IO Imports
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.awt.event.FocusEvent;
import javax.swing.table.*;
// import javax.swing.table.TableCellRenderer;
// import javax.swing.table.TableModel;
// import javax.swing.table.DefaultTableCellRenderer;
// import javax.swing.table.DefaultCellEditor;
import javax.swing.text.TableView;

import edu.csus.csc131.euc.libraries.jdatepicker.java.org.jdatepicker.JDatePanel;
import edu.csus.csc131.euc.libraries.jdatepicker.java.org.jdatepicker.JDatePicker;
import edu.csus.csc131.euc.libraries.jdatepicker.java.org.jdatepicker.UtilDateModel;

/*
// Util Imports
import java.util.Date;
import java.util.Iterator;
import java.text.DateFormat;

// Text Imports
import java.text.SimpleDateFormat;
import java.text.DateFormat;
*/

/* Local Imports */
// Local MVC Imports
import edu.csus.csc131.euc.view.View;
import edu.csus.csc131.euc.model.Model;
import edu.csus.csc131.euc.controller.actionlistener.viewactionlisteners.*;
import edu.csus.csc131.euc.controller.tablerecords.Record;
import edu.csus.csc131.euc.controller.actionlistener.modelactionlisteners.*;
import edu.csus.csc131.euc.model.data.Day;
import edu.csus.csc131.euc.view.panels.ViewCalculatePanel;
import edu.csus.csc131.euc.model.data.Profile;
import edu.csus.csc131.euc.model.data.Rates;


public class Controller {
    // Instance Variables for Controller
    private View view;
    private final Model model;

    // current Day Index
    private int dayIndex;
    private boolean isSummer = true;
    private boolean isUserEditedPeriods = false;
    private boolean isUserEditedRates = false;
    private ImageRenderer EditCellEditor = new ImageRenderer(new JTextField(), "src\\main\\assets\\manualinputres\\editfield.png");
    private ClientsTableButtonRenderer EditCellRenderer = new ClientsTableButtonRenderer("src\\main\\assets\\manualinputres\\editfield.png");
    private ImageRenderer DeleteCellEditor = new ImageRenderer(new JTextField(), "src\\main\\assets\\manualinputres\\deletebutton.png");
    private ClientsTableButtonRenderer DeleteCellRenderer = new ClientsTableButtonRenderer("src\\main\\assets\\manualinputres\\deletebutton.png");

    //array to keep track of duplicate entries


    public Controller(final Model m, final View v) {
        this.model = m;
        this.view = v;
        dayIndex = 0;

        // Initializers for the Controller
        initializeView();
        initializeActionListeners();
        updateComponentsViewCalculate();
    }

    // Initializes all action listeners
    public void initializeActionListeners() {

        /* Action Listeners for Main Panel */

        // Set AL for Panels
        view.getMainPanel().getImportJsonButton()
                .addActionListener(new PanelSwitchButtonListener(view, "Import JSON Panel"));
        view.getMainPanel().getManualInputButton()
                .addActionListener(new PanelSwitchButtonListener(view, "Manual Input Panel"));
        view.getMainPanel().getViewCalcButton()
                .addActionListener(new PanelSwitchButtonListener(view, "View & Calculate Panel"));

        // Action Listener for Add Entry button
        view.getManualInputPanel().getAddEntryButton().addActionListener(new AddEntryListener());
        // Edit.getButton().addActionListener(new EditRowListener());
        // Delete.getButton().addActionListener(new DeleteRowListener());

        // Focus Listener for UsageTextField and date
        view.getManualInputPanel().getEnterUsageField().addFocusListener(new Focus());
        view.getManualInputPanel().getEnterDateField().addFocusListener(new Focus());
        // Set AL for Buttons in Import JSON Panel
        // For Button Views
        view.getImportPanel().getAddNewFileButton().addActionListener(new IJPanelButtonViewListener(view));
        view.getImportPanel().getBrowseButton().addActionListener(new IJPanelButtonViewListener(view));
        view.getImportPanel().getImportButton().addActionListener(new IJPanelButtonViewListener(view));
        view.getImportPanel().getImportButton().addActionListener(new IJPanelIBActionListener(view, model, this));

        // Set AL for View Calculate Panel
        view.getViewCalculatePanel().getNavLeftButton().addActionListener(new ArrowNavigation());
        view.getViewCalculatePanel().getNavRightButton().addActionListener(new ArrowNavigation());
        view.getViewCalculatePanel().getSeasonToggleButton().addActionListener(new SummerToggle());
        view.getViewCalculatePanel().getSubmitUserValuesButton().addActionListener(new InputRates());
        // view.getViewCalculatePanel().getSubmitUserValuesButton().addActionListener(new InputPeriod());
        view.getViewCalculatePanel().getResetDefaultButton().addActionListener(new ResetDefault());

        // Set AL for Manual Input Panel
        view.getManualInputPanel().getEnterDateField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                final char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                   (c == KeyEvent.VK_BACK_SPACE) ||
                   (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))
                {
                  JOptionPane.showMessageDialog(null, "Please Enter Valid");
                  e.consume();
                }
            }

            @Override
            public void keyPressed(final KeyEvent e) {
                // Do Nothing

            }

            @Override
            public void keyReleased(final KeyEvent e) {
                // Do Nothing

            }
        });

        // Set AL for key input
        PeriodInputVerification verifier = new PeriodInputVerification();
        view.getViewCalculatePanel().getSummerOffPeakPeriod().setInputVerifier(verifier);
        view.getViewCalculatePanel().getSummerMidPeakPeriod().setInputVerifier(verifier);
        view.getViewCalculatePanel().getSummerPeakPeriod().setInputVerifier(verifier);

        // Set Doc Listener for Search Input MI
        view.getManualInputPanel().getTextTableFilter().getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = view.getManualInputPanel().getTextTableFilter().getText();

                if (text.trim().length() == 0) {
                    view.getManualInputPanel().getRowSorter().setRowFilter(null);
                } else {
                    view.getManualInputPanel().getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = view.getManualInputPanel().getTextTableFilter().getText();

                if (text.trim().length() == 0) {
                    view.getManualInputPanel().getRowSorter().setRowFilter(null);
                } else {
                    view.getManualInputPanel().getRowSorter().setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

    }

    public void createAndShowGUI() {
        // Display Window
        this.view.getFrame().pack();
        this.view.getFrame().setVisible(true);

        // RUN DEBUG PRINT OUTS FOR COMPONENTS HERE

        // DEBUG FOR PANEL SIZES
        System.out.println("Dim Frame: " + view.getFrame().getSize() );
        System.out.println("Dim Main Panel: " + view.getMainPanel().getPanel().getSize() );
        System.out.println("Dim View and Calculate Panel: " + view.getViewCalculatePanel().getPanel().getSize() );
        System.out.println("Dim Import Panel: " + view.getImportPanel().getPanel().getSize() );
        System.out.println("Dim Manual Input Panel: " + view.getManualInputPanel().getPanel().getSize() );

    }

    public void initializeView() {
        // Initialize the view
        final View v = new View();
        this.view = v;
    }

    public void updateComponentsViewCalculate(){
        final ViewCalculatePanel panel = view.getViewCalculatePanel();
        final Profile profile = model.getModelProfile();

        //updates the rate values
        if(isSummer){
            panel.displaySummerSeasonTitle();
            panel.getSummerMidPeakRate().setText(Float.toString(Rates.getMidPeakSummer()));
            panel.getSummerOffPeakRate().setText(Float.toString(Rates.getOffPeakSummer()));
            panel.getSummerPeakRate().setText(Float.toString(Rates.getPeakSummer()));

            if(isUserEditedPeriods){
                System.out.println(model.getModelProfile().getSummerRates().getUserSummerDefinedPeriods()[0]);
                panel.getSummerOffPeakPeriod().setText(model.getModelProfile().getSummerRates().getUserSummerDefinedPeriods()[0]);
                panel.getSummerMidPeakPeriod().setText(model.getModelProfile().getSummerRates().getUserSummerDefinedPeriods()[1]);
                panel.getSummerPeakPeriod().setText(model.getModelProfile().getSummerRates().getUserSummerDefinedPeriods()[2]);
            }else{
                panel.getSummerOffPeakPeriod().setText("00:00 to 12:00");
                panel.getSummerMidPeakPeriod().setText("12:00 to 17:00 20:00 to 00:00");
                panel.getSummerPeakPeriod().setText("17:00 to 20:00");
            }

            panel.displaySummerSeasonTitle();
        }
        else{
            panel.displayNonSummerSeasonTitle();
            panel.getSummerMidPeakRate().setText("N/A");
            panel.getSummerOffPeakRate().setText(Float.toString(Rates.getOffPeakNonSummer()));
            panel.getSummerPeakRate().setText(Float.toString(Rates.getPeakNonSummer()));
            System.out.println(panel.getSummerOffPeakRate().getText());

            if(isUserEditedPeriods){
                panel.getSummerOffPeakPeriod().setText(model.getModelProfile().getNonSummerRates().getUserNonSummerDefinedPeriods()[0]);
                panel.getSummerMidPeakPeriod().setText(model.getModelProfile().getNonSummerRates().getUserNonSummerDefinedPeriods()[1]);
                panel.getSummerPeakPeriod().setText(model.getModelProfile().getNonSummerRates().getUserNonSummerDefinedPeriods()[2]);
            }else{
                panel.getSummerOffPeakPeriod().setText("00:00 to 17:00 20:00 to 00:00");
                panel.getSummerMidPeakPeriod().setText("N/A");
                panel.getSummerPeakPeriod().setText("17:00 to 20:00");
            }

            panel.displayNonSummerSeasonTitle();
        }

        try{
            //updates the total values by day
            panel.getUsageCostTotalCost().setText(panel.getDollarSign() + formatDecimals(profile.getTotalCostByDay(dayIndex), 2));
            panel.getUsageCostTotalUsage().setText( "<html>" + formatDecimals(profile.getTotalUsageByDay(dayIndex), 4) + " <font size=3>kWH</font></html>");

            // Set up season detail dates
            panel.getSeasonDetailDate().setText(profile.getDays().get(dayIndex).getDate());

            // If it is summer, display summer icon and summer ; Else display, non-summer icon and non-summer
            System.out.println("Boolean for this day: " + profile.getDays().get(dayIndex).isSummer());
            if(profile.getDays().get(dayIndex).isSummer()){
                panel.getSeasonDetailTitle().setText("Summer");
                panel.displaySummerIcon();
            }
            else {
                panel.getSeasonDetailTitle().setText("Non-Summer");
                panel.displayNonSummerIcon();
            }

        }
        catch(Exception e){

        }
        if(model.getModelProfile().getDays().size() != 0){
            //updates time range totals
            if(profile.getDays().get(dayIndex).isSummer()){
                // Update Usage - Summer
                panel.getUsageCostNonPeakUsage().setText("<html>" + formatDecimals(profile.getUsageOffPeakSummer(dayIndex), 4) + " <font size=4>kWH</font></html>");
                panel.getUsageCostMidPeakUsage().setText("<html>" + formatDecimals(profile.getUsageMidPeakSummer(dayIndex), 4) + " <font size=4>kWH</font></html>");
                panel.getUsageCostPeakUsage().setText("<html>" + formatDecimals(profile.getUsagePeakSummer(dayIndex), 4) + " <font size=4>kWH</font></html>");

                // Update Cost - Summer
                panel.getUsageCostNonPeakCost().setText("$ " + formatDecimals(profile.getCostOffPeakSummer(dayIndex), 2));
                panel.getUsageCostMidPeakCost().setText("$ " + formatDecimals(profile.getCostMidPeakSummer(dayIndex), 2));
                panel.getUsageCostPeakCost().setText("$ " + formatDecimals(profile.getCostPeakSummer(dayIndex), 2));
            }
            else{
                // Update Usage - NonSummer
                panel.getUsageCostNonPeakUsage().setText("<html>" + formatDecimals(profile.getUsageOffPeakNonSummer(dayIndex), 4) + " <font size=4>kWH</font></html>");
                panel.getUsageCostMidPeakUsage().setText("<html>0.000 <font size=4>kWH</font></html>");
                panel.getUsageCostPeakUsage().setText("<html>" + formatDecimals(profile.getUsagePeakNonSummer(dayIndex), 4) + " <font size=4>kWH</font></html>");

                // Update Cost - NonSummer
                panel.getUsageCostNonPeakCost().setText("$ " + formatDecimals(profile.getCostOffPeakNonSummer(dayIndex), 2));
                panel.getUsageCostMidPeakCost().setText("$ 0.00");
                panel.getUsageCostPeakCost().setText("$ " + formatDecimals(profile.getCostPeakNonSummer(dayIndex), 2));
            }

        }
        //updates the total values
        panel.getTotalCost().setText(panel.getDollarSign() + formatDecimals(profile.calculateKWH(), 2));
        panel.getTotalUsage().setText("<html>" + formatDecimals(profile.getTotalUsage(), 2) + " <font size=5>kWH</font></html>" );
        model.getModelProfile().dumpValues();
    }

    public String formatDecimals(final float value, int decimal){
        final String s = String.format("%."+decimal+"f", value);
        return s;
    }

    public ImageRenderer getEdit(){
        return EditCellEditor;
    }

    public ImageRenderer getDelete(){
        return DeleteCellEditor;
    }

    // Listner to verify input
    public class PeriodInputVerification extends InputVerifier {
        @Override
        public boolean verify(JComponent input){

            // String pattern = "(([0-1]?[0-9]|2[0-3]):[0-5][0-9] to ([0-1]?[0-9]|2[0-3]):[0-5][0-9]\\s*){1,}";
            String pattern = "([0-2][0-9]:[0-5][0-9] to [0-2][0-9]:[0-5][0-9]\\s*){1,}";
            String text = ((JTextField) input).getText();

            if(text == "N/A" && !isSummer){
                input.setBackground(new Color(149,195,163));
                return true;
            }

            boolean isMatch = Pattern.matches(pattern, text);

            if (isMatch){
                input.setBackground(new Color(149,195,163));
                return true;
            }

            input.setBackground(new Color(195,149,149));

            // Show Input Field Message to Fix Error
            JTextField in = (JTextField) input;
            in.setText(JOptionPane.showInputDialog(input, "Please correct: " + text + "\nFormat is (HH:MM to HH:MM ...)"));

            return false;

        }
    }

    // Additional Action Listeners needs to be put into appropriate folders
    //Function to set Jbutton Icon to the Table
    public class ImageRenderer extends DefaultCellEditor {
        protected JButton button;

        public ImageRenderer(JTextField text, String file) {
          super(text);
          this.button = new JButton(new ImageIcon(file));
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
          button.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
          button.setBorderPainted(false);
          return button;
        }

         //setter
         public void setButton(JButton b) { this.button = b; }

         //getter
         public JButton getButton() { return this.button; }

      }

      class ClientsTableButtonRenderer extends JButton implements TableCellRenderer
        {
            private String file;
            public ClientsTableButtonRenderer(String file)
            {
                setOpaque(true);
                this.file = file;
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                setIcon(new ImageIcon(file));
                setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                setBorderPainted(false);
                setText((value == null) ? "" : value.toString());
                return this;
            }

            //getter
            public JButton getButton() { return this; }
        }

    class AddEntryListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            String date = view.getManualInputPanel().getDatePicker().getTextField().getText();

            int month = view.getManualInputPanel().getDatePicker().getMonth();

            System.out.println(date);

            int index =  view.getManualInputPanel().getEnterPeriodField().getSelectedIndex();
            int rows = view.getManualInputPanel().getModel().getRowCount();

            // Set the summer/non-summer boolean
            Object[] row = new Object[5];
            if(!date.equals("")){
                try{
                    float usage = Float.parseFloat(view.getManualInputPanel().getEnterUsageField().getText());

                    //Add's a row to View and Calculate Panel
                    row[0] = view.getManualInputPanel().getDatePicker().getTextField().getText();
                    row[1] = view.getManualInputPanel().getEnterPeriodField().getSelectedItem().toString();
                    row[2] = String.format("%.4f", Float.parseFloat(view.getManualInputPanel().getEnterUsageField().getText()));
                    //Add Edit button
                    view.getManualInputPanel().getTable().getColumn("Edit").setCellEditor(EditCellEditor);
                    view.getManualInputPanel().getTable().getColumn("Edit").setCellRenderer(EditCellRenderer);
                    EditCellEditor.getButton().addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int row = view.getManualInputPanel().getTable().getSelectedRow();
                            view.getManualInputPanel().getDatePicker().getTextField().setText(view.getManualInputPanel().getModel().getValueAt(row, 0).toString());
                            view.getManualInputPanel().getEnterPeriodField().setSelectedItem(view.getManualInputPanel().getModel().getValueAt(row, 1).toString());
                            view.getManualInputPanel().getEnterUsageField().setText(view.getManualInputPanel().getModel().getValueAt(row, 2).toString());
                        }
                    });

                    //Add Delete Button
                    view.getManualInputPanel().getTable().getColumn("Delete").setCellEditor(DeleteCellEditor);
                    view.getManualInputPanel().getTable().getColumn("Delete").setCellRenderer(DeleteCellRenderer);
                    DeleteCellEditor.getButton().addActionListener(new DeleteRowListener());

                    //always uses summer rates for now
                    Day day = new Day(date, true);

                    boolean duplicateExists = (Record.findDuplicateDay(date) != -1);


                    //if there isn't a duplicate day
                    if(!duplicateExists){
                        //if between start of June and before October
                        System.out.println(month);
                        if(month >= 6 && month < 10){
                            day.setSummer(true);
                        }else{
                            day.setSummer(false);
                        }
                        System.out.println("summer:" + day.isSummer());
                        day.setUsage(usage, index);

                        model.getModelProfile().addDay(day);
                        Record.addRecord(new Record(date, index));
                        view.getManualInputPanel().getModel().addRow(row);

                    }
                    //if there is a duplicate day
                    else{
                        //no duplicate record
                        if(Record.findDuplicate(date, index) == -1){

                            model.getModelProfile().getDay(date).setUsage(usage, index);
                            Record.addRecord(new Record(date, index));
                            view.getManualInputPanel().getModel().addRow(row);
                        }
                        else{
                            int result = JOptionPane.showConfirmDialog(
                                view.getFrame(), "Duplicate record found! Do you want to replace?", "Duplicate",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                            if(result == JOptionPane.YES_OPTION){

                                int rowNo = Record.findDuplicate(date, index);

                                //remove duplicate row
                                //model.getModelProfile().deleteRecord(date, index);
                                Record.deleteRecord(rowNo);
                                view.getManualInputPanel().getModel().removeRow(rowNo);

                                //sync day data
                                model.getModelProfile().getDay(date).setUsage(usage, index);
                                Record.addRecord(new Record(date, index));
                                view.getManualInputPanel().getModel().addRow(row);
                            }

                        }

                    }
                    view.getManualInputPanel().getEnterDateField().setText("mm/dd/yyyy");
                    view.getManualInputPanel().getEnterPeriodField().setSelectedIndex(0);
                    view.getManualInputPanel().getEnterUsageField().setText("Enter Usage");



                    updateComponentsViewCalculate();
                }
                // catch(final NullPointerException ex){
                //     System.out.println("Usage cannot be empty");
                //     JOptionPane.showMessageDialog(view.getFrame(), "Usage cannot be empty");
                // }
                catch(final NumberFormatException ex){
                    System.out.println("Usage has to be float!");
                    JOptionPane.showMessageDialog(view.getFrame(), "Usage has to be a number.");
                }
            }
            else{
                JOptionPane.showMessageDialog(view.getFrame(), "Choose a Date.");
            }

        }
    }

    class Focus implements FocusListener {

        @Override
        public void focusGained(final FocusEvent e) {
           final JTextField j = (JTextField) e.getSource();
           j.selectAll();

        }

        @Override
        public void focusLost(final FocusEvent e) {
            //nothing

        }

    }

    class ArrowNavigation implements ActionListener{
        @Override
        public void actionPerformed(final ActionEvent e) {
            final JButton b = (JButton) e.getSource();
            try {
                model.getModelProfile().getDays().get(dayIndex-1);
                if(b.equals(view.getViewCalculatePanel().getNavLeftButton())){
                    dayIndex--;
                    updateComponentsViewCalculate();
                }
            }
            catch (final Exception ex){
            }
            try{
                model.getModelProfile().getDays().get(dayIndex+1);
                if(b.equals(view.getViewCalculatePanel().getNavRightButton())) {
                    dayIndex++;
                    updateComponentsViewCalculate();
                }
            }
            catch (Exception ex){
            }
            System.out.println("Navigation button pressed!");
        }
    }

    class SummerToggle implements ActionListener{

        @Override
        public void actionPerformed(final ActionEvent e) {
           isSummer = !isSummer;
           updateComponentsViewCalculate();

        }

    }

    class InputRates implements ActionListener{

        @Override
        public void actionPerformed(final ActionEvent e) {

            try{
                if(isSummer){
                    model.getModelProfile().getSummerRates().setPeakSummer(Float.parseFloat(view.getViewCalculatePanel().getSummerPeakRate().getText()));
                    model.getModelProfile().getSummerRates().setMidPeakSummer(Float.parseFloat(view.getViewCalculatePanel().getSummerMidPeakRate().getText()));
                    model.getModelProfile().getSummerRates().setOffPeakSummer(Float.parseFloat(view.getViewCalculatePanel().getSummerOffPeakRate().getText()));
                }
                else{
                    model.getModelProfile().getNonSummerRates().setPeakNonSummer(Float.parseFloat(view.getViewCalculatePanel().getSummerPeakRate().getText()));
                    model.getModelProfile().getNonSummerRates().setOffPeakNonSummer(Float.parseFloat(view.getViewCalculatePanel().getSummerOffPeakRate().getText()));
                }

                model.getModelProfile().resetToNewRates();
                // updateComponentsViewCalculate();
            }
            catch(final Exception ex){
                JOptionPane.showMessageDialog(view.getFrame(), "Rate values have to be a number.");
            }

            // Regex pattern for ' HH:MM to HH:MM , ' for one or more occurences
            // String pattern = "(([0-1]?[0-9]|2[0-3]):[0-5][0-9] to ([0-1]?[0-9]|2[0-3]):[0-5][0-9]\\s*){1,}";

            // // Grab values for periods
            String offpeak = view.getViewCalculatePanel().getSummerOffPeakPeriod().getText();
            String midpeak = view.getViewCalculatePanel().getSummerMidPeakPeriod().getText();
            String peak = view.getViewCalculatePanel().getSummerPeakPeriod().getText();

            // boolean isOffPeakMatch = Pattern.matches(pattern, offpeak);
            // boolean isMidPeakMatch = Pattern.matches(pattern, midpeak);
            // boolean isPeakMatch = Pattern.matches(pattern, peak);

            // System.out.println(offpeak);
            // System.out.println(midpeak);
            // System.out.println(peak);

            if(isSummer){
                // if(offpeak != "00:00 to 12:00" && isOffPeakMatch)
                if(!offpeak.contains("00:00 to 12:00")){
                    JOptionPane.showMessageDialog(view.getFrame(), "New Off Peak Period: " + offpeak);

                    // setPeriod( String Period, isSummer, isOffPeak, isMidPeak, isPeak )
                    model.getModelProfile().getSummerRates().setUserPeriod(offpeak, isSummer, true, false, false);
                    isUserEditedPeriods = true;
                }

                // if(midpeak != "12:00 to 17:00 20:00 to 00:00" && isMidPeakMatch)
                if(!midpeak.equals("12:00 to 17:00 20:00 to 00:00")){
                    JOptionPane.showMessageDialog(view.getFrame(), "New Mid Peak Period: " + midpeak);
                    view.getViewCalculatePanel().getSummerMidPeakPeriod().setText("12:00 to 17:00 20:00 to 00:00");
                    model.getModelProfile().getSummerRates().setUserPeriod(midpeak, isSummer, false, true, false);
                    isUserEditedPeriods = true;
                }

                // if(peak != "17:00 to 20:00" && isPeakMatch)
                if(!peak.equals("17:00 to 20:00")){
                    JOptionPane.showMessageDialog(view.getFrame(), "New Peak Period: " + peak);
                    model.getModelProfile().getSummerRates().setUserPeriod(peak, isSummer, false, false, true);
                    isUserEditedPeriods = true;
                }

            }
            else{

                view.getViewCalculatePanel().getSummerMidPeakPeriod().setText("N/A");
                view.getViewCalculatePanel().getSummerMidPeakRate().setText("N/A");

                // if(offpeak != "00:00 to 17:00 20:00 to 00:00")
                if(!offpeak.equals("00:00 to 17:00 20:00 to 00:00")){
                    JOptionPane.showMessageDialog(view.getFrame(), "New Off Peak Period: " + offpeak);
                    view.getViewCalculatePanel().getSummerOffPeakPeriod().setText("00:00 to 17:00 20:00 to 00:00");
                    model.getModelProfile().getNonSummerRates().setUserPeriod(offpeak, isSummer, true, false, false);
                    isUserEditedPeriods = true;
                }

                // if(peak != "17:00 20:00" && isPeakMatch)
                if(!peak.equals("17:00 to 20:00")){
                    JOptionPane.showMessageDialog(view.getFrame(), "New Peak Period: " + peak);
                    model.getModelProfile().getNonSummerRates().setUserPeriod(peak, isSummer, false, false, true);
                    isUserEditedPeriods = true;
                }
            }


            model.getModelProfile().getNonSummerRates().setUserRates(false);
            model.getModelProfile().getSummerRates().setUserRates(true);

            // Set new user rates
            // if(isSummer){
            //     model.getModelProfile().getSummerRates().setUserRates(isSummer);
            // }
            // else{
            //     model.getModelProfile().getNonSummerRates().setUserRates(isSummer);
            // }

            updateComponentsViewCalculate();

        }
    }




    class ResetDefault implements ActionListener{

        @Override
        public void actionPerformed(final ActionEvent e) {
            model.getModelProfile().resetDefault();
            updateComponentsViewCalculate();
        }
    }

    class DeleteRowListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            int row = view.getManualInputPanel().getTable().getSelectedRow();
            if (!(row == -1)){

                String date = view.getManualInputPanel().getModel().getValueAt(row, 0).toString();
                String period = view.getManualInputPanel().getModel().getValueAt(row, 1).toString();


                view.getManualInputPanel().getModel().removeRow(row);

                Record.deleteRecord(row);

                view.getManualInputPanel().getEnterPeriodField().setSelectedItem(period);
                int index = view.getManualInputPanel().getEnterPeriodField().getSelectedIndex();

                model.getModelProfile().getDay(date).setUsage(0, index);
                System.out.println(Arrays.toString(model.getModelProfile().getDay(date).getUsage().getUsage()));
                dayIndex = 0;
                // if(model.getModelProfile().getDays().size() == dayIndex-1) {
                //     dayIndex--;
                // }
                model.getModelProfile().removeEmptyDays();
                updateComponentsViewCalculate();
            }

        }
    }

}