package com.example.appka_cvika_1.ui.async.rocketDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.appka_cvika_1.base.State
import com.example.appka_cvika_1.ui.async.launches.RocketLaunchesViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RocketDetailScreen(
    rocketId: String?,
    viewModel: RocketDetailViewModel = getViewModel{
        parametersOf(rocketId)
    }
){

    val context = LocalContext.current
    val rocketDetail = viewModel.rocketDetailState.collectAsState()
    val state = viewModel.state.collectAsState()

    when(val result = state.value){
        State.None, State.Loading -> {
            CircularProgressIndicator()
        }
        is State.Failure -> {
            Column() {
                Text(text = "Chyba! - ${result.throwable.localizedMessage}")
                Button(onClick = { viewModel.fetchRocketDetail() }) {
                    Text("Zkusit znovu")
                }
            }
        }
        is State.Success ->{
            val rocketDetail = rocketDetail.value

            if(rocketDetail == null){
                Text(text = "špatné id")
            }else{
                Column() {
                    Text(text = "Rocket detail")
                    Text(text = rocketDetail.name)
                    Text(text = rocketDetail.description)
                    Button(onClick = { openWebBrowser(context,rocketDetail.wikipediaLink) }) {
                        Text(text = "Otevřít ve wiki")
                    }
                }
            }

        }
    }

}

private fun openWebBrowser(context: Context, url: String){
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}