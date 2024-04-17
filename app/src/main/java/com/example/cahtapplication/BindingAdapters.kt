package com.example.cahtapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("android:error")
fun setErrorToTextInputLayout(textInputLayout: TextInputLayout, error: String?){
    textInputLayout.error = error
}

@BindingAdapter("image_form_category")
fun setImageByCategory(image: ImageView, category: String?){
    when (category) {
        "movies" -> image.setImageResource(R.drawable.image_movies_cat)
        "music" -> image.setImageResource(R.drawable.image_music_cat)
        "sports" -> image.setImageResource(R.drawable.image_sports_cat)
    }
}