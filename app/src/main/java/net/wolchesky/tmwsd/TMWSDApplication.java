package net.wolchesky.tmwsd;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import net.wolchesky.tmwsd.utils.ParseConstants;

public class TMWSDApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "zMIlt7imGa6RkQPUrEbbz06Smysdi3osgo3ubnVY", "5HT8hUKWwOiIPGJnV1KK8oi3DzAi41x5VowBatwl");

        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    public static void updateParseInstallation(ParseUser user) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put(ParseConstants.KEY_USER_ID, user.getObjectId());
        installation.saveInBackground();
        Log.d("DEBUG", "Saved new installation information.");
    }
}
