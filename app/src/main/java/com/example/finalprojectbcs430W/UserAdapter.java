package com.example.finalprojectbcs430W;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewUserName;
        public TextView textViewUserEmail;

        public MyViewHolder(View view) {
            super(view);
            textViewUserName = (TextView) view.findViewById(R.id.textViewUserNameForRecyclerView);
            textViewUserEmail=(TextView) view.findViewById(R.id.textViewUserEmailForRecyclerView);
        }
    }
    public UserAdapter(List<User> userList){
        this.userList= userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
        View  userView = LayoutInflater.from(group.getContext())
                .inflate(R.layout.user_recyclerview_item, group,false);
        return new MyViewHolder(userView);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        User user = userList.get(position);

        String userName = user.getName();
        String userEmail = user.getEmail();

        holder.textViewUserName.setText(userName);
        holder.textViewUserEmail.setText(userEmail);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setData(List<User> userList)
    {
        this.userList = userList;
        notifyDataSetChanged();
    }
}
