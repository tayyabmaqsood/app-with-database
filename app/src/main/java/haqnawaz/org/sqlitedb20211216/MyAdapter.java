package haqnawaz.org.sqlitedb20211216;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<StudentModel> studentModellist;
    private Context context;

    public MyAdapter(List<StudentModel> studentModellist, Context context){
        this.studentModellist = studentModellist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        StudentModel student = studentModellist.get(position);
        holder.studentRowView.setText(student.toString());
    }

    @Override
    public int getItemCount() {
        return studentModellist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView studentRowView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentRowView = (TextView)itemView.findViewById(R.id.studentRowView);
        }
    }
}

