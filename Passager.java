import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Passager extends Personne {
    private int idPassager;

    // Constructeur
    public Passager(int idPersonne, String nom, String prenom, String email, String numeroDeTelephone, Date dateDeNaissance, String motDePasse, int idPassager) {
        super(idPersonne, nom, prenom, email, numeroDeTelephone, dateDeNaissance, motDePasse);
        this.idPassager = idPassager;
    }

    // Getter et Setter spécifique à Passager
    public int getIdPassager() {
        return idPassager;
    }

    public void setIdPassager(int idPassager) {
        this.idPassager = idPassager;
    }

    public void inscription() throws SQLException {
        String insertionPersonneQuery = "INSERT INTO Personne (idPersonne, nom, prenom, email, numeroDeTelephone, dateDeNaissance, motDePasse) " +
                                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertionPassagerQuery = "INSERT INTO Passager (idPassager, idPersonne) VALUES (?, ?)";

        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement personneStatement = connection.prepareStatement(insertionPersonneQuery);
            PreparedStatement passagerStatement = connection.prepareStatement(insertionPassagerQuery)) {

            // Insérer dans la table Personne
            personneStatement.setInt(1, getIdPersonne());
            personneStatement.setString(2, getNom());
            personneStatement.setString(3, getPrenom());
            personneStatement.setString(4, getEmail());
            personneStatement.setString(5, getNumeroDeTelephone());
            personneStatement.setDate(6, (Date) getDateDeNaissance());
            personneStatement.setString(7, getMotDePasse());

            personneStatement.executeUpdate();

            // Insérer dans la table Passager
            passagerStatement.setInt(1, getIdPassager());
            passagerStatement.setInt(2, getIdPersonne());

            passagerStatement.executeUpdate();

            System.out.println("Passager inscrit avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription du passager : " + e.getMessage());
            throw e; // Propagation de l'exception pour une gestion supérieure
        }
    }

    //Methode faireReservation

    public void faireReservation(Date dateReservation, int nombreDePassager, String statut) throws SQLException {
        String insereReservation = "INSERT INTO Reservation (idPassager, dateReservation, nombreDePassager, status) " +
                                "VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement reservationStatement = connection.prepareStatement(insereReservation)) {

            // Insérer la réservation dans la base de données
            reservationStatement.setInt(1, getIdPassager());
            reservationStatement.setDate(2, dateReservation);
            reservationStatement.setInt(3, nombreDePassager);
            reservationStatement.setString(4, statut);

            reservationStatement.executeUpdate();

            System.out.println("Réservation effectuée avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la réservation : " + e.getMessage());
            throw e; // Propagation de l'exception pour une gestion supérieure
        }
    }

    //Methode modifierReservation

    public void modifierReservation(int idReservation, String nouveauStatut) throws SQLException {
        String modificationQuery = "UPDATE Reservation SET status = ? WHERE idReservation = ? AND idPassager = ?";

        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(modificationQuery)) {

            // Paramètres pour la modification de la réservation
            statement.setString(1, nouveauStatut);
            statement.setInt(2, idReservation);
            statement.setInt(3, getIdPassager());

            // Exécution de la requête de mise à jour
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Réservation modifiée avec succès !");
            } else {
                System.out.println("Aucune réservation trouvée avec l'ID : " + idReservation);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la réservation : " + e.getMessage());
            throw e; // Propagation de l'exception pour une gestion supérieure
        }
    }

    //Methode verifierStatutReservation

    public void verifierStatutReservation(int idReservation) throws SQLException {
        String verificationQuery = "SELECT status FROM Reservation WHERE idReservation = ? AND idPassager = ?";

        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(verificationQuery)) {

            // Paramètres pour la vérification du statut de la réservation
            statement.setInt(1, idReservation);
            statement.setInt(2, getIdPassager());

            // Exécution de la requête de sélection
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String statut = resultSet.getString("status");
                    System.out.println("Statut de la réservation (ID " + idReservation + ") pour ce passager : " + statut);
                } else {
                    System.out.println("Aucune réservation trouvée avec l'ID : " + idReservation);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification du statut de la réservation : " + e.getMessage());
            throw e; // Propagation de l'exception pour une gestion supérieure
        }
    }

    public boolean seConnecter(String email, String motDePasse) {
        String selectPassagerQuery = "SELECT idPersonne FROM Personne WHERE email = ? AND motDePasse = ?";
        try (Connection connection = ConnectDB.getConnection();
            PreparedStatement statement = connection.prepareStatement(selectPassagerQuery)) {
            // Paramètres pour la requête SELECT
            statement.setString(1, email);
            statement.setString(2, motDePasse);

            // Exécution de la requête SELECT
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idPersonne = resultSet.getInt("idPersonne");
                    System.out.println("Vous êtes connecté en tant que passager (ID : " + idPersonne + ")");
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
