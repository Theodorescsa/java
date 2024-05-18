public class Calculator {
    
    public double add(double... numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum+=num;
        }
        return sum;
    }
    public int add(int... numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum+=num;
        }
        return sum;
    }
    public double minus(double... Numbers) {
        double minuend = Numbers[0];
        double result = minuend;
        for (int i = 1;i < Numbers.length;i++) {
            result -= Numbers[i];
        }
        return result;
    }
    public int minus(int... Numbers) {
        int minuend = Numbers[0];
        int result = minuend;
        for (int i = 1;i < Numbers.length;i++) {
            result -= Numbers[i];
        }
        return result;
    }
    public double multyply(double... numbers) {
        double result = 1;
        for(double num:numbers) {
            result*=num;
        }
        return result;
    }
    public int multyply(int... numbers) {
        int result = 1;
        for(int num:numbers) {
            result*=num;
        }
        return result;
    }
    public double divide(double... numbers) {
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = result / numbers[i];
        }
        return result;
    }
    public int divide(int... numbers) {
        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = result / numbers[i];
        }
        return result;
    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        double addDoubleResult = calc.add(1.0,2.0,3.0,4.0);
        double minusDoubleResult = calc.minus(5.0,2.0,3.0);
        double multyDoubleResult = calc.multyply(5.0,2.0);
        double divideDoubleResult = calc.divide(5.0,2.0);
        int addResult = calc.add(1,2,3,4);
        int minusResult = calc.minus(5,2,3);
        int multyResult = calc.multyply(5,2);
        int divideResult = calc.divide(5,2);

        System.out.println("Kieu double-Tong la:"+addDoubleResult+",Hieu la:"+ minusDoubleResult+",Tich la:"+ multyDoubleResult+",Thuong la:"+ divideDoubleResult);
        System.out.println("Kieu int-Tong la:"+addResult+",Hieu la:"+ minusResult+",Tich la:"+ multyResult+",Thuong la:"+ divideResult);
        
    }
}