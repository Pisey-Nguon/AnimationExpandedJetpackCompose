package com.example.jetpackcomposeexample.ui.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.jetpackcomposeexample.R
import com.example.jetpackcomposeexample.model.UserModel
import com.example.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeExampleTheme(titleActionBar = "JetpackCompose") {
                ListOfUser()
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Greeting(itemUserModel: UserModel, viewModel: MainActivityViewModel) {
    AnimatedVisibility(
        visible = !viewModel.deletedItemUser.contains(itemUserModel),
        enter = expandVertically(),
        exit = shrinkVertically(animationSpec = tween(durationMillis = 1000))
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .animateContentSize(),
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp,
            color = MaterialTheme.colors.onBackground,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.toggleExpanded(itemUserModel = itemUserModel) }
                    .padding(all = 8.dp)
                ,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = itemUserModel.imageUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                )
                Column(modifier = Modifier.weight(weight = 1f)) {
                    Text(text = "Name: ${itemUserModel.userName}",color = MaterialTheme.colors.primary,style = TextStyle(fontWeight = FontWeight.Bold))
                    Text(text = itemUserModel.desc,color = MaterialTheme.colors.primaryVariant,maxLines = if(itemUserModel.isExpanded) Int.MAX_VALUE else 1)
                }
                IconButton(
                    onClick = { viewModel.removeItem(deletedItem = itemUserModel) },
                ) {
                    Icon(modifier = Modifier.size(24.dp),tint = MaterialTheme.colors.secondary,painter = painterResource(id = R.drawable.ic_baseline_delete_24), contentDescription = "Button Delete" )
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ListOfUser(viewModel: MainActivityViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    LazyColumn(){
        items(viewModel.listUserModel){ userProfile ->
            Greeting(itemUserModel = userProfile,viewModel = viewModel)
        }
    }
}

@ExperimentalAnimationApi
@Preview(showSystemUi = true,device = Devices.DEFAULT,uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    JetpackComposeExampleTheme {
       ListOfUser()
    }
}