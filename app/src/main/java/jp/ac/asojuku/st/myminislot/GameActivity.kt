package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.support.constraint.solver.GoalRow
import android.view.View
import android.widget.TextView
import jp.ac.asojuku.st.myminislot.R.drawable.*
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    val threeLine = arrayOf(20,10,5,2,2,2,2,2,2)
    val twoLine = arrayOf(3,0,0,0,0,0,0,0,0)
    val imageArray:Array<Int> = arrayOf(
        R.drawable.banana,
        R.drawable.bar,
        R.drawable.bigwin,
        R.drawable.cherry,
        R.drawable.grape,
        R.drawable.lemon,
        R.drawable.orange,
        R.drawable.seven,
        R.drawable.watermelon
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        val a = (Math.random()*9).toInt();
        val b = (Math.random()*9).toInt();
        val c = (Math.random()*9).toInt();
        // 開始ボタン
        slot1_img.setOnClickListener {
            slot1_img.setImageResource(imageArray[a])
            slot2_img.setImageResource(imageArray[b])
            slot3_img.setImageResource(imageArray[c])

            if (a == b && b == c) {
                when (a) {
                    7 -> editor.putInt("BAI", 20)
                    2 -> editor.putInt("BAI", 10)
                    1 -> editor.putInt("BAI", 5)

                    else -> editor.putInt("BAI", 2)
                }
            } else if (a == b || a == c || b == c) {
                if (a == b) {
                    when (a) {
                        7 -> editor.putInt("BAI", 3)
                        else -> editor.putInt("BAI", 1)
                    }
                } else if (a == c) {
                    when (a) {
                        7 -> editor.putInt("BAI", 3)
                        else -> editor.putInt("BAI", 1)
                    }
                } else if (b == c) {
                    when (a) {
                        7 -> editor.putInt("BAI", 3)
                        else -> editor.putInt("BAI", 1)
                    }
                }
            } else if (a == 8 || b == 8 || c == 8) {
                editor.putInt("BAI", 1)
            } else if (a == 6 && b == 3 && c == 5) {
                editor.putInt("BAI", 30)
            } else if (a == 8 && b == 0 && c == 4) {
                editor.putInt("BAI", 30)
            } else {
                editor.putInt("BAI", 0)
            }

            editor.putInt("TOTAL_COUNT", pref.getInt("BAI_RITU",0) * pref.getInt("KAKE_COIN",0))
                .putInt("MY_COIN",pref.getInt("MY_COIN",0) + pref.getInt("TOTAL_COUNT",0))
                .putInt("KAKE_COIN",0)
                .apply()

        }
        // バックボタン
        back_button.setOnClickListener { onBackButtonTapped(it) }
    }


    private fun onBackButtonTapped(view: View?){
        // スロットの画像をはてなに戻す処理

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

//    private fun onSlot1ButtonTapped(){
//        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//        val editor = pref.edit()
//
//        val num = (Math.random() * 9).toInt()
//        slot1_img.setImageResource(img[num]);
//
//
//        editor.putInt("SLOT1",num)
//            .apply()
//
//        if(pref.getInt("SLOT1",9) != 9 && pref.getInt("SLOT2",9) != 9 && pref.getInt("SLOT3",9) != 9){
//            Calc()
//        }
//    }
//
//    private fun onSlot2ButtonTapped(){
//        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//        val editor = pref.edit()
//
//        val num = (Math.random() * 9).toInt()
//        slot2_img.setImageResource(img[num]);
//        slot2_int.text = num.toString()
//
//        editor.putInt("SLOT2",num)
//            .apply()
//
//        if(pref.getInt("SLOT1",9) != 9 && pref.getInt("SLOT2",9) != 9 && pref.getInt("SLOT3",9) != 9){
//            Calc()
//        }
//    }
//
//    private fun onSlot3ButtonTapped(){
//        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//        val editor = pref.edit()
//
//        val num = (Math.random() * 9).toInt()
//        slot3_img.setImageResource(img[num]);
//        slot3_int.text = num.toString()
//
//        editor.putInt("SLOT3",num)
//            .apply()
//
//        if(pref.getInt("SLOT1",9) != 9 && pref.getInt("SLOT2",9) != 9 && pref.getInt("SLOT3",9) != 9){
//            Calc()
//        }
//    }
//
    private fun Calc(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putInt("COUNT",0)
            .putInt("TOTAL_COUNT",0)
            .putInt("HIT_NUM",0)
            .putInt("BAI_RITU",0)
            .apply()
//
//        val slot = arrayOf(pref.getInt("SLOT1",9),pref.getInt("SLOT2",9),pref.getInt("SLOT2",9))
//
//        if(slot[0] == slot[1]){
//            editor.putInt("COUNT",pref.getInt("COUNT",4) + 1)
//                .putInt("HIT_NUM",slot[0])
//                .apply()
//            slot1_int.text = pref.getInt("COUNT",0).toString()
//        }
//        if(slot[0] == slot[2]){
//            editor.putInt("COUNT",pref.getInt("COUNT",4) + 1)
//                .putInt("HIT_NUM",slot[0])
//                .apply()
//            slot1_int.text = pref.getInt("COUNT",0).toString()
//        }
//        if(slot[1] == slot[2]){
//            editor.putInt("COUNT",pref.getInt("COUNT",4) + 1)
//                .putInt("HIT_NUM",slot[1])
//                .apply()
//            slot1_int.text = pref.getInt("COUNT",0).toString()
//        }
//
//
//        if(pref.getInt("COUNT",4) == 3){
//            editor.putInt("BAI_RITU", threeLine[slot[0]])
//                .apply()
//
//        }else if(pref.getInt("COUNT",4) == 1){
//            // 1になる
//            editor.putInt("BAI_RITU", twoLine[pref.getInt("HIT_NUM",0)])
//                .apply()
//        }else{
//            if(slot[0] == 8 || slot[1] == 8 || slot[2] == 8){
//                editor.putInt("BAI_RITU", 1)
//                    .apply()
//            }else if(slot[0] == 6 && slot[1] == 3 && slot[2] == 5){
//                editor.putInt("BAI_RITU", 30)
//                    .apply()
//            }else if(slot[0] == 8 && slot[1] == 7 && slot[2] == 4){
//                editor.putInt("BAI_RITU", 10)
//                    .apply()
//            }
//        }
//
//        editor.putInt("TOTAL_COUNT", pref.getInt("BAI_RITU",0) * pref.getInt("KAKE_COIN",0))
//
//        // 所持コインの増減処理
//        if(pref.getInt("TOTAL_COUNT",0) != 0){
//            editor.putInt("MY_COIN",pref.getInt("MY_COIN",0) + pref.getInt("TOTAL_COUNT",0))
//                .apply()
//        }else{
//            editor.putInt("MY_COIN",pref.getInt("MY_COIN",0) - pref.getInt("TOTAL_COUNT",0))
//                .apply()
//        }
//
//        editor.putInt("SLOT1",9)
//            .putInt("SLOT2",9)
//            .putInt("SLOT3",9)
//            .putInt("KAKE_COIN",0)
//            .apply()
//
//
//        val ata = findViewById(R.id.atari_coin) as TextView
//        val at = pref.getInt("TOTAL_COUNT",111122)
//        ata.text = at.toString()
//
//        val bai = findViewById(R.id.bai_ritu) as TextView
//        val ba = pref.getInt("BAI_RITU",0)
//        bai.text = ba.toString()
    }
}
