/* PACKAGE PATH */
package edu.csus.csc131.euc.view.panels;

/*Library Imports*/
import javax.swing.*;
import java.awt.*;
import edu.csus.csc131.euc.view.panels.subpanels.*;

public class ViewCalculatePanel extends Panel {

    /* Constants */ // RF: 4/27/2020 - Pix
    // SIZES
    // Main Sub Panels
    private final Dimension USAGE_DETAILS_MAIN_SUB_PANEL_SIZE         = new Dimension(1028,532);
    private final Dimension SUMMARY_DETAILS_MAIN_SUB_PANEL_SIZE       = new Dimension(1028,332);

    // Components
    // Usage Details
    private final Dimension USAGE_DETAILS_TITLE_SIZE                  = new Dimension(270,40);
    private final Dimension SEASON_DETAILS_PANEL_SIZE                 = new Dimension(240,80);
    private final Dimension USAGE_COST_DETAILS_PANEL_SIZE             = new Dimension(920,240);
    private final Dimension TOTAL_COST_DETAILS_PANEL_SIZE             = new Dimension(670,200);

    // Summary Details
    private final Dimension TOTAL_USAGE_PANEL_SIZE                    = new Dimension(450,100);
    private final Dimension TOTAL_COST_PANEL_SIZE                     = new Dimension(450,100);
    private final Dimension SUMMARY_DETAILS_BUTTON_SIZE               = new Dimension(150,30);
    private final Dimension SUMMARY_DETAILS_TEXTFIELD_SIZE            = new Dimension(180,30);

    // PADDING
    // Generic
    private static final Insets DEFAULT_INSETS                        = new Insets(0,0,0,0);

    // Usage Details
    private static final Insets USAGE_DETAILS_TITLE_PADDING           = new Insets(70,0,0,270);
    private static final Insets SEASON_DETAILS_PANEL_PADDING          = new Insets(35,0,0,20);
    private static final Insets USAGECOST_PANEL_PADDING               = new Insets(0,70,20,50);
    private static final Insets USAGECOST_COMP_PADDING                = new Insets(0,0,0,37);
    private static final Insets SEASON_DETAIL_TITLE                   = new Insets(10,0,0,0);
    private static final Insets NAV_LEFT_PADDING                      = new Insets(0,0,0,0);
    private static final Insets NAV_RIGHT_PADDING                     = new Insets(0,0,0,15);

    // Summary Details
    private static final Insets SUMMARY_DETAILS_COMP_PADDING          = new Insets(0,0,0,15);
    private static final Insets SUMMARY_DETAILS_BUTTON_PADDING        = new Insets(20,30,5,0);
    private static final Insets SEASON_TITLE_PADDING                  = new Insets(0,70,0,10);
    private static final Insets OFF_MID_PEAK_TITLE_PADDING            = new Insets(0,65,0,0);
    private static final Insets PEAK_TITLE_PADDING                    = new Insets(0,100,0,0);
    private static final Insets TOTAL_SUB_PANEL_PADDING               = new Insets(5,0,0,5);
    private static final Insets TOTAL_VALUE_PADDING                   = new Insets(0,0,0,10);

    /* Instance Variables */

    /* usagedetailsmainpanel : Main Panel */
    private JPanel usagedetailsmainpanel = new JPanel(new GridBagLayout());

    /* usagedetailsmainpanel: Components */
    // Title Label
     private JLabel usagetitle = new JLabel("Usage Details");

    /* usagedetailsmainpanel : Sub Panels */
    /* usagecostdetailspanel : Sub Panel 1 */
    private RoundedPanel usagecostdetailspanel = new RoundedPanel();

    /* usagecostdetailspanel: Components */
    // General Navigation
    private JButton navrightbutton = new JButton( new ImageIcon("src\\main\\assets\\viewandcalculateres\\nextdaybutton.png"));
    private JButton navleftbutton = new JButton( new ImageIcon("src\\main\\assets\\viewandcalculateres\\previousdaybutton.png"));

    // 1st Column: Usage (Labels)
    private JLabel usagecosttotalusagelbl = new JLabel("Total: ");
    private JLabel usagecostnonpeakusagelbl = new JLabel("Non-Peak: ");
    private JLabel usagecostmidpeakusagelbl = new JLabel("Mid-Peak: ");
    private JLabel usagecostpeakusagelbl = new JLabel("Peak: ");

    // 1st Column: Usage (Values)
    private JLabel usagecosttotalusage = new JLabel("<html>0.000 <font size=4>kWH</font></html>");
    private JLabel usagecostnonpeakusage = new JLabel("<html>0.000 <font size=4>kWH</font></html>");
    private JLabel usagecostmidpeakusage = new JLabel("<html>0.000 <font size=4>kWH</font></html>");
    private JLabel usagecostpeakusage = new JLabel("<html>0.000 <font size=4>kWH</font></html>");

