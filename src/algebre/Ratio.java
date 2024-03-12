package algebre;
import java.lang.Math;


public class Ratio extends Nombre  {
    //Ses variables
    //positive est un booleen
    final private int num;
    final private int denom;
    final private boolean signed;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ratio){
            return this.signed == ((Ratio) obj).signed && this.getNum() == ((Ratio) obj).getNum() && this.getDenom() == ((Ratio) obj).getDenom();
        }else{
            return super.equals(obj);
        }
    }

    //Les constructeurs
    Ratio(int a, int b){
        this.signed = ( a < 0)  ^ ( b < 0);
        int[] v = Useful.simplifier(Math.abs(a), Math.abs(b));
        this.num = v[0];
        this.denom = v[1];

    }

    Ratio(int v){
        this.signed = v < 0;
        this.num = Math.abs(v);
        this.denom = 1;
    }

    Ratio(){
        this.signed = false;
        this.denom = 1;
        this.num = 0;
    }

    Ratio(int a, int b, boolean s){
        int[] v = Useful.simplifier(Math.abs(a), Math.abs(b));
        this.num = v[0];
        this.denom = v[1];
        this.signed = s;
    }

    public boolean isNull(){
        return this.num == 0;
    }

    public int getNum(){
        return this.num;
    }

    public int getDenom(){
        return this.denom;
    }

    public boolean getPositif(){
        return this.signed;
    }

    @Override
    public Nombre oppose(){
        return new Ratio(this.num, this.denom,!this.signed);
    }

    @Override
    NomEntier type() {
        return NomEntier.rationnel;
    }

    @Override
    public String toString(){
        if (this.num == 0){
            return "0";
        }else{
            String s;
            if (this.signed){
                s = "-";
            }else{
                s = "";
            }
            if (this.denom == 1){ //si c'est un nombre entier
                return s +this.num;
                }
            else{
                return s + this.num + "/" + this.denom;
            }
        }


    }

    public Ratio addRatio(Ratio Q){

        if (this.num == 0){
            return Q;
        }else{
            if (Q.num == 0){
                return this;
            }else{
                int D = Useful.ppcm(this.denom, Q.denom);
                //System.out.println(D);

                int m1 = D / this.denom;//le nombre par lequel on multipliera la fraction de gauche
                int m2 = D / Q.denom; // le nombre par lequel on multipliera la fraction de droite

                int q1 = this.num * m1;
                int q2 = Q.num * m2;

                if (this.signed == Q.signed){ // si ils sont de meme signe alors on garde le meme signe
                    // et on les additionne
                    return new Ratio(q1 + q2, D, this.signed);
                }else{ // si ils sont de signes différents
                    // c est le plus gros qui l'emporte
                    if (q1 > q2){
                        return new Ratio(q1 - q2, D, this.signed);
                    }else{
                        if (q1 < q2){
                            return new Ratio(q2 - q1, D, Q.signed);
                        }else{
                            return new Ratio();
                        }

                    }

                }
            }
        }
    }


    @Override
    Nombre add(Nombre Q) {
        
        return switch (Q.type()){
            case rationnel -> this.addRatio((Ratio) Q);
            case reel -> throw new ArithmeticException("Un rationnel peut pas aditionner double");
        };
    }

    @Override
    Nombre neg(Nombre N) {
        Nombre Q = N.oppose();
        return this.add(Q);
    }

    Ratio mulRatio(Ratio Q){
        if (this.isNull() || Q.isNull()){
            return new Ratio();
        }else{
            return new Ratio(Q.num * this.num, Q.denom * this.denom, Q.signed ^this.signed);}
    }

    @Override
    Nombre mul(Nombre N) {
        return switch (N.type()){
            case rationnel -> this.mulRatio((Ratio) N);
            case reel -> throw new ArithmeticException("multiplication entre double et rationnel interdits");
        };
    }

    @Override
    Nombre inv() {

        if (this.num == 0){
            throw new ArithmeticException("On ne peut calculer l'inverse de zero");
        }
        return new Ratio(this.denom, this.num, this.signed);
    }

    @Override
    Nombre div(Nombre N) {
        return switch (N.type()) {
            case rationnel -> this.mulRatio((Ratio) (N.inv()));
            case reel -> throw new ArithmeticException("division entre double et rationnel interdits");
        };
    }

}
