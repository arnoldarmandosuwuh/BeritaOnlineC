package its.papsi.beritaonlinec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import its.papsi.beritaonlinec.model.News;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView ivImage;
    private TextView tvTitle, tvAuthor, tvCreatedAt, tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        ivImage = findViewById(R.id.iv_image);
        tvTitle = findViewById(R.id.tv_title);
        tvAuthor = findViewById(R.id.tv_author);
        tvCreatedAt = findViewById(R.id.tv_created_at);
        tvContent = findViewById(R.id.tv_content);

        // ambil data parcelable nya
        // cast??? konversi
        News news = (News) getIntent().getExtras().get("data");

        setData(news);
    }

    private void setData(News news) {

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(news.getTitle());
        }

        Glide.with(this)
                .load(news.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .into(ivImage);

        tvTitle.setText(news.getTitle());
        tvAuthor.setText(news.getAuthor());
        tvCreatedAt.setText(news.getCreatedAt());
        tvContent.setText(news.getContent());
    }
}
