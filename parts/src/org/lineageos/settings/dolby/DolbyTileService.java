package org.lineageos.settings.dolby;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class DolbyTileService extends TileService {

    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
=======
        if (DolbyUtils.getInstance(getApplicationContext()).getDsOn()) {
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
=======
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
>>>>>>> f5f0794 (sm6150-common: parts: Set proper summary for dolby settings)
=======
        DolbyUtils dolbyUtils = DolbyUtils.getInstance(getApplicationContext());
        if (dolbyUtils.getDsOn()) {
>>>>>>> parent of 1ff3778 (sm6150-common: parts: Introduce Dolby Atmos)
            tile.setState(Tile.STATE_ACTIVE);
        } else {
            tile.setState(Tile.STATE_INACTIVE);
        }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        tile.setSubtitle(dolbyUtils.getProfileName());
=======
>>>>>>> dc22fa2 (sm6150-common: parts: Introduce Dolby Atmos)
=======
        tile.setSubtitle(dolbyUtils.getProfileName());
>>>>>>> f5f0794 (sm6150-common: parts: Set proper summary for dolby settings)
=======
        tile.setSubtitle(dolbyUtils.getProfileName());
>>>>>>> parent of 1ff3778 (sm6150-common: parts: Introduce Dolby Atmos)
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
