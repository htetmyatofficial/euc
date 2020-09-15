/* PACKAGE PATH */
package edu.csus.csc131.euc.model.data;

/* Libaray Imports */
import java.util.Arrays;

// ----------------------------------------- //
// Rates Class: Contains rates               //
// ----------------------------------------- //

public class Rates {

    // Constants
    //private static final int HOURS = 24;
    //removed final property
    private static float RATES_DEFAULT_OFFPEAK_SUMMER = (float)0.1209;
    private static float RATES_DEFAULT_OFFPEAK_NONSUMMER = (float)0.1006;
    private static float RATES_DEFAULT_MIDPEAK_SUMMER = (float)0.1671;
    private static float RATES_DEFAULT_PEAK_SUMMER = (float)0.2941;
    private static float RATES_DEFAULT_PEAK_NONSUMMER = (float)0.1388;

    private static final float RATES_DEFAULT_OFFPEAK_SUMMER_DEFAULT = (float)0.1209;
    private static final float RATES_DEFAULT_OFFPEAK_NONSUMMER_DEFAULT = (float)0.1006;
    private static final float RATES_DEFAULT_MIDPEAK_SUMMER_DEFAULT = (float)0.1671;
    private static final float RATES_DEFAULT_PEAK_SUMMER_DEFAULT = (float)0.2941;
    private static final float RATES_DEFAULT_PEAK_NONSUMMER_DEFAULT = (float)0.1388;

    private static String[] summerPeriod = new String[24];
    private static String[] nonSummerPeriod = new String[24];

    // Instance Variables
    private float [] rates;
    private static String [] userdefinedsummmerperiods = {"00:00 to 12:00", "12:00 to 17:00 20:00 to 00:00", "17:00 to 20:00"};
    private static String [] userdefinednonsummerperiods = {"00:00 to 17:00 20:00 to 00:00", "N/A", "17:00 to 20:00"};

    // Default Constructor
    public Rates(final boolean isSummer){
        rates = new float[24];
        setDefaultRates();
        setDefaultPeriod();
        setUserRates(isSummer);
    }

    // Setters
    public void setRate(final float [] r){ this.rates = r; }
    public void setRateAtIndex(final float r, final int i){ this.rates[i] = r; }

    // Getters
    public float [] getRates(){ return this.rates; }
    public float getRateAtIndex(final int i){ return this.rates[i]; }
    public static float getOffPeakSummer(){ return RATES_DEFAULT_OFFPEAK_SUMMER;}
    public static float getMidPeakSummer(){ return RATES_DEFAULT_MIDPEAK_SUMMER;}
    public static float getPeakSummer(){ return RATES_DEFAULT_PEAK_SUMMER;}
    public static float getPeakNonSummer(){ return RATES_DEFAULT_PEAK_NONSUMMER;}
    public static float getOffPeakNonSummer(){ return RATES_DEFAULT_OFFPEAK_NONSUMMER;}
    public String [] getUserSummerDefinedPeriods(){ return userdefinedsummmerperiods; }
    public String [] getUserNonSummerDefinedPeriods(){ return userdefinednonsummerperiods; }


    //setters for default rates
    public void setOffPeakSummer(final float value){
        RATES_DEFAULT_OFFPEAK_SUMMER = value;
    }
    public void setMidPeakSummer(final float value){
        RATES_DEFAULT_MIDPEAK_SUMMER = value;
    }
    public void setPeakSummer(final float value){
        RATES_DEFAULT_PEAK_SUMMER = value;
    }
    public void setPeakNonSummer(final float value){
        RATES_DEFAULT_PEAK_NONSUMMER = value;
    }
    public void setOffPeakNonSummer(final float value){
        RATES_DEFAULT_OFFPEAK_NONSUMMER = value;
    }

