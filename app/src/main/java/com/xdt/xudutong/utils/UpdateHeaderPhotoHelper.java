package com.xdt.xudutong.utils;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;

import java.io.File;

/**
 * Created by Administrator on 2018/5/22.
 */

public class UpdateHeaderPhotoHelper {

    public static final int METHOD_GALLERY = 1;
    public static final int METHOD_CAMERA = 2;
    public static final int PHOTOWIDTH = 600;

    public static UpdateHeaderPhotoHelper of() {
        return new UpdateHeaderPhotoHelper();
    }

    private UpdateHeaderPhotoHelper() {
    }

    public void onClick(TakePhoto takePhoto, int method) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);

        switch (method) {
            case METHOD_GALLERY:
                takePhoto.onPickFromGallery();
                break;
            case METHOD_CAMERA:
                Log.e("imageUri", "onClick: "+imageUri );
                takePhoto.onPickFromCapture(imageUri);
                break;
        }
    }

    private void configCompress(TakePhoto takePhoto) {
        CompressConfig config = new CompressConfig.Builder().setMaxPixel(PHOTOWIDTH).create();
        takePhoto.onEnableCompress(config, true);
    }

    private CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(PHOTOWIDTH).setAspectY(PHOTOWIDTH);
        builder.setWithOwnCrop(false);
        return builder.create();
    }

}
