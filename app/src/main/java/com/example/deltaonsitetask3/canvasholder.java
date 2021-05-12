package com.example.deltaonsitetask3;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class canvasholder extends Fragment {

    canvas can;
    endviewmodel res;
    Observer<Boolean> end, start;
    Handler handler;
    TextView time;
    Button retry;
    NavController nav;
    long starttime, miliseconds;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.canvasholder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        can = new canvas(requireContext());
        res = new ViewModelProvider(requireActivity()).get(endviewmodel.class);
        time = view.findViewById(R.id.time);
        retry = view.findViewById(R.id.retry);
        nav = Navigation.findNavController(view);

        retry.setVisibility(View.INVISIBLE);
        retry.setEnabled(false);

        handler = new Handler();






        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nav.navigate(R.id.action_canvasholder_self);
            }
        });



        res.getEnd().removeObservers(requireActivity());
        end = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    handler.removeCallbacks(runnable);
                    retry.setEnabled(true);
                    retry.setVisibility(View.VISIBLE);
                }

            }
        };
        res.getEnd().observe(requireActivity(), end);




        res.getStart().removeObservers(requireActivity());
        start = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);

                }
            }
        };
        res.getStart().observe(requireActivity(), start);

    }








    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            miliseconds = SystemClock.uptimeMillis() - starttime;
            time.setText(miliseconds + "ms");
            handler.postDelayed(this, 0);
        }
    };


}
