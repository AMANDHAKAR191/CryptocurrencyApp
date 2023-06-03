package com.aman.cryptocurrencyapp.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aman.cryptocurrencyapp.presentation.Screen
import com.aman.cryptocurrencyapp.presentation.coin_detail.componants.CoinTag
import com.aman.cryptocurrencyapp.presentation.coin_detail.componants.TeamListItem
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Centered TopAppBar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)){
                    Text(
                        text = "CoinListScreen",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                state.coin?.let { coin ->
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(20.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.weight(8f)
                                )
                                Text(
                                    text = if (coin.isActive) "Active" else "Inactive",
                                    color = if (coin.isActive) Color.Green else Color.Red,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier
                                        .align(CenterVertically)
                                        .weight(2f)
                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = coin.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "Tags",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            FlowRow(
                                mainAxisSpacing = 10.dp,
                                crossAxisSpacing = 10.dp,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                coin.tags.forEach { tag ->
                                    CoinTag(tag = tag)
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "Team members",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                        items(coin.team) { teamMember ->
                            TeamListItem(
                                teamMember = teamMember,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                            Divider()
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxSize()){
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    )

}