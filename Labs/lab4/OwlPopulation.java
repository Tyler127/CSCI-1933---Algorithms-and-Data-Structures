import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

public class OwlPopulation {
    private String fileName;
    private Owl[] data;


    public int populateData() throws FileNotFoundException {
        File f = new File(fileName);
        Scanner scanner = new Scanner(f);

        int numLines = 0;
        while(scanner.hasNextLine()){
            numLines++;
            String s = scanner.nextLine();
        }
        scanner.close();

        data = new Owl[numLines];   //data is is allocated the exact amount of space it needs
        scanner = new Scanner(f);
        int counta = 0;

        //TODO: Populate the data with owls constructed from the lines of the file given
        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            String[] stuff = s.split(",");
            String owlName = stuff[0];
            int age = Integer.parseInt(stuff[1]);
            double weight = Double.parseDouble(stuff[2]);
            Owl toAdd = new Owl(owlName, age, weight);
            data[counta] = toAdd;
            counta ++;
        }
        scanner.close();
        return data.length;
    }

    public OwlPopulation(String fileName) throws FileNotFoundException {
        //TODO: Populate the class variables in OwlPopulation
        this.fileName = fileName;
        populateData();
    }

    public double averageAge(){
        double ageTotal = 0;
        for(int i = 0; i < data.length; i++){
            ageTotal += data[i].getAge();
        }
        return ageTotal / data.length;
    }

    public Owl getYoungest(){
        if(data.length == 0){
            return null;
        }
        Owl currYoung = data[0];
        for(int i = 1; i < data.length; i++){
            if(data[i].getAge() < currYoung.getAge()){
                currYoung = data[i];
            }
        }

        return currYoung;
    }

    public Owl getHeaviest(){
        if(data.length == 0){
            return null;
        }
        Owl currChungus = data[0];
        for(int i = 1; i < data.length; i++){
            if(data[i].getWeight() > currChungus.getWeight()){
                currChungus = data[i];
            }
        }

        return currChungus;
    }

    public String toString(){
        String youngestString = "The youngest owl is " + this.getYoungest().getName() + ", which is " + this.getYoungest().getAge() + " years old. \n";
        String heaviestString = "The heaviest owl is " + this.getHeaviest().getName() + ", which weighs " + this.getHeaviest().getWeight() + "pounds. \n";
        String popAvgString = "The average age of the population is " + this.averageAge();

        return youngestString + heaviestString + popAvgString;
    }

    public boolean containsOwl(Owl other){
        //TODO: method you can implement as a helper function to your merge method
        return false;
    }
	
    public void merge(OwlPopulation other){
        //TODO: a brief overview of what you can do to implement this method is given below:

        //1) determine (and store) the distinct owls in the other population.
        // int uniques = 0;
        // for (int i = 0; i < this.data.length; i++){
        //     int dups = 0;
        //     for (int j = i + 1; j < other.data.length; j++){
        //         dups = dups + 1;
        //     }
        //     if (dups == 0){
        //         uniques += 1;
        //     }
        // }

        int length1 = this.data.length;
        int length2 = other.data.length;
        Owl[] merged = new Owl[length1 + length2];
        for(int i = 0; i < length1; i ++){
            merged[i] = this.data[i];
        }
        for(int i = 0; i < length2; i ++){
            merged[i + length1] = other.data[i];
        }

        Owl[] noDups = new Owl[length1 + length2];
        int uniques = 0;
        for(int i = 0; i < (length1 + length2)-1; i ++){
            if(merged[i] != merged[i + 1]){
                noDups[uniques++] = merged[i];
            }
        }
        this.data = noDups;
        //2) make a new data array to hold the correct number of owls for the merged population
        //3) copy over the distinct owls from each population to the data array


        //4) set the new data array to "this" data (where is the merged population? what happens to the original populations?)
    }

    public int popSize(){
        return data.length;
    }
	
    public static void main(String[] args) {
        try {

            //The following should run when you are complete. Feel free to comment out as you see fit while you work.
            OwlPopulation pop1 = new OwlPopulation("owlPopulation1.csv");
            //System.out.println(pop1);
            //System.out.println(pop1.popSize());
            // System.out.println("pop1 avg. age: " + pop1.averageAge());
            // System.out.println("pop1 youngest: " + pop1.getYoungest().getAge());
            // System.out.println("pop1 Heaviest: " + pop1.getHeaviest().getWeight();
            //System.out.println(pop1.toString());

            OwlPopulation pop2 = new OwlPopulation("owlPopulation2.csv");
            //System.out.println(pop2);
            //System.out.println(pop2.popSize());
            // System.out.println("pop2 avg. age: " + pop2.averageAge());
            //System.out.println(pop2.toString());


            pop1.merge(pop2);
            //System.out.println(pop1);
            System.out.println(pop1.popSize());
        }
        catch (FileNotFoundException f){
            System.out.println("File not found.");
        }
    }


}
