package algebre;
import java.lang.Math;


public class Ratio {
    //Ses variables
    //positive est un booleen
    private int num;
    private int denom;
    private boolean signed;

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
        this.signed = ( a < 0)  ^( b < 0);
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

    public Ratio add(Ratio Q){
        if (Q instanceof  Ratio){
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
        }}else{
            throw new ArithmeticException("Peut multiplier Ratio que avec Ratio");
        }
    }
    public boolean isNull(){
        return this.num == 0;
    }

    public Ratio add(int v){
        return this.add(new Ratio(v));
    }


    public Ratio neg(Ratio Q){
        Ratio R = new Ratio(Q.num, Q.denom, !Q.signed);
        return this.add(R);
    }

    public Ratio neg(int q){
        return this.add(new Ratio(-q));
    }

    public Ratio mul(Ratio Q){
        if (this.isNull() || Q.isNull()){
            return new Ratio();
        }else{
        return new Ratio(Q.num * this.num, Q.denom * this.denom, Q.signed ^this.signed);}
    }

    public Ratio mul(int q){
        if (q == 0){
            return new Ratio();
        }else{
        return this.mul(new Ratio(q, 1));}
    }

    public Ratio inv(){
        if (this.num == 0){
            throw new ArithmeticException("On ne peut calculer l'inverse de zero");
        }
        return new Ratio(this.denom, this.num, this.signed);
    }

    public Ratio div(Ratio Q){
        return this.mul(Q.inv());
    }

    public  Ratio div(int q){
        return this.mul(new Ratio(1, q).inv());
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
                return s + String.valueOf(this.num);
                }
            else{
                return s + this.num + "/" + this.denom;
            }
        }


    }




}
