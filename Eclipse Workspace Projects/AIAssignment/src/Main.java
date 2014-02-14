import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
	
	private String statementInput;
	private String outputFile;
	private String inputFile;


	public String getStatementInput() {
		return statementInput;
	}

	public void setStatementInput(String statementInput) {
		this.statementInput = statementInput;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		//System.out.println("set input file");
		this.inputFile = inputFile;
	}

	private String OR = "OR";
	private String AND = "AND";
	private String DIMP = "<=>";
	private String IMP = "=>";
	private String NOT = "NOT";

	Stack<String> operandStack = new Stack<String>();
	Stack<String> tempStack = new Stack<String>();

	
	public static void main(String[] args) {
		//fun("(AND (=> (OR a e) c) (AND a (AND b (NOT c))))");
		Main resolution = new Main();
		//System.out.println("in main");
		String program1 = args[0];
		//System.out.println(program1);

		if(args[1].equals("-task1"))

		{
			//System.out.println("In task1");	
			resolution.setInputFile(args[2]);
			//resolution.setOutputFile(args[3]);
		}
		else if(args[1].equals("-task2"))
		{
			resolution.setInputFile(args[2]);
			resolution.setOutputFile(args[3]);
		}
		else if(args[1].equals("-task3"))
		{
			resolution.setInputFile(args[2]);
			resolution.setStatementInput(args[3]);
			resolution.setOutputFile(args[4]);
		}

		
		resolution.createKB();
		//System.out.println("called create");
		
	}
	
	public void createKB()
	{
		try{
			File inputFile=new File(this.inputFile);
			//System.out.println("hello");
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String sampleLine ="((OR (AND pass (NOT reject)) (AND (NOT pass) reject)))";
			Pattern patter = Pattern.compile("AND|<=>|=>");
			Matcher match = patter.matcher(sampleLine);
			if(match.find()){	
				String result=fun2(sampleLine);
				System.out.println(result+"\n"); // try moving NOt out of the pattern here, and spread it.. 
			}else
				System.out.println(sampleLine);
			
			String strLine;
			
			while ((strLine = br.readLine()) != null)   {
				StringBuilder st= new StringBuilder();
				
				}
			br.close();
		}catch(Exception e){
			System.out.println("error in file parsing");
			e.printStackTrace();
		}
	}	
	public static String fun(String e){
		if(e.contains(" ")){
		String f=e.substring(0, e.indexOf(' '));
		String res=e;
		int counter = 0;
		for( int i=0; i<e.length(); i++ ) {
		    if( e.charAt(i) == ' ' ) {
		        counter++;
		    } 
		}
		if(counter==2){
			
			if(f.contains("<=>")){
				res=doubleimplies(e);
			}
			else if(f.contains("=>")){
				res=implies(e);
			}
			
		}
		if(counter==1 && f.contains("NOT")){
			res="NOT"+e;
		}
		
		if(counter==6 && f.contains("OR")){
			res=spread(e);
		}
		
		
		
		
		return res;
		}
		return e;
	}
	
