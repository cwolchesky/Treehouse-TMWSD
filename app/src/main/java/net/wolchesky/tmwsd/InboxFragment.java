package net.wolchesky.tmwsd;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Chris on 9/21/2014.
 */
public class InboxFragment extends ListFragment {

    protected List<ParseObject> mMessages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setProgressBarIndeterminate(true);

        ParseQuery<ParseObject> query = new ParseQuery(ParseConstants.CLASS_MESSAGES);
        query.whereEqualTo(ParseConstants.KEY_RECIPIENT_IDS, ParseUser.getCurrentUser().getObjectId());
        query.addDescendingOrder(ParseConstants.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> messages, ParseException e) {
                getActivity().setProgressBarIndeterminate(false);

                if (e == null) {
                    //messages
                    mMessages = messages;
                    String[] usernames = new String[mMessages.size()];
                    int i = 0;
                    for (ParseObject message : mMessages) {
                        usernames[i] = message.getString(ParseConstants.KEY_SENDER_NAME);
                        i++;
                    }
                    MessageAdapter adapter = new MessageAdapter(
                            getView().getContext(),
                            mMessages
                    );
                    setListAdapter(adapter);
                }
            }
        });
    }
}
