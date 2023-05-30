package com.example.triviagame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

import java.util.*;

import javafx.scene.control.RadioButton;

import javax.swing.*;

public class TriviaGameController extends gameLogic {// to use the public function from the gameLogic

    final int ANSWERS = 4;//4 optional answer choices
    final ToggleGroup answer = new ToggleGroup();

    private qReposetory qr;// question reposetory object
    private int currentQ = 0;//let us know what Quetion are we
    private int input;//let us choose in the window if we press OK of Cancel
    private Integer[] allAnswer;// int array to shuffle number from 0 to 3
    private int score = 0;// player score
    private String correct;//to get the player string from the radio button
    private question questions[];// using it to get a shuffled array of questions
    private RadioButton[] rb;// array of radio buttons for 4 optional answers
    private RadioButton rbTemp;// to get the text from the input of the radio button pressed

    @FXML
    private Label numQuestionLabel;// label to output the number of question
    @FXML
    private Label scoreLabel;//label to output the player score
    @FXML
    private Label QuestionLabel;//label to output the question text
    @FXML
    private GridPane grid;//grid for the questions and 4 answers


    public void initialize() {
        qr = QuestionArray(questions);//putting inside qr a shuffled array of all Question from the TXT file
        correct = new String();//create new string to use
        rbTemp = new RadioButton();//create new radio button to use
        allAnswer = new Integer[ANSWERS];// create the 0-3 array so we could shuffle the radio buttons answers
        for (int i = 0; i < ANSWERS; i++) {
            allAnswer[i] = i;
        }
        buildRadio();//function the build all the grid
    }

    @FXML
    void SelectAnswer(ActionEvent event) {// after clicking on the radio button this get the press radio button and check if it is the correct answer

        if (answer.getSelectedToggle() != null) {// if the radio button is pressed
            rbTemp = (RadioButton) answer.getSelectedToggle();//get the pressed radio button
            correct = rbTemp.getText();// get the text from the press radio button
            if (correct.equals(qr.getqArray()[currentQ].getAnswer1())) {//if the text of the radio button is the correct answer
                JOptionPane.showConfirmDialog(null, "Correct Answer", "", JOptionPane.CLOSED_OPTION);//tell the player he is correct
                score = score + 10;//give the player 10 points
                nextQuestion();//function the represents the next question
            } else {
                JOptionPane.showConfirmDialog(null, "Wrong Answer", "", JOptionPane.CLOSED_OPTION);//tell the player he is wrong
                score = score - 5;// the player lose 5 points
                nextQuestion();//function the represents the next question
            }
            answer.getSelectedToggle().setSelected(false);// reset the radio button to see if it is pressed in the next question;
        } else {
            JOptionPane.showConfirmDialog(null, "Please Select An Answer", "", JOptionPane.CLOSED_OPTION);//the player didnt select an answer
        }
    }

    private void cleargrid() {//clear the grid
        grid.setGridLinesVisible(false);// clear the grid than add the new labels for second word
        grid.getColumnConstraints().clear();// clear the grid than add the new labels for second word
        grid.getRowConstraints().clear();// clear the grid than add the new labels for second word
        grid.getChildren().clear();// get the first grid made by the screen builder
        grid.setAlignment(Pos.TOP_LEFT);//setting the alignment
    }

    @FXML
    void endGame(ActionEvent event) {// finish corrent game get the score and player another one if wanted else quit
        restart();//function the restart the game if wanted
    }

    private void restart() {//function the restart the game if wanted
        input = JOptionPane.showOptionDialog(null, "Your score is " + score + " Play Again?", "Game Ended", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);//open a window with player score and to see if player want to play another game
        if (input == JOptionPane.OK_OPTION) {// if the player pressed ok
            cleargrid();//clean the grid
            score = 0;//set score to 0
            currentQ = 0;//reset the question
            scoreLabel.setText((""));//reset the score label
            ShuffleArrayQ(qr.getqArray());//shuffle the question to start a new game
            buildRadio();// create the buttons
        }//the played lose activate the game again with same word
        else {
            System.exit(0);//player didnt want to play another game
        }
    }


    @FXML
    void newGame(ActionEvent event) {// let the player start a new game
        cleargrid();//clean the grid
        score = 0;//set score to 0
        currentQ = 0;//reset the question
        scoreLabel.setText((""));//reset the score label
        ShuffleArrayQ(qr.getqArray());//shuffle the question to start a new game
        buildRadio();// create the buttons
    }

    private void nextQuestion() {
        scoreLabel.setText("Score:" + score);//add the score the player got
        cleargrid();//clean the grid
        currentQ++;// get the next question from the array
        buildRadio();//build the grid again
    }

    public void buildRadio() {
        if ((currentQ) != qr.getNumOfQuestions()) {// check if the questions are over
            QuestionLabel = new Label(); // create new label for Question
            numQuestionLabel.setText((currentQ + 1 + ""));//set the number of question to the player +1 because the arrray start from 0 but question is 1
            QuestionLabel.setText(qr.getqArray()[currentQ].getQuestion());//set the label to the question text
            QuestionLabel.setWrapText(true);//place all the text inside the label no matter the size of the string
            grid.setHgap(10); //horizontal gap in pixels
            grid.setVgap(10); //vertical gap in pixels
            grid.setPadding(new Insets(10, 10, 10, 10)); //margins around the whole grid
            grid.add(QuestionLabel, 0, 0);//add the label to the grid
            rb = new RadioButton[ANSWERS];// create the array to be the size of the answers
            ShuffleArray(allAnswer);//shuffle the integer array
            for (int i = 0; i < ANSWERS; i++) {
                rb[i] = new RadioButton(qr.getqArray()[currentQ].GetAnswerIn(allAnswer[i] + 1));//for radio button in index i give a random answer of the question
                rb[i].setToggleGroup(answer);//set the toogle group of all radiobuttons to be the same
                grid.add(rb[i], 0, i + 1);//place it in the grid under the label in a line
            }
        } else {
            restart();//function the restart the game if wanted
        }
    }


    private Integer[] ShuffleArray(Integer[] allAnswer) {// shuffle an integer array
        List<Integer> integerList = Arrays.asList(allAnswer);//set list of int to get the numbers from the array
        Collections.shuffle(integerList);//shuffle the list
        integerList.toArray(allAnswer);//return it to array
        return allAnswer;//return shuffle array
    }

}







































