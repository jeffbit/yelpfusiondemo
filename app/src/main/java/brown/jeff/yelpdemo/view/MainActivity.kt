package brown.jeff.yelpdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import brown.jeff.yelpdemo.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

    }
}
