package com.example.triviagame;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;


public class gameLogic {

    private qReposetory q;// qReposetory object

    public qReposetory QuestionArray(question[] allQuestion) { //getting the words from the txt file and putting them in a shuffle array
        try {
            List<String> stgWords = new ArrayList<String>();// list to get the words from the text
            Scanner words = new Scanner(new File("Trivia.txt"));// read the file
            String str;// new string to save the file
            while (words.hasNextLine()) {//if there is still words in the file
                str = words.nextLine();//put the words in the string
                stgWords.add(str);// add all the words to the list
            }
            allQuestion = new question[stgWords.size() / 5];//the number of question is the number of txt lines/5 for each parameter in question class
            for (int i = 0; i < stgWords.size() / 5; i++) {//for each question take place lines 0-4 than 5-9 ...  from the txt file and set the questions
                allQuestion[i] = new question(allQuestion, stgWords.size() / 5, stgWords.get(i * 5), stgWords.get((i * 5) + 1), stgWords.get((i * 5) + 2), stgWords.get((i * 5) + 3), stgWords.get((i * 5) + 4));
            }
            ShuffleArrayQ(allQuestion);// shuffle the question array to get random questions
            q = new qReposetory(allQuestion, stgWords.size() / 5);//set qReposetory

        } catch (IOException e) {
            System.out.println("Error");
        }
        return q;//retun qReposetory object with shuffle array and the number of questions
    }


    public question[] ShuffleArrayQ(question[] allQuestion) {//get a question array and shuffle it
        List<question> questionsList = Arrays.asList(allQuestion);// put the question array in a list
        Collections.shuffle(questionsList);//shuffle it
        questionsList.toArray(allQuestion);//return it to array
        return allQuestion;// return shuffle array
    }
}






