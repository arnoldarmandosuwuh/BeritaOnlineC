package its.papsi.beritaonlinec;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class UtilMessage {
    private Activity activity;
    private Dialog progressDialog;

    public UtilMessage(Activity activity) {
        this.activity = activity;
    }

    public void showProgressBar(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View view = activity.getLayoutInflater()
                .inflate(R.layout.progressbar, null);
        TextView progressText = view.findViewById(R.id.tv_progress_text);

        if (text != null && !text.isEmpty()) {
            progressText.setText(text);
        }
        builder.setView(view);
        builder.setCancelable(false);

        progressDialog = builder.create();
        progressDialog.show();
    }

    public void dismissProgressBar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
