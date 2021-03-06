/*
 * ============================================================================
 * Copyright (C) 2017 Kaltura Inc.
 * 
 * Licensed under the AGPLv3 license, unless a different license for a
 * particular library is specified in the applicable library path.
 * 
 * You may obtain a copy of the License at
 * https://www.gnu.org/licenses/agpl-3.0.html
 * ============================================================================
 */

package com.kaltura.playkit.player;

import android.net.Uri;

import com.kaltura.playkit.PKMediaConfig;
import com.kaltura.playkit.PKMediaEntry;
import com.kaltura.playkit.PKMediaSource;
import com.kaltura.playkit.PKRequestParams;
import com.kaltura.playkit.PKTrackConfig;

/**
 * Created by Noam Tamim @ Kaltura on 29/03/2017.
 */
class PKMediaSourceConfig {

    PKMediaSource mediaSource;
    PKRequestParams.Adapter adapter;
    PKMediaEntry.MediaEntryType mediaEntryType;

    PKTrackConfig preferredAudioTrackConfig;
    PKTrackConfig preferredTextTrackConfig;

    boolean useTextureView;
    boolean cea608CaptionsEnabled;

    PKMediaSourceConfig(PKMediaConfig mediaConfig, PKMediaSource source, PKRequestParams.Adapter adapter, boolean cea608CaptionsEnabled, boolean useTextureView, PKTrackConfig audioTrackConfig, PKTrackConfig textTrackConfig) {
        this.adapter = adapter;
        this.mediaSource = source;
        this.useTextureView = useTextureView;
        this.cea608CaptionsEnabled = cea608CaptionsEnabled;
        this.mediaEntryType = mediaConfig.getMediaEntry().getMediaType();
        this.preferredAudioTrackConfig = audioTrackConfig;
        this.preferredTextTrackConfig = textTrackConfig;
    }

    Uri getUrl() {
        Uri uri = Uri.parse(mediaSource.getUrl());
        if (adapter == null) {
            return uri;
        } else {
            return adapter.adapt(new PKRequestParams(uri, null)).url;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PKMediaSourceConfig that = (PKMediaSourceConfig) o;

        if (mediaSource != null ? !mediaSource.equals(that.mediaSource) : that.mediaSource != null) {
            return false;
        }
        return adapter != null ? adapter.equals(that.adapter) : that.adapter == null;
    }

    @Override
    public int hashCode() {
        int result = mediaSource != null ? mediaSource.hashCode() : 0;
        result = 31 * result + (adapter != null ? adapter.hashCode() : 0);
        return result;
    }
}
