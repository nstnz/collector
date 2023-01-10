package com.nstnz.collector.common.feature.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nstnz.collector.common.basic.di.strings
import com.nstnz.collector.common.design.button.PrimaryButtonComponent
import com.nstnz.collector.common.design.button.SecondaryButtonComponent
import com.nstnz.collector.common.design.image.AsyncImage
import com.nstnz.collector.common.design.input.TextSelectorComponent
import com.nstnz.collector.common.design.navbar.NavigationBarComponent
import com.nstnz.collector.common.design.scaffold.GradientScaffold
import com.nstnz.collector.common.design.spacer.SpacerComponent
import com.nstnz.collector.common.design.theme.AppTheme
import com.nstnz.collector.common.design.theme.backgroundSecondary
import com.nstnz.collector.common.design.theme.primaryBackgroundText
import com.nstnz.collector.common.design.theme.secondaryBackgroundText
import com.nstnz.collector.common.design.topbar.DefaultNavComponent
import com.nstnz.collector.common.feature.core.domain.model.AccountModel
import com.nstnz.collector.common.feature.currencies.presentation.CurrencyCell

@Composable
internal fun SettingsScreen(
    viewState: SettingsScreenState,
    onConverterTabCLick: () -> Unit = {},
    onMainTabClick: () -> Unit = {},
    onChangeCurrencyClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
) {
    GradientScaffold(
        topBar = {
            DefaultNavComponent(
                title = strings.SettingsScreen_Title,
                showBackButton = false
            )
        },
        bottomBar = {
            NavigationBarComponent(
                settingsTabSelected = true,
                settingsTabClick = {},
                converterTabClick = onConverterTabCLick,
                mainTabClick = onMainTabClick,
            )
        }
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SpacerComponent { x3 }
            if (viewState.account == null) {
                LoginBlock(viewState, onLoginClick)
            } else {
                LoggedUserBlock(viewState.account)
            }
            SpacerComponent { x3 }
            Text(
                text = strings.Core_DefaultCurrency,
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium,
                modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
            )
            SpacerComponent { x1 }
            TextSelectorComponent(
                modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
                label = "",
                hint = strings.Core_DefaultCurrencyHint,
                text = viewState.currency?.code.orEmpty(),
                onClick = {
                    onChangeCurrencyClick()
                }
            )
            SpacerComponent { x4 }
            Text(
                text = strings.Core_FavCurrencies,
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium,
                modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
            )
            SpacerComponent { x2 }
            viewState.favoriteCurrencies.forEach {
                CurrencyCell(
                    it,
                    false,
                    false,
                    {}
                )
                SpacerComponent { x2 }
            }
            SpacerComponent { x5 }

            if (viewState.account != null) {
                SecondaryButtonComponent(
                    modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
                    text = strings.SettingsScreen_Logout,
                    onClick = {
                        onLogoutClick()
                    })
            }

            SpacerComponent { x3 }
        }
    }
}

@Composable
private fun LoginBlock(viewState: SettingsScreenState, onLoginClick: () -> Unit) {
    Text(
        text = strings.SettingsScreen_AddLogin,
        color = AppTheme.colors.secondaryBackgroundText(),
        style = AppTheme.typography.bodySmall,
        modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
    )
    SpacerComponent { x1 }
    SecondaryButtonComponent(
        modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
        text = strings.SettingsScreen_GoogleLogin,
        onClick = {
            onLoginClick()
        })
}

@Composable
private fun LoggedUserBlock(account: AccountModel) {
    Row(
        modifier = Modifier.padding(horizontal = AppTheme.indents.x3).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.size(AppTheme.indents.x10)
                .clip(AppTheme.shapes.x5)
                .background(AppTheme.colors.backgroundSecondary())
        ) {
            AsyncImage(
                url = account.photoUrl.orEmpty(),
                modifier = Modifier.size(AppTheme.indents.x10)
                    .clip(AppTheme.shapes.x5)
                    .background(AppTheme.colors.backgroundSecondary())
            )
        }
        SpacerComponent { x2 }
        Column(Modifier.weight(1f)) {
            Text(
                text = account.name.toString(),
                color = AppTheme.colors.primaryBackgroundText(),
                style = AppTheme.typography.headingMedium,
            )
            SpacerComponent { x0_5 }
            Text(
                text = account.email.orEmpty(),
                color = AppTheme.colors.secondaryBackgroundText(),
                style = AppTheme.typography.bodySmall,
            )
        }
    }
}
