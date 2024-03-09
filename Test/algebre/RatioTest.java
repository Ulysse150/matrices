package algebre;

import org.junit.Test;

import static org.junit.Assert.*;

public class RatioTest {

    @Test
    public  void testConstructeurSimple(){
        Ratio R = new Ratio(2);
        Ratio R2 = new Ratio(-7);
        assert R.getDenom() == 1 && R.getNum() == 2 && !R.getPositif();
        assert R2.getDenom() == 1 && R2.getNum() == 7 && R2.getPositif();

    }

    @Test
    public void testConstructeurVide(){
        Ratio Q = new Ratio();
        assert Q.getNum() == 0 && Q.getDenom() == 1;
    }

    @Test
    public void testConstructeur(){
        Ratio vrai = new Ratio(1,2);

        Ratio premier = new Ratio(-1, -2);
        Ratio deux = new Ratio(2, 4);
        Ratio trois = new Ratio(-100, -200);

        assert vrai.equals(premier) : "test premier failed";
        assert vrai.equals(deux):"test deux failed";
        assert vrai.equals(trois):"test trois failed";


        Ratio n3_8= new Ratio(-3, 8);

        //Tests pour des plus gros nombres on sait jamais
        Ratio n1 = new Ratio(3, -8);
        Ratio n2 = new Ratio(-30, 80);
        Ratio n3 = new Ratio(102, -272);

        assert n3_8.equals(n1) : "test n1 failed";
        assert n3_8.equals(n2) : "Test n2 failed";
        assert n3_8.equals(n3) : "Test n3 failed";

    }

    @Test
    public void testAdd(){
        Ratio zero = new Ratio();
        Ratio un = new Ratio(1);
        Ratio huit = new Ratio(8);

        Ratio p3_4 = new Ratio(3, 4);
        Ratio n3_4 = new Ratio(-3, 4);

        Ratio p1_4 = new Ratio(1, 4);

        Ratio p7 = new Ratio(7);
        Ratio n7 = new Ratio(-7);

        Ratio p8_3 = new Ratio(8, 3);
        Ratio n8_3 = new Ratio(-8, 3);

        Ratio p4_5 = new Ratio(4, 5);
        Ratio n4_5 = new Ratio(-4, 5);



        Ratio p52_15 = new Ratio(52, 15); //somme de 8/3 + 4/5
        //Test quand l'un des nombres est zero
        assert zero.add(p3_4).equals(p3_4) : "test  3/4 + 0 failed";
        assert p7.add(zero).equals(p7) : "test 0 + 7 failed";
        Ratio r = p3_4.add(p1_4);

        assert r.equals(un) : "test 3/4 + 1/4 failed";

        assert p3_4.add(n3_4).equals(zero) : "test 3/4 + -3/4 failed";
        r = p8_3.add(p4_5);
        //Maintenant les test plus pertinents
        assert r.equals(p52_15);

        assert p7.add(p8_3).equals(new Ratio(29, 3)) : "test 7 + 8/3 failed";

        assert p7.add(huit).equals(new Ratio(15)) : "test 7 + 8 failed";

        //Meme test mais avec tout le monde négatif
        assert n7.add(p7).equals(zero) : "Test -7 + 7 failed";
        assert n7.add(n8_3).equals(new Ratio(-29, 3)): "Test -7 + -8/3 failed";



        //Maintenant test quand ils sont de signes différents
        Ratio r1 = p8_3.add(n4_5); // ca fait 28/15
        Ratio r2 = n4_5.add(p8_3); // ca fait 28 / 15  aussi
        Ratio r3 = p1_4.add(n3_4); // ca fait -1/2
        Ratio r4 = n3_4.add(p1_4); // ca fait -1/2 aussi

        assert r1.equals(new Ratio(28, 15)) : "test 8/3  + - 4/5 failed";
        assert r2.equals(new Ratio(28, 15)) : "test - 4/5  + 8/3 failed";

        assert r3.equals(new Ratio(-1, 2)) : "test 3/4 - 1/4 failed";
        assert r4.equals(new Ratio(-1, 2)) : "test - 1/4 + 3/4 failed";




    }
    @Test
    public void TestMul(){
        Ratio zero = new Ratio();
        Ratio un = new Ratio(1);
        Ratio huit = new Ratio(8);

        Ratio p3_4 = new Ratio(3, 4);
        Ratio n3_4 = new Ratio(-3, 4);

        Ratio p1_4 = new Ratio(1, 4);

        Ratio p7 = new Ratio(7);
        Ratio n7 = new Ratio(-7);

        Ratio p8_3 = new Ratio(8, 3);
        Ratio n8_3 = new Ratio(-8, 3);

        Ratio p4_5 = new Ratio(4, 5);
        Ratio n4_5 = new Ratio(-4, 5);


        assert zero.mul(p8_3).equals(zero) : "test 0 * 8/3 failed";
        assert n4_5.mul(zero).equals(zero) : "test -4/5 * 0 failed";

        assert p3_4.mul(p1_4).equals(new Ratio(3, 16)) : "test 1/4 * 3 /4 failed";
        assert p1_4.mul(n3_4).equals(new Ratio(-3, 16)) : "test -3/4 * 1 /4 failed";

    }


}