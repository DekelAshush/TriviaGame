package com.example.triviagame;

public class qReposetory extends gameLogic {

    private question[] qArray;
    int numOfQuestions;
    public qReposetory(question[] qArray,int numOfQuestions) {//constractor
        this.qArray = qArray;
        this.numOfQuestions = numOfQuestions;//the number of questions
    }

    //get and set functions
    public question[] getqArray() {
        return qArray;
    }
    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setqArray(question[] qArray) {
        this.qArray = qArray;
    }

    public void setNumOfQuestions(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }


}