package com.thurainx.linklast.features

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.skydoves.landscapist.glide.GlideImage
import com.thurainx.linklast.others.Utils
import com.thurainx.linklast.persistance.LinkEntity
import com.thurainx.linklast.presentation.LinkViewModel
import com.thurainx.linklast.ui.theme.*

@Composable
fun LinkListScreen(viewModel: LinkViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = primary_purple,
                onClick = {
                    showDialog = !showDialog
                }
            ) {
                FaIcon(faIcon = FaIcons.Feather, size = 24.dp, tint = Color.White)
            }
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Link Last",
                style = text_header.copy(color = primary_purple),
                modifier = Modifier.padding(12.dp)
            )
            LazyColumn(Modifier.fillMaxSize()) {
                items(viewModel.linkState.linkList.size) {
                    LinkRow(
                        screenHeight = screenHeight,
                        link = viewModel.linkState.linkList[it].linkUrl ?: "- -",
                        onClick = {},
                        onDelete = {viewModel.deleteLink(viewModel.linkState.linkList[it])}
                    )
                }
            }

        }

    }

    if (showDialog) {
        AddLinkDialog(onDismissed = { showDialog = !showDialog }, viewModel = viewModel)

    }
}

@Composable
fun LinkRow(screenHeight: Dp, link: String, onClick: () -> Unit, onDelete: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .fillMaxWidth()
            .height(screenHeight * 0.06f)
            .border(
                width = 1.dp,
                color = black.copy(alpha = 0.2f),
                shape = RoundedCornerShape(12.dp)
            ).padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(Modifier.fillMaxWidth(0.8f)){
                GlideImage( // CoilImage, FrescoImage
                    imageModel = Utils.getWebsiteLogoLink(link = link),
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    // shows an indicator while loading an image.
                    loading = {
                        Box(modifier = Modifier.matchParentSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    // shows an error text if fail to load an image.
                    failure = {
                        Text(text = "image request failed.")
                    })
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = link, style = text_regular, overflow = TextOverflow.Ellipsis, maxLines = 1)
            }
            FaIcon(faIcon = FaIcons.Ban, tint = primary_purple, modifier = Modifier.clickable { onDelete() })


        }

    }

}

@Composable
fun AddLinkDialog(onDismissed: () -> Unit, viewModel: LinkViewModel) {
    var text by remember {
        mutableStateOf("")
    }
    Dialog(
        onDismissRequest = {
            onDismissed()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Box(
            modifier = Modifier
                .background(color = white, shape = RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = black.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 12.dp)

                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        onDismissed()

                        viewModel.insertLink(LinkEntity(linkUrl = text))
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                ) {
                    Text(text = "Save")
                }


            }

        }
    }
}