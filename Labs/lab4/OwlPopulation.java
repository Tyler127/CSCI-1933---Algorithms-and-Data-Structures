import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Arrays;

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
        
        ////make list size of other(LIST@), iterate over OTHER data and test against THIS
        ////add uniques to LIST@, then iterate over LIST@ and remove NULLS
        ////add length of LIST@ to length of THIS and make new OWL[] and add everything to it
        ////replace THIS data

        Owl[] listA = new Owl[other.data.length];
        int unique = 0;
        for(int i = 0; i <= other.data.length; i++){
            Owl currOwl = other.data[i];
            int dups = 0;
            for(int j = 0; j<= this.data.length; j++){
                if(currOwl.equals(this.data[j])){
                    dups += 1;
                }
            if(dups == 0){
                listA[i] = currOwl;
                unique += 1;
            }
        }
        }
        Owl[] listB = new Owl[unique];
        int counta = 0;
        for(int k = 0; k <= listA.length; k++){
            if(listA[k] != null){
                listB[counta] = listA[k];
                counta += 1;
            }
        }
        Owl[] listFinal = new Owl[listB.length + this.data.length];
        int counta2 = 0;
        for (int x = 0; x <= this.data.length; x ++){
            listFinal[counta2] = this.data[x];
            counta2 += 1;
        }
        for(int y = 0; y <= listB.length; y ++){
            listFinal[counta2] = listB[y];
            counta2 += 1;
        }
        this.data = listFinal;

        //TODO: a brief overview of what you can do to implement this method is given below:

        //1) determine (and store) the distinct owls in the other population.
       
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
            Owl[] ssu = new Owl[2];
            System.out.println(Arrays.toString(ssu));


            pop1.merge(pop2);
            System.out.println(pop1);
            System.out.println(pop1.popSize());
        }
        catch (FileNotFoundException f){
            System.out.println("File not found.");
        }
    }


}