    // 2nd Column: Usage (Labels)
    private JLabel usagecosttotalcostlbl = new JLabel("Total Cost: ");
    private JLabel usagecostnonpeakcostlbl = new JLabel("Cost Non-Peak: ");
    private JLabel usagecostmidpeakcostlbl = new JLabel(" Cost Mid-Peak: ");
    private JLabel usagecostpeakcostlbl = new JLabel("Cost Peak: ");

    // 2nd Column: Usage (Values)
    private JLabel usagecosttotalcost = new JLabel("$ 0.00");
    private JLabel usagecostnonpeakcost = new JLabel("$ 0.00");
    private JLabel usagecostmidpeakcost = new JLabel("$ 0.00");
    private JLabel usagecostpeakcost = new JLabel("$ 0.00");

    /* seasondetailspanel : Sub Panel 2 */
    private RoundedPanel seasondetailspanel = new RoundedPanel();

    /* seasondetailspanel: Components */
    // Labels
    private JLabel seasondetailtitle = new JLabel("Summer");
    private JLabel seasondetailsdate = new JLabel("MM/DD/YYYY");
    // Icon
    private JLabel summericon = new JLabel(new ImageIcon("src\\main\\assets\\viewandcalculateres\\summericon.png"));
    private JLabel nonsummericon = new JLabel(new ImageIcon("src\\main\\assets\\viewandcalculateres\\nonsummer.png"));

    // -------------------------------------------------------------

    /* Main Subpanel: Summary Details Panel */
    private JPanel summarydetailsmainpanel = new JPanel(new GridBagLayout());

    /* summarydetailsmainpanel: Components */
    /* summarydetailsmainpanel : Sub Panels */

    /* usagecostdetailspanel : Sub Panel 1 */
    private JPanel summarydetailwestgrouppanels = new JPanel(new CardLayout());

    /* summarydetailwestgrouppanels: Components */
    // General Labels
    private JLabel summertitle = new JLabel("Summer", new ImageIcon("src\\main\\assets\\viewandcalculateres\\summericon.png"), SwingConstants.LEFT);
    private JLabel nonsummertitle = new JLabel("Non-Summer", new ImageIcon("src\\main\\assets\\viewandcalculateres\\nonsummer.png"), SwingConstants.LEFT);
    private JLabel offpeaktitle = new JLabel("Off-Peak");
    private JLabel midpeaktitle = new JLabel("Mid-Peak");
    private JLabel peaktitle = new JLabel("Peak");
    private JLabel [] ratelbls = { new JLabel("Rate: ") , new JLabel("Rate: "), new JLabel("Rate: "), new JLabel("Rate: "), new JLabel("Rate: ") };
    private JLabel [] periodlbls = { new JLabel("Period: ") , new JLabel("Period: "), new JLabel("Period: "), new JLabel("Period: "), new JLabel("Period: ") };

    // summarydetailwestpanelsummer : Sub Panel 1 of summarydetailwestgrouppanels
    private JPanel summarydetailwestpanelsummer = new JPanel(new GridBagLayout());

    // summarydetailwestpanelsummer : Components
    private JTextField summeroffpeakrate = new JTextField("$0.1209");
    private JTextField summeroffpeakperiod = new JTextField("00:00 to 12:00");
    private JTextField summermidpeakrate = new JTextField("$0.1671");
    private JTextField summermidpeakperiod = new JTextField("12:00 to 17:00 20:00 to 00:00");
    private JTextField summerpeakrate = new JTextField("$0.2941");
    private JTextField summerpeakperiod = new JTextField("17:00 to 20:00");

    /* usagecostdetailspanel : Sub Panel 2 */
    private JPanel summarydetaileastpanel = new JPanel(new GridBagLayout());

    // Buttons for Summary Details
    private JButton seasontoggle = new JButton("Change Season");
    private JButton resetdefault = new JButton("Reset Defaults");
    private JButton submituservaluesbutton = new JButton("Submit Values");

    /* summarydetaileastpanel: Components */
    // totalusagepanel : Sub Panel 1 of summarydetaileastpanel
    private RoundedPanel totalusagepanel = new RoundedPanel();

    // totalusagepanel : Components
    // Label
    JLabel totalusagepaneltitle = new JLabel("Total Usage");
    // Value
    private JLabel totalusage = new JLabel("<html>58.73 <font size=5>kWH</font></html>");

    // totalcostpanel : Sub Panel 2 of summarydetaileastpanel
    private RoundedPanel totalcostpanel = new RoundedPanel();

