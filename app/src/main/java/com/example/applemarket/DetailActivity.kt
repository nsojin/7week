package com.example.applemarket

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var likeCount = false

    private val item: MyItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.ITEM_OBJECT, MyItem::class.java)
        } else {
            intent.getParcelableExtra<MyItem>(Constants.ITEM_OBJECT)
        }
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources, it.aImage, null
            )
        })
        binding.detailId.text = item?.seller
        binding.detailAddress.text = item?.aAddress
        binding.detailName.text = item?.aName
        binding.detail.text = item?.detail
        binding.detailPrice.text = DecimalFormat("#,###").format(item?.aPrice)+"원"

        likeCount = item?.likeCount == true

        binding.like2.setImageResource(if(likeCount){R.drawable.heart2} else {R.drawable.heart})

        //좋아요 버튼 누르는 기능
        binding.linearHeart.setOnClickListener{
            if(!likeCount){
                binding.like2.setImageResource(R.drawable.heart2)
                Snackbar.make(binding.constraintLayout2,"관심목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                likeCount = true
            }
            else {
                binding.like2.setImageResource(R.drawable.heart)
                likeCount = false
            }
        }

        binding.back.setOnClickListener{
            exit()
        }
    }

    // 리사이클러뷰에 넘겨주는 코드
    fun exit() {
        val intent = Intent(this,MainActivity::class.java).apply {
            putExtra("itemIndex", itemPosition)
            putExtra("isLike",likeCount)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }

    override fun onBackPressed() {
        exit()
    }
}