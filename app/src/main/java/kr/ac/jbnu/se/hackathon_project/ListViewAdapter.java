package kr.ac.jbnu.se.hackathon_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private TextView eventText;
    private TextView player1;
    private TextView player2;
    private TextView place;
    private TextView time;

    private ArrayList<MatchData> MatchDataList = new ArrayList<MatchData>();

    @Override
    public int getCount() {
        return MatchDataList.size();
    }

    @Override
    public MatchData getItem(int i) {
        return MatchDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        eventText = (TextView) view.findViewById(R.id.event);
        player1 = (TextView) view.findViewById(R.id.player1);
        player2 = (TextView) view.findViewById(R.id.player2);
        place = (TextView) view.findViewById(R.id.place);
        time = (TextView) view.findViewById(R.id.time);

        MatchData matchData = MatchDataList.get(i);

        eventText.setText(matchData.getEvent());
        player1.setText(matchData.getPlayer1());
        player2.setText(matchData.getPlayer2());
        place.setText(matchData.getPlace());
        time.setText((CharSequence) matchData.getTime());

        return view;
    }

    public void add(MatchData matchData){
        MatchDataList.add(matchData);
    }
}
