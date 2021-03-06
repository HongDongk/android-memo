package org.richcode.campusdiary.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.richcode.campusdiary.DataBase.DBHelper;
import org.richcode.campusdiary.DataClass.DiaryData;
import org.richcode.campusdiary.DataClass.HomeWorkData;
import org.richcode.campusdiary.DataClass.MemoData;
import org.richcode.campusdiary.R;

public class DialogContentMemo extends Dialog implements View.OnClickListener {

    Context context;
    MyDialogListener dialogListener;
    MemoData memoData;

    TextView ContentText;
    TextView DateText;

    Button OkButton;
    Button DeleteButton;
    Button ReviseButton;

    DBHelper dbHelper;

    public DialogContentMemo(@NonNull Context context, MemoData memoData) {
        super(context);
        this.context = context;
        this.memoData = memoData;
    }

    public void setDialogListener(MyDialogListener dialogListener){
        this.dialogListener = dialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contentmemo);

        dbHelper = new DBHelper(getContext(),"DataBase.db",null,2);

        ContentText = (TextView)findViewById(R.id.contentmemo_content);
        DateText = (TextView)findViewById(R.id.contentmemo_date);
        OkButton = (Button)findViewById(R.id.button_contentmemo_complete);
        DeleteButton = (Button)findViewById(R.id.memo_delete_button);
        ReviseButton = (Button)findViewById(R.id.memo_revise_button);

        ContentText.setText(memoData.getContent());
        DateText.setText(memoData.getDate());

        OkButton.setOnClickListener(this);
        DeleteButton.setOnClickListener(this);
        ReviseButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_contentmemo_complete:
                dismiss();
                break;
            case R.id.memo_delete_button:

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("삭제 하시겠습니까?");

                alertDialogBuilder
                        .setMessage("삭제할까요?")
                        .setCancelable(false)
                        .setPositiveButton("삭제",
                                new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        dbHelper.MemoDelete(memoData.getId());
                                        dialogInterface.cancel();
                                        dialogListener.onNegativeClicked();
                                        dismiss();
                                    }
                                })
                        .setNegativeButton("취소",
                                new OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                break;
            case R.id.memo_revise_button:
                DialogReviseMemo dialogReviseMemo = new DialogReviseMemo(getContext(),memoData);
                dialogReviseMemo.setCanceledOnTouchOutside(false);
                dialogReviseMemo.setDialogListener(new MyDialogListener() {
                    @Override
                    public void onPositiveClicked(String input) {

                    }

                    @Override
                    public void onMemoClicked(MemoData input) {
                        dialogListener.onMemoClicked(input);
                    }

                    @Override
                    public void onHomeWorkClicked(HomeWorkData input) {

                    }

                    @Override
                    public void onDiaryClicked(DiaryData input) {

                    }

                    @Override
                    public void onNegativeClicked() {

                    }
                });
                dialogReviseMemo.show();
                dismiss();
                break;
        }

    }
}
