package com.example.sqlite.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sqlite.R;
import com.example.sqlite.database.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NextAdapter extends RecyclerView.Adapter<NextAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Note> iNoteArrayList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView note;
        //        public TextView dot;
        public TextView timestamp;
        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.note);
//            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
            checkBox = view.findViewById(R.id.cb);
        }
    }


    public NextAdapter(Context context, ArrayList<Note> iNoteArrayList) {
        this.context = context;
        this.iNoteArrayList = iNoteArrayList;
    }

    @Override
    public NextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new NextAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NextAdapter.ViewHolder holder, int position) {
        Note note = iNoteArrayList.get(position);

        if (NotesAdapter.imageNoteArrayList.get(position).getSelected())
        {
            holder.note.setText(note.getNote());
            holder.timestamp.setText(formatDate(note.getTimestamp()));
        }

        holder.note.setText(note.getNote());
        holder.timestamp.setText(formatDate(note.getTimestamp()));


//        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(iNoteArrayList.get(position).getSelected());

        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkBox.getTag();

                if (iNoteArrayList.get(pos).getSelected()) {
                    iNoteArrayList.get(pos).setSelected(false);
                } else {
                    iNoteArrayList.get(pos).setSelected(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
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
