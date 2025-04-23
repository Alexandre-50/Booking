package dao;
import modele.Disponibilites;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateDisponibleDAOImpl implements DateDisponibleDAO
{

    private DaoFactory daoFactory;
    public DateDisponibleDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Disponibilites> getDisponibilitesParLogement(int idLogement)
    {
        List<Disponibilites> disponibilites = new ArrayList<>();
        String query = "SELECT * FROM disponibilites WHERE id_logements = ? ORDER BY dateDisponible";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement statement = connexion.prepareStatement(query))
        {
            statement.setInt(1, idLogement);
            try (ResultSet rs = statement.executeQuery())
            {
                while (rs.next())
                {
                    Disponibilites dispo = new Disponibilites(
                            rs.getInt("id"),
                            rs.getInt("id_logements"),
                            rs.getDate("dateDisponible").toLocalDate(),
                            rs.getBoolean("estDisponible")
                    );
                    disponibilites.add(dispo);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return disponibilites;
    }
}
