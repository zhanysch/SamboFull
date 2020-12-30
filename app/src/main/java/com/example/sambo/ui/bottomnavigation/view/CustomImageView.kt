package com.example.sambo.ui.bottomnavigation.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.sambo.R
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.android.synthetic.main.recycler_helper.view.*

class CustomImageView(context: Context, attributeSet: AttributeSet) : AppCompatImageView(context, attributeSet) {
     fun makeroundImage() { // метод для обработки image чтоб углы закгруглялись
        val radius = resources.getDimension(R.dimen.imageRadius)
        val shape = imageCourse.shapeAppearanceModel.toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .setTopRightCorner(CornerFamily.ROUNDED, radius)
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED, radius)

            .build()

        imageCourse.shapeAppearanceModel = shape
    }
}