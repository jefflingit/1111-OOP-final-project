
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{
        // TODO Auto-generated method stub
        File reader =new File("test.txt");
        Scanner input=new Scanner(reader);
        String deptInput=input.nextLine().substring(3);
        Department myDept=deptSelector(deptInput);
        String index=input.nextLine();
        String[] indexList=index.split(" ");

        while(input.hasNextLine()){
            String line=input.nextLine();
            Scanner lineScan=new Scanner(line);
            String name=lineScan.next();
            double credits=lineScan.nextDouble();
            String category=lineScan.next();
            String subcategory=lineScan.next();
            if(subcategory.equals("none")){
                Course newCourse=new Course(name,credits,category);
                myDept.addCourse(newCourse);
            }else{
                Course newCourse=new Course(name,credits,category,subcategory);
                myDept.addCourse(newCourse);
            }


            lineScan.close();
        }

        myDept.summarize();
        input.close();
    }




    public static Department deptSelector(String dept){
        switch(dept){
            case "BA":
                Department ba=new BA();;
                return ba;
            case "IB":
                Department ib=new IB();
                return ib;
            case "ACCT":
                Department acct=new ACCT();
                return acct;
            default:
                Department myDept=new Department();
                return myDept;
        }
    }




}
