package org.threehundredtutor.ui_core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestOptions
import org.threehundredtutor.ui_core.databinding.PhotoItemBinding
import java.security.MessageDigest
import kotlin.math.ceil

class BannerItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding = PhotoItemBinding.inflate(LayoutInflater.from(context), this)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = resources.getDimensionPixelSize(R.dimen.size_108)
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
    }

    fun loadImage(path: String, @DrawableRes placeHolder: Int, index: Int) {
        binding.imageView.loadBanner(path, placeHolder, index)
        // binding.imageView.loadBanner(path, placeHolder) { drawable ->
        //     binding.imageView.scaleBanner(drawable)
        // }
    }
}

fun ImageView.scaleBanner(drawable: Drawable) {
    drawable.let {
        val matrix = Matrix()

        Log.d("scaleBanner", "measuredWidth = $measuredWidth")
        Log.d("scaleBanner", "measuredHeight = $measuredHeight")
        Log.d("scaleBanner", "intrinsicWidth = ${drawable.intrinsicWidth}")
        Log.d("scaleBanner", "intrinsicHeight = ${drawable.intrinsicHeight}")
        val scaleX = measuredWidth.toFloat() / drawable.intrinsicWidth
        val scaleY = measuredHeight.toFloat() / drawable.intrinsicHeight
        val scale = maxOf(scaleX, scaleY)

        val left = (measuredWidth - (drawable.intrinsicWidth * scale))
        val top = ((measuredHeight - (drawable.intrinsicHeight * scale)) / 2)


        Log.d("scaleBanner", "left = $left")
        Log.d("scaleBanner", "top = $top")
        Log.d("scaleBanner", "intrinsicWidth = ${drawable.intrinsicWidth}")
        Log.d("scaleBanner", "intrinsicHeight = ${drawable.intrinsicHeight}")

        matrix.postScale(scale, scale)
        matrix.postTranslate(left, top)

        scaleType = ImageView.ScaleType.MATRIX
        imageMatrix = matrix
    }
}

fun ImageView.loadBanner(
    path: String,
    @DrawableRes placeHolder: Int,
    index: Int
) {
    if (index == 4) {
        val requestOptions = RequestOptions().transform(MyTransformation())
        val thumbnail: RequestBuilder<Drawable> =
            Glide.with(context).load(placeHolder).apply(requestOptions)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)

        Glide.with(context).load(path)
            .apply(requestOptions)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .thumbnail(thumbnail)
            .into(this)

        return
    }

    if (index % 2 == 0) {
        Glide.with(context).load(path)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(placeHolder)
            .centerCrop()
            .into(this)
    } else {
        Glide.with(context).load(path)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(placeHolder)
            .into(this)
    }
}

fun ImageView.loadBanner2(
    path: String,
    @DrawableRes placeHolder: Int,
) {
    Glide.with(context).load(path)
        .placeholder(placeHolder)
        .centerCrop()
        .into(this)
}

fun ImageView.loadBannerThumbnail(
    path: String,
    @DrawableRes placeHolder: Int,
) {
    val requestOptions = RequestOptions().transform(MyTransformation())
    val thumbnail: RequestBuilder<Drawable> =
        Glide.with(context).load(placeHolder).apply(requestOptions)

    Glide.with(context).load(path)
        .apply(requestOptions)
        .thumbnail(*arrayOf(thumbnail))
        .into(this)
}

class MyTransformation : BitmapTransformation() {
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((getId()).toByteArray(CHARSET))
    }

    override fun transform(
        pool: BitmapPool,
        bitmap: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        Log.d("transform", "outWidth = $outWidth, outHeight = $outHeight")
        Log.d("transform", "bitmap.width = ${bitmap.width}, bitmap.height = ${bitmap.height}")

        val scaleX = outWidth.toFloat() / bitmap.width
        val scaleY = outHeight.toFloat() / bitmap.height

        Log.d("transform", "scaleX = $scaleX, scaleY = $scaleY")

        val scale = maxOf(scaleX, scaleY)

        val widthScale = bitmap.width * scale
        val heightScale = bitmap.height * scale

        Log.d("transform", "widthScale = $widthScale, heightScale = $heightScale")

        val wight = ceil(widthScale).toInt()
        val height = ceil(heightScale).toInt()

        Log.d("transform", "ceilWight = $wight, ceilHeight = $height")

        val bitmapScale = Bitmap.createScaledBitmap(bitmap, wight, height, false)

        Log.d("transform", "x = ${wight - outWidth}, y = ${height - outHeight}")

        return Bitmap.createBitmap(
            bitmapScale,
            wight - outWidth,
            height - outHeight,
            outWidth,
            outHeight
        )
    }

    fun getId(): String {
        return "com.example.myapp.MyTransformation"
    }
}