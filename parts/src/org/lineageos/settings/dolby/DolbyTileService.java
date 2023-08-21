package org.lineageos.settings.dolby;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class DolbyTileService extends TileService {

    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
<<<<<<< HEAD
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
=======
        if (DolbyUtils.getInstance(getApplicationContext()).getDsOn()) {
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setState(Tile.STATE_INACTIVE);
        }
<<<<<<< HEAD
        tile.setSubtitle(dolbyUtils.getProfileName());
=======
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
        tile.updateTile();
        super.onStartListening();
    }

    @Override
    public void onClick() {
        Tile tile = getQsTile();
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
            dolbyUtils.setDsOn(false);
            tile.setState(Tile.STATE_INACTIVE);
        } else {
            dolbyUtils.setDsOn(true);
            tile.setState(Tile.STATE_ACTIVE);
        }
        tile.updateTile();
        super.onClick();
    }
}
