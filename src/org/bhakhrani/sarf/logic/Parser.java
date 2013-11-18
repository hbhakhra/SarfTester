package org.bhakhrani.sarf.logic;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.res.AssetManager;


public class Parser
{
	private String fileName;
	private Scanner scanner;
	private ArrayList<Baab> allBaabs = new ArrayList<Baab>();
	private ArrayList<Verb> listOfVerbs = new ArrayList<Verb>();
	private FileReader inputReader;
	private String baabCode = "Baab";
	private Baab crntBaab;
	private int crntBaabNumber = 1;
	private boolean scanningBaab = false;
	private String allText = "";
//	private Baab blankBaab = new Baab(null, 0);
	
	
	public Parser(AssetManager assetManager, String inputFileName)
	{
//		allBaabs.add(blankBaab);
		try {
			fileName = inputFileName;
			InputStream is = assetManager.open(fileName);
			scanner = new Scanner(is);
			Parse(scanner);	
			} 
		catch (FileNotFoundException e) 
			{
				System.out.println("File Not Found!");
			}
		catch (IOException e) {
			System.out.println("IO Error!");
		}
	}
	
	public Parser(InputStream stream) {
		try {
			scanner = new Scanner(stream);
			Parse(scanner);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void Parse(Scanner fileScanner)
	{
		boolean numFound = false;
		int number = 0;
		Scanner scanner = fileScanner;
		String crntLine = scanner.nextLine();
//		crntLine
//		String crntLine = scanner.nextLine();
		
//		for(int i=0; i<crntLine.length(); i++)
//		{
//			System.out.print("Char at " + i + "is " + crntLine.charAt(i) + ".");
//		}
		
		//block comments here
		
		while (scanner.hasNext()) 
		{
//			String
			crntLine = scanner.nextLine();
			allText += "\n" + crntLine;
//			System.out.println(crntLine);
//			String crntLineM = crntLine.trim();
//			System.out.println(crntLineM.length());
			if (!crntLine.isEmpty()) 
			{
				if (baabCode.equals(crntLine)) 
				{
//					System.out.println("scanning baab name");
					ScanBaabName(scanner);
//					Parse(scanner);
				} 
				else if (crntLine.charAt(0) == '-') 
				{
//					System.out.println("Finished Baab");
				} 
				else
				{
//					System.out.println("Scanning verb");
					Verb crntVerb = ScanVerb(crntLine);
					listOfVerbs.add(crntVerb);					
				}
			}
		}
		
		/*		System.out.println();
		Scanner lineScanner = new Scanner(crntLine);
		String crntWord = lineScanner.next();
		crntWord = lineScanner.next();
		System.out.println("Crnt Word first Letter: \" " + crntWord.charAt(0) + "\"");
		crntWord = crntWord.trim();
		System.out.println("Crnt Word first Letter: \" " + crntWord.charAt(0) + "\"");
		if(lineScanner.hasNextInt())
		{
			number = lineScanner.nextInt();
			System.out.println(number);
			numFound = true;
		}
		System.out.println();
		System.out.println(crntLine);
		System.out.println(baabCode);
		System.out.println(baabCode.equalsIgnoreCase(crntWord));
		System.out.println(baabCode.equals(crntWord));*/
	}

	private Verb ScanVerb(String crntLine) 
	{
		Scanner lineScanner = new Scanner(crntLine);
		String verb = lineScanner.next();
		lineScanner.next();
		String definition = lineScanner.nextLine();
		Verb crntVerb = new Verb(crntBaab, verb, definition);
//		System.out.println("----------");
//		System.out.println(verb.toString());
		return crntVerb;
		}

	private void ScanBaabName(Scanner text_Scanner) 
	{
		Scanner scanner = text_Scanner;
		String baabName = scanner.nextLine();
//		System.out.println(baabName);
//		System.out.println();
//		for(int i=0; i<baabName.length(); i++)
//		{
//			System.out.println("Char at " + i + "is " + baabName.charAt(i) + "  *   ");
//		}
//		System.out.println();
		crntBaab = new Baab(baabName);
		crntBaabNumber++;
//		System.out.println(crntBaabNumber-1 + " " + crntBaab.getName());
		allBaabs.add(crntBaab);
		//baab made, now need to get individual harakaat
//		baabName.charAt(0);
//		baabName.charAt(1);
		
	}

	public ArrayList<Baab> getAllBaabs() {
		return allBaabs;
	}

	public ArrayList<Verb> getListOfVerbs() {
		return listOfVerbs;
	}

	public String getAllText() {
		return allText;
	}

}
