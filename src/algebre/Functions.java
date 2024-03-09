package algebre;



public class Functions {
    public static Matrice getMatriceLigneCol(Matrice M, int ligne, int colonne){
        //Renvoie la matrice privee de la ligne et de la colonne donee
        //Sert pour le calcul du determinant
        Matrice A = new Matrice(M.getHauteur() - 1, M.getLargeur() - 1);
        int indiceLigne = 0;
        int indiceColonne;

        for (int i = 0; i < M.getHauteur(); i++){
            indiceColonne = 0;
            if (i != ligne){
                for (int j = 0; j < M.getLargeur(); j++){
                    if (j != colonne){
                        A.setValue(indiceLigne, indiceColonne, M.get(i, j));
                        indiceColonne++;

                    }
                }
                indiceLigne++;
            }
        }

        return A;
    }



    public static Matrice multiplier(Matrice A, Matrice B){
        if (A.getLargeur() != B.getHauteur()){
            throw new ArithmeticException("Les matrices doivent avoir une largeur et hauteur egales");
        }else{
            //On crée la matrice
            Matrice C = new Matrice(A.getHauteur(), B.getLargeur());

            int N = A.getLargeur();
            for (int i = 0; i < C.getHauteur(); i++){
                for (int j = 0; j < C.getLargeur(); j++){
                    double resultat = 0 ;
                    for (int k = 0; k < N; k++){
                        resultat += A.get(i, k)*B.get(k, j);
                    }
                    C.setValue(i, j, resultat);
                }
            }


            return C;
        }
    }

    public static double det(Matrice M){
        if (M.getHauteur() != M.getLargeur()){
            throw new ArithmeticException("Matrice doit etre caree");
        }else{
            return sousDet(M);
        }
    }

    public static double sousDet(Matrice M){
        //cas d'arret
        if (M.getLargeur() == 2){ // si la matrice est une 2*2 (on considere matrice carree)
            // c'est notre cas d'arret
            return M.get(0,0)*M.get(1,1) - M.get(0,1)*M.get(1,0);

        }else{
            //on developpe à partir de la première ligne
            int N = M.getLargeur();
            double coeff;
            double total = 0;
            for (int i = 0; i < N; i++){
                coeff = M.get(0, i);
                if (coeff != 0){
                    total += Useful.signe(i) * coeff * sousDet(getMatriceLigneCol(M, 0, i));
                }
            }
            return total;
        }

    }


    public static Matrice Comatrice(Matrice M) {
        Matrice Com;
        if (M.getLargeur() == M.getHauteur()) {
            Com = new Matrice(M.getHauteur(), M.getLargeur());
            for (int i = 0; i < M.getHauteur(); i++) {
                for (int j = 0; j < M.getLargeur(); j++) {
                    Com.setValue(i, j, Useful.signe(i + j) * sousDet(getMatriceLigneCol(M, i, j)));
                    }
                }

            return Com;}
        else{
            throw new ArithmeticException("Matrice doit etre caree");
        }

    }

    public static Matrice inverse(Matrice M){
        double d = Functions.det(M);
        if (!Useful.estNul(d)){
            Matrice A = Comatrice(M).transposee();
            return A.mul(1/d);
        }else{
            throw new ArithmeticException("La matrice n est pas inversible");
        }
    }




}
