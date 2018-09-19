package com.niraj.sparkprofile;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class SparkGlideModule extends AppGlideModule {

    public SparkGlideModule() {
        super();
    }

    /**
     * Returns {@code true} if Glide should check the AndroidManifest for {@link GlideModule}s.
     * <p>
     * <p>Implementations should return {@code false} after they and their dependencies have migrated
     * to Glide's annotation processor.
     * <p>
     * <p>Returns {@code true} by default.
     */
    @Override
    public boolean isManifestParsingEnabled() {

        return super.isManifestParsingEnabled();
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {

        super.applyOptions(context, builder);
    }
}
