package com.example.deltaonsitetask3;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class START extends Fragment {

    Button start;
    TextView name;
    NavController nav;
    Animation buttonanim, textanim;
    CountDownTimer x;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.start, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nav = Navigation.findNavController(view);
        start = view.findViewById(R.id.startbutton);
        name = view.findViewById(R.id.name);
        buttonanim = AnimationUtils.loadAnimation(requireContext(), R.anim.startbutton);
        textanim = AnimationUtils.loadAnimation(requireContext(), R.anim.textstart);

        /*textanim.setRepeatCount(Animation.INFINITE);
        buttonanim.setRepeatCount(Animation.INFINITE);
        textanim.setRepeatMode(Animation.REVERSE);
        buttonanim.setRepeatMode(Animation.REVERSE);
*/



        start.setAnimation(buttonanim);
        name.setAnimation(textanim);
        repeat();





        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x.cancel();
                nav.navigate(R.id.action_START_to_canvasholder);
            }
        });
    }


    void repeat(){
        x =new CountDownTimer(3000,750){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                start.startAnimation(buttonanim);
                name.startAnimation(textanim);
                repeat();
            }
        };
        x.start();
    }


}
