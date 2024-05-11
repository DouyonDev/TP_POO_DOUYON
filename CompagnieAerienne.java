
public class CompagnieAerienne{
    private int id_comp;
    private String nom_comp;
    private int nbred_avion;
    private String mdp;
    private String siteWeb;
    private int admin_id; // Clé étrangère vers la table Admin

    // Constructeur
    public CompagnieAerienne(int id_comp, String nom_comp, int nbred_avion, String mdp, String siteWeb, int admin_id) {
        this.id_comp = id_comp;
        this.nom_comp = nom_comp;
        this.nbred_avion = nbred_avion;
        this.mdp = mdp;
        this.siteWeb = siteWeb;
        this.admin_id = admin_id;
    }

    public CompagnieAerienne() {
        
    }

    // Getters et setters
    public int getId_comp() {
        return id_comp;
    }

    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }

    public String getNom_comp() {
        return nom_comp;
    }

    public void setNom_comp(String nom_comp) {
        this.nom_comp = nom_comp;
    }

    public int getNbred_avion() {
        return nbred_avion;
    }

    public void setNbred_avion(int nbred_avion) {
        this.nbred_avion = nbred_avion;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

     // Méthodes pour gérer les vols
/*  public void ajouterVol(Vol vol) {
        vols.add(vol);
    }

    public void supprimerVol(Vol vol) {
        vols.remove(vol);
    }

    public void modifierVol(Vol vol, String nouveauDepart, String nouvelleDestination, String nouvelleDate) {
        vol.setDepart(nouveauDepart);
        vol.setDestination(nouvelleDestination);
        vol.setDate(nouvelleDate);
    }

    // Méthodes pour gérer les avions
    public void ajouterAvion(Avion avion) {
        avions.add(avion);
    }

    public void supprimerAvion(Avion avion) {
        avions.remove(avion);
    }

    public void modifierAvion(Avion avion, String nouveauModele, int nouvelleCapacite) {
        avion.setModele(nouveauModele);
        avion.setCapacite(nouvelleCapacite);
    }

    public String getNombreDAvion() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getNombreDAvion'");
    }*/
}