package com.example.jetpackcomposeexample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.jetpackcomposeexample.model.UserModel
import com.example.jetpackcomposeexample.sampledata.SampleData
import com.example.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeExampleTheme(titleActionBar = "JetpackCompose") {
                ListOfUser(listUser = SampleData.allUserInfo())
            }
        }
    }
}

@Composable
fun Greeting(user: UserModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
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
                .clickable { isExpanded = !isExpanded }
                .padding(all = 8.dp)
            ,
        ) {
            Image(
                painter = rememberImagePainter(
                    data = user.imageUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column() {
                Text(text = "Name: ${user.userName}",color = MaterialTheme.colors.primary,style = TextStyle(fontWeight = FontWeight.Bold))
                Text(text = user.desc,color = MaterialTheme.colors.primaryVariant,maxLines = if(isExpanded) Int.MAX_VALUE else 1)
            }
        }
    }
}

@Composable
fun ListOfUser(listUser:List<UserModel>){
    LazyColumn(){
        items(listUser){ userProfile ->
            Greeting(user = userProfile)
        }
    }
}

@Preview(showSystemUi = true,device = Devices.DEFAULT,uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    JetpackComposeExampleTheme {
       ListOfUser(listUser = SampleData.allUserInfo())
    }
}