    // totalcostpanel : Components
    // Label
    JLabel totalcostpaneltitle = new JLabel("Total Cost");
    // Value
    private JLabel totalcost = new JLabel("$ 21.94");
    private String dollarsign = "$ ";

    /* END INSTANCE VARIABLES */
    // -------------------------------------------------------------

    // Constructor
    public ViewCalculatePanel() {
        /* Intializers for this Panel */
        setBackgroundColor(Color.lightGray);
        initializePanel();
        intializeComponentPreferences();
        intializeConstraints();
    }

    /* SETTERS AND GETTERS */

    /* Setters */
    // usagecost
    public void setUsageCostTotalUsage(float d){ this.usagecosttotalusage.setText(Float.toString(d)); }
    public void setUsageCostNonPeakUsage(float d){ this.usagecostnonpeakusage.setText(Float.toString(d)); }
    public void setUsageCostMidPeakUsage(float d){ this.usagecostmidpeakusage.setText(Float.toString(d)); }
    public void setUsageCostPeakUsage(float d){ this.usagecostpeakusage.setText(Float.toString(d)); }
    public void setUsageCostTotalCost(float d){ this.usagecosttotalcost.setText(Float.toString(d)); }
    public void setUsageCostNonPeakCost(float d){ this.usagecostnonpeakcost.setText(Float.toString(d)); }
    public void setUsageCostMidPeakCost(float d){ this.usagecostmidpeakcost.setText(Float.toString(d)); }
    public void setUsageCostPeakCost(float d){ this.usagecostpeakcost.setText(Float.toString(d)); }

    // seasondetailspanel
    public void setSeasonDetailTitle(String s){ this.seasondetailtitle.setText(s); }
    public void setSeasonDetailDate(String s){ this.seasondetailsdate.setText(s); }
    public void setSeasonIcon(ImageIcon i){ this.summericon.setIcon(i); }

    // summarydetailwestpanelsummer
    public void setSummerOffPeakRate(float f){ this.summeroffpeakrate.setText(Float.toString(f)); }
    public void setSummerOffPeakPeriod(float f){ this.summeroffpeakperiod.setText(Float.toString(f)); }
    public void setSummerOffMidPeakRate(float f){ this.summermidpeakrate.setText(Float.toString(f)); }
    public void setSummerOffMidPeakPeriod(float f){this.summermidpeakperiod.setText(Float.toString(f)); }
    public void setSummerPeakRate(float f){ this.summerpeakrate.setText(Float.toString(f)); }
    public void setSummerPeakPeriod(float f){ this.summerpeakperiod.setText(Float.toString(f)); }

    // totalusagepanel
    public void setTotalUsage(float f){ this.totalusage.setText(Float.toString(f)); }

    // totalcostpanel
    public void setTotalCost(float f){ this.totalcost.setText(Float.toString(f)); }

    /* Getters */
    // usagecostdetailspanel
    public JButton getNavRightButton(){ return this.navrightbutton; }
    public JButton getNavLeftButton(){ return this.navleftbutton; }

    public JLabel getUsageCostTotalUsage(){  return this.usagecosttotalusage; }
    public JLabel getUsageCostNonPeakUsage(){  return this.usagecostnonpeakusage; }
    public JLabel getUsageCostMidPeakUsage(){  return this.usagecostmidpeakusage; }
    public JLabel getUsageCostPeakUsage(){  return this.usagecostpeakusage; }

    public JLabel getUsageCostTotalCost(){  return this.usagecosttotalcost; }
    public JLabel getUsageCostNonPeakCost(){  return this.usagecostnonpeakcost; }
    public JLabel getUsageCostMidPeakCost(){  return this.usagecostmidpeakcost; }
    public JLabel getUsageCostPeakCost(){  return this.usagecostpeakcost; }

    // seasondetailspanel
    public JLabel getSeasonDetailTitle(){ return this.seasondetailtitle; }
    public JLabel getSeasonDetailDate(){ return this.seasondetailsdate; }
    public JLabel getSeasonIcon(){ return this.summericon; }

    //summarydetailwestpanelsummer
    public JTextField getSummerOffPeakRate(){ return this.summeroffpeakrate; }
    public JTextField getSummerOffPeakPeriod(){ return this.summeroffpeakperiod; }
    public JTextField getSummerMidPeakRate(){ return this.summermidpeakrate; }
    public JTextField getSummerMidPeakPeriod(){return this.summermidpeakperiod; }
    public JTextField getSummerPeakRate(){ return this.summerpeakrate; }
    public JTextField getSummerPeakPeriod(){ return this.summerpeakperiod; }

    // season toggle
    public JButton getSeasonToggleButton(){ return seasontoggle;}
    public JButton getSubmitUserValuesButton(){ return submituservaluesbutton;}
    public JButton getResetDefaultButton(){ return resetdefault;}

