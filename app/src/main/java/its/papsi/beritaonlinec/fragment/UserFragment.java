package its.papsi.beritaonlinec.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import its.papsi.beritaonlinec.FeedbackActivity;
import its.papsi.beritaonlinec.LoginActivity;
import its.papsi.beritaonlinec.R;
import its.papsi.beritaonlinec.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private TextView tvUserId;
    private Button btnLogout, btnFeedback;
    private SessionManager sessionManager;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUserId = view.findViewById(R.id.tv_user_id);
        btnLogout = view.findViewById(R.id.btn_logout);
        btnFeedback = view.findViewById(R.id.btn_feedback);

        sessionManager = new SessionManager(getContext());

        tvUserId.setText("User Id : " + sessionManager.getUserId());

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Konfirmasi");
                builder.setMessage("Anda yakin untuk logout?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManager.clearEditor();
                        Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                        startActivity(intentLogin);
                        getActivity().finish();
                    }
                });
                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }
}
