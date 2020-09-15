/* PACKAGE PATH */
package edu.csus.csc131.euc.model;

/* Local Imports */
import edu.csus.csc131.euc.model.data.*;

public class Model {
    // Instance Variables handling all the models
    Profile profile;
    Usage usage;
    Rates rates;
    int index;

    // Construtor for all Models
    public Model(int id){
        this.profile = new Profile(id);
        this.usage = new Usage();
        // Always true for summer for now
        this.rates = new Rates(true);
        this.index = 0;
    }

    // Setters
    public void setModelProfile(Profile p){ this.profile = p; }
    public void setModelUsage(Usage u){ this.usage = u; }
    public void setModelRates(Rates r){ this.rates = r; }
    public void setModelIndex(int i){ this.index = i; }

    // Getters
    public Profile getModelProfile(){ return this.profile; }
    public Usage getModelUsage(){ return this.usage; }
    public Rates getModelRates(){ return this.rates; }
    public int getModelIndex(){ return this.index; }

}