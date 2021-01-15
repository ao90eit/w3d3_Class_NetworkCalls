package com.aoinc.w3d3_class_networkcalls.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aoinc.w3d3_class_networkcalls.R;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileInfoFragment extends Fragment {

    @BindView(R.id.profile_pic_imageView)
    ImageView profilePicImageView;

    @BindView(R.id.profile_user_name_textView)
    TextView userNameTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    public void updateProfilePic(String picURL) {
        Glide.with(this)
                .load(picURL)
                .placeholder(R.drawable.ic_baseline_person_24)
                .circleCrop()
//                .transition(withCrossFade())
                .into(profilePicImageView);
    }

    public void updateUserName(String userName) {
        userNameTextView.setText("@" + userName);
    }
}
