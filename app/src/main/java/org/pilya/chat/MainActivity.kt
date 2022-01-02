package org.pilya.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*

import org.pilya.chat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        binding.bnSend.setOnClickListener{
            myRef.setValue(binding.editText.text.toString())
        }
        onChangeListener(myRef)
     }


    private fun onChangeListener(databaseReference: DatabaseReference){
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.apply {
                    rcView.append("\n")
                    rcView.append(snapshot.value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}