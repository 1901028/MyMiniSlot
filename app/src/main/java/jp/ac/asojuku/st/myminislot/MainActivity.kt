package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 手持ちコインの表示処理
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val my_COIN = findViewById(R.id.myCoin) as TextView
        val kake_COIN = findViewById(R.id.kakeCoin) as TextView

        // 手持ちコインの更新表示処理
        val my = pref.getInt("MY_COIN",1002);
        my_COIN.text = my.toString();

        // 掛け金の表示処理
        val kake = pref.getInt("KAKE_COIN",0);
        kake_COIN.text = kake.toString();


        // スタートボタン
        start_button.setOnClickListener { onStartButtonTapped(it) }
        // リセットボタン
        reset_button.setOnClickListener { onResetButtonTapped() }
        // 掛け金UPボタン
        kakekin_up.setOnClickListener { onKakeUpButtonTapped() }
        // 掛け金DOWNボタン
        kakekin_down.setOnClickListener { onKakeDownButtonTapped() }
    }

    private fun onStartButtonTapped(view: View?) {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun onResetButtonTapped() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("MY_COIN",1000)
        .putInt("KAKE_COIN",0)
        .putInt("KEKKA_COIN",0)
        .putInt("SLOT1",9)
        .putInt("SLOT2",9)
        .putInt("SLOT3",9)
        .apply()

        val my_COIN = findViewById(R.id.myCoin) as TextView
        val my = pref.getInt("MY_COIN",1001)
        my_COIN.text = my.toString()

        val kake_COIN = findViewById(R.id.kakeCoin) as TextView
        val kake = pref.getInt("KAKE_COIN",0)
        kake_COIN.text = kake.toString()
    }

    private fun onKakeUpButtonTapped() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KAKE_COIN", pref.getInt("KAKE_COIN",0) + 10)
            .putInt("MY_COIN", pref.getInt("MY_COIN",10) - 10)
            .apply()

        val kake_COIN = findViewById(R.id.kakeCoin) as TextView
        val kake = pref.getInt("KAKE_COIN",0)
        kake_COIN.text = kake.toString()

        val my_COIN = findViewById(R.id.myCoin) as TextView
        val my = pref.getInt("MY_COIN",10)
        my_COIN.text = my.toString()
    }

    private fun onKakeDownButtonTapped() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KAKE_COIN", pref.getInt("KAKE_COIN",0) - 10)
            .putInt("MY_COIN", pref.getInt("MY_COIN",10) + 10)
            .apply()

        val kake_COIN = findViewById(R.id.kakeCoin) as TextView
        val kake = pref.getInt("KAKE_COIN",0)
        kake_COIN.text = kake.toString()

        val my_COIN = findViewById(R.id.myCoin) as TextView
        val my = pref.getInt("MY_COIN",10)
        my_COIN.text = my.toString()
    }
}