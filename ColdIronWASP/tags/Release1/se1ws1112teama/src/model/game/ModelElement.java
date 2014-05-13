package model.game;


public class ModelElement{
   private String id;
   
   public void setId(String value) {
      this.id = value;
   }
   
   public String getId() {
      return this.id;
   }
   /**
    * <pre>
    *           0..1     0..1
    * ModelElement ------------------------- ModelElementTable
    *           modelElement        &lt;       modelElementTable
    * </pre>
    */
   
   private ModelElementTable modelElementTable;
   
   public boolean setModelElementTable (String partnerKey, ModelElementTable value)		
   {
      boolean changed = false;
   
      if (this.modelElementTable != value)
      {
   ModelElementTable oldValue = this.modelElementTable;
         if (this.modelElementTable != null)
         {
            this.modelElementTable = null;
            oldValue.removeFromModelElement(this);
         }
         this.modelElementTable = value;
   
         if (value != null)
         {
            value.addToModelElement(partnerKey, this);
         }
         changed = true;
      }
      return changed;
   }
   
   public ModelElementTable getModelElementTable ()	
   {
      return this.modelElementTable;
   }
   /**
    * <pre>
    *           0..n     0..1
    * ModelElement ------------------------- CompModelElement
    *           kids        &lt;       parent
    * </pre>
    */
   
   private CompModelElement parent;
   
   public boolean setParent (CompModelElement value)		
   {
      boolean changed = false;
   
      if (this.parent != value)
      {
   CompModelElement oldValue = this.parent;
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.removeFromKids(this);
         }
         this.parent = value;
   
         if (value != null)
         {
            value.addToKids(this);
         }
         changed = true;
      }
      return changed;
   }
   
   public CompModelElement getParent ()	
   {
      return this.parent;
   }
   
   public void removeYou()
   {
         this.setModelElementTable (null, null);
         this.setParent (null);
      }
   
}
