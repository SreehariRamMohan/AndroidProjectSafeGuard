package com.mokshithvoodarla.tinovationsecurityapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import static com.mokshithvoodarla.tinovationsecurityapp.R.id.imageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<ProfileInfo> contactList;
    private String username;
    private String picturename;
    public ProfileAdapter(List<ProfileInfo> contactList) {
        this.contactList = contactList;


    }

    // Create a storage reference from our app
    //private StorageReference storageRef = storage.getReference();

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(final ProfileViewHolder contactViewHolder, int i) {
        ProfileInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.description.setText(ci.description);
        contactViewHolder.imageView.setImageBitmap(ci.bitmap);


//        contactViewHolder.imageView.setImageResource(R.drawable.aki);

        contactViewHolder.vAction1.setText(ci.action1);
        contactViewHolder.vAction1.setClickable(false);
        //contactViewHolder.vAction2.setText(ci.action2);
        //contactViewHolder.vAction2.setClickable(false);
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new ProfileViewHolder(itemView);
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView description;
        protected ImageView imageView;
        protected Button vAction1;
        //protected Button vAction2;

        public ProfileViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.name);
            description =  (TextView) v.findViewById(R.id.description);
            imageView = (ImageView)  v.findViewById(R.id.imageView);
            vAction1 = (Button)  v.findViewById(R.id.btnOne);
            //vAction2 = (Button) v.findViewById(R.id.btnTwo);
        }
    }
}