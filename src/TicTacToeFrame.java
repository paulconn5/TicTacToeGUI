import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeFrame extends JFrame {

    private JButton[][] buttons; // 2D array of buttons
    private JButton quitButton;
    private char[][] board; // game board
    private char currentPlayer; // current player (X or O)
    private boolean gameOver; // game over flag

    public TicTacToeFrame() {
        // initialize the game
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;

        // set up the JFrame
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));

        // create an array of buttons for the squares
        buttons = new JButton[3][3];

        // create and add the buttons to the panel
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].addActionListener(new ButtonListener(row, col));
                buttonPanel.add(buttons[row][col]);
            }
        }

        // create and add the quit button to the JFrame
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitListener());
        add(quitButton, BorderLayout.SOUTH);

        // add the button panel to the JFrame
        add(buttonPanel, BorderLayout.CENTER);

        // set the size and make the JFrame visible
        setSize(800, 800);
        setVisible(true);
    }

    // inner class for the quit button listener
    private class QuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // inner class for the button listener
    private class ButtonListener implements ActionListener {
        private int row;
        private int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            if (!gameOver) {
                if (board[row][col] == '\u0000') {
                    // set the square to the current player
                    board[row][col] = currentPlayer;
                    buttons[row][col].setText(Character.toString(currentPlayer));

                    // check for a win or a tie
                    if (checkWin(currentPlayer)) {
                        JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                        gameOver = true;
                        playAgain();

                    } else if (checkTie()) {
                        JOptionPane.showMessageDialog(null, "Tie game!");
                        gameOver = true;
                        playAgain();
                    } else {
                        // switch to the other player
                        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Square already taken.");
                }
            }
        }
    }
    //check for a win
    private boolean checkWin(char player) {
        // check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        // no winner
        return false;
    }


    // check for a tie


    private boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '\u0000') { //if == null (\u0000)
                    // there is an empty square, game is not over
                    return false;
                }
            }
        }
        // all squares are filled and no winner, it's a tie
        return true;
    }

    private void playAgain() {
        int option = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            // reset board
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board[row][col] = '\u0000';
                    buttons[row][col].setText("");
                    buttons[row][col].setEnabled(true);
                }
            }
            currentPlayer = 'X';
            gameOver = false;
        } else {
            System.exit(0);
        }
    }

}




