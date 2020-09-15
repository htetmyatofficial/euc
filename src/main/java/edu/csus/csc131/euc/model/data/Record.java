package edu.csus.csc131.euc.model.data;

import java.util.ArrayList;
import java.util.ListIterator;

public class Record {

    private String date;
    private int index;

    private static ArrayList<Record> r = new ArrayList<>();

    public Record(String date, int index){
        this.date = date;
        this.index = index;
    }

    public static void addRecord(Record rec){
        r.add(rec);
    }

    public static int findDuplicate(String date, int index){
        ListIterator<Record> iter = r.listIterator();
        Record rec;
        int indexCount = 0;
        while(iter.hasNext()){
            rec = iter.next();
            if(rec.date.equals(date) && rec.index == index){
                return indexCount;
            }
            indexCount++;
        }
        return -1;
    }

    public static int findDuplicateDay(String date){
      ListIterator<Record> iter = r.listIterator();
      Record rec;
      int indexCount = 0;
      while(iter.hasNext()){
          rec = iter.next();
          if(rec.date.equals(date)){
              return indexCount;
          }
          indexCount++;
      }
      return -1;
  }

    // public Record getRecordByDate(String date){
    //     ListIterator<Record> iter = r.listIterator();
    //     Record rec;
    //     while(iter.hasNext()){
    //         rec = iter.next();
    //         if(rec.date.equals(date)){
    //             return rec;
    //         }
    //     }
    //     return null;
    // }

    public static void deleteRecord(int index){
        r.remove(index);
    }
}