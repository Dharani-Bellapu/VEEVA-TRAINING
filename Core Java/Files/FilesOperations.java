import java.io.*;
import java.util.*;
class FilesOperations{
    public static void main(String args[]){
        Scanner sc =new Scanner(System.in);
        System.out.println("enter a file name :");
        String filename=sc.next();
        File f1=new File(filename);
        if(f1.exists()){
            System.out.println("yes, file exists");
            if(f1.isDirectory()){
                System.out.println("It" +
                        " is a directory");
            }
            else if(f1.isFile()){
                System.out.println("it is a text File");
            }
            else{
                System.out.println("not both");
            }
            if(f1.canRead()&&f1.canWrite()){
                System.out.println("for bot read and write");
            }
            else if(f1.canRead()){
                System.out.println("for read only");
            }
            else{
                System.out.println("write only");
            }
        }
        else{
            System.out.println("File doesn't exit");
        }

    }
}
