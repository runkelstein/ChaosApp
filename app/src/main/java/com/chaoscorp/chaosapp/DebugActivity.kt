package com.chaoscorp.chaosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.chaoscorp.chaosServer.client.ClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
//import kotlin.coroutines.CoroutineContext
//import kotlinx.coroutines.*
//import kotlinx.coroutines.android.*

class DebugActivity : AppCompatActivity() {

    lateinit var  idToken : String;
    lateinit var  yamlObjectMapper : ObjectMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        findViewById<Button>(R.id.sign_out_button).setOnClickListener(::onSignOutClicked)
        findViewById<Button>(R.id.btnLoadList).setOnClickListener(::onLoadListClicked)

        val bundle = intent.extras
        idToken = bundle?.getString("idToken") ?: "none"
        findViewById<TextView>(R.id.debug_text_view).text = idToken

        yamlObjectMapper = ObjectMapper(YAMLFactory())
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val value = data?.getStringExtra("idToken")
        findViewById<TextView>(R.id.debug_text_view).setText(value)
    }

    fun onSignOutClicked(view : View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    fun onLoadListClicked(view : View) {

        val id = findViewById<EditText>(R.id.edListId)?.text?.toString()?.toLongOrNull() ?: return;
        val client= ClientBuilder.createChaosListClient("https://chaoscorp.org:8443", idToken);
        val userDto = client.googleSignIn()
        val listDto = client.getList(id)
        val yamlText = yamlObjectMapper.writeValueAsString(listDto)
        findViewById<TextView>(R.id.debug_text_view).setText(yamlText)

    }

}
