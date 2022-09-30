package edu.northeastern.numad22fa_jiyoonjeong;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This is a recyclerview adapter class, the purpose of this class is to act as a bridge between the
 * collection (arraylist) and the view (recyclerview). This class provides 3 methods that are
 * utilised for binding the data to the view. The explanation of each method is provided in comments
 * within the methods.
 */
public class LinkAdapter extends RecyclerView.Adapter<LinkViewHolder> {

    private final List<Link> links;
    private final Context context;

    /**
     * Creates a PersonAdapter with the provided arraylist of Person objects.
     *
     * @param links    arraylist of url link list Name object.
     * @param context   context of the activity used for inflating layout of the viewholder.
     */
    public LinkAdapter(List<Link> links, Context context) {
        this.links = links;
        this.context = context;
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create an instance of the viewholder by passing it the layout inflated as view and no root.
        return new LinkViewHolder(LayoutInflater.from(context).inflate(R.layout.item_link, null));
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        // sets the name of the website to the name textview of the viewholder.
        holder.name.setText(links.get(position).getName());
        // sets the url of the website to the age textview of the viewholder.
        holder.url.setText(links.get(position).getUrl());

        // when value is int
        //holder.url.setText(String.valueOf(links.get(position).getUrl()));

        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String urlparse = links.get(holder.getAdapterPosition()).getUrl();
                if (!urlparse.startsWith("http://") && !urlparse.startsWith("https://"))
                    urlparse = "http://" + urlparse;

//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(holder.getAdapterPosition()).getUrl())));
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlparse)));
            }
        });

        // set a CLICK event on the whole itemView (every element of the recyclerview).
        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, links.get(position).getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        // Returns the size of the recyclerview that is the list of the arraylist.
        return links.size();
    }
}

