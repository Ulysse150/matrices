package algebre;



public abstract class ProduitScalaire {

    public abstract  double valeur(Vector v1, Vector v2);
}

class Canonique extends ProduitScalaire{

   public double valeur(Vector v1, Vector v2){
       if (v1.dimension != v2.dimension){
           throw  new ArithmeticException("Les vecteurs doivent avoir la meme taille.");
       }else{
           double d = 0;
           for (int i = 0; i < v1.dimension; i++){
               d = v1.index(i) * v2.index(i);
           }
           return d;
       }
   }
}
