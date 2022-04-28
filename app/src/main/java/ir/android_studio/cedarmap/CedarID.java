package ir.android_studio.cedarmap;

import android.app.Application;

import com.cedarstudios.cedarmapssdk.CedarMaps;
import com.cedarstudios.cedarmapssdk.model.MapID;

public class CedarID extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CedarMaps.getInstance()
                .setClientID("ferdowscouncil-1825468867077990982")
                .setClientSecret("cO6v72ZlcmRvd3Njb3VuY2lsjs_YP9etgV8wI3KZyx5Fez2uf5Uc_y1nSdKl-7dsXt4=")
                .setContext(this)
                .setMapID(MapID.MIX);
    }

}