    // totalusagepanel
    public JLabel getTotalUsage(){ return this.totalusage; }

    // totalcostpanel
    public JLabel getTotalCost(){ return this.totalcost; }
    public String getDollarSign(){ return this.dollarsign; }


    /* END SETTERS AND GETTERS */
    // -------------------------------------------------------------

    /* Functions for ViewCalculate Panel*/

    // Intializes Preferences for each Component
    public void intializeComponentPreferences(){
        /* Component Preferences */
        // Set Usage Main Sub Panel Size
        usagedetailsmainpanel.setMinimumSize(USAGE_DETAILS_MAIN_SUB_PANEL_SIZE);
        usagedetailsmainpanel.setPreferredSize(USAGE_DETAILS_MAIN_SUB_PANEL_SIZE);

        // Set Summary Detail Main Sub Panel Size
        summarydetailsmainpanel.setMinimumSize(SUMMARY_DETAILS_MAIN_SUB_PANEL_SIZE);
        summarydetailsmainpanel.setPreferredSize(SUMMARY_DETAILS_MAIN_SUB_PANEL_SIZE);

        // Set Season Detail Sub Panel Size
        seasondetailspanel.setMinimumSize(SEASON_DETAILS_PANEL_SIZE);
        seasondetailspanel.setPreferredSize(SEASON_DETAILS_PANEL_SIZE);

        // Set Usage Detail Sub Panel Size
        usagecostdetailspanel.setMinimumSize(USAGE_COST_DETAILS_PANEL_SIZE);
        usagecostdetailspanel.setPreferredSize(USAGE_COST_DETAILS_PANEL_SIZE);

        // Set Total Usage Sub Panel Size
        totalusagepanel.setMinimumSize(TOTAL_USAGE_PANEL_SIZE);
        totalusagepanel.setPreferredSize(TOTAL_USAGE_PANEL_SIZE);

        // Set Total Cost Sub Panel Size
        totalcostpanel.setMinimumSize(TOTAL_COST_PANEL_SIZE);
        totalcostpanel.setPreferredSize(TOTAL_COST_PANEL_SIZE);

        // TITLE Sizes
        usagetitle.setPreferredSize(USAGE_DETAILS_TITLE_SIZE);
    }

