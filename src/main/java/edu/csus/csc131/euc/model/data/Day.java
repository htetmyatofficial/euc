package edu.csus.csc131.euc.model.data;

public class Day {

  private String date;
  private Usage usage;
  private boolean isSummer;

  public Day(String date, boolean isSummer){
    this.date = date;
    this.usage = new Usage();
    this.isSummer = isSummer;
  }

  //getters

  public String getDate(){
    return date;
  }

  public Usage getUsage(){
    return usage;
  }

  public float getTotalUsage(){
    float sum = 0;
    for(int i  = 0; i < 24; i++){
      sum += usage.getUsageAtIndex(i);
    }
    return sum;
  }

  public float getUsageAtIndex(int index){
    return usage.getUsageAtIndex(index);
  }

  public boolean isSummer(){
    return isSummer;
  }

  //setters

  public void setDate(String date){
    this.date = date;
  }

  // This function initializes usage specific for the profile
  public void setUsage(float value, int index){
    usage.setUsageAtIndex(value, index);
  }

  public void setUsage(float value, int from, int to){
    for(int i = from; i < to; i++){
      usage.setUsageAtIndex(value, i);
    }
  }

  public void setSummer(boolean isSummer){
    this.isSummer = isSummer;
  }


}
