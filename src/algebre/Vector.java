package algebre;
import java.util.ArrayList;

public class Vector {

    public int dimension;
    public ArrayList<Double> coeffs;

    public Vector(ArrayList<Double> L){
        this.coeffs = L;
        this.dimension = L.size();
    }

    @Override
    public String toString(){
        String s = "(";
        Double d;
        for (int i = 0; i < this.dimension; i++){
            d = this.coeffs.get(i);
            s = s + String.valueOf(d) + " , ";
        }
        s = s + ")";
        return s;
    }

    public Double index(int i){
        return coeffs.get(i);
    }




    public static Vector multiply(Double alpha, Vector v){
        ArrayList<Double>  list = new ArrayList<>();

        for (int i = 0; i < v.dimension ; i++ ){
            list.add(alpha * v.index(i));
        }

        return new Vector(list);
    }

    public static Vector sum(Vector v1, Vector v2){
        ArrayList<Double>  list = new ArrayList<>();

        for (int i = 0; i < v1.dimension ; i++ ){
            list.add(v1.index(i) + v2.index(i));
        }

        return new Vector(list);
    }

    public static Vector sub(Vector v1, Vector v2){
        ArrayList<Double>  list = new ArrayList<>();

        for (int i = 0; i < v1.dimension ; i++ ){
            list.add(v1.index(i) - v2.index(i));
        }

        return new Vector(list);
    }


}