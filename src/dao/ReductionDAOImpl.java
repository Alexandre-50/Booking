package dao;

import modele.Reduction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReductionDAOImpl implements ReductionDAO {
    private DaoFactory daoFactory;

    public ReductionDAOImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void addReduction(Reduction reduction) {
        String sql = "INSERT INTO reductions (description, pourcentage, dateDebut, dateFin) VALUES (?, ?, ?, ?)";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reduction.getDescription());
            stmt.setDouble(2, reduction.getPourcentage());
            stmt.setDate(3, reduction.getDateDebut());
            stmt.setDate(4, reduction.getDateFin());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateReduction(Reduction reduction) {
        String sql = "UPDATE reductions SET description = ?, pourcentage = ?, dateDebut = ?, dateFin = ? WHERE id_reductions = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reduction.getDescription());
            stmt.setDouble(2, reduction.getPourcentage());
            stmt.setDate(3, reduction.getDateDebut());
            stmt.setDate(4, reduction.getDateFin());
            stmt.setInt(5, reduction.getIdReduction());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReduction(int idReduction) {
        String sql = "DELETE FROM reductions WHERE id_reductions = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReduction);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reduction> getAllReductions() {
        List<Reduction> list = new ArrayList<>();
        String sql = "SELECT * FROM reductions";
        try (Connection conn = daoFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Reduction(
                        rs.getInt("id_reductions"),
                        rs.getString("description"),
                        rs.getDouble("pourcentage"),
                        rs.getDate("dateDebut"),
                        rs.getDate("dateFin")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Reduction getReductionById(int idReduction) {
        String sql = "SELECT * FROM reductions WHERE id_reductions = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReduction);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reduction(
                            rs.getInt("id_reductions"),
                            rs.getString("description"),
                            rs.getDouble("pourcentage"),
                            rs.getDate("dateDebut"),
                            rs.getDate("dateFin")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
