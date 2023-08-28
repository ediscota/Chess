package it.univaq.disim.datamodel;

import java.io.Serializable;

public enum Color implements Serializable{
	BIANCO, NERO;

   public Color oppositeColor (){
 return this == BIANCO ? NERO : BIANCO;
   }

}
