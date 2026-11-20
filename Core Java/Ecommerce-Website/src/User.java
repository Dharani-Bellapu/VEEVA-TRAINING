public class User {
   private String name;
   private String email;
   private String address;
   private String phone;
   private int user_id;
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName(){
       return name;
   }
   public String getEmail(){
       return email;
   }
   public String getAddress(){
       return address;
   }
   public String getPhone(){
       return phone;
   }
   public void setName(String name){
       this.name=name;
   }
   public void setEmail(String email){
       this.email=email;
   }
   public void setAddress(String address){
       this.address=address;
   }
   public void setPhone(String phone){
       this.phone=phone;
   }
   public int getUser_id(){
       return user_id;
   }
   public void setUser_id(int user_id){
       this.user_id=user_id;
   }

}
