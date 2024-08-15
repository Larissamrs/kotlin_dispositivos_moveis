package com.weatherapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.ui.theme.WeatherAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                RegisterPage()
            }
        }
    }
}

    @Composable
    fun RegisterPage(modifier: Modifier = Modifier) {
        var name by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }
        val activity = LocalContext.current as? Activity

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crie sua conta!",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.size(24.dp))

            OutlinedTextField(
                value = name,
                label = { Text(text = "Digite seu nome") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { name = it }
            )
            Spacer(modifier = Modifier.size(24.dp))

            OutlinedTextField(
                value = email,
                label = { Text(text = "Digite seu e-mail") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.size(24.dp))

            OutlinedTextField(
                value = password,
                label = { Text(text = "Digite sua senha") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.size(24.dp))

            OutlinedTextField(
                value = confirmPassword,
                label = { Text(text = "Repita sua senha") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { confirmPassword = it },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.size(24.dp))

            Row(modifier = modifier) {
                Button(
                    onClick = {
                        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
                            Toast.makeText(activity, "Registro OK!", Toast.LENGTH_LONG).show()
                            activity?.finish()
                        } else {
                            Toast.makeText(activity, "Por favor, preencha todos os campos corretamente.", Toast.LENGTH_LONG).show()
                        }
                    },
                    enabled = name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword
                ) {
                    Text("Registrar")
                }
                Spacer(modifier = Modifier.size(24.dp))
                Button(
                    onClick = {
                        name = ""
                        email = ""
                        password = ""
                        confirmPassword = ""
                    }
                ) {
                    Text("Limpar")
                }
            }
        }
    }

