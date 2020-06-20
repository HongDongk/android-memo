package org.richcode.campusdiary.Dialog;

import org.richcode.campusdiary.DataClass.DiaryData;
import org.richcode.campusdiary.DataClass.HomeWorkData;
import org.richcode.campusdiary.DataClass.MemoData;

/**
 * Created by charlie on 2017. 8. 18..
 */

public interface MyDialogListener {
    public void onPositiveClicked(String input);
    public void onMemoClicked(MemoData input);
    public void onHomeWorkClicked(HomeWorkData input);
    public void onDiaryClicked(DiaryData input);
    public void onNegativeClicked();
}
