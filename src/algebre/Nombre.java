package algebre;

public abstract class Nombre {


    abstract Nombre mul(Nombre N);

    abstract Nombre neg(Nombre N);

    abstract Nombre add(Nombre N);

    abstract Nombre div(Nombre N);

    abstract Nombre inv(Nombre N);

    abstract Nombre oppose();

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

}
