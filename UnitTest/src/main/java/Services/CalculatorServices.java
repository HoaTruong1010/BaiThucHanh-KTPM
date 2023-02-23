/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

/**
 *
 * @author admin
 */
public class CalculatorServices {
    public static boolean isNguyenTo(int n) {
        if (n < 0)
            throw new ArithmeticException();
        if ( n >= 2 ) {
            for(int i = 2; i <= Math.sqrt(n); i++)
                if(n % i == 0)
                    return false;
            return true;
        }
            
        return true;
        
    }
    
    public static double Power(double x, int n) {
        if (n == 0) {
            return 1.0;
        } else if (n > 0) {
            return n * Power(x, n - 1);
        } else {
            return Power(x, n + 1) / x;
        }
    }
}
