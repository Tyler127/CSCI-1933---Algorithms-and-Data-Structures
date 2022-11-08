public class PostFix {
    static double evaluate(String[] expression){
        Stack stamck = new Stack();

        for(int i = 0; i < expression.length; i++){
            try{
                double x = Double.parseDouble(expression[i]);
                stamck.push(x);
            }catch (NumberFormatException nfe){
                num1 = stamck.pop();
                num2 = stamck.pop();

            }
        }
    }
}
