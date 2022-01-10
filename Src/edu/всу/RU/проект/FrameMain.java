package edu.vsu.ru.project;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;
import ru.vsu.cs.util.ArrayUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;


public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JTable tableOfArray;
    private JButton buttonLoadDataFromFile;
    private JButton buttonFillWithRandomNumbers;
    private JButton buttonSaveToOriginalFile;
    private JTextField textFieldAnswer;
    private JButton buttonSaveInFinalFile;
    private JButton buttonAnalyseArray;
    private JPanel JPanel;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;


    public FrameMain() {
        this.setTitle("TASK_8");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableOfArray, 50, true, true, true, true);
        tableOfArray.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("FrameMain");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        JTableUtils.writeArrayToJTable(tableOfArray, new int[][]{
                {0, 1, 2, 3, 4},
                {11, 12, 13, 14, 5},
                {10, 9, 8, 7, 6}
        });

        this.pack();

        buttonLoadDataFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] arr = ArrayUtils.readIntArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableOfArray, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonFillWithRandomNumbers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = ArrayUtils.createRandomIntMatrix(
                            tableOfArray.getRowCount(), tableOfArray.getColumnCount(), 100);
                    JTableUtils.writeArrayToJTable(tableOfArray, matrix);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSaveToOriginalFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOfArray);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        ArrayUtils.writeArrayToFile(file, matrix);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonAnalyseArray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOfArray);

                    if (AnalyseArray.analyseArrayForMonotone(matrix)) {
                        textFieldAnswer.setText("Array is monotone");
                    } else {
                        textFieldAnswer.setText("Array isn't monotone");
                    }

                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSaveInFinalFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String result = textFieldAnswer.getText();
                        String file = fileChooserSave.getSelectedFile().getPath();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        fileOutputStream.write(result.getBytes(StandardCharsets.UTF_8));
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

    }
}
