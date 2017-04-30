package interviewmaster.admin.interview.com.androidex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ADMIN on 29-04-2017.
 */

public class RetroAdapter extends RecyclerView.Adapter<RetroAdapter.Myview> {
    // LayoutInflater layoutInflater;
    Context c;
    //   Employee item;
    List<Example> examples;

    public RetroAdapter(Context applicationContext, List<Example> examples1) {
//        layoutInflater = LayoutInflater.from(c.getApplicationContext());
        this.examples = examples1;
        this.c = applicationContext;
    }

    public class Myview extends RecyclerView.ViewHolder {

        TextView textView, textView2;
        ImageView imageView;

        public Myview(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public Myview onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);       // View view = .inflate(R.layout.design, parent, false);
        View row = inflater.inflate(R.layout.design, parent, false);
        Myview v = new Myview(row);

        return v;
    }

    @Override
    public void onBindViewHolder(Myview holder, int position) {
        StringBuilder stringBuilder=new StringBuilder();
        StringBuilder stringBuilder1=new StringBuilder();

        Employee item = examples.get(0).getEmployee().get(position);

//        Log.d("u",item.getImageURL());

        for(int gh=0;gh<item.getSkills().get(0).getExtraCurricular().size();gh++){
            stringBuilder1.append(item.getSkills().get(0).getExtraCurricular().get(gh));
            holder.textView.setText(stringBuilder1);
        }

        for (int m = 0; m < item.getSkills().get(0).getTechnical().size(); m++) {
            stringBuilder.append(item.getSkills().get(0).getTechnical().get(m));
            holder.textView2.setText(stringBuilder);
        }
        Picasso.with(c).load(item.getImageURL()).into(holder.imageView);


    }


    @Override

    public int getItemCount() {
        if (examples.get(0).getEmployee().size() == 0) {
            return 0;
        } else {
            return examples.get(0).getEmployee().size();
        }
    }
}
