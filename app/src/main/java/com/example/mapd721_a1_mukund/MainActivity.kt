package com.example.mapd721_a1_mukund

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mapd721_a1_mukund.ui.theme.MAPD721_A1_MukundTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MAPD721_A1_MukundTheme {
                MyApp()
            }
        }
    }
}

@Preview
@Composable
fun MyApp() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()
    val store = UserStore(context)

    var idValue by remember { mutableStateOf(TextFieldValue("876")) }
    var usernameValue by remember { mutableStateOf(TextFieldValue()) }
    var courseNameValue by remember { mutableStateOf(TextFieldValue()) }

    val storedId = store.getID.collectAsState(initial = "125")
    val storedUsername = store.getUserName.collectAsState(initial = "")
    val storedCourseName = store.getCourseName.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = idValue,
            onValueChange = { idValue = it },
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = usernameValue,
            onValueChange = { usernameValue = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = courseNameValue,
            onValueChange = { courseNameValue = it },
            label = { Text("Course Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = {
                    idValue = TextFieldValue(storedId.value)
                    usernameValue = TextFieldValue(storedUsername.value)
                    courseNameValue = TextFieldValue(storedCourseName.value)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(255, 179, 0, 121),
                    contentColor = Color(0,0,0),
                ),
            ) {
                Text("Load")
            }
            OutlinedButton(
                onClick = {
                    scope.launch {
                        store.saveID(idValue.text)
                        store.saveUserName(usernameValue.text)
                        store.saveCourseName(courseNameValue.text)
                    }
                    keyboardController?.hide()
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(124, 179, 66, 134),
                    contentColor = Color(0,0,0),
                ),
            ) {
                Text("Store")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = {
                idValue = TextFieldValue("")
                usernameValue = TextFieldValue("")
                courseNameValue = TextFieldValue("")
                scope.launch {
                    store.saveID("")
                    store.saveUserName("")
                    store.saveCourseName("")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(229, 57, 53, 136),
                contentColor = Color(0,0,0),
            ),
        ) {
            Text("Reset")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text("Mukund Kapadia")
        Text("301403876")
    }
}