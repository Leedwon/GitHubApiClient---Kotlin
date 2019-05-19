package com.ledwon.jakub.githubapiclientkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ledwon.jakub.githubapiclientkotlin.R
import com.ledwon.jakub.githubapiclientkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IMainActivity {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.iMainActivity = this

        binding.inputLayoutUsername.editText?.setOnClickListener({ v ->
            setErrorOnInputLayout(!validateUsername(getUsername()))
        })
    }

    private fun validateUsername(username: String) = username.trim().isNotEmpty()

    private fun getUsername() = binding.inputLayoutUsername.editText?.text.toString()

    private fun setErrorOnInputLayout(isError: Boolean) {
        if (isError) {
            binding.inputLayoutUsername.isErrorEnabled = true
            binding.inputLayoutUsername.error = resources.getString(R.string.edit_text_username_error)
        } else
            binding.inputLayoutUsername.isErrorEnabled = false
    }

    override fun startListOfReposActivity() {
        val username = getUsername()
        val isError = !validateUsername(username)
        setErrorOnInputLayout(isError)
        if (!isError) {
            intent = Intent(this, ListOfReposActivity::class.java)
            intent.putExtra(
                ListOfReposActivity.BUNDLE_USERNAME_KEY,
                username
            )
            startActivity(intent)
        }
    }

}
