package com.example.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var cList : MutableList<Claim>
    lateinit var cService : ClaimService

    fun refreshScreen(cObj : Claim){
        Log.d("Claim Service", "Refreshing the Screen. ")
        val claimTitleView : EditText = findViewById(R.id.claim_title)
        val claimDateView : EditText = findViewById(R.id.claim_date)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fldRowGenerator = ObjDetailSectionGenerator(this)
        val colView = fldRowGenerator.generate()
        setContentView(colView)
        val bView: Button = findViewById(R.id.add_btn)
        bView.setOnClickListener {
            var claimTitle = getResources().getResourceEntryName(R.id.claim_title)
            var claimDate = getResources().getResourceEntryName(R.id.claim_date)
            val newcObj = Claim(claimTitle, claimDate)
            val cObj = cService.addClaim(newcObj)
            refreshScreen(newcObj)
        }


    }
}
