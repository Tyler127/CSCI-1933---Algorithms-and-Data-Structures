package lab4;

public class Owl{
    private String name;
    private int age;
    private double weight;

    public Owl(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public boolean equals(Owl other){
        if(other.getName().equals(this.name)&& other.getAge() == this.age && other.getWeight() == this.weight){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args){
        // Owl sus1 = new Owl("jeph", 20, 1000);
        // Owl sus2 = new Owl("jeff", 20, 500);
        // Owl sus3 = new Owl("jeph", 20, 1000);
        // System.out.println(sus1.equals(sus2));
        // System.out.println(sus1.equals(sus3));

        }
   
}