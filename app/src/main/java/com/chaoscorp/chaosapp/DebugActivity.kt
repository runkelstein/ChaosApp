package com.chaoscorp.chaosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.chaoscorp.chaosServer.client.ClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import kotlinx.coroutines.*
import kotlinx.android.synthetic.main.activity_debug.*

class DebugActivity : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Default) {

    private lateinit var  idToken : String;
    private lateinit var  yamlObjectMapper : ObjectMapper
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        sign_out_button.setOnClickListener(::onSignOutClicked)
        btnLoadList.setOnClickListener(::onLoadListClicked)

        val bundle = intent.extras
        idToken = bundle?.getString("idToken") ?: "none"
        debug_text_view.text = idToken

        yamlObjectMapper = ObjectMapper(YAMLFactory())
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val value = data?.getStringExtra("idToken")
        debug_text_view.setText(value)
    }

    fun onSignOutClicked(view : View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    fun onLoadListClicked(view : View) {

        val id = edListId?.text?.toString()?.toLongOrNull() ?: return;

        launch {
            println("Net: I'm working in thread ${Thread.currentThread().name}")
            val client= ClientBuilder.createChaosListClient("https://chaoscorp.org:8443", idToken);
            val userDto = client.googleSignIn()
            val listDto = client.getList(id)
            val yamlText = yamlObjectMapper.writeValueAsString(listDto)

            launch(mainScope.coroutineContext) {
                println("UI: I'm working in thread ${Thread.currentThread().name}")
                debug_text_view.setText(yamlText)
            }

        }

    }

}
