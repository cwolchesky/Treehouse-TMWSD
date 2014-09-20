package net.wolchesky.tmwsd;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class TMWSDApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "zMIlt7imGa6RkQPUrEbbz06Smysdi3osgo3ubnVY", "5HT8hUKWwOiIPGJnV1KK8oi3DzAi41x5VowBatwl");

        ParseObject testObject = new ParseObject("testObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
