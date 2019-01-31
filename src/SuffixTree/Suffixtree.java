/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuffixTree;

/**
 *
 * @author koksa
 */
import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

public class Suffixtree extends Applet implements ActionListener {

    Button button1, button2, button3, button4;
    JTextField textField1, textField2;
    Label lbl1;
    public static int indeks = 1;
    public static int width, height;
    

    public void init() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        this.setLayout(new FlowLayout());
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight();
        this.setBackground(new Color(255, 255, 255));

        this.setBackground(new Color(224, 224, 224));
        this.setLayout(null);

        this.setSize(width, height);
        button1 = new Button("Suffix Tree oluştur.");
        button1.setBackground(new Color(255, 153, 153));
        button1.setBounds(375, 20, 200, 30);
        add(button1);
        button1.addActionListener(this);

        button2 = new Button("Ara");
        button2.setBackground(new Color(255, 153, 153));
        button2.setBounds(825, 20, 200, 30);
        add(button2);
        button2.addActionListener(this);

        button3 = new Button("En uzun tekrar eden stringi bul.");
        button3.setBackground(new Color(255, 153, 153));
        button3.setBounds(1050, 20, 200, 30);
        add(button3);
        button3.addActionListener(this);

        button4 = new Button("En çok tekrar eden stringi bul.");
        button4.setBackground(new Color(255, 153, 153));
        button4.setBounds(1275, 20, 200, 30);
        add(button4);
        button4.addActionListener(this);
        this.setLayout(null);

    }

    public static int durum = 0;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != button1) {

            for (int i = 0; isaretlenenler != null && i < isaretlenenler.size(); i++) {
                gr.setColor(Color.PINK);
                gr.fillOval(isaretlenenler.get(i).x, isaretlenenler.get(i).y, 17, 17);
                gr.setColor(Color.black);
                gr.drawString(isaretlenenler.get(i).str, stringler.get(i)[0], stringler.get(i)[1]);
            }
            for (int i = 0; encoktekraredenlernodes != null && i < encoktekraredenlernodes.size(); i++) {
                gr.setColor(Color.PINK);
                gr.fillOval(encoktekraredenlernodes.get(i).x, encoktekraredenlernodes.get(i).y, 17, 17);
            }
            for (int i = 0; en_uzun_yollar!=null &&i < en_uzun_yollar.size(); i++) {
                for (int j = 0; en_uzun_yollar.get(i)!=null && j < en_uzun_yollar.get(i).size(); j++) {
                    gr.setColor(Color.pink);
                    gr.fillOval(en_uzun_yollar.get(i).get(j).x,en_uzun_yollar.get(i).get(j).y, 17, 17);
                }
            }

        }
        if (e.getSource() == button1) {
            try {
                indeks = 1;
                agac_olustur(textField1.getText(), gr);
            } catch (InterruptedException ex) {
                Logger.getLogger(Suffixtree.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == button2) {
            int sonuc = 0;
            try {
                sonuc = icinde_geciyor_mu(textField2.getText(), kok, 0, 0);
            } catch (InterruptedException ex) {
                Logger.getLogger(Suffixtree.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("sonuc:  " + sonuc);
            if (sonuc == 1) {
                System.out.println("bulundu");
                JOptionPane.showMessageDialog(null,
                        textField2.getText() + " stringi bulunmaktadır",
                        "SONUC",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("String bulunmamaktadır.");
                JOptionPane.showMessageDialog(null,
                        textField2.getText() + " stringi bulunmamaktadır",
                        "SONUC",
                        JOptionPane.INFORMATION_MESSAGE);
                gr.setColor(Color.PINK);
                gr.fillOval(kok.x, kok.y, 17, 17);
                for (int i = 0; i < isaretlenenler.size(); i++) {
                    gr.setColor(Color.PINK);
                    gr.fillOval(isaretlenenler.get(i).x, isaretlenenler.get(i).y, 17, 17);
                    gr.setColor(Color.black);
                    gr.drawString(isaretlenenler.get(i).str, stringler.get(i)[0], stringler.get(i)[1]);
                }
            }
        } else if (e.getSource() == button3) {
            en_uzunlar = new ArrayList<String>();
            ArrayList<Node> yol = new ArrayList<Node>();
            en_uzun_yollar = new ArrayList<>();
            //System.out.println(en_uzun_tekrar_eden(kok, "",""));
            String en_uz = "";
            try {
                en_uz = en_uzun_tekrar_eden(kok, "", "", yol);
            } catch (InterruptedException ex) {
                Logger.getLogger(Suffixtree.class.getName()).log(Level.SEVERE, null, ex);
            }
            String mesaj = "";
            for (int i = 0; i < en_uzunlar.size(); i++) {
                mesaj += en_uzunlar.get(i) + "\n";
            }
            
            agac_ciz(kok, 0, width, 960, 80); 
            for (int i = 0; i < en_uzun_yollar.size(); i++) {
                for (int j = 0; j < en_uzun_yollar.get(i).size(); j++) {
                    gr.setColor(Color.green);
                    gr.fillOval(en_uzun_yollar.get(i).get(j).x,en_uzun_yollar.get(i).get(j).y, 17, 17);
                }
            }
            
            JOptionPane.showMessageDialog(null,
                    "En uzun tekrar eden string veya stringler\n " + en_uz + "\n" + mesaj,
                    "SONUC",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {

            String mesaj2 = "";
            encoktekraredenler = new ArrayList<>();
            encoktekraredenlernodes = new ArrayList<>();
            int tekrar_sayisi = 0;
            try {
                tekrar_sayisi = en_cok_tekrar_eden(kok);
            } catch (InterruptedException ex) {
                Logger.getLogger(Suffixtree.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < encoktekraredenler.size(); i++) {
                mesaj2 += encoktekraredenler.get(i) + "\n";
                System.out.println("**********" + encoktekraredenler.get(i));
            }
            //System.out.println("Tekrar etme sayısı: "+tekrar_sayisi+"\n string: "+kok.children.get(en_cok_tekrar_eden_indeks).str);
            String mesaj = "En cok tekrar eden string veya stringler\n " + mesaj2 + " Tekrar etme sayısı: " + tekrar_sayisi;
            JOptionPane.showMessageDialog(null,
                    mesaj,
                    "SONUC",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public static ArrayList<String> encoktekraredenler;
    public static ArrayList<Node> encoktekraredenlernodes;
    public static int en_cok_tekrar_eden_indeks = 0;
    public static int root = 0;

    public static int en_cok_tekrar_eden(Node nd) throws InterruptedException {
        if (nd.str == "" || root == 1) {
            gr.setColor(Color.green);
            gr.fillOval(nd.x, nd.y, 17, 17);
        } else {
            gr.setColor(Color.red);
            gr.fillOval(nd.x, nd.y, 17, 17);
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println("str: " + nd.str);
        int max = 0, deger = 0;
        if (nd.children == null) {
            System.out.println("leaf");
            deger = 1;
            gr.setColor(Color.pink);
            gr.fillOval(nd.x, nd.y, 17, 17);
        } else {
            System.out.println("internal");
            for (int i = 0; i < nd.children.size(); i++) {
                if (nd.str == "") {
                    root = 1;
                } else {
                    root = 0;
                }
                deger += en_cok_tekrar_eden(nd.children.get(i));
                if (nd.str == "" && deger >= max && nd.children.get(i).indeks == -1) {
                    gr.setColor(Color.green);
                    gr.fillOval(nd.children.get(i).x, nd.children.get(i).y, 17, 17);
                    if (deger > max) {
                        for (int j = 0; j < encoktekraredenlernodes.size(); j++) {
                            gr.setColor(Color.pink);
                            gr.fillOval(encoktekraredenlernodes.get(j).x, encoktekraredenlernodes.get(j).y, 17, 17);

                        }
                        encoktekraredenler = new ArrayList<>();
                        encoktekraredenlernodes = new ArrayList<>();
                    }
                    encoktekraredenlernodes.add(kok.children.get(i));
                    System.out.println("deger: " + deger + " max: " + max);
                    System.out.println("kok.children.get(i).str" + kok.children.get(i).str);
                    encoktekraredenler.add(kok.children.get(i).str);
                    max = deger;
                    en_cok_tekrar_eden_indeks = i;

                }
                if (nd.str == "") {
                    deger = 0;
                }
            }

            if (nd.str == "") {
                deger = max;
            }

            System.out.println("deger: " + deger);
        }

        if (nd.str != "" && root != 1) {
            gr.setColor(Color.pink);
            gr.fillOval(nd.x, nd.y, 17, 17);

        }
        return deger;
    }

    public static ArrayList<String> en_uzunlar;
    public static ArrayList<ArrayList<Node>> en_uzun_yollar;

    public static String en_uzun_tekrar_eden(Node nd, String en_uzun, String parent_str, ArrayList<Node> yol) throws InterruptedException {
        
        ArrayList<Node> temp=new ArrayList(yol);
        temp.add(nd);
        gr.setColor(Color.red);
        gr.fillOval(nd.x, nd.y, 17, 17);
        TimeUnit.SECONDS.sleep(1);

        if (nd.children != null) {
           
            if (en_uzun.length() < (parent_str + nd.str).length()) {
                for (int i = 0; i < en_uzun_yollar.size(); i++) {

                    for (int j = 0; j < en_uzun_yollar.get(i).size(); j++) {
                        gr.setColor(Color.pink);
                        gr.fillOval(en_uzun_yollar.get(i).get(j).x, en_uzun_yollar.get(i).get(j).y, 17, 17);

                    }

                }
                for (int i = 0; i < en_uzun_yollar.size(); i++) {

                    en_uzun_yollar.remove(i);

                }

                for (int i = 0; i < en_uzun_yollar.size(); i++) {

                    for (int j = 0; j < en_uzun_yollar.get(i).size(); j++) {
                        gr.setColor(Color.pink);
                        gr.fillOval(en_uzun_yollar.get(i).get(j).x, en_uzun_yollar.get(i).get(j).y, 17, 17);

                    }

                }

                for (int i = 0; i < temp.size(); i++) {
                    gr.setColor(Color.green);
                    gr.fillOval(temp.get(i).x, temp.get(i).y, 17, 17);
                }

                ArrayList deneme = new ArrayList(temp);
                en_uzun_yollar.add(deneme);
                gr.setColor(Color.green);
                gr.fillOval(nd.x, nd.y, 17, 17);
                en_uzun = parent_str + nd.str;
                en_uzunlar = new ArrayList<>();

            } else if (en_uzun.length() == (parent_str + nd.str).length()) {
                
                 for (int i = 0; i < temp.size(); i++) {
                    gr.setColor(Color.green);
                    gr.fillOval(temp.get(i).x, temp.get(i).y, 17, 17);
                }
                ArrayList deneme = new ArrayList(temp);
                en_uzun_yollar.add(deneme);
                gr.setColor(Color.green);
                gr.fillOval(nd.x, nd.y, 17, 17);
                en_uzunlar.add(parent_str + nd.str);
            }
            for (int i = 0; i < nd.children.size(); i++) {
                en_uzun = en_uzun_tekrar_eden(nd.children.get(i), en_uzun, parent_str + nd.str, temp);
            }
        } else {
            gr.setColor(Color.pink);
            gr.fillOval(nd.x, nd.y, 17, 17);
        }

        return en_uzun;

    }

    public static Graphics gr;

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 70, width, height - 200);
        /*g.setColor(Color.black);
        g.drawString("deneme", 200, 200);
        g.setColor(new Color(0,204,204));
        g.drawString("deneme", 250, 200);*/

        gr = getGraphics();
        textField1 = new JTextField();
        textField2 = new JTextField();
        
        textField1.setVisible(true);
        textField2.setVisible(true);
        //textField1.setToolTipText("Stringi giriniz");
        add(textField1);
        add(textField2);
        textField1.setBounds(50, 20, 300, 30);
        Border border = BorderFactory.createLineBorder(Color.black, 2);
        textField1.setBorder(border);
        textField2.setBounds(600, 20, 200, 30);
        textField2.setBorder(border);

    }

    public static ArrayList<Node> isaretlenenler = new ArrayList<>();
    public static ArrayList<int[]> stringler = new ArrayList<>();

    public static int icinde_geciyor_mu(String s2, Node nd, int parentx, int parenty) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        gr.setColor(Color.RED);
        gr.fillOval(nd.x, nd.y, 17, 17);
        TimeUnit.SECONDS.sleep(1);
        System.out.println(s2 + "   " + nd.str);
        int boyut = 0, farkli = 0;
        if (nd.str == "") {
        } else if (s2.charAt(0) != nd.str.charAt(0)) {
            gr.setColor(Color.PINK);
            gr.fillOval(nd.x, nd.y, 17, 17);
            return -1;
        } else {

            if (nd.str.length() < s2.length()) {
                boyut = nd.str.length();
            } else {
                boyut = s2.length();
            }

            for (int i = 0; i < boyut; i++) {
                if (nd.str.charAt(i) != s2.charAt(i)) {
                    farkli = 1;
                    break;
                }
            }
            if (farkli == 1) {
                gr.setColor(Color.PINK);
                gr.fillOval(nd.x, nd.y, 17, 17);
                return 0;
            }

        }
        String yedek = new String(s2);
        s2 = s2.substring(boyut, s2.length());
        int koor = Math.abs(nd.x + parentx) / 2;
        koor = Math.abs(nd.x + koor) / 2;
        int bulundu = 0;
        int[] parentxy = new int[2];
        if (farkli == 0 && s2.length() == 0) {
            gr.setColor(Color.GREEN);
            gr.fillOval(nd.x, nd.y, 17, 17);
            gr.setColor(new Color(0, 204, 204));
            gr.drawString(yedek + "", koor, parenty + 60);
            parentxy[0] = koor;
            parentxy[1] = parenty + 60;
            stringler.add(parentxy);
            isaretlenenler.add(nd);
            return 1;
        } else if (farkli == 0 && s2.length() != 0) {
            gr.setColor(Color.GREEN);
            gr.fillOval(nd.x, nd.y, 17, 17);
            gr.setColor(new Color(0, 204, 204));
            gr.drawString(nd.str + "", koor, parenty + 60);
            parentxy[0] = koor;
            parentxy[1] = parenty + 60;
            stringler.add(parentxy);
            isaretlenenler.add(nd);
            for (int i = 0; nd.children != null && i < nd.children.size(); i++) {

                System.out.println("recursive");
                bulundu = icinde_geciyor_mu(s2, nd.children.get(i), nd.x, nd.y);
                System.out.println(bulundu);
                if (bulundu == 0 || bulundu == 1) {
                    break;
                }

            }

        }

        return bulundu;
    }

    
    
    public static void agac_ciz(Node nd, int bas, int son, int parentx, int parenty) {

        
        System.out.println("konum: " + (bas + son) / 2);
        int boyut = 0;
        gr.setColor(Color.red);
        if (nd.children != null) {
            boyut = (son - bas) / nd.children.size();
        }

        gr.setColor(Color.PINK);
        gr.fillOval((bas + son) / 2, parenty + 80, 17, 17);
        nd.x = (bas + son) / 2;
        nd.y = parenty + 80;
        if (nd.str != "") {
            gr.drawLine(parentx + 5, parenty + 5, (bas + son) / 2 + 5, parenty + 85);
        }
        gr.setColor(Color.red);
        if (nd.indeks != -1) {
            gr.drawString(nd.indeks + "", (bas + son) / 2 + 5, parenty + 107);
        }
        gr.setColor(Color.black);
        int koor = Math.abs(nd.x + parentx) / 2;
        koor = Math.abs(nd.x + koor) / 2;
        gr.drawString(nd.str + "", koor, parenty + 60);
        for (int i = 0; nd.children != null && i < nd.children.size(); i++) {

            agac_ciz(nd.children.get(i), bas + i * boyut, bas + i * boyut + boyut, (bas + son) / 2, parenty + 80);
        }
    }
    static int y = 0;
    public static Node kok;

    public void agac_olustur(String dizilim, Graphics g) throws InterruptedException {

        int indks=dizilim.indexOf(dizilim.charAt(dizilim.length()-1));
        if(indks!=dizilim.length()-1)
           dizilim += "$";
        System.out.println(dizilim);
        /*
        for (int i = dizilim.length() - 1; i >= 0; i--) {
            String suffix = dizilim.substring(i, dizilim.length());
            System.out.println(suffix);
            if (dizilim.indexOf(suffix) != i) {
                dizilim += "$";
                System.out.println(suffix);
                break;
            }

        }*/

        kok = new Node("");
        kok.x = 80;
        kok.y = width / 2;
        kok.indeks = -1;
        System.out.println("SUFFİXLER");
        agac_ciz(kok, 0, width, 960, 80);        
        for (int i = 0; i < dizilim.length(); i++) {

            String ekle = dizilim.substring(i, dizilim.length());
            //System.out.println("ekle:  "+ekle+"i  "+i);
            gr.setColor(Color.black);
            gr.drawString(ekle, 1550, 40);
            agaca_ekle(ekle, kok);
            indeks++;
            System.out.println("-----------------------------------"+indeks);
            TimeUnit.SECONDS.sleep(1);

            g.setColor(Color.white);
            g.fillRect(0, 70, width, height - 200);

            agac_ciz(kok, 0, width, 960, 80);
            TimeUnit.SECONDS.sleep(1);
            gr.setColor(new Color(224, 224, 224));
            gr.fillRect(1550, 20, 500, 30);

        }
        

    }

    public static void agaca_ekle(String eklenecek, Node nd) {
        System.out.println("kontrol " + eklenecek + " " + nd.str);
        Node yeni = new Node(eklenecek);
        int flag = 0;
        if (nd.str == "" || yeni.str.startsWith(nd.str)) {
            yeni.str = yeni.str.substring(nd.str.length(), yeni.str.length());
            System.out.println("0");
            for (int i = 0; nd.children != null && i < nd.children.size(); i++) {
                System.out.println(yeni.str.charAt(0) + "   " + nd.children.get(i).str.charAt(0));
                if (yeni.str.charAt(0) == nd.children.get(i).str.charAt(0)) {
                    flag = 1;
                    System.out.println("recursive");
                    agaca_ekle(yeni.str, nd.children.get(i));
                    break;
                }
            }

            if (flag == 0) {
                System.out.println("eklendi:" + yeni.str);
                if (nd.children == null) {
                    nd.children = new ArrayList<Node>();
                }
                yeni.indeks = indeks;
                nd.children.add(yeni);

            }

        } else {

            System.out.println("kesme islemi");
            for (int j = 0; j < nd.str.length(); j++) {
                System.out.println(nd.str.charAt(j) + "   " + yeni.str.charAt(j));
                if (nd.str.charAt(j) != yeni.str.charAt(j)) {
                    Node n1 = new Node(yeni.str.substring(j, yeni.str.length()));
                    Node n2 = new Node(nd.str.substring(j, nd.str.length()));
                    nd.str = nd.str.substring(0, j);
                    n1.indeks = indeks;
                    n2.children = nd.children;
                    n2.indeks = nd.indeks;
                    nd.indeks = -1;
                    nd.children = new ArrayList<Node>();
                    System.out.println("nodee" + n1.str + " " + n2.str);
                    nd.children.add(n1);
                    nd.children.add(n2);
                    yeni.indeks = indeks;
                    break;

                }

            }

        }

    }

}
