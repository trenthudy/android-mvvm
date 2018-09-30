package io.hudepohl.mvvm.util

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions


object DataBindingAdapters {

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("items")
    fun setItems(view: RecyclerView, items: List<Any>) {
        (view.adapter as? RecyclerViewAdapter<Any, RecyclerView.ViewHolder>)?.setItems(items)
                ?: throw RuntimeException("use ${RecyclerViewAdapter::class.java.name} with custom 'items' BindingAdapter")
    }

    @JvmStatic
    @BindingAdapter(
            value = ["url", "placeholder", "fallback", "error"],
            requireAll = false)
    fun setImageSource(view: ImageView, url: String?, placeholder: Drawable?, fallback: Drawable?, error: Drawable?) {

        GlideApp.with(view.context)
                .load(url)
                .apply(RequestOptions()
                        .fitCenter()
                        .placeholder(placeholder)
                        .fallback(fallback)
                        .error(error))
                .into(view)
    }
}
