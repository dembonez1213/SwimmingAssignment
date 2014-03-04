package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import People.Swimmer;
import Utils.Helper;

public class Main {

	static Swimmer[] swimmers;
	private static Scanner scanner = new Scanner(System.in);
	private static String fullText = "";
	
	public static void main(String[] args)
	{
		readFlatFile("C:/Users/Daniel/Desktop/swim_Data.txt",",");
		showMenu();
		checkInput();
	}
	
	public static void readFlatFile(String fileURL, String delimiter)
	{
		try {
			Scanner scan = new Scanner(new FileReader(fileURL));
			int numLines = -1, numCategories = 0;
			String fileData = "";		
			while(scan.hasNextLine() == true)
			{
				String line = scan.nextLine();
				fileData += line;
				fullText += line + "\n";
				if(numLines == -1)
				{
					numCategories = fileData.split(delimiter).length;
				}
				numLines++;
			}
			fileData = fileData.replaceAll("\\s", "");
			String[] swimData = fileData.split(delimiter);
			swimmers = new Swimmer[numLines];
			for(int x = 0; x < swimmers.length; x++)
			{
				swimmers[x] = new Swimmer();
				swimmers[x].setID(swimData[numCategories*(x+1)]);
				swimmers[x].setFirstName(swimData[(numCategories*(x+1))+1]);
				swimmers[x].setLastName(swimData[(numCategories*(x+1))+2]);
				swimmers[x].setLastEvent(swimData[(numCategories*(x+1))+3]);
				swimmers[x].setLastTime(Integer.parseInt(swimData[(numCategories*(x+1))+4]));
				swimmers[x].setAge(Byte.parseByte(swimData[(numCategories*(x+1))+5]));
				swimmers[x].setClub(swimData[(numCategories*(x+1))+6]);
				swimmers[x].setPlace(Byte.parseByte(swimData[(numCategories*(x+1))+7]));
				swimmers[x].setDisqualified(Boolean.parseBoolean(swimData[(numCategories*(x+1))+8]));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Exception:\t" + e.getMessage());
		}
	}
	
	
	public static void showMenu()
	{
		System.out.println("Welcome to Swim Software!\n"
				+ "Commands: (Type 'quit' or 'exit' to stop)\n"
				+ "(a)\tShow all swimmers\n"
				+ "(b)\tSearch by event\n"
				+ "(c)\tSearch by ID\n"
				+ "(d)\tAdd a swimmer\n");
	}
	
	public static void checkInput()
	{
		String input = getUserInput();
		while(checkQuit(input) == false)
		{
			if(input.equalsIgnoreCase("a") || input.equalsIgnoreCase("(a)"))
			{
				System.out.println(fullText);
			}
			else if(input.equalsIgnoreCase("b") || input.equalsIgnoreCase("(b)"))
			{
				System.out.println("What is the event you wish to search by?(no spaces, i.e \"50mbackstroke\")");
				System.out.println(Helper.searchByEvent(swimmers, scanner.nextLine()));
			}
			else if(input.equalsIgnoreCase("c") || input.equalsIgnoreCase("(c)"))
			{
				System.out.println("What is the ID you wish to search by?(ID's are 9 characters long)");
				System.out.println(Helper.searchByID(swimmers,scanner.nextLine()));
			}
			else
			{
				System.out.println("Invalid option!");
			}
			showMenu();
			input = getUserInput();
		}
	}
	
	private static String getUserInput()
	{
		return scanner.nextLine();
	}
	
	private static boolean checkQuit(String checkString)
	{
		if(checkString.equalsIgnoreCase("exit") || checkString.equalsIgnoreCase("quit"))
		{
			System.exit(0);
			return true;
		}
		else
		{
			return false;
		}
	}
}
