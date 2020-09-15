/* PACKAGE PATH */
package edu.csus.csc131.euc.controller.actionlistener.modelactionlisteners;

/* Library Imports */
// AWT Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// IO Imports
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

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
import edu.csus.csc131.euc.model.data.Day;
import edu.csus.csc131.euc.controller.Controller;
// Simple Local Import
import edu.csus.csc131.euc.libraries.simple.JSONArray;
import edu.csus.csc131.euc.libraries.simple.JSONObject;
import edu.csus.csc131.euc.libraries.simple.parser.JSONParser;
import edu.csus.csc131.euc.libraries.simple.parser.ParseException;

public class IJPanelIBActionListener implements ActionListener {
    // Instance Variables
    View view;
    Model model;
    Controller controller;

    public IJPanelIBActionListener(View v, Model m, Controller c){
        this.view = v;
        this.model = m;
        this.controller = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JSONParser parser = new JSONParser();

        // Throws a FILENOTFOUNDEXCEPTION which cascades into other issues.. need to debug this.
        File file = new File(view.getImportPanel().getImportField().getText());

        try (Reader reader = new FileReader(file)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            // Imports the data to the model for the profileID (userId), unit, and siteTimeZoneId
            model.getModelProfile().setProfileID(Integer.parseInt(jsonObject.get("userId").toString()));
            model.getModelProfile().setProfileUnit((String) jsonObject.get("unit"));
            model.getModelProfile().setProfileSiteTimeZoneID((String) jsonObject.get("siteTimeZoneId"));

            JSONArray reads = (JSONArray) jsonObject.get("reads");
            //TEST
            System.out.println(jsonObject);
            Day day = new Day("", false);
            // Imports the data to the model under read: for the startTime, endTime, and value
            for (int i = 0; i < reads.size(); i++) {
                JSONObject readsInternalObject = new JSONObject((JSONObject)reads.get(i));
                String startTime = readsInternalObject.get("startTime").toString();
                //technically end time is not needed since all are in 1 hr chunks
                //but it is still added for so that importing is less prone to errors in JSON
                String endTime = readsInternalObject.get("endTime").toString();

                //uses bigdecimal to convert to float since casting did not work
                double valueD = (double) readsInternalObject.get("value");
                BigDecimal d = new BigDecimal(valueD);
                float value = d.floatValue();

                String[] start = startTime.split("T");
                String date = start[0];
                String[] timeStart = start[1].split(":");
                String[] end = endTime.split("T");
                String[] timeEnd = end[1].split(":");

                String[] dateArray = date.split("-");
                int year = Integer.parseInt(dateArray[0]);
                int month = Integer.parseInt(dateArray[1]);
                int dayvalue = Integer.parseInt(dateArray[2]);

                date = "";
                switch (month){
                    case 1:
                        date += "Jan";
                        break;
                    case 2:
                        date += "Feb";
                        break;
                    case 3:
                        date += "Mar";
                        break;
                    case 4:
                        date += "Apr";
                        break;
                    case 5:
                        date += "May";
                        break;
                    case 6:
                        date += "Jun";
                        break;
                    case 7:
                        date += "Jul";
                        break;
                    case 8:
                        date += "Aug";
                        break;
                    case 9:
                        date += "Sep";
                        break;
                    case 10:
                        date += "Oct";
                        break;
                    case 11:
                        date += "Nov";
                        break;
                    case 12:
                        date += "Dec";
                        break;
                }
                date += " " + dayvalue + ", " + year;
                System.out.println(date);

                //if between start of June and before October
                // if(month >= 6 && month < 10){
                //     day.setSummer(true);
                // }
                // //set date if it is not done already
                // if(day.getDate().equals("")){
                //     day.setDate(date);
                // }
                view.getManualInputPanel().getDatePicker().setMonth(month);
                view.getManualInputPanel().getDatePicker().getTextField().setText(date);
                view.getManualInputPanel().getEnterPeriodField().setSelectedIndex(Integer.parseInt(timeStart[0]));
                view.getManualInputPanel().getEnterUsageField().setText(String.format("%.4f", value));

                view.getManualInputPanel().getAddEntryButton().getActionListeners()[0].actionPerformed(new ActionEvent(
                    view.getManualInputPanel().getAddEntryButton(), dayvalue, date));

                //Object[] row = new Object[5];

                /*
                row[0] = date;
                view.getManualInputPanel().getEnterPeriodField().setSelectedIndex(Integer.parseInt(timeStart[0]));
                row[1] = view.getManualInputPanel().getEnterPeriodField().getSelectedItem().toString();
                row[2] = String.format("%.4f", value);
                //Add Edit button
                view.getManualInputPanel().getTable().getColumn("Edit").setCellRenderer(controller.getEdit());
                // Edit.getButton().addActionListener(new EditRowListener());

                //Add Delete Button
                view.getManualInputPanel().getTable().getColumn("Delete").setCellRenderer(controller.getDelete());


                day.setUsage(value, Integer.parseInt(timeStart[0]), Integer.parseInt(timeEnd[0]));
                Record.addRecord(new Record(date, Integer.parseInt(timeStart[0])));
                view.getManualInputPanel().getModel().addRow(row);
                */

            }
            //model.getModelProfile().addDay(day);
            //updates the view calculate panel
            // Set a default view for view/calcuate which takes the datetime of the first rate in the array.
            // In this case, after importing the program, the index will be at 0 and we will see the start/end
            // time and the rate in the beginning of the dataset in the JSON file.
            //model.setModelIndex(0); // this will always set the index value to 0 at import.
            // view.getViewCalculatePanel().getUsageStartTime().setText(model.getModelRates().getStartTimeAtIndex(model.getModelIndex()));
            // view.getViewCalculatePanel().getUsageEndTime().setText(model.getModelRates().getEndTimeAtIndex(model.getModelIndex()));

        }
        catch (IOException exception) { System.out.println("File not found."); } //exception.printStackTrace(); }
        catch (IndexOutOfBoundsException exception) { System.out.println("Index out of bounds."); } //exception.printStackTrace(); }
        catch (ParseException exception) {
            System.out.println("Parsing Error.");
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid JSON File.");
        } //exception.printStackTrace(); }
        catch (NullPointerException exception) { System.out.println("No file selected."); }
    }
}

