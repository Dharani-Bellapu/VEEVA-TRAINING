import java.util.*;
import java.util.stream.*;
public class comp1{
   public static void main(String args[]){
       Scanner sc=new Scanner(System.in);
       ArrayList<Student> list = new ArrayList<>();
       while(true) {
           System.out.println("1.add student\n2.get student with top mars in each class\n3.get student with low marks from each class:")
           int choice=sc.nextInt();
           if(choice==1){
               int id=sc.nextInt();
               int marks=sc.nextInt();
               String clas=sc.next();
               Student s=new Student(id,marks,clas);
               list.add(s);
           }
           if(choice==2){

           }

           list.stream().map(x -> x < 50 ? x + 50 : x).sorted().distinct().forEach(System.out::println);
       }
   }
}
class Student{
    int id;
    int marks;
    String clas;
    public Student(int id,int marks,String clas){
        this.id=id;
        this.marks=marks;
        this.clas=clas;
    }
}