package com.example.pawan.whatsupcleaner.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pawan.whatsupcleaner.datas.Details;
import com.example.pawan.whatsupcleaner.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    private Context ctx;

    private List<Details> datalist;

    private OnItemClickListener listener;



    public DetailsAdapter(Context ctx, List<Details> datalist, OnItemClickListener listener) {
        this.ctx = ctx;
        this.datalist = datalist;
        this.listener = listener;
    }
    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.main_content2, parent, false);
        return new DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.DetailsViewHolder detailsViewHolder, int positions) {

        Details details = datalist.get(positions);
        detailsViewHolder.title.setText(details.getTitle());
        detailsViewHolder.data.setText(String.valueOf(details.getData() + "MB"));
        detailsViewHolder.image.setCircleBackgroundColor(ContextCompat.getColor(detailsViewHolder.image.getContext(), details.getColor()));
        detailsViewHolder.image.setBorderColor(ContextCompat.getColor(detailsViewHolder.image.getContext(), details.getColor()));
        detailsViewHolder.image.setImageResource(details.getImage());

        final int pos = positions;
//        detailsViewHolder.data.setText(details.getData());

        detailsViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent;
                switch (pos) {
                    case 0:
                        /*//differnt activites for differnt cards
                        // TODO: 1/13/19 First we need to rector this into the mainActivity.java  class
                     */
                        if (listener != null)
                            listener.onImagesClicked();
                        break;
                    case 1:
                        if (listener != null)
                            listener.onDocumentsClicked();
                        break;

                    case 2:
                        if (listener !=null)
                            listener.onVideosClicked();
                        break;

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }


    public class DetailsViewHolder extends RecyclerView.ViewHolder {

        TextView title, data;
        CircleImageView image;
        CardView cardView;
        ProgressBar progressBar;

        public DetailsViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.data);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.card_view1);
        }
    }

    // TODO: 1/13/19 So we create an interface that calls main Activity when the user clicks on any item
    public interface OnItemClickListener {
        void onImagesClicked();
        void onDocumentsClicked();
        void onVideosClicked();
//        void onAudiosClicked();
//        void onGifsClicked();
//        void onWallpapersClicked();
//        void onVoiceClicked();



        //We will add more to this as the need arises for now w have to modify the constructor to provide an instance of this interface
    }

}