    public void setUserRates(boolean isSummer){
        if(isSummer){
            for(int i = 0; i < 24; i++){
                if(summerPeriod[i].equals("RATES_DEFAULT_MIDPEAK_SUMMER")){
                    rates[i] = RATES_DEFAULT_MIDPEAK_SUMMER;
                }
                else if(summerPeriod[i].equals("RATES_DEFAULT_PEAK_SUMMER")){
                    rates[i] = RATES_DEFAULT_PEAK_SUMMER;
                }
                else if(summerPeriod[i].equals("RATES_DEFAULT_OFFPEAK_SUMMER")){
                    rates[i] = RATES_DEFAULT_OFFPEAK_SUMMER;
                }
            }
        }else{
            for(int i = 0; i < 24; i++){
                if(nonSummerPeriod[i].equals("RATES_DEFAULT_PEAK_NONSUMMER")){
                    rates[i] = RATES_DEFAULT_PEAK_NONSUMMER;
                }
                else if(nonSummerPeriod[i].equals("RATES_DEFAULT_OFFPEAK_NONSUMMER")){
                    rates[i] = RATES_DEFAULT_OFFPEAK_NONSUMMER;
                }
            }
        }

    }
    // Method for setting default time values
    public static void setDefaultRates(){

        RATES_DEFAULT_OFFPEAK_SUMMER = RATES_DEFAULT_OFFPEAK_SUMMER_DEFAULT;
        RATES_DEFAULT_MIDPEAK_SUMMER = RATES_DEFAULT_MIDPEAK_SUMMER_DEFAULT;
        RATES_DEFAULT_PEAK_SUMMER = RATES_DEFAULT_PEAK_SUMMER_DEFAULT;

        RATES_DEFAULT_OFFPEAK_NONSUMMER = RATES_DEFAULT_OFFPEAK_NONSUMMER_DEFAULT;
        RATES_DEFAULT_PEAK_NONSUMMER = RATES_DEFAULT_PEAK_NONSUMMER_DEFAULT;


    }

