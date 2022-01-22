package haqnawaz.org.sqlitedb20211216;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<StudentModel> studentModellist;


    public MyAdapter(List<StudentModel> studentModellist, Context context){
        this.studentModellist = studentModellist;
        this.context = context;
    }

    public Activity mcontext;


    private Context context;
    private int clicks = 0;
    private int positionclick = -1;

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        int clickposition = position;
        StudentModel student = studentModellist.get(position);
        holder.studentRowView.setText(student.toString());
        holder.studentRowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positionclick == clickposition)
                    clicks += 1;
                    if(clicks == 2) {
                        editStudent(student);
                    }
                else {
                        positionclick = clickposition;
                        clicks = 1;
                    }
            }
        });

        //<== Remove student ==>
        holder.studentRowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view){
                removeStudent(student);
                return false;
            }
        });
    }

    private void editStudent(StudentModel student) {
       showCustomDialog(student);
    }

    public void showCustomDialog(StudentModel student){
        int position = studentModellist.indexOf(student);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.layout_dialog);
        EditText editname = dialog.findViewById(R.id.edit_name);
        EditText editage =   dialog.findViewById(R.id.edit_age);
        editname.setText(student.getName());
        editage.setText(Integer.toString(student.getAge()));

        Button editSubmitButton =  dialog.findViewById(R.id.UpdateStudent);
        editSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                student.setName(editname.getText().toString());
                student.setAge(Integer.parseInt(editage.getText().toString()));
                studentModellist.set(position, student);
                Toast.makeText(context, "Item update at position " + (position + 1),Toast.LENGTH_SHORT).show();
                notifyItemChanged(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    // Delete Student From Recycler View
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

