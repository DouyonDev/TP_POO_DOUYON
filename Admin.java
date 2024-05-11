import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Admin extends Personne {
    private int idAdmin;

    // Constructeur
    public Admin(int idPersonne, String nom, String prenom, String email, String numeroDeTelephone, Date dateDeNaissance, String motDePasse, int idAdmin) {
        super(idPersonne, nom, prenom, email, numeroDeTelephone, dateDeNaissance, motDePasse);
        this.idAdmin = idAdmin;
    }

    // Getter et Setter spécifique à Admin
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }


    public void ajouterCompagnie(CompagnieAerienne compagnie) throws SQLException {
        String ajoutComp = "INSERT INTO Compagnie (nomCompagnie, nombreDAvion, motDePasse, siteWeb, idAdmin) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(ajoutComp)) {
            statement.setString(1, compagnie.getNom_comp());
            statement.setInt(2, compagnie.getNbred_avion());
            statement.setString(3, compagnie.getMdp());
            statement.setString(4, compagnie.getSiteWeb());
            statement.setInt(5, this.getIdAdmin()); // Utilise l'ID de l'admin courant
            statement.executeUpdate();
        }
    }

    public void supprimerCompagnie(int idCompagnie) throws SQLException {
        String supprComp = "DELETE FROM Compagnie WHERE idCompagnie = ?";
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(supprComp)) {
            statement.setInt(1, idCompagnie);
            statement.executeUpdate();
        }
    }

    public void modifierCompagnie(CompagnieAerienne compagnie) throws SQLException {
        String modifComp = "UPDATE Compagnie SET nomCompagnie = ?, nombreDAvion = ?, motDePasse = ?, siteWeb = ? WHERE idCompagnie = ?";
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(modifComp)) {
            statement.setString(1, compagnie.getNom_comp());
            statement.setInt(2, compagnie.getNbred_avion());
            statement.setString(3, compagnie.getMdp());
            statement.setString(4, compagnie.getSiteWeb());
            statement.setInt(5, compagnie.getId_comp());
            statement.executeUpdate();
        }
    }

    public boolean seConnecter(String email, String motDePasse) {
        String selectAdminQuery = "SELECT idPersonne FROM Personne WHERE email = ? AND motDePasse = ?";
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectAdminQuery)) {
            // Paramètres pour la requête SELECT
            statement.setString(1, email);
            statement.setString(2, motDePasse);

            // Exécution de la requête SELECT
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idPersonne = resultSet.getInt("idAdmin");
                    System.out.println("Vous êtes connecté en tant qu'administrateur (ID : " + idPersonne + ")");
                    return true; // Connexion réussie
                } else {
                    System.out.println("Identifiants incorrects. Connexion échouée.");
                    return false; // Connexion échouée (identifiants incorrects)
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion : " + e.getMessage());
            return false; // Connexion échouée en raison d'une exception SQL
        }
    }
}
