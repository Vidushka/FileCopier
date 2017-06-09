import service.CopyService;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vidushka on 19/05/17.
 */
public class Copier {
    CopyService copyService = new CopyService();
    private JPanel panelCoppier;
    private JLabel lblSource;
    private JLabel lblDestination;
    private JButton btnStart;
    private JButton btnStop;
    private JButton btnPause;
    private JComboBox comboOption;
    private JLabel lblHedding;
    private JButton btnSource;
    private JButton btnDestination;
    private JProgressBar progressBar;
    private JFileChooser selectFile;
    private String sourceLocation;
    private String destinationLocation;
    private String copyOption;

    Thread t1 = new Thread(() -> {
        copyOption = comboOption.getSelectedItem().toString();
        copyService.startCoping(sourceLocation, destinationLocation, copyOption, progressBar);
    });

    public void copyInitializer() {
        JFrame frame = new JFrame();
        frame.setTitle("Copier");
        frame.setSize(380, 140);
        frame.setResizable(false);
        frame.add(panelCoppier);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        comboOption.addItem("Copy");
        comboOption.addItem("Move");
        progressBar.setVisible(false);

        btnStart.addActionListener(e -> {
            t1.start();
        });

        btnStop.addActionListener(e -> {
            t1.interrupt();

            copyService.resetVAlues(progressBar);
            JOptionPane.showMessageDialog(panelCoppier, "Stopped coping!!");
            copyService.resetVAlues(progressBar);
            btnSource.setBackground(Color.decode("#E8E8E8"));
            btnDestination.setBackground(Color.decode("#E8E8E8"));
        });

        btnPause.addActionListener(e -> {

        });

        btnSource.addActionListener(e -> {
            try {
                selectFile = new JFileChooser();
                selectFile.setDialogTitle("Select a file");
                selectFile.showDialog(null, "Ok");
                sourceLocation = selectFile.getSelectedFile().getAbsolutePath();
                if (!sourceLocation.equals("null")) {
                    btnSource.setBackground(Color.decode("#00ffbb"));
                } else {
                    btnSource.setBackground(Color.decode("#E8E8E8"));
                }
            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null, "No file selected");
            }
        });

        btnDestination.addActionListener(e -> {
            try {
                selectFile = new JFileChooser();
                selectFile.setDialogTitle("Copy to");
                selectFile.showDialog(null, "Ok");
                selectFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                selectFile.setAcceptAllFileFilterUsed(false);
                destinationLocation = selectFile.getSelectedFile().getAbsolutePath();
                if (!destinationLocation.equals("null")) {
                    btnDestination.setBackground(Color.decode("#00ffbb"));
                } else {
                    btnDestination.setBackground(Color.decode("#E8DB45"));
                }
            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null, "Select a destination location\n" +
                        "& enter a file name(example.xxx)");
            }
        });
    }

}
