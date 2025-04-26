package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import dao.ReductionDAO;
import dao.ReductionDAOImpl;
import dao.DaoFactory;
import modele.Reduction;

public class GestionReductionsPanel extends JFrame {
    private ReductionDAO reductionDAO = new ReductionDAOImpl(DaoFactory.getInstance("projet_booking_bdd", "root", ""));
    private JTable table;
    private DefaultTableModel model;

    public GestionReductionsPanel() {
        setTitle("Gérer les Réductions");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"ID", "Description", "%", "Date début", "Date fin"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton ajouterBtn = new JButton("Ajouter");
        JButton modifierBtn = new JButton("Modifier");
        JButton supprimerBtn = new JButton("Supprimer");

        ajouterBtn.addActionListener(e -> new AjouterReductionForm(this, reductionDAO));
        modifierBtn.addActionListener(e -> new ModifierReductionForm(this, reductionDAO));
        supprimerBtn.addActionListener(e -> supprimerReduction());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        rafraichirTableau();
        setVisible(true);
    }

    public void rafraichirTableau() {
        model.setRowCount(0);
        List<Reduction> reductions = reductionDAO.getAllReductions();
        for (Reduction r : reductions) {
            model.addRow(new Object[]{r.getIdReduction(), r.getDescription(), r.getPourcentage(), r.getDateDebut(), r.getDateFin()});
        }
    }

    private void supprimerReduction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int idReduction = (int) model.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cette réduction ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                reductionDAO.deleteReduction(idReduction);
                rafraichirTableau();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une réduction.");
        }
    }
}