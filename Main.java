import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /* 
        // Création d'une instance de la classe Admin avec des valeurs factices
        Admin admin = new Admin(1, "GOITA", "Assimi", "assimigoita@gmail.com", null, null, null, 0);

        // Création d'une instance de la classe CompagnieAerienne
        CompagnieAerienne compagnie = new CompagnieAerienne(1, "Air Mali", 0, null, null, 0);

        // Utilisation de la méthode getNom() pour récupérer le nom de l'admin
        admin.ajouterCompagnie(compagnie);

        // Affichage du nom de l'admin
        // System.out.println("Nom de l'admin : " + idAdmin);*/

        try {
            // Création d'une instance de la classe Admin
            Admin admin = new Admin(1, "GOITA", "Assimi", "abdoulayedouyon@gmail.com", null, null, "adouyon", 1);

    
            // Création d'une instance de la classe CompagnieAerienne
            //CompagnieAerienne compagnie = new CompagnieAerienne(1, "Air Mali", 0, null, null, admin.getIdAdmin());
            //CompagnieAerienne compagnie2 = new CompagnieAerienne(2, "Air Burkina", 0, null, null, admin.getIdAdmin());
    
            // Appel de la méthode ajouterCompagnie() de l'objet Admin
            //admin.ajouterCompagnie(compagnie2);
            admin.supprimerCompagnie(5);

    
        // Tentative de connexion avec des identifiants valides
        boolean isConnected = admin.seConnecter("abdoulayedouyon@gmail.com", "adouyon");
        if (isConnected) {
            // Réaliser des opérations après la connexion réussie...
        }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la compagnie : " + e.getMessage());
        }
    }
}

