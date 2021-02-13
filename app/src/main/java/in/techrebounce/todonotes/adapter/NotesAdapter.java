package in.techrebounce.todonotes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.techrebounce.todonotes.R;
import in.techrebounce.todonotes.clicklisteners.ItemClickListener;
import in.techrebounce.todonotes.model.Notes;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private final List<Notes> listNotes;
    private final ItemClickListener itemClickListener;

    public NotesAdapter(List<Notes> notes, ItemClickListener itemClickListener) {
        this.listNotes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        // bind the data from list to viewholder
        Notes note = listNotes.get(position);
        String title = note.getTitle();
        String description = note.getDescription();
        holder.textViewTitle.setText(title);
        holder.textViewDescription.setText(description);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle, textViewDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}