    // Initializes Constraints for GridBag Layout
    public void intializeConstraints(){
        /* MAIN SUB PANEL CONSTRAINTS */

        GridBagConstraints c = new GridBagConstraints();

        // Usage Details Main Sub Panel
        // Regular Attributes
        usagedetailsmainpanel.setBackground(new Color(13, 156, 204));
        setPanelContraints(getPanel(), usagedetailsmainpanel, 0, GridBagConstraints.NORTH, 0, 0, DEFAULT_INSETS);

        // Summary Detail Main Sub Panel
        // Regular Attributes
        summarydetailsmainpanel.setBackground(Color.WHITE);
        // Grid Bag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = c.ipady = 50;
        c.anchor = GridBagConstraints.SOUTH;
        // setPanelContraints(getPanel(), summarydetailsmainpanel, 0, GridBagConstraints.SOUTH, 0, 1, DEFAULT_INSETS);

        getPanel().add(summarydetailsmainpanel, c);

        /* MAIN SUB PANEL COMPONENTS  */

        /* Usage Details Main Sub Panel */
        // Main Sub Panel Title
        // Regular Attributes
        usagetitle.setForeground(Color.WHITE);
        usagetitle.setFont(new Font("Poppins", Font.BOLD, 30));
        // Grid Bag Attributes
        c = new GridBagConstraints();
        c.weightx = c.weighty = 1.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = USAGE_DETAILS_TITLE_PADDING;
        usagedetailsmainpanel.add(usagetitle, c);

        // Season Details Sub Panel
        // Regular Attributes
        // Grid Bag Attributes
        setViewCalConstraints(usagedetailsmainpanel, seasondetailspanel, 1.0, GridBagConstraints.EAST, 1, 2, 0, SEASON_DETAILS_PANEL_PADDING);


        // Usage Cost Details Sub Panel
        // Regular Attributes
        usagecostdetailspanel.setBackground(Color.WHITE);
        // Grid Bag Attributes
        // Label Attributes
        setViewCalConstraints(usagedetailsmainpanel, usagecostdetailspanel, 1.0, GridBagConstraints.WEST, 2, 1, 1, USAGECOST_PANEL_PADDING);

        // RoundedPanel usagecostleft = new RoundedPanel();
        JPanel usagecostleft = new JPanel(new GridBagLayout());
        usagecostleft.setOpaque(false);
        usagecostleft.setBackground(Color.WHITE);
        createPanel(usagecostdetailspanel, usagecostleft, GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 0);

        RoundedPanel usagecostcenter = new RoundedPanel();
        // JPanel usagecostcenter = new JPanel(new GridBagLayout());
        usagecostcenter.setBackground(new Color(245,245,245));
        // usagecostcenter.setBackground(Color.WHITE);
        createPanel(usagecostdetailspanel, usagecostcenter, TOTAL_COST_DETAILS_PANEL_SIZE, GridBagConstraints.BOTH, GridBagConstraints.BOTH, 1, 0, DEFAULT_INSETS);

        // RoundedPanel usagecostright = new RoundedPanel();
        JPanel usagecostright = new JPanel(new GridBagLayout());
        usagecostright.setOpaque(false);
        usagecostright.setBackground(Color.WHITE);
        createPanel(usagecostdetailspanel, usagecostright, GridBagConstraints.BOTH, GridBagConstraints.EAST, 2, 0);

        // Navigation Attributes
        navleftbutton.setFocusPainted(false);
        navleftbutton.setOpaque(false);
        navleftbutton.setContentAreaFilled(true);
        navleftbutton.setBackground(Color.WHITE);
        navleftbutton.setBorderPainted(false);

        navrightbutton.setFocusPainted(false);
        navrightbutton.setOpaque(false);
        navrightbutton.setContentAreaFilled(true);
        navrightbutton.setBackground(Color.WHITE);
        navrightbutton.setBorderPainted(false);

        setPanelContraints(usagecostleft, navleftbutton, GridBagConstraints.BOTH, GridBagConstraints.WEST, 0, 0, NAV_LEFT_PADDING);
        setPanelContraints(usagecostright, navrightbutton, GridBagConstraints.BOTH, GridBagConstraints.EAST, 0, 0, NAV_RIGHT_PADDING);

        // Panel Details
        //Labels
        //total lbl
        setViewCalculateFont(usagecostcenter, usagecosttotalusagelbl, 0, 0, Font.PLAIN, 15, USAGECOST_COMP_PADDING, "east");

        //non-peak lbl
        setViewCalculateFont(usagecostcenter, usagecostnonpeakusagelbl, 0, 1, Font.PLAIN, 15, USAGECOST_COMP_PADDING, "east");

        //mid-peak lbl
        setViewCalculateFont(usagecostcenter, usagecostmidpeakusagelbl, 0, 2, Font.PLAIN, 15, USAGECOST_COMP_PADDING, "east");

        //peak lbl
        setViewCalculateFont(usagecostcenter, usagecostpeakusagelbl, 0, 3, Font.PLAIN, 15, USAGECOST_COMP_PADDING, "east");

        //total cost lbl
        setViewCalculateFont(usagecostcenter, usagecosttotalcostlbl, 2, 0, Font.PLAIN, 15, USAGECOST_COMP_PADDING, "east");

        // non cost lbl
        setViewCalculateFont(usagecostcenter, usagecostnonpeakcostlbl, 2, 1, Font.PLAIN, 15, USAGECOST_COMP_PADDING, "east");

        // mid cost lbl
        setViewCalculateFont(usagecostcenter, usagecostmidpeakcostlbl, 2, 2, Font.PLAIN, 15, USAGECOST_COMP_PADDING, "east");

        //  peak cost lbl
        setViewCalculateFont(usagecostcenter, usagecostpeakcostlbl, 2, 3, Font.PLAIN, 15, USAGECOST_COMP_PADDING, "east");

        // Usage (Values)
        // total usage
        // Regular Attributes
        usagecosttotalusage.setForeground(new Color(23, 104, 172));
        setViewCalculateFont(usagecostcenter, usagecosttotalusage, 1, 0, Font.BOLD, 25, USAGECOST_COMP_PADDING, "east");

        // non usage
        setViewCalculateFont(usagecostcenter, usagecostnonpeakusage, 1, 1, Font.BOLD, 25, USAGECOST_COMP_PADDING, "east");

        // mid usage
        setViewCalculateFont(usagecostcenter, usagecostmidpeakusage, 1, 2, Font.BOLD, 25, USAGECOST_COMP_PADDING, "east");

        // peak usage
        setViewCalculateFont(usagecostcenter, usagecostpeakusage, 1, 3, Font.BOLD, 25, USAGECOST_COMP_PADDING, "east");

       // total cost usage
       // Regular Attributes
       usagecosttotalcost.setForeground(new Color(76, 175, 106));
       setViewCalculateFont(usagecostcenter, usagecosttotalcost, 3, 0, Font.BOLD, 25, USAGECOST_COMP_PADDING, "east");

        // total cost usage
        setViewCalculateFont(usagecostcenter, usagecostnonpeakcost, 3, 1, Font.BOLD, 25, USAGECOST_COMP_PADDING, "east");
        setViewCalculateFont(usagecostcenter, usagecostmidpeakcost, 3, 2, Font.BOLD, 25, USAGECOST_COMP_PADDING, "east");
        setViewCalculateFont(usagecostcenter, usagecostpeakcost, 3, 3, Font.BOLD, 25, USAGECOST_COMP_PADDING, "east");


        /* Summary Details Main Sub Panel */
        //  Summary West Panel
        // Regular Attributes
        summarydetailwestpanelsummer.setBackground(Color.WHITE);
        summarydetailwestgrouppanels.add(summarydetailwestpanelsummer, "summer");

        // GridBag Attributes
        setPanelContraints(summarydetailsmainpanel, summarydetailwestgrouppanels, GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 0, 1, DEFAULT_INSETS);

        // Summary East Panel
        // Regular Attributes
        summarydetaileastpanel.setBackground(Color.WHITE);
        // GridBag Attributes
        setPanelContraints(summarydetailsmainpanel, summarydetaileastpanel, GridBagConstraints.VERTICAL, GridBagConstraints.WEST, 1, 1, DEFAULT_INSETS);

        // Summary Details Panel Title
        // Regular Attributes
        // summarytitle.setFont(new Font("Poppins", Font.BOLD, 30));
        // // GridBag Attributes
        // // setPanelContraints(summarydetailsmainpanel, summarytitle, 0, GridBagConstraints.CENTER, 1, 0, SUMMARY_TITLE, false);
        // setPanelContraints(summarydetaileastpanel, summarytitle, GridBagConstraints.CENTER, GridBagConstraints.CENTER, 0, 0, SUMMARY_TITLE, false);

        // Summary Details Panel Season Title / Summer
        // Regular Attributes
        summertitle.setFont(new Font("Poppins", Font.BOLD, 25));
        summertitle.setForeground(Color.BLACK);
        summertitle.setHorizontalTextPosition(SwingConstants.LEFT);
        summertitle.setIconTextGap(10);
        // GridBag Attributes
        // setPanelContraints(summarydetailsmainpanel, summertitle, 0, GridBagConstraints.WEST, 0, 0, SEASON_TITLE, false);
        setPanelContraints(summarydetailwestpanelsummer, summertitle, GridBagConstraints.CENTER, GridBagConstraints.WEST, 0, 0, SEASON_TITLE_PADDING, 3);

        // Summary Details Panel Season Title / Non-Summer
        // Regular Attributes
        nonsummertitle.setFont(new Font("Poppins", Font.BOLD, 25));
        nonsummertitle.setForeground(Color.BLACK);
        nonsummertitle.setHorizontalTextPosition(SwingConstants.LEFT);
        nonsummertitle.setIconTextGap(10);
        nonsummertitle.setVisible(false);
        // GridBag Attributes
        // setPanelContraints(summarydetailsmainpanel, nonsummertitle, 0, GridBagConstraints.WEST, 0, 0, SEASON_TITLE, false);
        setPanelContraints(summarydetailwestpanelsummer, nonsummertitle, 0, GridBagConstraints.WEST, 0, 0, SEASON_TITLE_PADDING, 3);


        // Off Peak Label
        // Regular Attributes
        // GridBag Attributes
        setViewCalculateFont(summarydetailwestpanelsummer, offpeaktitle, 0, 1, Font.BOLD, 20, OFF_MID_PEAK_TITLE_PADDING, false);

        // Off Peak Rate Label
        // Regular Attributes
        ratelbls[0].setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, ratelbls[0], 0, GridBagConstraints.EAST, 1, 1, DEFAULT_INSETS, false);


