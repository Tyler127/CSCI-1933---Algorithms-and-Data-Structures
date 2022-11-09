public class PostFix {
    static double evaluate(String[] expression){
        Stack<Double> stack = new Stack<Double>(expression.length);//change size to arbitrary(such as 5) to cause stack exception error

        for(int i = 0; i < expression.length; i++){
            System.out.println("token: " + expression[i]);
            String token = expression[i];
            try{

                double x = Double.parseDouble(token);
                System.out.println("is number, attempting push");

                try{
                    stack.push(x);
                    System.out.println("push successful");
                } catch(StackException e){
                    System.out.println("push failed");
                }

            }catch (Exception p){

                System.out.println("exception p");
                try{

                    System.out.println("in try block");
                    double num1 = stack.pop();
                    System.out.println("num1: " + num1);
                    double num2 = stack.pop();
                    System.out.println("num2: " + num2);

                    switch(token){
                        case "+":stack.push(num1 + num2);
                            System.out.println("switch +");
                            break;
                        case "-":stack.push(num1 - num2);
                            System.out.println("switch -");
                            break;
                        case "*":stack.push(num1 * num2);
                            System.out.println("switch *");
                            break;
                        case "/":stack.push(num1 / num2);
                            System.out.println("switch /");
                            break;
                    }

                } catch(StackException e){System.out.println("Stack Exception: " + e.getSize());}

            }
        }
        return stack.getTopData();
    }

    public static void main(String[] args){
       String[] testy = new String[]{"4", "5", "*", "-3", "+"};
       double evalNum = evaluate(testy);
       System.out.println("should be 17 " + evalNum);
    }
}
