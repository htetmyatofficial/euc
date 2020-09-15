/* PACKAGE PATH */
package edu.csus.csc131.euc.view.panels;

/* Library Imports */
import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import edu.csus.csc131.euc.libraries.jdatepicker.java.org.jdatepicker.JDatePanel;
import edu.csus.csc131.euc.libraries.jdatepicker.java.org.jdatepicker.JDatePicker;
import edu.csus.csc131.euc.libraries.jdatepicker.java.org.jdatepicker.UtilDateModel;

public class ManualInputPanel extends Panel {

    /* Constants */
    private static final Dimension DIMENSION_TEXTFIELD = new Dimension(100, 25);
    private static final Dimension DIMENSION_SCROLL_PANE = new Dimension(200, 200);
    private static final Dimension TABLE_PANEL_SIZE = new Dimension(663, 786);
    private static final Dimension INPUT_PANEL_SIZE = new Dimension(350, 786);
    private static final int HOURS = 24;

    // Padding
    private static final Insets GLOBAL_PADDING = new Insets(10, 10, 10, 10);
    private static final Insets DEFAULT_INSETS = new Insets(0, 0, 0, 0);
    private static final Insets TITLE_INSETS = new Insets(0, 10, 0, 10);
    private UtilDateModel datemodel = new UtilDateModel();
    private JDatePanel datePanel = new JDatePanel(datemodel);
    private JDatePicker datePicker = new JDatePicker(datePanel);

