package view.gui;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Analysis;
import view.ViewController;

public class AnalysisDialog extends JDialog {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public AnalysisDialog(MainFrame mainframe, ViewController controller) {
        Analysis analysis = controller.getController().getAnalysis();
        JPanel pAnalysis = new JPanel();
        pAnalysis.setBackground(Color.WHITE);
        pAnalysis.add(new JLabel(analysis.getDescription()));
        JButton bt = new JButton("Save Analysis");
        bt.addActionListener(e -> {
            final JFileChooser analysisChooser = new JFileChooser();
            analysisChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            analysisChooser.setDialogTitle("Choose a file");
            if (analysisChooser.showSaveDialog(mainframe) == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.getController().saveAnalysis(analysisChooser.getSelectedFile().getPath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "...");
                }
            }
        });
        this.pack();
        this.setVisible(true);
    }
}