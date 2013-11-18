package org.bhakhrani.sarf.logic;
import java.util.ArrayList;


public class Verb 
{
	private Baab baab;
	private char فPosition; 
	private char عPosition;
	private char لPoistion;
	private ArrayList<Character> letters = new ArrayList<Character>();
	private String فWithHarakhMaadi;
	private String عWithHarakhMaadi;
	private String لWithHarakhMaadi;
	//private String فWithHarakhMudaari;
	//private String عWithHarakhMudaari;
	//private String لWithHarakhMudaari;
	private String definition;
	//private String verb;
	//private String maadiBase;
	//private String mudaariBase;
	
	public Verb(Baab baab, char فPosition, char عPosition, char لPoistion,
			String definition) 
	{
		this.baab = baab;
		this.فPosition = فPosition;
		this.عPosition = عPosition;
		this.لPoistion = لPoistion;
		this.definition = definition;
		addToBaab();
	}

	public Verb(Baab baab, String verb, String definition)
	{
		this.baab = baab;
//		consider using enumerated types
//		System.out.println("Verb string input is: " + verb);
		فPosition = verb.charAt(0);
		عPosition = verb.charAt(1);
		لPoistion = verb.charAt(2);
		letters.add(فPosition);
		letters.add(عPosition);
		letters.add(لPoistion);
		this.definition = definition;
		addToBaab();
	}
	
	public String maadiBase()
	{
		ArrayList<String> maadiAffixes = baab.getMaadiAffixes();
		String  maddiBase = maadiAffixes.get(0);
		int i =1;
		
		for(int j = 0; j<letters.size(); j++)
		{
//			System.out.println(letters.get(i));
//			System.out.println(maadiHarakat.get(i));
//			System.out.println("---");
			maddiBase += letters.get(j);
			if(i <= maadiAffixes.size()){
				maddiBase += maadiAffixes.get(i);
			}
			i++;
		}
		
//		System.out.println("end word");
		return maddiBase;
	}
	
	public String mudaariBase()
	{
		String mudaariBase = "";
		ArrayList<String> mudaariAffixes = baab.getMudaariAffixes();
		for(String s: mudaariAffixes){
			System.out.println(s);
		}
		
		mudaariBase = mudaariAffixes.get(0);
		int i =1;
		
		for(int j = 0; j<letters.size(); j++)
		{
			mudaariBase += letters.get(j);
			mudaariBase += mudaariAffixes.get(i);
			i++;
		}
		
		mudaariBase = mudaariBase.substring(1);
		return mudaariBase;
	}
	public void addToBaab()
	{
		baab.addWord(this);
	}
	
	public String toString()
	{
		String toString = "Baab: " + baab.getName() + "\n";
		toString += "Verb: ";
		for(int i=0; i<letters.size(); i++)
		{
			toString += letters.get(i) + " ";
		}
		toString += "\nDefinition: " + definition;
		return toString;
		
	}

	public Baab getBaab() {
		return baab;
	}

	public char getفPosition() {
		return فPosition;
	}

	public char getعPosition() {
		return عPosition;
	}

	public char getلPoistion() {
		return لPoistion;
	}

	public String getDefinition() {
		return definition;
	}

	public String getVerb() {
		String maadi = this.maadiBase();
		String mudaari = Mudaari.conjugate(1, this);
		String toReturn = maadi + " " + mudaari;
		return toReturn;
	}

	public String getفWithHarakh() {
		return فWithHarakhMaadi;
	}

	public void setفWithHarakh(String فWithHarakh) {
		this.فWithHarakhMaadi = فWithHarakh;
	}

	public String getعWithHarakh() {
		return عWithHarakhMaadi;
	}

	public void setعWithHarakh(String عWithHarakh) {
		this.عWithHarakhMaadi = عWithHarakh;
	}

	public String getلWithHarakh() {
		return لWithHarakhMaadi;
	}

	public void setلWithHarakh(String لWithHarakh) {
		this.لWithHarakhMaadi = لWithHarakh;
	}
	
	
	
	
	
}
