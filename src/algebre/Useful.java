package algebre;
import java.lang.Math;


public class Useful {

    public static double precision  = 1e-06;

    public static int signe(int i){
        if (i %2 == 0){
            return 1;
        }else{
            return -1;
        }
    }

    public static boolean estNul(double valeur){
        //Renvoie si un nombre est considere nul
        // c est à dire inférieur à la precision (ici 1e-05)
        //cela vient du fait que les operations sur les float sont imprécises
        return Math.abs(valeur) <= precision;
    }

    public static int pgcd(int a, int b){
        if (b == 0){
            return a;
        }else{
            return pgcd(b, a%b);
        }
    }
    public static int ppcm(int a, int b){
        return (a*b)/(pgcd(a, b));
    }

    public static int[] simplifier(int a, int b){
        int[] arr = new int[2];
        if (a == 0){
            //arr[0] = 0;
            arr[1] = 1;
            return arr;
        }else{

            int d = pgcd(a, b);
            arr[0] = a / d;
            arr[1] = b / d;
            return arr;}
    }

}
