
/* Yash Deshpande  CS-570 C   Cwid- 20025089  */

import java.util.*;
import java.lang.*;
public class BinaryNumber {

    private int data[];
    private boolean overflow;

    /* Constructor for Binary Number containing all 0s with length as input from user  */
    public BinaryNumber(int length){
        data = new int[length];
        for(int a = 0;a<length;a++){
            data[a] = 0;
        }
    }

    /* Constructor for Binary Number containing String input from user  */
    public BinaryNumber(String str){
        data = new int[str.length()];
        for(int b = 0;b<str.length();b++){
            if(Character.getNumericValue(str.charAt(b)) == 0 ||
                    Character.getNumericValue(str.charAt(b)) == 1){
                data[b] = Character.getNumericValue(str.charAt(b));
                System.out.print("\nBinary digit entered is "+data[b]+"\n");
            }else{
                System.out.println("\nInvalid Binary Number inputted");
                return;
            }
        }

    }
    /* Function to get length of array containing binary number  */
    public int getLength(){
        return data.length;
    }

    /* Function to get specific digit at a particular index inputted by user  */
    public int getDigit(int index){
        if(index<0 || index>data.length-1){
            System.out.println("Index out of bounds");
            return -1;
        }else{
            return data[index];
        }

    }

    /* Function to convert binary number to decimal using little endian method  */
    public int toDecimal(){
        int sum = 0;
        int m = 0;
        for(int c =0;c<data.length;c++){
            sum = (int) (sum + data[c] * Math.pow(2,m));
            m++;
        }
        return sum;
    }

    /* Function to shift binary number to right by given amount of digits  */
    public void shiftR(int amount){
        int total = amount + data.length;
        int[] shiftAr = new int[total];
        int counter = 0;
        for(int d = 0;d<amount;d++){
            shiftAr[d] = 0;
            System.out.print(shiftAr[d]);
        }
        for(int e = amount;e<total;e++){
            shiftAr[e] = data[counter];
            System.out.print(shiftAr[e]);
            counter++;
        }
        System.out.print("\n");
    }

    /* Function to add binary numbers  */
    public void add(BinaryNumber aBinaryNumber){
        int[] arr2 = new int[data.length];
        arr2 = aBinaryNumber.data;
        int car = 0;
        int sum = 0;
        if(aBinaryNumber.getLength()!= data.length){
            System.out.println("Sorry cannot add two binary numbers of different sizes");
        }else{
            for(int i=0;i< data.length;i++){
               sum = car + data[i] + arr2[i];
               if(sum == 0){
                   data[i] = 0;
                   car = 0;
               }else if(sum ==1){
                   data[i] = 1;
                   car = 0;
               }else if(sum == 2){
                   data[i] = 0;
                   car = 1;
               }else if(sum ==3){
                   data[i] = 1;
                   car = 1;
               }

               }
            if(car == 1){
                overflow = true;

            }
        }

    }

    /* Function to clear overflow flag from true to fals  */
    public void clearOverflow(){
        overflow = false;
    }

    /* Function to convert array of binary number to string  */
    public String toString(){
        String st1 = "";
        if(overflow){
            System.out.println("Overflow");
        }else {
            StringBuffer sb = new StringBuffer();
            for(int j=0;j<data.length;j++) {
                sb.append(data[j]);
            }
            st1= sb.toString();

        }
        return st1;

    }
}
