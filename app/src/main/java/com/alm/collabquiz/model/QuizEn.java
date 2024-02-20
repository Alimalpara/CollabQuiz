package com.alm.collabquiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuizEn implements Serializable {
    @SerializedName("answer")
    @Expose
    public String answer;
    @SerializedName("opt_A")
    @Expose
    public String optA;
    @SerializedName("opt_B")
    @Expose
    public String optB;
    @SerializedName("question")
    @Expose
    public String question;
    @SerializedName("type")
    @Expose
    public String type;
}
