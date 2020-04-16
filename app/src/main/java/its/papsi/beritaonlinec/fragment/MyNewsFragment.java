package its.papsi.beritaonlinec.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import its.papsi.beritaonlinec.R;
import its.papsi.beritaonlinec.SessionManager;
import its.papsi.beritaonlinec.UtilMessage;
import its.papsi.beritaonlinec.adapter.NewsAdapter;
import its.papsi.beritaonlinec.model.News;

import static its.papsi.beritaonlinec.GlobalVariable.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyNewsFragment extends Fragment {
    
    private RecyclerView rvList;
    private NewsAdapter newsAdapter;
    private UtilMessage utilMessage;
    private SessionManager sessionManager;
    
    public MyNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        rvList = view.findViewById(R.id.rv_list);

        utilMessage = new UtilMessage(getActivity());
        sessionManager = new SessionManager(getContext());
        newsAdapter = new NewsAdapter(getContext(), new ArrayList<News>());
        newsAdapter.setNewsAdapterListener(new NewsAdapter.NewsAdapterListener() {
            @Override
            public void onItemClickListener(News news) {
                Toast.makeText(getContext(), "Clicked: " + news.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(newsAdapter);

        getData();
    }

    private void getData() {
        utilMessage.showProgressBar("Getting news...");
        StringRequest request = new StringRequest(Request.Method.GET,
                BASE_URL + "ambil_berita.php?id_penulis=" + sessionManager.getUserId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        utilMessage.dismissProgressBar();

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray jsonData = jsonResponse.getJSONArray("data");

                            ArrayList<News> data = new ArrayList<>();
                            for (int index = 0; index < jsonData.length(); index++) {
                                JSONObject item = jsonData.getJSONObject(index);

                                News news = new News();
                                news.setImageUrl(item.getString("gambar"));
                                news.setTitle(item.getString("judul"));
                                news.setContent(item.getString("isi"));
                                news.setAuthor(item.getString("nama_penulis"));
                                news.setCreatedAt(item.getString("created_at"));

                                data.add(news);
                            }

                            newsAdapter.setData(data);

                        } catch (JSONException e) {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        utilMessage.dismissProgressBar();
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(getContext()).add(request);
    }
}
