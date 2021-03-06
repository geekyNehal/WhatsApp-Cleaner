package com.example.pawan.whatsupcleaner.adapters.innerAdapeters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.pawan.whatsupcleaner.datas.FileDetails;
import com.example.pawan.whatsupcleaner.R;

import java.io.File;
import java.util.ArrayList;


public class InnerDetailsAdapter_doc extends RecyclerView.Adapter<InnerDetailsAdapter_doc.InnerDataViewHolder> {

    private Context ctx;
    ArrayList<FileDetails> innerDataList;
    private OnCheckboxlistener onCheckboxlistener;

    public InnerDetailsAdapter_doc(Context ctx, ArrayList<FileDetails> innerDataList, OnCheckboxlistener onCheckboxlistener){
        this.ctx = ctx;
        this.innerDataList = innerDataList;
        this.onCheckboxlistener = onCheckboxlistener;
    }

    @Override
    public InnerDetailsAdapter_doc.InnerDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.doc_content, parent, false);

        return new InnerDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InnerDetailsAdapter_doc.InnerDataViewHolder innerDataViewHolder, final int positions) {


        final FileDetails details = innerDataList.get(positions);
        innerDataViewHolder.tittle_name.setText(details.getName());
        innerDataViewHolder.data.setText(String.valueOf(details.getSize()));
       // Log.e("size ", "Size" + details.getSize());

        final int pos = positions;


        innerDataViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File a = new File(details.getPath());
                Uri file = FileProvider.getUriForFile(ctx, ctx.getApplicationContext().getPackageName() +
                        ".my.package.name.provider",a);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(file, "application/pdf");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                ctx.startActivity(intent);



            }
        });

        // FIXME: 1/26/19

        innerDataViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onCheckboxlistener != null) {
                      onCheckboxlistener.onCheckboxClicked(buttonView, innerDataViewHolder.getAdapterPosition());
                  }

            }
        });

   }

    @Override
    public int getItemCount() {
        return innerDataList.size();
    }

    public class InnerDataViewHolder extends RecyclerView.ViewHolder  {

        TextView tittle_name, data;
        CardView cardView;
        CheckBox checkBox;

        public InnerDataViewHolder(View itemView) {
            super(itemView);

            tittle_name = itemView.findViewById(R.id.title);
            data = itemView.findViewById(R.id.data);
            cardView = itemView.findViewById(R.id.card_view1);
            checkBox = itemView.findViewById(R.id.checkbox);

        }

    }

    public interface OnCheckboxlistener{
        void onCheckboxClicked(View view, int pos);
    }
}
