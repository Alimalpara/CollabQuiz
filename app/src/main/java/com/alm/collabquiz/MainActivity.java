package com.alm.collabquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alm.collabquiz.databinding.ActivityMainBinding;
import com.alm.collabquiz.model.QuestionsModel;
import com.alm.collabquiz.model.QuizEn;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView score,timer,tvquestion,option1,option2,correctans,quesno;
    private Button nextQue;
    Question  question;
    QuizEn selectedQuestion;
    ArrayList<Question> questions;
    ArrayList<QuizEn> quizEnArrayList;
    int index=0;
    ActivityMainBinding binding;
    CountDownTimer timer1;

    Fragment ResultFragment;

    int correctAnswers = 0;

    ArrayList<Boolean> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        quizEnArrayList = new ArrayList<>();
        list=new ArrayList<>();
/*

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "bezkoder.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<QuestionsModel>>() { }.getType();

        List<QuestionsModel> users = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < users.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + users.get(i));
        }
*/



        try {
            AssetManager assetManager = getAssets();
            InputStream ims = assetManager.open("questions.txt");

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(ims);

            QuestionsModel questionsModel = gson.fromJson(reader, QuestionsModel.class);

            if (questionsModel!=null){
               //Check Level of user from getUser API

                quizEnArrayList.addAll(questionsModel.levels.get(0).en);

//                if(questionsModel.getLevels()!=null&& questionsModel.getLevels().size()>0){
//                    data.addAll();
//                }

            }


        }catch(IOException e) {
            e.printStackTrace();
        }




        initViews();

        questions = new ArrayList<>();
        questions.add(new Question("Imam Ali a.s was born in","Qaba","Masjid","Qaba","He a.s was born in qaba"));
        questions.add(new Question("Imam Ali a.s birth date is","15 shaban","13th rajab","13th rajab","He a.s was born on 13th rajab"));
        questions.add(new Question("Imam Ali a.s mother name is","J. fatema binte asad s.a","J. khadija binte khuwelid s.a","J. fatema binte asad s.a","His mother name was fatema binte asad s.a"));
        questions.add(new Question("marhab was in which war","khandaq","khaibar","khaibar","He was in khaibar"));
        questions.add(new Question("Imam ali a.s died on","21st mahe ramdan","21st moharram","21st mahe ramdan","He was martyred on 21st mahe ramzan"));



        resetTimer();
//        setNextQuestion();
        setQuestions();
    }

    private void setQuestions() {
        if(timer1!=null)
            timer1.cancel();

        timer1.start();

        if(index < quizEnArrayList.size()){
            binding.queno.setText(String.format("%d/%d",(index+1),quizEnArrayList.size()));


            selectedQuestion = quizEnArrayList.get(index);
            binding.tvQuestion.setText(selectedQuestion.question);
            binding.tvOption1.setText(selectedQuestion.optA);
            binding.tvOption2.setText(selectedQuestion.optB);
            binding.tvCorrectans.setText(" ");
        }
    }


    void resetTimer(){
        timer1=new CountDownTimer(16000,1000) {
            @Override
            public void onTick(long l) {
   //to set time to our timer text
                binding.tvTimer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                if(index < quizEnArrayList.size()){
                    index++;
                    setQuestions();
                }


            }
        };
    }






  //to set questions
    void setNextQuestion(){
        //to restart the timer
        if(timer1!=null)
            timer1.cancel();

        timer1.start();

        if(index < questions.size()){

//To set quesno in our question text
            binding.queno.setText(String.format("%d/%d",(index+1),questions.size()));


            question = questions.get(index);
            binding.tvQuestion.setText(question.getQuestion());
            binding.tvOption1.setText(question.getOption1());
            binding.tvOption2.setText(question.getOption2());
            binding.tvCorrectans.setText(" ");




        }
        if(index>questions.size()-1){

           show_result();

        }
    }

    void checkAnswer(TextView textView, String selectedOpt){

        String selectedOption = textView.getText().toString();

        if(selectedOpt.equalsIgnoreCase(selectedQuestion.answer)){
            list.add(true);
            correctAnswers++;
            textView.setBackground(getResources().getDrawable(R.drawable.correctansdesign));
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_tick),null,null,null);
            textView.setCompoundDrawablePadding(50);
 //to set expalination
//            binding.tvCorrectans.setText(question.getExplanation());
        }
        else {// showAnswer(); uncomment to show answers
            list.add(false);
            textView.setBackground(getResources().getDrawable(R.drawable.wrongansdesign));
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_wrong),null,null,null);

    }
        //timer is used when option is selected and to reset the textviews background
        new CountDownTimer(2000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                if(index < questions.size()-1){
                    index++;
//to reset drawable
                    textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null);
                    setQuestions();
                }
                else {




                    Toast.makeText(MainActivity.this, " Quiz is completed", Toast.LENGTH_SHORT).show();
                    ResultFragment= new ResultFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("correct12",String.valueOf(correctAnswers));
                    bundle.putString("total12",String.valueOf(questions.size()));
                  /*  bundle.putInt("correct", 3);
                    bundle.putInt("total", 5);*/

                   /* bundle.putString("ABC","Hey there");
                    bundle.putString("QWE","there you");*/
                   // ResultFragment.setArguments(bundle);
                    show_result();
                }
                //sendData();

//Reset method
                reset();
                         }}.start();



    }


//to reset textview to og design in next question
    void reset(){
        binding.tvOption1.setBackground(getResources().getDrawable(R.drawable.optiondesign));
        binding.tvOption2.setBackground(getResources().getDrawable(R.drawable.optiondesign));
    }


//To set clickevent on options
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvOption1:
                if (timer1 != null)
                    timer1.cancel();
                TextView selected = (TextView) view;
                checkAnswer(selected,"opt_A");
                break;
            case R.id.tvOption2:

                //to stop timer when option is selected
                if (timer1 != null)
                    timer1.cancel();
                TextView selected1 = (TextView) view;
                checkAnswer(selected1,"opt_B");
                break;

        }
    }



    void show_result(){

        binding.fragmentContainer.setVisibility(View.VISIBLE);
        binding.cvQuizMain.setVisibility(View.GONE);
        ResultFragment= new ResultFragment();
        Bundle bundle=new Bundle();
       /* bundle.putInt("correct", correctAnswers);
        bundle.putInt("total", questions.size());*/

        bundle.putString("ABC","Hey there");
        bundle.putString("QWE","there you");
        ResultFragment.setArguments(bundle);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,ResultFragment).commit();



    }

    /** Initialization */
    private void initViews(){
        score = findViewById(R.id.tvScore);
        timer = findViewById(R.id.tvTimer);
        tvquestion = findViewById(R.id.tvQuestion);
        option1 =  findViewById(R.id.tvOption1);
        option2 =  findViewById(R.id.tvOption2);
        correctans =  findViewById(R.id.tvCorrectans);
        quesno=findViewById(R.id.queno);


    }
    //To show answers
    public void showAnswer(){
        if(question.getAnswer().equals(binding.tvOption1.getText().toString()))
            binding.tvOption1.setBackground(getResources().getDrawable(R.drawable.correctansdesign));
        else if(question.getAnswer().equals(binding.tvOption2.getText().toString()))
            binding.tvOption2.setBackground(getResources().getDrawable(R.drawable.correctansdesign));

    }

    public void sendData(){
        Bundle bundle=new Bundle();
        bundle.putString("correct", String.valueOf(correctAnswers));
        bundle.putString("total", String.valueOf(questions.size()));
// set Fragmentclass Arguments
        ResultFragment fragobj = new ResultFragment();

        fragobj.setArguments(bundle);
    }

}