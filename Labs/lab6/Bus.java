// package Labs.lab6;

// public class Bus{
//     private int capacity;
//     private passengers = new Passenger[];
//     private int numPass = 0;

//     public Bus(int capacity) {
//         this.capacity = capacity;
//         this.passengers = new Passenger[capacity];
//     }
//     public Bus(){
//         this.capacity = 40;
//         this.passengers = new Passenger[40];
//     }

//     public void addPassenger(Passenger p){
//         this.passengers[numPass] = p;
//         numPass += 1;
//     }

//     public int numOfPassengers(){
//         this.numPass = 0;
//         for(int i = 0; i < passengers.length(); i++){
//             if(passengers[i] != null){
//                 this.numPass += 1;
//             }
//         }
//         return this.numPass;
//     }

//     //which sorting method:bubble sort, moves lower ones straight to the back on the first pass
// }