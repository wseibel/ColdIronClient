package model.game;


public class Peon extends Unit {
/**
    * <pre>
    *           0..n     0..1
    * Peon ------------------------- Resource
    *           peon        &gt;       collecting
    * </pre>
    */
   
   public static final String PROPERTY_COLLECTING = "collecting";
   private Resource collecting;
   
   public boolean setCollecting (Resource value)		
   {
      boolean changed = false;
   
      if (this.collecting != value)
      {
   Resource oldValue = this.collecting;
         if (this.collecting != null)
         {
            this.collecting = null;
            oldValue.removeFromPeon(this);
         }
         this.collecting = value;
   
         if (value != null)
         {
            value.addToPeon(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_COLLECTING, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Resource getCollecting ()	
   {
      return this.collecting;
   }
/**
    * <pre>
    *           0..n     0..1
    * Peon ------------------------- Building
    *           peon        &gt;       workingOn
    * </pre>
    */
   
   public static final String PROPERTY_WORKING_ON = "workingOn";
   private Building workingOn;
   
   public boolean setWorkingOn (Building value)		
   {
      boolean changed = false;
   
      if (this.workingOn != value)
      {
   Building oldValue = this.workingOn;
         if (this.workingOn != null)
         {
            this.workingOn = null;
            oldValue.removeFromPeon(this);
         }
         this.workingOn = value;
   
         if (value != null)
         {
            value.addToPeon(this);
         }
            getPropertyChangeSupport().firePropertyChange(PROPERTY_WORKING_ON, oldValue, value);
         changed = true;
      }
      return changed;
   }
   
   public Building getWorkingOn ()	
   {
      return this.workingOn;
   }
   
   public void removeYou()
   {
   this.setCollecting (null);
      this.setWorkingOn (null);
         super.removeYou();
   }
      
   }
