package fi.utu.rental;

public class Asunto {
    private String kuvaFile;
    private String nimi;
    private String osoite;
    private int rakennusyear;
    private float neliömäärä;
    private int vuokra;
    private String muutEhdot;
    private String[] kohteenDescription;
    private String sähkömail;

    public String getKuvaFile() {
        return kuvaFile;
    }

    public void setKuvaFile(String kuvaTiedosto) {
        this.kuvaFile = kuvaTiedosto;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public int getRakennusyear() {
        return rakennusyear;
    }

    public void setRakennusyear(int rakennusyear) {
        this.rakennusyear = rakennusyear;
    }

    public float getNeliömäärä() {
        return neliömäärä;
    }

    public void setNeliömäärä(float neliömäärä) {
        this.neliömäärä = neliömäärä;
    }

    public int getVuokra() {
        return vuokra;
    }

    public void setVuokra(int vuokra) {
        this.vuokra = vuokra;
    }

    public String getMuutEhdot() {
        return muutEhdot;
    }

    public void setMuutEhdot(String muutEhdot) {
        this.muutEhdot = muutEhdot;
    }

    public String[] getKohteenDescription() {
        return kohteenDescription;
    }

    public void setKohteenDescription(String[] kohteenDescription) {
        this.kohteenDescription = kohteenDescription;
    }

    public String getSähkömail() {
        return sähkömail;
    }

    public void setSähkömail(String sähkömail) {
        this.sähkömail = sähkömail;
    }

    public String toString() {
        return
                nimi + "\n" +
                "Osoite: " + osoite + "\n" +
                "Rakennettu: " + rakennusyear + "\n" +
                "Koko: " + neliömäärä + "m^2\n" +
                "Vuokra: " + vuokra + " eur\n" +
                "Ehdot: " + muutEhdot + "\n" +
                "Sähköposti: " + sähkömail + "\n" +
                "Kuva: " + kuvaFile + "\n";
    }
}