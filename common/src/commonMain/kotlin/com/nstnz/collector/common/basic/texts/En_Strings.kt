package com.nstnz.collector.common.basic.texts

object En_Strings : Strings {
    override val locale: String
        get() = "en"

    override val MainScreen_Title: String
        get() = "Main"
    override val MainScreen_SumInFavs: String
        get() = "Sum in favorite currencies:"
    override val MainScreen_SumInDefault: String
        get() = "Sum in default currency:"
    override val MainScreen_SourcesTitle: String
        get() = "Sources"
    override val MainScreen_EmptySourcesTitle: String
        get() = "No sources yet. Use plus button to add a bank or a crypto source"

    override val CurrenciesScreen_Title: String
        get() = "Currencies"

    override val ConverterScreen_Title: String
        get() = "Converter"
    override val ConverterScreen_Desc: String
        get() = "You can convert any sum you want from one currency to another. Just enter the sum and select desirable currencies"

    override val SettingsScreen_Title: String
        get() = "Settings"

    override val Source_GraphNoData: String
        get() = "No data"
    override val Source_GraphYearCource: String
        get() = "1 year period"
    override val Source_AddSourceNameHint: String
        get() = "Enter the name of the source"
    override val Source_AddSourceTitle: String
        get() = "Add source"
    override val Source_AddSourceDesc: String
        get() = "You can add any source you want! It can be a bank where you have you accounts or even a crypto service"
    override val Source_EditSourceTitle: String
        get() = "Edit source"
    override val Source_EditSourceDesc: String
        get() = "You can rename source or change its default currency"
    override val Source_CountsTitle: String
        get() = "Accounts"
    override val Source_EmptyCountsTitle: String
        get() = "No sources yet. Use plus button to add an account to this source"
    override val Source_ErrorEmptyName: String
        get() = "You should enter a name for the source"
    override val Source_SumInDefault: String
        get() = "Sum in source main currency:"

    override val Count_AddCountTitle: String
        get() = "Add account"
    override val Count_AddCountDesc: String
        get() = "You can add all your accounts connected to this source in any currency"
    override val Count_AddButtonTitle: String
        get() = "Income"
    override val Count_RemoveButtonTitle: String
        get() = "Outcome"
    override val Count_ChangeBalanceTitle: String
        get() = "Edit account balance"
    override val Count_ChangeBalanceDesc: String
        get() = "Please choose the operation you want to be done"
    override val Count_CurrentSumOnCount: String
        get() = "Current sum:"
    override val Count_FutureSumOnCount: String
        get() = "New sum:"
    override val Count_AddToCountTitle: String
        get() = "Add money to account"
    override val Count_RemoveFromCountTitle: String
        get() = "Remove maoney from account"
    override val Count_AddToCountDesc: String
        get() = "Enter the sum you want to add to your account balance:"
    override val Count_RemoveFromCountDesc: String
        get() = "Enter the sum you want to substract from your account balance:"

    override val Welcome_Title: String
        get() = "Welcome to $Core_AppName!"
    override val Welcome_Desc: String
        get() = "Please, choose currency you want to use as default"

    override val Core_AppName: String
        get() = "Collector"
    override val Core_AppSlogan: String
        get() = "Your money tracker"
    override val Core_Currency: String
        get() = "Currency"
    override val Core_DefaultCurrency: String
        get() = "Default currency"
    override val Core_DefaultCurrencyHint: String
        get() = "This currency will be used as default main currency. If you change it, existing sources and accounts will not be affected."
    override val Core_CurrenciesList: String
        get() = "Currencies list"
    override val Core_Search: String
        get() = "Search"
    override val Core_Name: String
        get() = "Name"
    override val Core_Cancel: String
        get() = "Cancel"
    override val Core_FavCurrencies: String
        get() = "Favorite currencies"
    override val Core_AddFavCurrency: String
        get() = "Add currency"
}