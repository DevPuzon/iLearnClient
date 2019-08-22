package com.softwaresolution.ilearnclient.Activity.MainActivityData;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.softwaresolution.ilearnclient.JsonData.AllData;
import com.softwaresolution.ilearnclient.R;

import java.util.List;

public class ListPreviewResult extends ArrayAdapter<String> {
    Activity context;
    List<String> listQuestion;
    List<String> listA;
    List<String> listB;
    List<String> listC;
    List<String> listD;
    List<String> listCorrect;
    List<String> listMyAns;
    int listview;
    public ListPreviewResult(Activity context ,int listview) {
        super(context, listview, AllData.dataPreviewResult.question);
        this.context = context;
        this.listview = listview;
        listQuestion = AllData.dataPreviewResult.question;
        listA = AllData.dataPreviewResult.a;
        listB = AllData.dataPreviewResult.b;
        listC = AllData.dataPreviewResult.c;
        listD = AllData.dataPreviewResult.d;
        listCorrect = AllData.dataPreviewResult.correctanswer;
        listMyAns = AllData.dataPreviewResult.myanswer;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(listview,null,true);
        TextView txt_isAnsw = (TextView) v.findViewById(R.id.txt_isAnswer);
        TextView txt_question = (TextView) v.findViewById(R.id.txt_question);
        TextView txt_a = (TextView) v.findViewById(R.id.txt_a);
        TextView txt_b = (TextView) v.findViewById(R.id.txt_b);
        TextView txt_c = (TextView) v.findViewById(R.id.txt_c);
        TextView txt_d = (TextView) v.findViewById(R.id.txt_d);
        TextView txt_correct = (TextView) v.findViewById(R.id.txt_correct);
        TextView txt_myasnwer = (TextView) v.findViewById(R.id.txt_myasnwer);

        txt_question.setText(AllData.dataPreviewResult.question.get(position));
        txt_a.setText("A. "+AllData.dataPreviewResult.a.get(position));
        txt_b.setText("B. "+AllData.dataPreviewResult.b.get(position));
        txt_c.setText("C. "+AllData.dataPreviewResult.c.get(position));
        txt_d.setText("D. "+AllData.dataPreviewResult.d.get(position));
        txt_correct.setText("Correct answer : "+AllData.dataPreviewResult.correctanswer.get(position));
        txt_myasnwer.setText("My answer : "+AllData.dataPreviewResult.myanswer.get(position));


        String correctAns = AllData.dataPreviewResult.correctanswer.get(position);
        String myans = AllData.dataPreviewResult.myanswer.get(position);

        if (correctAns.equals(myans)){
            txt_isAnsw.setBackgroundColor(context.getResources().getColor(R.color.correct));
            txt_isAnsw.setText("Correct answer");
        }else{
            txt_isAnsw.setBackgroundColor(context.getResources().getColor(R.color.wrong));
            txt_isAnsw.setText("Wrong answer");
        }
        return v;
    }
}
