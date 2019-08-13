/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonacci;

/**
 *
 * @author franc
 */
public class Fibonacci {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(fact(22));
    }
    
    public static long fib(int x){
        if(x == 1 || x == 2){
            return 1;
        }
        return fib(x-1) + fib(x-2);
    }
    
    public static long fact(int x){
        if(x == 0 || x == 1){
            return 1;
        }
        return x*fact(x-1);
    }
}
