package sg.edu.rp.c346.id18015362.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner taskSpinner;
    Button btnAdd;
    Button btnDelete;
    Button btnRemove;
    EditText etTask;
    ListView lvTodo;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskSpinner = findViewById(R.id.taskSpinner);
        etTask = findViewById(R.id.etTask);
        btnAdd = findViewById(R.id.addBtn);
        btnDelete = findViewById(R.id.deleteBtn);
        btnRemove = findViewById(R.id.removeBtn);
        lvTodo = findViewById(R.id.lvTodo);
        alTask = new ArrayList<>();

        taskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        etTask.setHint("Type in a new task here");
                        break;

                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        aaTask =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);

        lvTodo.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTask.clear();
                aaTask.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toastText = "";
                int pos = Integer.parseInt(etTask.getText().toString());
                if(alTask.size() == 0){
                    toastText = "Delete when no task is in the list";
                    Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_LONG).show();
                } else if(alTask.size() < pos + 1){
                    toastText = "Wrong index number";
                    Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_LONG).show();
                } else {
                    alTask.remove(pos);
                    aaTask.notifyDataSetChanged();
                }
                etTask.setText("");
            }
        });

    }
}
