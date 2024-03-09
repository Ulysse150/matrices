package algebre;
import java.io.Serial;
import java.util.Arrays;

public class Matrice {
    // les variables
    private final int largeur;
    private final int hauteur;
    private double[][] grid;

    // Les constructeurs
    //Celui pour une matrice vide
    public Matrice(int h, int l){
        /*
         * Créé une matrice vide de largeur*hauteur
         * @param l un entier
         * @param h un entier
         *
         */
        this.grid = new double[h][l];
        this.largeur = l;
        this.hauteur = h;
    }

    //Le constructeur pour une matrice carree ( pas besoin de se casser le cul)
    public Matrice(int i){
        /*
         * Cree une matrice carree vide
         */
        this(i, i);
    }
    // Le constructeur de base
    public Matrice(double[][] contenu){
        this(contenu.length, contenu[0].length);
        for (int i = 0; i < this.hauteur; i++){
            if (this.largeur >= 0) System.arraycopy(contenu[i], 0, this.grid[i], 0, this.largeur);
        }
    }

    //Les fonctions pour obtenir les valeur
    public double get(int i, int j){
        //Renvoie la valeur de la i eme ligne j eme colonne
        return this.grid[i][j];
    }

    public int getLargeur(){
        //Renvoie la largeur
        return this.largeur;
    }

    public int getHauteur(){
        //Renvoie la hauteur
        return this.hauteur;
    }

    public double[] getLigne(int i){
        //Renvoie la ieme ligne
        return this.grid[i];
    }


    //Pour l'affichage
    public String afficheLigne(int indice){
        StringBuilder repBuilder = new StringBuilder("|");
        for (int j = 0; j < this.largeur; j++){
            repBuilder = new StringBuilder(repBuilder + String.valueOf(this.grid[indice][j]) + " ");
        }
        String rep = repBuilder.toString();


        rep = rep + "|\n";
        return rep;
    }

    @Override
    public String toString() {
        StringBuilder rep = new StringBuilder();
        for (int i = 0; i < this.hauteur; i++) {
            rep.append(afficheLigne(i));
        }
        return rep.toString();
    }




    //Setters
    public void setValue(int i, int j, double value){
        this.grid[i][j] = value;
    }

    public Matrice transposee(){
        Matrice B = new Matrice(this.getLargeur(), this.getHauteur());
        for (int i = 0; i < B.hauteur; i++){
            for (int j = 0; j < B.largeur; j++){
                B.grid[i][j] = this.grid[j][i];
            }
        }
        return B;
    }

    public Matrice mul(double a){
        Matrice rep = new Matrice(this.getHauteur(), this.getLargeur());
        for (int i = 0; i < rep.getHauteur(); i++){
            for (int j = 0; j < rep.getLargeur(); j++){
                rep.grid[i][j] = a * this.grid[i][j];
            }
        }
        return rep;
    }

    public boolean estInversible(){
        return Useful.estNul(Functions.det(this));
    }


}
