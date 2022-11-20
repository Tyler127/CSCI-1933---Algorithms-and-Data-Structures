package lab9;

public class PostFix {
    static double evaluate(String[] expression){
        Stack<Double> stack = new Stack<Double>(expression.length);//change size to arbitrary(such as 4 for example in main) to cause stack exception error

        for(int i = 0; i < expression.length; i++){
            //System.out.println("token: " + expression[i]);
            String token = expression[i];
            try{

                double x = Double.parseDouble(token);
                //System.out.println("is number, attempting push");

                try{
                    stack.push(x);
                    //System.out.println("push successful");
                } catch(StackException e){
                    //System.out.println("push failed");
                }

            }catch (Exception p){

                //System.out.println("exception p");
                try{

                    //System.out.println("in try block");
                    double num1 = stack.pop();
                    //System.out.println("num1: " + num1);
                    double num2 = stack.pop();
                    //System.out.println("num2: " + num2);

                    switch(token){
                        case "+":stack.push(num1 + num2);
                            //System.out.println("switch +");
                            break;
                        case "-":stack.push(num1 - num2);
                            //System.out.println("switch -");
                            break;
                        case "*":stack.push(num1 * num2);
                            //System.out.println("switch *");
                            break;
                        case "/":if(num2 != 0.0){
                            stack.push(num1 / num2);//if 0 were to appear, discard zero and token, push num1 back, print message
                            System.out.println("switch /");
                            }else{
                                stack.push(num1);
                                System.out.println("0 Numerator detected, 0 and / token discarded");
                            }
                            break;
                    }

                } catch(StackException e){System.out.println("Stack Exception: " + e.getSize());}

            }
        }
        return stack.getTopData();
    }

    public static void main(String[] args){
       String[] testy = new String[]{"4", "5", "*", "3", "+"};
       double evalNum = evaluate(testy);
       System.out.println("should be 23.0: " + evalNum);
       String[] testy2 = new String[]{"4", "5", "*", "-3", "+"};
       double evalNum2 = evaluate(testy2);
       System.out.println("should be 17.0: " + evalNum2);
       String[] testy3 = new String[]{"4", "4", "-", "200", "/"};
       double evalNum3 = evaluate(testy3);
       System.out.println("should be 200.0: " + evalNum3);
    }
}
