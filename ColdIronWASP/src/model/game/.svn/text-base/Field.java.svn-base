package model.game;

public class Field {

   public void removeYou()
   {
   }
	private String id;
	private Field[] neighbor = new Field[4];

	public Field(int neighborId, Field neighbor) {
		this.neighbor[neighborId] = neighbor;
	}

	public Field() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Field[] getNeighbor() {
		return neighbor;
	}

	public void setNeighbor(Field[] neighbor) {
		this.neighbor = neighbor;
	}
	
	public void setNeighbor(int neighborId, Field neighbor) {
		this.neighbor[neighborId] = neighbor;
	}
	
	public void setTop(Field f) {
		this.neighbor[0] = f;
	}

	public void setRight(Field f) {
		this.neighbor[1] = f;
	}
	
	public void setBottom(Field f) {
		this.neighbor[2] = f;
	}
	
	public void setLeft(Field f) {
		this.neighbor[3] = f;
	}

}
