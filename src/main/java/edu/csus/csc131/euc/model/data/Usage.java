/* PACKAGE PATH */ 
package edu.csus.csc131.euc.model.data;

// ----------------------------------------- //
// Usage Class: Contains usages              //
// ----------------------------------------- //

public class Usage {
    // Deafault number of hourly timeslots within a day, where 0 = 0-1 hr time slot  
    private static final int HOURS = 24; 

    // Instance Variables 
    private float [] usage = new float[HOURS];

    // Default Constructor 
    public Usage(){ this.usage = new float[HOURS]; }

    // Constructor
    public Usage(float [] u){ this.usage = u; }

    // Setter 
    public void setUsage(float [] u){ this.usage = u; }
    public void setUsageAtIndex(float d, int h){ this.usage[h] = d; }

    // Getter
    public float [] getUsage(){ return this.usage; };
    public float getUsageAtIndex(int i){ return this.usage[i]; }

}