package org.lineageos.settings.dolby;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class DolbyTileService extends TileService {

    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
<<<<<<< HEAD
<<<<<<< HEAD
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
=======
        if (DolbyUtils.getInstance(getApplicationContext()).getDsOn()) {
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
=======
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
>>>>>>> dd2acc8 (marble: parts: Set proper summary for dolby settings)
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setState(Tile.STATE_INACTIVE);
        }
<<<<<<< HEAD
<<<<<<< HEAD
        tile.setSubtitle(dolbyUtils.getProfileName());
=======
>>>>>>> b194474 (marble: parts: Introduce Dolby Atmos)
=======
        tile.setSubtitle(dolbyUtils.getProfileName());
>>>>>>> dd2acc8 (marble: parts: Set proper summary for dolby settings)
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
