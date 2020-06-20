package org.richcode.campusdiary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.richcode.campusdiary.Adapter.DiaryCardAdapter;
import org.richcode.campusdiary.DataBase.DBHelper;
import org.richcode.campusdiary.DataClass.DiaryData;
import org.richcode.campusdiary.DataClass.HomeWorkData;
import org.richcode.campusdiary.DataClass.MemoData;
import org.richcode.campusdiary.Dialog.DialogEditDiary;
import org.richcode.campusdiary.Dialog.MyDialogListener;

import java.util.ArrayList;

public class FragmentDiary extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<DiaryData> diaryDataArrayList;
    ArrayList<DiaryData> CopyDataList;

    Button EditButton;

    DBHelper dbHelper;

    public FragmentDiary() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);



        dbHelper = new DBHelper(getContext(),"DataBase.db",null,2);

        diaryDataArrayList = new ArrayList<DiaryData>();
        CopyDataList = dbHelper.getResultDiaryData();

        for(int i=CopyDataList.size()-1; i>=0; i--){
            diaryDataArrayList.add(CopyDataList.get(i));
        }

        recyclerView = view.findViewById(R.id.cardview_diary);
        EditButton = (Button)view.findViewById(R.id.diary_edit_button);

        layoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DiaryCardAdapter(diaryDataArrayList,getContext(),getActivity());
        recyclerView.setAdapter(adapter);


        EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogEditDiary dialogEditDiary = new DialogEditDiary(getContext());
                dialogEditDiary.setCanceledOnTouchOutside(false);
                dialogEditDiary.setDialogListener(new MyDialogListener() {
                    @Override
                    public void onPositiveClicked(String input) {
                    }

                    @Override
                    public void onMemoClicked(MemoData input) {

                    }

                    @Override
                    public void onHomeWorkClicked(HomeWorkData input) {

                    }

                    @Override
                    public void onDiaryClicked(DiaryData input) {

                    }

                    @Override
                    public void onNegativeClicked() {
                        refresh();
                    }
                });
                dialogEditDiary.show();
            }
        });


        return view;
    }

    public void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }

}
