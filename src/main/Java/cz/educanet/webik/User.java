package cz.educanet.webik;

public class User {
    private String jmeno;
    private String heslo;
    private String prijmeni;
    private String prvniJmeno;
    private String email;

    public User(String prvniJmeno, String jmeno, String heslo, String prijmeni, String email) {
        this.jmeno = jmeno;
        this.heslo = heslo;
        this.email = email;
        this.prijmeni = prijmeni;
        this.prvniJmeno = prvniJmeno;
    }

    public String dostanJmeno() { return jmeno; }
    public String dostanHeslo() { return heslo; }

    public String prejmenujUser(String noveJmeno) { return jmeno = noveJmeno; }
}
