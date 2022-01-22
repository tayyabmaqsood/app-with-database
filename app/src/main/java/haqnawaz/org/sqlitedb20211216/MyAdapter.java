package haqnawaz.org.sqlitedb20211216;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<StudentModel> studentModellist;


    public MyAdapter(List<StudentModel> studentModellist, Context context){
        this.studentModellist = studentModellist;
        this.context = context;
    }

    private Context context;
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

        holder.studentRowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                removeStudent(student);
                return false;
            }
        });
    }

    private void removeStudent(StudentModel student) {
        DbHelper dbHelper = new DbHelper(context);
        dbHelper.deleteStudent(student);
        int position = studentModellist.indexOf(student);
        studentModellist.remove(position);
        Toast.makeText(context, "Item Deleted at position " + (position + 1),Toast.LENGTH_SHORT).show();
        notifyItemRemoved(position);

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

