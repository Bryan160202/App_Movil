package com.mkiperszmid.travelguideai.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mkiperszmid.travelguideai.home.presentation.components.HomeFilterButton
import com.mkiperszmid.travelguideai.home.presentation.components.HomeFilterDialog
import com.mkiperszmid.travelguideai.home.presentation.components.HomeSearchBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state

    if (state.showDialog) {
        HomeFilterDialog(onDismiss = {
            viewModel.onFilterDismiss()
        }, filterSettings = state.filterSettings, onAction = {
                viewModel.onSettingsChange(it)
            })
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "A donde viajas?")
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeSearchBar(
                onSearch = {
                    viewModel.search()
                },
                placeholder = "Pais, Ciudad",
                inputText = state.searchText,
                onValueChange = { viewModel.onSearchTextChange(it) }
            )
            HomeFilterButton(onClick = { viewModel.onFilterClick() })
        }
    }
}
