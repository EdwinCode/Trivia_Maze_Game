package View;

import Controller.TriviaMazeBrain;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final JFrame mainFrame;

    MazePanel MP;
    DirectionButtonPanel DP;

    private final int ROW = 4;
    private final int COL = 4;

    private final JPanel gamePanel;
    private final JMenuBar mainMenuBar;

    private final TriviaMazeBrain triviaMazeBrain;
    private QuestionPanel questionPanel;
    private AnswerPanel answerPanel;

    public GamePanel(JFrame mainFrame, TriviaMazeBrain triviaMazeBrain) {
        this.mainFrame = mainFrame;

        this.triviaMazeBrain = triviaMazeBrain;

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(2,2));

        // sets up these panels and adds to gamePanel
        MP = new MazePanel(gamePanel, triviaMazeBrain);
        DP = new DirectionButtonPanel(gamePanel, MP, triviaMazeBrain);
        questionPanel = new QuestionPanel(gamePanel);
        answerPanel = new AnswerPanel(gamePanel, triviaMazeBrain, questionPanel, DP);

        // display menuBar on JFrame only when game is in progress
        mainMenuBar = new JMenuBar();
        setupMenuBar("Admin Settings");
        setupMenuBar("About Hodgepodge Team");
        setupMenuBar("Game Info");
        setupMenuBar("Save Game");
        setupMenuBar("Exit Game");

        mainFrame.add(gamePanel);
        mainFrame.setJMenuBar(mainMenuBar);

        gamePanel.setVisible(true);

    }

    public void askQuestion(String[] questionList, String directionType) {
        DP.disableAllButtons();
        questionPanel.createQuestion(questionList);
        answerPanel.createQuestionType(questionList, directionType);
    }

    public MazePanel getMP() {
        return MP;
    }

    private void setupMenuBar(String menuTitle) {
        final JMenu addMenu = new JMenu(menuTitle);

        if (menuTitle.equals("Admin Settings")) {
            final JCheckBoxMenuItem menuCheckBoxItem = new JCheckBoxMenuItem("Enable Debug Feature");
            addMenuActionListener(menuCheckBoxItem, menuTitle);
            addMenu.add(menuCheckBoxItem);
        }

        else {
            final JMenuItem menuItem = new JMenuItem(menuTitle);

            addMenuActionListener(menuItem, menuTitle);

            addMenu.add(menuItem);
        }

        mainMenuBar.add(addMenu);

        mainMenuBar.setVisible(true);
    }

    void resetDirectionButtonPanel() {
        DP = new DirectionButtonPanel(gamePanel, MP, triviaMazeBrain);
    }

    private void addMenuActionListener(JMenuItem menuItem, String menuName) {
        if (menuName.equals("Game Info")) {
            menuItem.addActionListener(
                    e -> {
                        gamePanel.setVisible(false);
                        mainMenuBar.setVisible(false);
                        new GameInfo(mainFrame, gamePanel, mainMenuBar);
                    }
            );
        }
        else if (menuName.equals("About Hodgepodge Team")) {
            menuItem.addActionListener(
                    e -> {
                        gamePanel.setVisible(false);
                        mainMenuBar.setVisible(false);
                        new AboutTeam(mainFrame, gamePanel, mainMenuBar);
                    }
            );
        }
        else if (menuName.equals("Admin Settings")) {
            menuItem.addActionListener(
                    e -> {
                        System.out.println("Admin settings changed");
                    }
            );
        }
        // edit to include save game option
        // exit game should check if game is saved
        // if not saved prompt for saving, else:
        else {
            menuItem.addActionListener(
                    e -> {
                        gamePanel.setVisible(false);
                        mainMenuBar.setVisible(false);
                        new MainMenuPanel(mainFrame, triviaMazeBrain);
                        triviaMazeBrain.resetGameState();
                        resetDirectionButtonPanel();
                    }
            );
        }
    }
}
