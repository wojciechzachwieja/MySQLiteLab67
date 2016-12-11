package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.chart.mysqlite.R;
import com.example.chart.mysqlite.db.DataManagerImpl;
import com.example.chart.mysqlite.model.object.GroupModel;
import com.example.chart.mysqlite.table.object.Student;
import com.example.chart.mysqlite.table.object.StudentDAO;
import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static List<Student> names;
    private String mParam1;
    private String mParam2;
    private DataManagerImpl dataManager;
    public BlankFragment() {
    }

    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dataManager = new DataManagerImpl(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        StudentDAO studentDAO = new StudentDAO(getContext());
        ArrayList<Student> students = studentDAO.getStudents();
        names = new ArrayList<>(students);

        final ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(getContext(),
                android.R.layout.simple_list_item_single_choice, names);

        final ListView listView = (ListView) view.findViewById(R.id.myList);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = names.get(position);
                BlankFragment2 firstFragment = BlankFragment2.newInstance(student.getName(), student.getSurname());

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, firstFragment).commit();
                return true;
            }
        });

        Button button = (Button) view.findViewById(R.id.delete_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listView.getCheckedItemPosition() != -1) {
                    Student student = names.get(listView.getCheckedItemPosition());
                    if (student != null) {
                        StudentDAO student1 = new StudentDAO(getContext());
                        student1.delete(student);
                        names.remove(student);
                        adapter.notifyDataSetChanged();
                        Fragment currentFragment = BlankFragment.this;
                        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
                        fragTransaction.replace(R.id.fragment, currentFragment).commit();
                    }
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listViewGroups = (ListView) getActivity().findViewById(R.id.myList2);
                ArrayAdapter<GroupModel> groups = new ArrayAdapter<GroupModel>(getContext(), android.R.layout.simple_list_item_1,
                        dataManager.getStudent(names.get(position).getIdStudent()).getGroups());
                listViewGroups.setAdapter(groups);
            }
        });
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        return view;
    }
}
