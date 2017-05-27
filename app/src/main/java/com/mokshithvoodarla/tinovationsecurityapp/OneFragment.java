package com.mokshithvoodarla.tinovationsecurityapp;

/**
 * Created by Mokshith on 11/24/16.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OneFragment extends Fragment{
    List<ProfileInfo> result;
    String username;
    String time;
    String info1 = "";
    String personName = "";
    DatabaseReference root;
    ArrayList<String> info;
    ProfileInfo ci;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        username = getActivity().getIntent().getExtras().get("Username").toString();
        root = FirebaseDatabase.getInstance().getReference().child(username).child("Stream");

        final RecyclerView recList = (RecyclerView) getView().findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("An event was added!");
                ArrayList<String> data = new ArrayList<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    data.add(((DataSnapshot)i.next()).getKey());
                }

                int index = username.indexOf("@");
                String sub = username.substring(0, index);
                Log.v("sree", "username == > " + sub);
                ProfileAdapter adapter = new ProfileAdapter(createList(data), sub, time);
                recList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private List<ProfileInfo> createList(final ArrayList<String> times) {
        result = new ArrayList<>();
        for (int j=0; j < times.size(); j++) {
            time = times.get(j);
            doIt(time);

        }

        return result;
    }
    public void doIt(final String time){
        System.out.println("I just entered doIt");
        root.child(time).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                info1 = dataSnapshot.child("event").getValue().toString();
                personName = dataSnapshot.child("person").getValue().toString();
                ci = new ProfileInfo();
                ci.name=personName;
                ci.description=username;
                String timeUpdated = time.substring(time.indexOf("m")+1);
                timeUpdated = timeUpdated.substring(0,2)+":"+timeUpdated.substring(2);
                ci.action1 = timeUpdated + (timeUpdated.startsWith("a") ? " AM" : " PM");
                ci.action2 = info1;
                result.add(ci);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}