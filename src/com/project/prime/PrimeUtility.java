package com.project.prime;

public class PrimeUtility {
    protected static boolean isPrime(long num) {
        if (num <= 1 || num % 2 == 0) {
            return false;
        }
            
        int upperdivisor = (int) Math.ceil(Math.sqrt(num));
        for (int i = 3; i <= upperdivisor; i+=2) {
            if (num % i == 0) {
                
                return false;
            }
        }
        return true;
    }
}