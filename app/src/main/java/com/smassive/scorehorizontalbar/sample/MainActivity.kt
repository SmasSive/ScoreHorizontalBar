package com.smassive.scorehorizontalbar.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.scoreHorizontalBarL1
import kotlinx.android.synthetic.main.activity_main.scoreHorizontalBarL2
import kotlinx.android.synthetic.main.activity_main.scoreHorizontalBarL3
import kotlinx.android.synthetic.main.activity_main.scoreHorizontalBarL4
import kotlinx.android.synthetic.main.activity_main.scoreHorizontalBarL5

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onStart() {
    super.onStart()

    scoreHorizontalBarL1.render(1.5F)
    scoreHorizontalBarL2.render(3.4F)
    scoreHorizontalBarL3.render(5.9F)
    scoreHorizontalBarL4.render(7.1F)
    scoreHorizontalBarL5.render(8.3F)
  }
}
