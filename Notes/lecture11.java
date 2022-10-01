package Notes;

public class lecture11 {

//     Copying vs Cloning:

// What is a memory leak?
// 	• When unused stuff does not return memory to be reused
// 	• When you don’t allocate space manually, it creates more opportunities for memory to be wasted

// Copy:
// 	• Supply 2 objects one that is the original and one that will be the copy

// Cloning:
// 	• Supply only the original. The second is manufactured by the clone method 
// 	• A non static cloning method needs no arguments it just makes itself again 


    // create the static method
    Public static Complex4[] cloneComplexArray( complex4[] c) {

	// create a temporary array
	Complex4[] temp = new Complex4[c.length];
	
	// copy over elements over the array length
	For (int I = 0; I < c; i++) {
		Temp[i] = c[i];
	}
	
	Return temp;
    }


    Public static void main(String[] args) {

        // creates an empty array
        // after array has stuff, points to it
        Complex4[] numbers;
         
         // finds space on heap for the array
        // creates space for 10 objects
        Numbers = new Complex4[10];
        
        // We don’t actually have an array of the objects until a loop like this has run that actually creates the objects for every space in the array 
        // if this is not done there will be a null pointer exception error
        For ( int I = 0; I < numbers.length; i++ ) {
            Numbers[i] = new Complex4();
        }
        
        // Say we want to clone this array of complex numbers
        Complex4[] cloneNumbers = cloneComplexArray(numbers);
        
    }

//     When you create an array of objects it starts with nothing in it.

// This is a shallow clone creating method.
// Points to the same objects in memory as the first array that was cloned.

// To create a deep clone you have to create a new object for each object in the original array.
// To do this you have to call a clone method for each object inside of the array to create new objects in memory.

    
}