    public static void setDefaultPeriod(){
        Arrays.fill(summerPeriod, 0, 12, "RATES_DEFAULT_OFFPEAK_SUMMER");
        Arrays.fill(summerPeriod, 12, 17, "RATES_DEFAULT_MIDPEAK_SUMMER");
        Arrays.fill(summerPeriod, 17, 20, "RATES_DEFAULT_PEAK_SUMMER");
        Arrays.fill(summerPeriod, 20, 24, "RATES_DEFAULT_MIDPEAK_SUMMER");
        Arrays.fill(nonSummerPeriod, 0, 17, "RATES_DEFAULT_OFFPEAK_NONSUMMER");
        Arrays.fill(nonSummerPeriod, 17, 20, "RATES_DEFAULT_PEAK_NONSUMMER");
        Arrays.fill(nonSummerPeriod, 20, 24, "RATES_DEFAULT_OFFPEAK_NONSUMMER");
    }
   // Helper function to parse hours from string
   public void setUserPeriod(final String period, final boolean isSummer, final boolean isOffPeak, final boolean isMidPeak, final boolean isPeak){

        // Catch User Defined Period
        if(isOffPeak && isSummer){
            userdefinedsummmerperiods[0] = period;
            System.out.println(userdefinedsummmerperiods[0]);
        }
        if(isMidPeak&& isSummer){ userdefinedsummmerperiods[1] = period; }
        if(isPeak&& isSummer){ userdefinedsummmerperiods[2] = period; }

        if(isOffPeak&& !isSummer){ userdefinednonsummerperiods[0] = period; }
        if(isPeak&& !isSummer){ userdefinednonsummerperiods[2] = period; }

        // Split according to whitespace
        final String [] split = period.split("\\s+");

        // TESTING SPLIT
        // System.out.println("Listing all elements: ");
        // for (String element : split){System.out.println(element); }

        int to = 0;
        int from = 0;
        boolean isFrom = true;
        boolean doInput = false;

        for (final String element : split){
            // System.out.println(element);
            // if it is "to" then flip flag
            if( element.equals("to") ){ isFrom = false; }
            else{
                // Get only the hours section of the string and replace all 0's with nothing
                String hours = element.substring(0, 2);

                // TEST SUBSTRING
                // System.out.println(hours);

                // Catching values with leading 0
                if(hours.startsWith("0")){
                    hours = hours.replace("0","");
                }

                // TEST HOURS REPLACEMENT
                // System.out.println(hours);
                // System.out.println(hours.isEmpty());

                // If isFrom set 'from', else set 'to'
                try{
                    if (hours.isEmpty() && isFrom){
                        from = 0;
                        doInput = false;
                    }
                    else if(hours.isEmpty() && !isFrom){
                        to = 0;
                        doInput = true;
                        isFrom = true;
                    }else if(!hours.isEmpty() && isFrom){
                        from = Integer.parseInt(hours);
                        doInput = false;
                    }else{
                        to = Integer.parseInt(hours);
                        doInput = true;
                        isFrom = true;
                    }
                }catch(final Exception ex){
                    System.out.println(ex);
                }

                // Catching times from previous day to midnight of next day
                if(to == 0 && from > to){
                    to = 24;
                }
            }

            // Call appropriate period setting method
            if(doInput){
                System.out.println("Processing Input");
                if (isOffPeak && isSummer){
                    System.out.println("From: " + from + " To: " + to);
                    setOffPeakSummerPeriod(from, to, false);
                }
                else if (isOffPeak && !isSummer){
                    System.out.println("From: " + from + " To: " + to);
                    setOffPeakNonSummerPeriod(from, to, false);
                }
                else if (isMidPeak && isSummer){
                    System.out.println("From: " + from + " To: " + to);
                    setMidPeakSummerPeriod(from, to, false);
                }
                else if (isMidPeak && !isSummer){
                    // Do Nothing
                }
                else if (isPeak && isSummer){
                    System.out.println("From: " + from + " To: " + to);
                    setPeakSummerPeriod(from, to, false);
                }
                else if (isPeak && !isSummer){
                    System.out.println("From: " + from + " To: " + to);
                    setPeakNonSummerPeriod(from, to, false);
                }
                else {
                    // Do Nothing
                }
            }

        }

    }
    public void setOffPeakSummerPeriod(int from, int to, boolean def){
        if(def){
            Arrays.fill(summerPeriod, 0, 12, "RATES_DEFAULT_OFFPEAK_SUMMER");
        }
        else{
            Arrays.fill(summerPeriod, from, to, "RATES_DEFAULT_OFFPEAK_SUMMER");
        }
    }
    public void setMidPeakSummerPeriod(int from, int to, boolean def){
        if(def){
            Arrays.fill(summerPeriod, 12, 17, "RATES_DEFAULT_MIDPEAK_SUMMER");
            Arrays.fill(summerPeriod, 20, 24, "RATES_DEFAULT_MIDPEAK_SUMMER");
        }
        else{
            Arrays.fill(summerPeriod, from, to, "RATES_DEFAULT_MIDPEAK_SUMMER");
        }
    }
    public void setPeakSummerPeriod(int from, int to, boolean def){
        if(def){
            Arrays.fill(summerPeriod, 17, 20, "RATES_DEFAULT_PEAK_SUMMER");
        }
        else{
            Arrays.fill(summerPeriod, from, to, "RATES_DEFAULT_PEAK_SUMMER");
        }
    }

    public void setPeakNonSummerPeriod(int from, int to, boolean def){
        if(def){
            Arrays.fill(nonSummerPeriod, 17, 20, "RATES_DEFAULT_PEAK_NONSUMMER");
        }
        else{
            Arrays.fill(nonSummerPeriod, from, to, "RATES_DEFAULT_PEAK_NONSUMMER");
        }
    }

    public void setOffPeakNonSummerPeriod(int from, int to, boolean def){
        if(def){
            Arrays.fill(nonSummerPeriod, 0, 17, "RATES_DEFAULT_OFFPEAK_NONSUMMER");
            Arrays.fill(nonSummerPeriod, 20, 24, "RATES_DEFAULT_OFFPEAK_NONSUMMER");
        }
        else{
            Arrays.fill(nonSummerPeriod, from, to, "RATES_DEFAULT_OFFPEAK_NONSUMMER");
        }
    }
    public String getSummerPeriod(int index){
        return summerPeriod[index];
    }

    public String getNonSummerPeriod(int index){
        return nonSummerPeriod[index];

    }

}