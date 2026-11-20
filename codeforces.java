import java.util.*;
public class codeforces{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        for(int k=0;k<t;k++){
            int n=sc.nextInt();
            int a[]=new int[n];
            for(int i=0;i<n;i++){
                a[i]=sc.nextInt();
            }
            int start=a[n-1]+a[n-1];
            for(int j=n-2;j>=0;j--){
              if(a[j]>start){
                  int temp=a[j]-start;
                  start=start+2*temp;
              }
              else if(a[j]<start){
                  int temp=start-a[j];
                  start=start-2*temp;
              }
            }
            if(start==0)
                System.out.println(0);
            else if(start>0){
                if(start%2!=0)
                   System.out.println("not available");
                else{
                    System.out.println(start/2);
                }
            }
            else{
                if(start%2!=0)
                    System.out.println("not available");
                else{
                    System.out.println(start/2);
                }
            }
        }
    }
}

class Piles{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in)
                int a=sc.nextInt();
        int b=sc.nextInt();
        int c=sc.nextInt();
        if(a==b&&b==c){
            System.out.println("NO");
        }
        else if(a+1==b&&b+1==c){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }
}
class A2271A