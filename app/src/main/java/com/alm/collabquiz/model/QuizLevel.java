package com.alm.collabquiz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuizLevel implements Serializable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("en")
    @Expose
    public List<QuizEn> en = null;
}
