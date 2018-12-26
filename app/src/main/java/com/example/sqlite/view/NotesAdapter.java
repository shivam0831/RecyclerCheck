package com.example.sqlite.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.sqlite.R;
import com.example.sqlite.database.model.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Note> notesList;
    public static ArrayList<Note> imageNoteArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView note;
//        public TextView dot;
        public TextView timestamp;
        public CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.note);
//            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
            checkBox = view.findViewById(R.id.cb);
        }
    }


    public NotesAdapter(Context context, ArrayList<Note> imageNoteArrayList) {
        this.context = context;
        this.imageNoteArrayList = imageNoteArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Note note = imageNoteArrayList.get(position);

        holder.note.setText(note.getNote());

        // Displaying dot from HTML character code
//        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.timestamp.setText(formatDate(note.getTimestamp()));

        holder.checkBox.setChecked(imageNoteArrayList.get(position).getSelected());

        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkBox.getTag();
                Toast.makeText(context, imageNoteArrayList.get(pos).getNote() + " clicked!", Toast.LENGTH_SHORT).show();

                if (imageNoteArrayList.get(pos).getSelected()) {
                    imageNoteArrayList.get(pos).setSelected(false);
                } else {
                    imageNoteArrayList.get(pos).setSelected(true);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return imageNoteArrayList.size();
    }


    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
}
