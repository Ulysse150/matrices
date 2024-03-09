package algebre;

public abstract class Nombre {


    abstract Nombre mul(Nombre N);

    abstract Nombre neg(Nombre N);

    abstract Nombre add(Nombre N);

    abstract Nombre div(Nombre N);

    abstract Nombre inv(Nombre N);

    public abstract Ratio add(Ratio Q);
}
