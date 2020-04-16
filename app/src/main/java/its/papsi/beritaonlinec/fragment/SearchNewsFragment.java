package its.papsi.beritaonlinec.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import its.papsi.beritaonlinec.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchNewsFragment extends Fragment {

    public SearchNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_news, container, false);
    }
}
