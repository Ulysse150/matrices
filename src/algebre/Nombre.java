package algebre;

enum NomEntier{
    rationnel,
    entier,
    reel,
}


public abstract class Nombre {



    abstract Nombre mul(Nombre N);

    abstract Nombre neg(Nombre N);

    abstract Nombre add(Nombre N);

    abstract Nombre div(Nombre N);

    abstract Nombre inv();

    abstract Nombre oppose();

    abstract NomEntier type();



    public boolean IsSameClass(Nombre N){
        //Checks if is same instance
        if (this instanceof Ratio){
            return N instanceof Ratio;
        }else{
            //TODO: à coder la verification pour les autres types
            return true;
        }
    }

    public void CheckIsSameClass(Nombre N){
        if (!(this.IsSameClass(N))){
            if (this instanceof Ratio){
                throw new ArithmeticException("N doit etre un rationnel.");
            }
        }
    }

    abstract boolean isNull();



}

class Entier extends Nombre{
    int value;

    Entier(int v){
        this.value = v;
    }

    @Override
    NomEntier type(){
        return NomEntier.entier;
    }

    @Override
    Nombre mul(Nombre N) {
        return null;
    }

    @Override
    Nombre neg(Nombre N) {
        return null;
    }

    @Override
    Nombre add(Nombre N) {
        return null;
    }

    @Override
    Nombre div(Nombre N) {
        return null;
    }

    @Override
    Nombre inv() {
        return null;
    }

    @Override
    Nombre oppose() {
        return null;
    }

    @Override
    boolean isNull() {
        return this.value == 0;
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }




}