// Evaluates =>
	public static String implies(String e){
			String operator= e.substring(e.indexOf('(')+1, e.indexOf(' '));
			String temp1=e.substring(operator.length()+2);
			String exp1=temp1.substring(0, temp1.indexOf(' '));
			temp1=e.substring(operator.length()+2+exp1.length()+1);
			String exp2=temp1.substring(0, temp1.indexOf(')'));
			String result="("+"OR "+"("+"NOT "+exp1+") "+exp2+")";
		//	System.out.println(result);
			return result;
		
	}
	// Evaluates <=>  by calling =>
	public static String doubleimplies(String e){
		String operator= e.substring(e.indexOf('(')+1, e.indexOf(' '));
		String temp1=e.substring(operator.length()+2);
		String exp1=temp1.substring(0, temp1.indexOf(' '));
		temp1=e.substring(operator.length()+2+exp1.length()+1);
		String exp2=temp1.substring(0, temp1.indexOf(')'));
		String result=implies("("+"=> "+exp1+" "+exp2+")")+" \n "+implies("("+"=> "+exp2+" "+exp1+")");
		//System.out.println(result);
		return result;
	
}
	//evaluates NOT
	public static String not(String e)
	{
		String operator= e.substring(e.indexOf('(')+1, e.indexOf(' '));
		String temp1=e.substring(operator.length()+2);
		String exp1=temp1.substring(0, temp1.length()-1);
		//temp1=e.substring(operator.length()+2+exp1.length()+1);
		//String exp2=temp1.substring(0, temp1.indexOf(')'));
		String result = "( NOT "+exp1+")";
		
		return result;
		
	}
	
	//Evaluates and over Or
	public static String spread(String e){
		String operator= e.substring(e.indexOf('(')+1, e.indexOf(' '));
		String temp1=e.substring(operator.length()+2);
		String exp1=temp1.substring(0, temp1.indexOf(' '));
		temp1=e.substring(operator.length()+2+exp1.length()+1); // +1
		String exp2=temp1.substring(0, temp1.indexOf(')')+1);
		
		String operator1= exp1.substring(exp1.indexOf('(')+1, exp1.indexOf(' '));
		String temp11=exp1.substring(operator1.length()+2);
		String exp11=temp11.substring(0, temp11.indexOf(' '));
		temp11=exp1.substring(operator1.length()+2+exp11.length()+1);
		String exp21=temp11.substring(0, temp11.indexOf(')'));
		
		String operator2= exp2.substring(exp2.indexOf('(')+1, exp2.indexOf(' '));
		String temp12=exp2.substring(operator2.length()+2);
		String exp12=temp12.substring(0, temp12.indexOf(' '));
		temp12=exp2.substring(operator2.length()+2+exp12.length()+1);
		String exp22=temp12.substring(0, temp12.indexOf(')'));
		
		String result="("+exp11+" OR "+exp12+")"+" \n "+"("+exp21+" OR "+exp12+")";
		result+=" \n ";
		result+="("+exp11+" OR "+exp22+")"+" \n "+"("+exp21+" OR "+exp22+")";
		//System.out.println(result);
		return result;
	
}
	// Function with recursion 
	public static String fun2(String e){
		String expr1=null;
		String expr2=null;
		String f=e.substring(0,e.indexOf(' '));
		String temp2=e.substring(f.length()+1,e.length()-1);
		
		Pattern p = Pattern.compile("(.*\\))[\\s](\\(.*)$|(.*\\))[\\s](.*)$|(.*)[\\s](\\(.*)$|(.*)[\\s](.*)$");
		Matcher m = p.matcher(temp2);
		if(m.find()){
			if(m.group(1)!=null){
		expr1=m.group(1);
		expr2=m.group(2);
			}
			else if(m.group(3)!=null){
				expr1=m.group(3);
				expr2=m.group(4);
					}
			else if(m.group(5)!=null){
				expr1=m.group(5);
				expr2=m.group(6);
					}
			else if(m.group(7)!=null){
				expr1=m.group(7);
				expr2=m.group(8);
					}
		}
		String res=null;
		if(f.contains("<=>")){
		res=doubleimplies(f+" X Y)");
		}
		else if(f.contains("=>")){
			res=implies(f+" X Y)");
		}
		else if(f.contains("NOT")){
			res=not(f+" X)");
			
		}
		else if(f.contains("OR")){
			
			res=spread(f+" X Y)");
		}
		
		else{
			res=f+" X Y)";
		}
		int counter=0;
		
		if(expr1 != null)
			for( int i=0; i<expr1.length(); i++ ) {
		    if( expr1.charAt(i) == ' ' ) {
		        counter++;
		    } 
		}
		
		
		int counter1=0;
		
		if(expr2 != null)
		for( int i=0; i<expr2.length(); i++ ) {
		    if( expr2.charAt(i) == ' ' ) {
		        counter1++;
		    } 
		}
		
		
		String X=null;
		if(counter==2||(counter==6&&expr1.substring(0,expr1.indexOf(' ')).contains("OR"))) // distribute or over and
		X=fun(expr1);
		else if(counter<2 && (expr1!=null))
		X=fun(expr1);
		else
		X=fun2(expr1);
		String Y=null;
		if(counter1==2||(counter1==6&&expr2.substring(0,expr2.indexOf(' ')).contains("OR")))
		Y=fun(expr2);
		else if(counter1<2)
		Y=fun(expr2);
		else
		Y=fun2(expr2);	
		res=res.replace("X",X);
		res=res.replace("Y",Y);
		//System.out.println(res);
		return res;
	}
}
