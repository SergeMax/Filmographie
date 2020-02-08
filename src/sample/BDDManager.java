package sample;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;
/**
 Created by cladlink on 12/03/16.
 */

class BDDManager {
    private final String BDD_URL = "jdbc:mysql://localhost:3306";
    private final String BDD_USER = "root";
    private final String BDD_PASSWORD =  "";
    private Connection connection;
    private Statement statement;

    /**
     * start()
     * sert à initialiser la connexion à la BDD
     */
    public void start()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection (BDD_URL ,BDD_USER,BDD_PASSWORD);
            statement = connection.createStatement();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * stop()
     * Sert à rompre la connexion avec le BDD
     */
    public void stop()
    {

        try
        {
            connection.close();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * edit
     * Sert pour l'envoie de toutes requêtes sauf les SELECT
     * @param requete ()
     */
    public void edit(String requete)
    {
        System.out.println(requete);
    //    try
        {
    //        statement.executeUpdate(requete);
        }
    //    catch (SQLException e)
        {
     //       e.printStackTrace();
        }
    }

    /**
     * ask(String Requete)
     * Sert à envoyer au serveur toute requête de type SELECT
     * @param requete ()
     * @return ()
     */
    public ArrayList<ArrayList<String>> ask(String requete)
    {
        System.out.println(requete);
        ArrayList<ArrayList<String>> select = new ArrayList<>();

        try
        {
            ResultSet rs = statement.executeQuery(requete);
            ResultSetMetaData rsmd = rs.getMetaData();
            int nbcols = rsmd.getColumnCount();

            int i=0;
            while(rs.next())
            {
                select.add(new ArrayList<>());
                for (int j = 1; j <= nbcols; j++)
                    select.get(i).add(rs.getString(j));
                i++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return select;

    }

    /**
     * lire()
     * permet de lire un fichier sql et de l'exécuter
     */
    public void lire(String adressSQLFile)
    {
        BufferedReader lecture;
        String fichier = "", fichierTemp;
        String[] requete;
        try
        {
            lecture = new BufferedReader(new FileReader(adressSQLFile));
            try
            {
                while ((fichierTemp = lecture.readLine()) != null)
                {
                    fichier += fichierTemp;
                    fichier += " ";
                }
                requete = fichier.split(";");
                for (int i = 0; i<requete.length; i++)
                {
                    requete[i] += ";";
                    System.out.println(i);
                    System.out.println(requete[i]);
                    if (requete[i].contains("SELECT"))
                        this.ask(requete[i]);
                    else
                        this.edit(requete[i]);
                }
            }
            catch (EOFException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    lecture.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        catch (FileNotFoundException e)
        {
            System.err.println("le fichier est introuvable");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * main
     * ce main n'est utiliser que pour créer les tables.
     */

}
