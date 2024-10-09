package com.paierjulia.knuckelbones
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.paierjulia.knuckelbones.databinding.DiceFieldBinding

class DiceFieldView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: DiceFieldBinding


    fun bindViews(binding: DiceFieldBinding) {
        this.binding = binding
    }

    @SuppressLint("SetTextI18n")
    fun updateColumnPointsDisplay(columnId: Int, points: Int) {
        when (columnId) {
            1 -> binding.columnOnePoints.text = points.toString()
            2 -> binding.columnTwoPoints.text = points.toString()
            3 -> binding.columnThreePoints.text = points.toString()
        }
    }


    fun setColumnClickListeners(onColumnClick: (Int) -> Unit) {
        binding.columnOne.setOnClickListener { onColumnClick(1) }
        binding.columnTwo.setOnClickListener { onColumnClick(2) }
        binding.columnThree.setOnClickListener { onColumnClick(3) }
    }


    private fun placeDice(currentValue: Int){

    }

    // for testing

}