        // Off Peak Rate
        // Regular Attributes
        summeroffpeakrate.setPreferredSize(SUMMARY_DETAILS_TEXTFIELD_SIZE);
        summeroffpeakrate.setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, summeroffpeakrate, 0, GridBagConstraints.WEST, 2, 1, SUMMARY_DETAILS_COMP_PADDING, false);


        // Off Peak Period Label
        // Regular Attributes
        periodlbls[0].setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, periodlbls[0], 0, GridBagConstraints.EAST, 1, 2, DEFAULT_INSETS, false);

        // Off Peak Period
        // Regular Attributes
        summeroffpeakperiod.setEditable(true);
        summeroffpeakperiod.setPreferredSize(SUMMARY_DETAILS_TEXTFIELD_SIZE);
        summeroffpeakperiod.setFont(new Font("Poppins", Font.BOLD, 12));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, summeroffpeakperiod, 0, GridBagConstraints.WEST, 2, 2, SUMMARY_DETAILS_COMP_PADDING, false);

        // Mid-peak Label
        // Regular Attributes
        midpeaktitle.setFont(new Font("Poppins", Font.BOLD, 20));
        // GridBag Attributes
        setViewCalculateFont(summarydetailwestpanelsummer, midpeaktitle, 0, 3, Font.BOLD, 20, OFF_MID_PEAK_TITLE_PADDING, false);


        // Mid-peak Rate Label
        ratelbls[1].setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, ratelbls[1], 0, GridBagConstraints.EAST, 1, 3, DEFAULT_INSETS, false);


        // Mid-peak Rate
        summermidpeakperiod.setEditable(true);
        summermidpeakperiod.setPreferredSize(SUMMARY_DETAILS_TEXTFIELD_SIZE);
        summermidpeakperiod.setFont(new Font("Poppins", Font.BOLD, 12));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, summermidpeakrate, 0, GridBagConstraints.WEST, 2, 3, SUMMARY_DETAILS_COMP_PADDING, false);

        // Mid-peak Period Label
        periodlbls[1].setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, periodlbls[1], 0, GridBagConstraints.EAST, 1, 4, DEFAULT_INSETS, false);


        // Mid-peak Rate
        summermidpeakrate.setPreferredSize(SUMMARY_DETAILS_TEXTFIELD_SIZE);
        summermidpeakrate.setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, summermidpeakperiod, 0, GridBagConstraints.WEST, 2, 4, SUMMARY_DETAILS_COMP_PADDING, false);

        // Peak Label
        peaktitle.setFont(new Font("Poppins", Font.BOLD, 20));
        // GridBag Attributes
        setViewCalculateFont(summarydetailwestpanelsummer, peaktitle, 0, 5, Font.BOLD, 20, PEAK_TITLE_PADDING, false);

        // Peak Rate Label
        ratelbls[2].setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, ratelbls[2], 0, GridBagConstraints.EAST, 1, 5, DEFAULT_INSETS, false);

        // Peak Rate
        summerpeakrate.setPreferredSize(SUMMARY_DETAILS_TEXTFIELD_SIZE);
        summerpeakrate.setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, summerpeakrate, 0, GridBagConstraints.WEST, 2, 5, SUMMARY_DETAILS_COMP_PADDING, false);

        // Peak Period label
        periodlbls[2].setFont(new Font("Poppins", Font.BOLD, 15));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, periodlbls[2], 0, GridBagConstraints.EAST, 1, 6, DEFAULT_INSETS, false);

        // Peak Period
        summerpeakperiod.setEditable(true);
        summerpeakperiod.setPreferredSize(SUMMARY_DETAILS_TEXTFIELD_SIZE);
        summerpeakperiod.setFont(new Font("Poppins", Font.BOLD, 12));
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, summerpeakperiod, 0, GridBagConstraints.WEST, 2, 6, SUMMARY_DETAILS_COMP_PADDING, false);

        // Season Toggle Button
        // Regular Attributes
        seasontoggle.setFont(new Font("Poppins", Font.BOLD, 15));
        seasontoggle.setForeground(Color.WHITE);
        seasontoggle.setBackground(new Color(2, 29, 62));
        seasontoggle.setBorderPainted(false);
        seasontoggle.setFocusPainted(false);
        seasontoggle.setPreferredSize(SUMMARY_DETAILS_BUTTON_SIZE);
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, seasontoggle, 0, GridBagConstraints.WEST, 0, 7, SUMMARY_DETAILS_BUTTON_PADDING, false);

        // Reset Default Button
        // Regular Attributes
        resetdefault.setFont(new Font("Poppins", Font.BOLD, 15));
        resetdefault.setForeground(Color.WHITE);
        resetdefault.setBackground(new Color(2, 29, 62));
        resetdefault.setBorderPainted(false);
        resetdefault.setFocusPainted(false);
        resetdefault.setPreferredSize(SUMMARY_DETAILS_BUTTON_SIZE);
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, resetdefault, 0, GridBagConstraints.WEST, 1, 7, SUMMARY_DETAILS_BUTTON_PADDING, false);

        // Reset Default Button
        // Regular Attributes
        submituservaluesbutton.setFont(new Font("Poppins", Font.BOLD, 15));
        submituservaluesbutton.setForeground(Color.WHITE);
        submituservaluesbutton.setBackground(new Color(76,175,106));
        submituservaluesbutton.setBorderPainted(false);
        submituservaluesbutton.setFocusPainted(false);
        submituservaluesbutton.setPreferredSize(SUMMARY_DETAILS_BUTTON_SIZE);
        // GridBag Attributes
        setPanelContraints(summarydetailwestpanelsummer, submituservaluesbutton, 0, GridBagConstraints.WEST, 2, 7, SUMMARY_DETAILS_BUTTON_PADDING, false);

        // Total Usage Sub Panel
        // Regular Attributes
        totalusagepanel.setBackground(new Color(2,29,62));
        // GridBag Attributes
        setPanelContraints(summarydetaileastpanel, totalusagepanel, 0, GridBagConstraints.WEST, 0, 1, TOTAL_SUB_PANEL_PADDING, false);

        // Total Cost Sub Panel
        // Regular Attributes
        totalcostpanel.setBackground(new Color(76,175,106));
        // GridBag Attributes
        setPanelContraints(summarydetaileastpanel, totalcostpanel, 0, GridBagConstraints.WEST, 0, 2, TOTAL_SUB_PANEL_PADDING, false);

        /* INDIVIDUAL SUB PANEL COMPONENT CONSTRAINTS */

        /* Season Details Components */

        // Season Detail Title
        // Regular Attributes
        setViewCalculateFont(seasondetailspanel, seasondetailtitle, 0, 0, Font.BOLD, 20, SEASON_DETAIL_TITLE);

        // Summer Icon
        // Regular Attributes
        // GridBag Attributes
        c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = DEFAULT_INSETS;
        seasondetailspanel.add(summericon, c);

        // Non Summer Icon
        // Regular Attributes
        nonsummericon.setVisible(false);
        // GridBag Attributes
        c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = DEFAULT_INSETS;
        seasondetailspanel.add(nonsummericon, c);

        // Season Date
        // Regular Attributes
        seasondetailsdate.setFont(new Font("Poppins", Font.BOLD, 12));
        setPanelContraints(seasondetailspanel, seasondetailsdate, 0, GridBagConstraints.NORTH, 0, 1, DEFAULT_INSETS);;

        /* Total Usage Sub Panel Components */
        // Total Usage Label
        // Regular Attributes
        totalusagepaneltitle.setFont(new Font("Poppins", Font.PLAIN, 30));
        totalusagepaneltitle.setForeground(Color.WHITE);
        totalusagepaneltitle.setHorizontalAlignment(SwingConstants.CENTER);

        // GridBag Attributes
        setPanelContraints(totalusagepanel, totalusagepaneltitle, 0, GridBagConstraints.CENTER, 0, 0, DEFAULT_INSETS);

        // totalusagepanel.add(totalusagepaneltitle, c);

        // Total Usage Value
        // Regular Attributes
        totalusage.setFont(new Font("Poppins", Font.BOLD, 50));
        totalusage.setForeground(Color.WHITE);
        totalusage.setBackground(new Color(23, 104, 172));
        totalusage.setHorizontalAlignment(SwingConstants.CENTER);
        totalusage.setBounds(new Rectangle(300,75));
        totalusage.setOpaque(true);

        // GridBag Attributes
        setPanelContraints(totalusagepanel, totalusage, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 0, TOTAL_VALUE_PADDING);

        /* Total Cost Sub Panel Components */
        // Total Cost Label
        // Regular Attributes
        totalcostpaneltitle.setFont(new Font("Poppins", Font.PLAIN, 30));
        totalcostpaneltitle.setForeground(Color.WHITE);
        totalcostpaneltitle.setHorizontalAlignment(SwingConstants.CENTER);

        // GridBag Attributes
        setPanelContraints(totalcostpanel, totalcostpaneltitle, 0, GridBagConstraints.CENTER, 0, 0, DEFAULT_INSETS);


        // Total Cost Value
        // Regular Attributes
        totalcost.setFont(new Font("Poppins", Font.BOLD, 46));
        totalcost.setForeground(Color.WHITE);
        totalcost.setBackground(new Color(11, 83, 81));
        totalcost.setHorizontalAlignment(SwingConstants.CENTER);
        // totalcost.setBounds(new Rectangle(300,75));
        totalcost.setOpaque(true);

        // GridBag Attributes
        setPanelContraints(totalcostpanel, totalcost, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 0, TOTAL_VALUE_PADDING);
    }

    // Season Detail Sub Panel Methods: Helper methods to display correct summer icon
    public void displaySummerIcon(){
        this.nonsummericon.setVisible(false);
        this.summericon.setVisible(true);
    }

    public void displayNonSummerIcon(){
        this.summericon.setVisible(false);
        this.nonsummericon.setVisible(true);
    }

    // Settion the non summer title: Helper methods to display the correct summary detail season title
    public void displaySummerSeasonTitle(){
        this.nonsummertitle.setVisible(false);
        this.summertitle.setVisible(true);

    }

    public void displayNonSummerSeasonTitle(){
        this.summertitle.setVisible(false);
        this.nonsummertitle.setVisible(true);

    }

}