    // Main Panels
    private DefaultTableModel model = new DefaultTableModel(){
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column)
        {
            switch (column) {
                case 3: return true; //Edit Cell needs to be true to allow for action listener
                case 4: return true; //Delete Cell needs to be true to allow for action listener
                default:
                    return false; //This causes all other cells to be not editable
             }
        }
    };

    // Table Elements 
    private JScrollPane scrollpane;
    private JPanel tablepanel = new JPanel(new BorderLayout()); 
    private JPanel filterpanel = new JPanel(new BorderLayout());
    private JTable datatable = new JTable(model);
    // Table Field Components
    private JTableHeader header = datatable.getTableHeader();
    // Row Search and Sort
    private JLabel searchlbl = new JLabel(new ImageIcon("src\\main\\assets\\manualinputres\\search.png")); 
    private TableRowSorter<TableModel> rowsorter = new TableRowSorter<>(datatable.getModel());
    private JTextField texttablefilter = new JTextField();

    // Table Field Components

    // Input Panel 
    private JPanel inputpanel = new JPanel(new GridBagLayout());
    // Input Field Components
    private JLabel inputfieldtitle = new JLabel("Entry Details");
    private JLabel periodlbl = new JLabel("Period", new ImageIcon("src\\main\\assets\\manualinputres\\clockicon.png"), SwingConstants.LEFT );
    private JComboBox<String> enterperiodfield;
    private JLabel datelbl = new JLabel("Date", new ImageIcon("src\\main\\assets\\manualinputres\\datecalendar.png"), SwingConstants.LEFT );
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    private JFormattedTextField enterdatefield;

    private JLabel usagelbl = new JLabel("Usage", new ImageIcon("src\\main\\assets\\manualinputres\\plugicon.png"), SwingConstants.LEFT );
    private JTextField enterusagefield;
    private JButton addentrybutton;


    // Constructor
    public ManualInputPanel() {
        /* Intializers for this Panel */
        // setBackgroundColor(Color.LIGHT_GRAY);
        initializePanel();
        initializeComponents();
        intializeConstraints();
    }

    // Setters
    public void setAddEntryButton(JButton b) { this.addentrybutton = b; }
    public void setScrollPane(JScrollPane s) { this.scrollpane = s; }
    public void setEnterDateField(JFormattedTextField t) { this.enterdatefield = t; }
    public void setEnterPeriodField(JComboBox<String> t) { this.enterperiodfield = t; }
    public void setEnterUsageField(JTextField t) { this.enterusagefield = t; }
    public void setTable(JTable t) { this.datatable = t; }
    public void setModel(DefaultTableModel tm) { this.model = tm; }
    //public void addTable()


    // Getters
    public JButton getAddEntryButton() { return this.addentrybutton; }
    public JScrollPane getScrollPane() { return this.scrollpane; }
    public JFormattedTextField getEnterDateField() { return this.enterdatefield; }
    public JComboBox<String> getEnterPeriodField() { return this.enterperiodfield; }
    public JTextField getEnterUsageField() { return this.enterusagefield; }
    public JTable getTable() { return this.datatable; }
    public DefaultTableModel getModel() { return this.model; }
    public JDatePicker getDatePicker(){ return datePicker;}
    public JTextField getTextTableFilter(){ return texttablefilter; }
    public TableRowSorter getRowSorter(){ return this.rowsorter; }


    // Intialize All Components
    public void initializeComponents(){

        datatable.getTableHeader().setReorderingAllowed(false);
        //Alternates Cells to be white or grey
        datatable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                return c;
            }
        });
       /* Instantiate all components of panel here */
        this.addentrybutton = new JButton("Add Entry");
        this.enterdatefield = new JFormattedTextField(df);
        this.enterperiodfield = new JComboBox<>();
        enterperiodfield.setEditable(false);

        for(int i = 0; i < HOURS; i++){
            enterperiodfield.addItem(String.format("%02d:00 - " + "%02d" + ":00", i, (int)(i+1)) );
        }

            Object[] columns = {"Date" , "Period" , "Usage" , "Edit" , "Delete"};

        this.enterusagefield = new JTextField("Enter Usage");
        this.model.setColumnIdentifiers(columns);
        this.datatable.setModel(this.model);
        this.scrollpane = new JScrollPane(this.datatable);
    

        datatable.getTableHeader().setResizingAllowed(false);

        RowSorter<TableModel> sorter =
        new TableRowSorter<TableModel>(model);
        datatable.setRowSorter(sorter);
        // Instantiate list components

        //sets the date field and usage field to be focusable
        enterdatefield.setFocusable(true);
        enterusagefield.setFocusable(true);

        // Setting up Searchable Table
        texttablefilter.setFont(new Font("Poppins", Font.BOLD, 15));
        texttablefilter.setMargin(new Insets(0,5,0,0));
        datatable.setRowSorter(this.rowsorter);
        filterpanel.add(searchlbl, BorderLayout.WEST);
        filterpanel.add(texttablefilter, BorderLayout.CENTER);
        tablepanel.add(filterpanel, BorderLayout.NORTH);
        tablepanel.add(scrollpane, BorderLayout.CENTER);

    }


    // Initializes Constraints for GridBag Layout
    public void intializeConstraints(){

        // table panel

        // Regular Attributes
        //Sets grid on table to match background color
        datatable.setGridColor(Color.WHITE);
        datatable.setIntercellSpacing(new Dimension(0,0));
        // tablepanel.setPreferredSize(TABLE_PANEL_SIZE);
        datatable.setRowHeight(30);
        datatable.setFont(new Font("Poppins", Font.BOLD, 15));
        
        //Change the Default alignment from left to center on the table.
        DefaultTableCellRenderer centerRender = (DefaultTableCellRenderer)datatable.getDefaultRenderer(Object.class);
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);

        // Gridbag Attributes
        GridBagConstraints c = new GridBagConstraints();

        /* table panel components */
        this.header.setBackground(new Color(10, 169, 212));
        this.header.setFont(new Font("Poppins", Font.BOLD, 20));
        this.header.setForeground(Color.WHITE);
        // setScrollPaneConstraints(this.scrollpane, 1, 1, DIMENSION_SCROLL_PANE, GLOBAL_PADDING);
        // Regular Attributes
        scrollpane.setFont(new Font("Poppins", Font.BOLD, 20));
        scrollpane.setPreferredSize(TABLE_PANEL_SIZE);
        scrollpane.setMaximumSize(TABLE_PANEL_SIZE);
        scrollpane.setMinimumSize(TABLE_PANEL_SIZE);
        // Gridbag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.BOTH;
        getPanel().add(tablepanel, c);

        // input panel
        // Regular Attributes
        inputpanel.setBackground(new Color(10, 169, 212));
        // inputpanel.setPreferredSize(INPUT_PANEL_SIZE);
        // Gridbag Attributes
        // setPanelContraints(getPanel(), inputpanel, GridBagConstraints.VERTICAL, GridBagConstraints.EAST, 1, 0, GLOBAL_PADDING);

        c = new GridBagConstraints();
        c.weightx = c.weighty = 1.0;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.BOTH;
        getPanel().add(inputpanel, c);

        /* input panel components */

        // Title
        // Regular Attributes
        inputfieldtitle.setForeground(Color.WHITE);
        inputfieldtitle.setHorizontalAlignment(SwingConstants.CENTER);
        inputfieldtitle.setFont(new Font("Poppins", Font.BOLD, 30));
        // Gridbag Attributes
        // setPanelContraints(inputpanel, inputfieldtitle, GridBagConstraints.HORIZONTAL, 0, 0, 0, TITLE_INSETS);

        c = new GridBagConstraints();
        c.weightx = c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        inputpanel.add(inputfieldtitle, c);

        // Date Field Label
        // Regular Attributes
        datelbl.setForeground(Color.WHITE);
        datelbl.setHorizontalAlignment(SwingConstants.CENTER);
        datelbl.setFont(new Font("Poppins", Font.BOLD, 20));
        datelbl.setHorizontalTextPosition(SwingConstants.LEFT);
        datelbl.setIconTextGap(10);

        // Gridbag Attributes
        c.weightx = c.weighty = 0.25;
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        inputpanel.add(datelbl, c);

        // Regular Attributes
        datePicker.getTextField().setFont(new Font("Poppins", Font.PLAIN, 15));
        datePicker.getTextField().setMargin(new Insets(0, 25, 0, 0));
        datePicker.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        // Gridbag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 0.125;
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        inputpanel.add(datePicker, c);

        // Period Field Label
        // Regular Attributes
        periodlbl.setForeground(Color.WHITE);
        periodlbl.setHorizontalAlignment(SwingConstants.CENTER);
        periodlbl.setFont(new Font("Poppins", Font.BOLD, 20));
        periodlbl.setHorizontalTextPosition(SwingConstants.LEFT);
        periodlbl.setIconTextGap(10);

        // Gridbag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 0.125;
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        inputpanel.add(periodlbl, c);

        // setConstraints(this.enterperiodfield, 4, 2, DIMENSION_TEXTFIELD, GLOBAL_PADDING);
        // Regular Attributes
        enterperiodfield.setFont(new Font("Poppins", Font.PLAIN, 15));
        // Gridbag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 0.125;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(0,10,0,10);
        inputpanel.add(enterperiodfield, c);

        // Usage Field Label
        // Regular Attributes
        usagelbl.setForeground(Color.WHITE);
        usagelbl.setHorizontalAlignment(SwingConstants.CENTER);
        usagelbl.setFont(new Font("Poppins", Font.BOLD, 20));
        usagelbl.setHorizontalTextPosition(SwingConstants.LEFT);
        usagelbl.setIconTextGap(10);

        // Gridbag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 0.125;
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        inputpanel.add(usagelbl, c);

        // setTextFieldConstraints(this.enterusagefield, 4, 3, DIMENSION_TEXTFIELD, GLOBAL_PADDING);
        // Regular Attributes
        enterusagefield.setFont(new Font("Poppins", Font.PLAIN, 15));
        enterusagefield.setHorizontalAlignment(SwingConstants.CENTER);

        // Gridbag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 0.125;
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        inputpanel.add(enterusagefield, c);

        // setButtonConstraints(this.addentrybutton, 0, 0, null, GLOBAL_PADDING);
        // Regular Attributes
        addentrybutton.setFont(new Font("Poppins", Font.BOLD, 30));
        addentrybutton.setHorizontalAlignment(SwingConstants.CENTER);
        addentrybutton.setText("+ Add Entry");
        addentrybutton.setForeground(Color.WHITE);
        addentrybutton.setBackground(new Color(76,175,106));
        addentrybutton.setFocusable(false);
        // Gridbag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 7;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0,10,0,10);
        inputpanel.add(addentrybutton, c);

    }
}