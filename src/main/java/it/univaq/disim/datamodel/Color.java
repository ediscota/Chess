package it.univaq.disim.datamodel;

public enum Color {
	BIANCO, NERO;

   public Color oppositeColor (){
 return this == BIANCO ? NERO : BIANCO;
   }

}
