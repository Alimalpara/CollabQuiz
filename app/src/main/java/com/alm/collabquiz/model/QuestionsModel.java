package com.alm.collabquiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

public class QuestionsModel implements Serializable {

    @SerializedName("levels")
    @Expose
    public List<QuizLevel> levels = null;












}