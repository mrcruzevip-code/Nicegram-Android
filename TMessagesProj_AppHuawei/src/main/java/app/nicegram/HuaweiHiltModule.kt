package app.nicegram

import android.content.Context
import android.content.SharedPreferences
import com.appvillis.core_data.ApiService
import com.appvillis.core_domain.repository.user.UserBalanceRepository
import com.appvillis.core_domain.repository.user.UserRepository
import com.appvillis.core_markets.MarketFeatureFlagsProvider
import com.appvillis.feature_nicegram_billing.domain.BillingManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HuaweiHiltModule {
    @Provides
    @Singleton
    fun provideBillingManger(
        @ApplicationContext context: Context,
        apiService: ApiService,
        userRepository: UserRepository,
        sharedPreferences: SharedPreferences,
        userBalanceRepository: UserBalanceRepository,
        coroutineScope: CoroutineScope
    ): BillingManager =
        HuaweiBillingManagerImpl(context, sharedPreferences, coroutineScope, userBalanceRepository, userRepository, apiService)

    @Provides
    @Singleton
    fun provideMarketFeatureFlagsProvider(): MarketFeatureFlagsProvider = object : MarketFeatureFlagsProvider {
        override val canShowAds = false
    }
}