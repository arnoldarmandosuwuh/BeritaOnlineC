package its.papsi.beritaonlinec.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import its.papsi.beritaonlinec.R;
import its.papsi.beritaonlinec.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private ArrayList<News> data;
    private NewsAdapterListener newsAdapterListener;

    public NewsAdapter(Context context, ArrayList<News> data) {
        this.context = context;
        this.data = data;
    }

    public interface NewsAdapterListener {
        void onItemClickListener(News news);
    }

    public void setData(ArrayList<News> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setNewsAdapterListener(NewsAdapterListener newsAdapterListener) {
        this.newsAdapterListener = newsAdapterListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_news_item, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;
        private TextView tvTitle, tvAuthor, tvCreatedAt;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.iv_image);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvCreatedAt = itemView.findViewById(R.id.tv_created_at);
        }

        public void bind(final News news) {
            tvTitle.setText(news.getTitle());
            tvAuthor.setText(news.getAuthor());
            tvCreatedAt.setText(news.getCreatedAt());

            Glide.with(context)
                    .load(news.getImageUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newsAdapterListener.onItemClickListener(news);
                }
            });
        }
    }
}
