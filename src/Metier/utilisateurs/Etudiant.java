    package Metier.utilisateurs;

    import Persistance.EtudiantDaoImp;

    import java.util.List;

    public class Etudiant extends Utilisateur  {
        private String id_etudiant;
        private EtudiantDaoImp etudiantDaoImp;

        public Etudiant(String nom, String prenom, String email,String id_etudiant,EtudiantDaoImp etudiantDaoImp) {
            super(nom, prenom, email);
            this.id_etudiant = id_etudiant;
            this.etudiantDaoImp = etudiantDaoImp;
        }
        public String getId_etudiant() {
            return id_etudiant;
        }
        public void setId_etudiant(String id_etudiant) {
            this.id_etudiant = id_etudiant;
        }

        public void addEtudiant() {
            if (etudiantDaoImp != null) {
                etudiantDaoImp.ajouterEtudiant(this);
            } else {
                throw new IllegalStateException("EtudiantDaoImp is not set");
            }
        }

        public void updateEtudiant(String id) {
            if (etudiantDaoImp != null) {
                etudiantDaoImp.modifierEtudiant(this);
            } else {
                throw new IllegalStateException("EtudiantDaoImp is not set");
            }
        }

        public void deleteEtudiant(String id) {
            if (etudiantDaoImp != null) {
                etudiantDaoImp.supprimerEtudiant(id);
            } else {
                throw new IllegalStateException("EtudiantDaoImp is not set");
            }
        }

        public static void displayEtudiantById(String id, EtudiantDaoImp dao) {
            Etudiant etudiant = dao.getEtudiantById(id);
            if (etudiant != null) {
                System.out.println("Nom: " + etudiant.getNom());
                System.out.println("Prenom: " + etudiant.getPrenom());
                System.out.println("Email: " + etudiant.getEmail());
                System.out.println("ID Etudiant: " + etudiant.getId_etudiant());
                System.out.println("---------------");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        }

        public void afficherAllEtudiants() {
            if (etudiantDaoImp != null) {
                List<Etudiant> etudiants = etudiantDaoImp.getAllEtudiants();
                for (Etudiant etudiant : etudiants) {
                    System.out.println("Nom: " + etudiant.getNom());
                    System.out.println("Prenom: " + etudiant.getPrenom());
                    System.out.println("Email: " + etudiant.getEmail());
                    System.out.println("ID Etudiant: " + etudiant.getId_etudiant());
                    System.out.println("---------------");
                }
            } else {
                throw new IllegalStateException("EtudiantDaoImp is not set");
            }
        }


        @Override
        public void afficher() {
            afficherAllEtudiants();

        }

        @Override
        public void ajouterUtilisateur(Utilisateur utilisateur) {
            addEtudiant();
        }

        @Override
        public void modifierUtilisateur(Utilisateur utilisateur) {
            updateEtudiant(((Etudiant) utilisateur).getId_etudiant());
        }

        @Override
        public void getUtilisateur(String id) {
            if (etudiantDaoImp != null) {
                displayEtudiantById(id, etudiantDaoImp);
            } else {
                throw new IllegalStateException("EtudiantDaoImp is not set");
            }

        }

        @Override
        public void supprimerUtilisateur(String id) {
            deleteEtudiant(id);
        }

        @Override
        public void checkUtilisateur(Utilisateur utilisateur) {
            if (utilisateur instanceof Etudiant) {
                Etudiant etudiant = (Etudiant) utilisateur;
                if (etudiantDaoImp.etudiantExistsByEmail(etudiant.getEmail())) {
                    System.out.println("Student with email " + etudiant.getEmail() + " already exists.");
                } else {
                    System.out.println("No student found with email " + etudiant.getEmail() + ".");
                }
            } else {
                System.out.println("The user is not an Etudiant.");
            }

        }
    }
