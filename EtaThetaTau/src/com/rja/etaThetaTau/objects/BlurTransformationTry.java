package com.rja.etaThetaTau.objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by rjaylward on 2/22/16
 */
public class BlurTransformationTry extends BitmapTransformation {

    RenderScript mRenderScript;
    Context mContext;

    public BlurTransformationTry(Context context, RenderScript renderScript) {
        super(context);

        mContext = context;
        mRenderScript = renderScript;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return render(toTransform, mRenderScript);
    }

    @Override
    public String getId() {
        // Return some id that uniquely identifies your transformation.
        return BlurTransformationTry.class.getCanonicalName();
    }

    private Bitmap render(Bitmap loadedImage, RenderScript renderScript) {
//        try {
            if(loadedImage != null && renderScript != null) {
                Allocation input = Allocation.createFromBitmap(renderScript, loadedImage);
                Allocation output = Allocation.createTyped(renderScript, input.getType());
                ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(mRenderScript));

                script.setRadius(8f);
                script.setInput(input);
                script.forEach(output);
                output.copyTo(loadedImage);
            }
//            else if(loadedImage != null)
//                imageView.setColorFilter(ContextCompat.getColor(imageView.getContext(), R.color.transparent_black_profile_shader), PorterDuff.Mode.ADD);
//        } catch(Exception e) {
//            Print.exception(e);
//        }

        return loadedImage;
    }
}
