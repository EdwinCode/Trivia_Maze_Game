package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    private static final String TITLE = "Hodgepodge Trivia Maze";

    private static final ImageIcon mazeIcon = new ImageIcon("src//View//Images//TriviaMazeIcon.jpg");

    private JFrame mainFrame;

    private JPanel adminPanel;

    private JPanel mainPanel;

    private JLabel gameTitle;

    private GamePanel gamePanel;

    private TriviaMazeBrain triviaMazeBrain;

    public MainMenuPanel(JFrame mainFrame, TriviaMazeBrain triviaMazeBrain) {
        this.mainFrame = mainFrame;
        this.triviaMazeBrain = triviaMazeBrain;

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        gameTitle = new JLabel(TITLE);
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(gameTitle, BorderLayout.NORTH);

        mainPanel.add(setupButtonPanel(), BorderLayout.CENTER);

        mainPanel.add(new JLabel(mazeIcon), BorderLayout.SOUTH);

        mainFrame.add(mainPanel);

        mainPanel.setVisible(true);
    }

    private JPanel setupButtonPanel() {
        JPanel mainMenuBtnPanel = new JPanel();

        JButton btnNewGame = new JButton("New Game");
        mainMenuBtnPanel.add(btnNewGame);

        JButton btnLoadGame = new JButton("Load Game");
        mainMenuBtnPanel.add(btnLoadGame);

        //JButton btnAddQuestion = new JButton("Admin Settings");
        //mainMenuBtnPanel.add(btnAddQuestion);

        addActionListener(btnNewGame, "New Game");
        addActionListener(btnLoadGame, "Load Game");
        //addActionListener(btnAddQuestion, "Admin Settings");

        return mainMenuBtnPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    private void addActionListener(JButton button, String buttonName) {
        if (buttonName.equals("New Game")) {
            button.addActionListener(
                    e -> {
                        mainPanel.setVisible(false);
                        gamePanel = new GamePanel(mainFrame, triviaMazeBrain);
                    }
            );
        }
        else if (buttonName.equals("Load Game")) {
            button.addActionListener(
                    e -> {
                        // call deserialize method
                        mainPanel.setVisible(false);
                        gamePanel = new GamePanel(mainFrame, triviaMazeBrain);
                    }
            );
        }
        else {
            button.addActionListener(
                    e -> {
                        mainPanel.setVisible(false);
                        new AdminPanel(mainFrame, triviaMazeBrain);
                    }
            );
        }
    }
}