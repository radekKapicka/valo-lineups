package com.example.valo_lineups.ui.auth


import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.valo_lineups.R
import com.example.valo_lineups.ui.basic.form.FormScreen
import com.example.valo_lineups.ui.theme.Headers
import com.example.valo_lineups.ui.theme.valoRed
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var mAuth: FirebaseAuth

@Composable
fun RequestScreen(
    navHostController: NavHostController,
){
    val context = LocalContext.current
    mAuth = FirebaseAuth.getInstance()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Request a lineup",
            color = valoRed,
            fontFamily = Headers,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (mAuth.currentUser == null) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "For add request you need to sign in")
            Button(onClick = {
                context.startActivity(Intent(context, UserAuthScreenActivity::class.java))
            }) {
                Text(text = "Sign in with Google")
            }
        } else {
            val user: FirebaseUser = mAuth.currentUser!!
            Button(onClick = {
                context.startActivity(Intent(context, UserAuthScreenActivity::class.java))
            }) {
                Text(text = "Logout")
            }
            Spacer(modifier = Modifier.height(30.dp))
            FormScreen(navHostController, email = user.email!!)
        }
    }
}



