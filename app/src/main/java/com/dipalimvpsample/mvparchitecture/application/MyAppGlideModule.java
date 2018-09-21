package com.dipalimvpsample.mvparchitecture.application;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;


/**
 * Created by Dipali on 2/28/2018
 * README : http://bumptech.github.io/glide/doc/generatedapi.html .
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    /*Set your cache , memory, request options here*/
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide,
                                   @NonNull Registry registry) {
       /* registry.register(SVG.class, PictureDrawable.class, new SvgDrawableTranscoder())
                .append(InputStream.class, SVG.class, new SvgDecoder());*/
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
