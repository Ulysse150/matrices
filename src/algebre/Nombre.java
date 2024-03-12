package algebre;

enum NomEntier{
    rationnel,
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


    public boolean IsSameClass(Nombre N) {
        //Checks if is same instance
        if (this instanceof Ratio) {
            return N instanceof Ratio;
        } else {
            //TODO: à coder la verification pour les autres types
            return true;
        }
    }

    public void CheckIsSameClass(Nombre N) {
        if (!(this.IsSameClass(N))) {
            if (this instanceof Ratio) {
                throw new ArithmeticException("N doit etre un rationnel.");
            }
        }
    }

    abstract boolean isNull();

}

class Reel extends Nombre{
    double value;
    Reel(double v){
        this.value = v;
    }

    @Override
    Nombre mul(Nombre N) {
        return switch (N.type()){
            case rationnel -> throw new ArithmeticException("multiplication entre double et rationnel interdits");
            case reel -> new Reel(this.value * ((Reel)N).value);
        };
    }

    @Override
    Nombre neg(Nombre N) {
        return switch (N.type()){
            case rationnel -> throw new ArithmeticException("Un rationnel peut pas soustraire double");
            case reel -> new Reel((this.value) - ((Reel)N).value);
        };
    }

    @Override
    Nombre add(Nombre N) {
        return switch (N.type()){
            case rationnel -> throw new ArithmeticException("Un rationnel peut pas additionner double");
            case reel -> new Reel((this.value) + ((Reel)N).value);
        };
    }



    @Override
    Nombre inv() {
        if (this.isNull()){
            throw new ArithmeticException("On ne peut pas calculer l'inverse d'un nombre nul");
        }else{
            return new Reel(1 / this.value);
        }
    }
    @Override
    Nombre div(Nombre N) {
        return this.mul(N.inv());
    }

    @Override
    Nombre oppose() {
        return new Reel(-1*this.value);
    }

    @Override
    NomEntier type() {
        return NomEntier.reel;
    }

    @Override
    boolean isNull() {
        return this.value <= 1e-8;
    }
}


