package com.kohei.ktpartygame

import android.app.AlertDialog
import android.graphics.drawable.Drawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_play_game_single.*
import android.os.CountDownTimer
import android.widget.ImageView
import java.io.IOException
import java.util.*
import android.graphics.drawable.BitmapDrawable




class PlayGameSingle : AppCompatActivity() {

    var score = 0
    var target1 = 0
    var target2 = 0
    var target3 = 0
    var target4 = 0
    var target5 = 0
    var target6 = 0
    var target7 = 0
    var target8 = 0
    var target9 = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game_single)

        imageView1.setOnClickListener { onImageClick(1) }
        imageView2.setOnClickListener { onImageClick(2) }
        imageView3.setOnClickListener { onImageClick(3) }
        imageView4.setOnClickListener { onImageClick(4) }
        imageView5.setOnClickListener { onImageClick(5) }
        imageView6.setOnClickListener { onImageClick(6) }
        imageView7.setOnClickListener { onImageClick(7) }
        imageView8.setOnClickListener { onImageClick(8) }
        imageView9.setOnClickListener { onImageClick(9) }

        startDialog()
    }

    fun onImageClick(resource: Int) {
         if(resource == 1) {
             score += target1
             imageView1.setImageResource(R.drawable.player1tap)
         }else if(resource == 2) {
             score += target2
             imageView2.setImageResource(R.drawable.player1tap)
         }else if(resource == 3) {
             score += target3
             imageView3.setImageResource(R.drawable.player1tap)
         }else if(resource == 4) {
             score += target4
             imageView4.setImageResource(R.drawable.player1tap)
         }else if(resource == 5) {
             score += target5
             imageView5.setImageResource(R.drawable.player1tap)
         }else if(resource == 6) {
             score += target6
             imageView6.setImageResource(R.drawable.player1tap)
         }else if(resource == 7) {
             score += target7
             imageView7.setImageResource(R.drawable.player1tap)
         }else if(resource == 8) {
             score += target8
             imageView8.setImageResource(R.drawable.player1tap)
         }else if(resource == 9) {
             score += target9
             imageView9.setImageResource(R.drawable.player1tap)
         }

    }

    fun startDialog() {
        // ポップアップメニュー表示
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("よーい...")
        alertDialog.setMessage("スタートを押すとはじまるで")
        alertDialog.setPositiveButton("スタート") { dialog, which ->
            audioPlay()
            //タイマーを設定
            val cdt = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val mm = millisUntilFinished / 1000 / 60
                    val ss = millisUntilFinished / 1000 % 60
                    textTimer.setText(String.format("%1$02d:%2$02d", mm, ss))
                    setTarget1()
                    setTarget2()
                    setTarget3()
                    textScore.text = score.toString()
                }

                override fun onFinish() {
                    textTimer.setText("00:00")
                    audioStop()
                    GoToResultDialog()
                }
            }.start()
        }
        val myDialog = alertDialog.create()
        myDialog.setCanceledOnTouchOutside(false)//ダイアログ画面外をタッチされても消えないようにする
        myDialog.show()
    }

    //ゲーム終了時にリザルト画面へ行ダイアログを出す
    private fun GoToResultDialog() {
        // ポップアップメニュー表示
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("おわり")
        alertDialog.setMessage("酔いは冷めたか？")
        alertDialog.setPositiveButton("結果を見る") { dialog, which ->
            //val intent = Intent(application, ResultSingle::class.java)
            //startActivity(intent)
        }
        val myDialog = alertDialog.create()
        myDialog.setCanceledOnTouchOutside(false)//ダイアログ画面外をタッチされても消えないようにする
        myDialog.show()
    }

    fun createRndNum(x: Int) = Random().nextInt(x)

    fun setTarget1() {
        val rnd = createRndNum(10)
        if(rnd == 0) {
            target1 = 0
            target2 = 0
            target3 = 0
            imageView1.setImageResource(R.drawable.human)
            imageView2.setImageResource(R.drawable.human)
            imageView3.setImageResource(R.drawable.human)
        }else if(rnd == 1) {
            target1 = 1
            target2 = 0
            target3 = 0
            imageView1.setImageResource(R.drawable.drunk)
            imageView2.setImageResource(R.drawable.human)
            imageView3.setImageResource(R.drawable.human)
        }else if(rnd == 2) {
            target1 = 0
            target2 = 1
            target3 = 0
            imageView1.setImageResource(R.drawable.human)
            imageView2.setImageResource(R.drawable.drunk)
            imageView3.setImageResource(R.drawable.human)
        }else if(rnd == 3) {
            target1 = 0
            target2 = 0
            target3 = 1
            imageView1.setImageResource(R.drawable.human)
            imageView2.setImageResource(R.drawable.human)
            imageView3.setImageResource(R.drawable.drunk)
        }else if(rnd == 4) {
            target1 = -1
            target2 = 1
            target3 = 0
            imageView1.setImageResource(R.drawable.right)
            imageView2.setImageResource(R.drawable.human)
            imageView3.setImageResource(R.drawable.human)
        }else if(rnd == 5) {
            target1 = 0
            target2 = 1
            target3 = -1
            imageView1.setImageResource(R.drawable.human)
            imageView2.setImageResource(R.drawable.human)
            imageView3.setImageResource(R.drawable.left)
        }else if(rnd == 6) {
            target1 = -2
            target2 = -1
            target3 = 2
            imageView1.setImageResource(R.drawable.human)
            imageView2.setImageResource(R.drawable.right)
            imageView3.setImageResource(R.drawable.human)
        }else if(rnd == 7) {
            target1 = 2
            target2 = -1
            target3 = -2
            imageView1.setImageResource(R.drawable.human)
            imageView2.setImageResource(R.drawable.left)
            imageView3.setImageResource(R.drawable.human)
        }else if(rnd == 8) {
            target1 = 2
            target2 = -1
            target3 = -2
            imageView1.setImageResource(R.drawable.human)
            imageView2.setImageResource(R.drawable.right_reverse)
            imageView3.setImageResource(R.drawable.human)
        }
        else if(rnd == 9) {
            target1 = -2
            target2 = -1
            target3 = 2
            imageView1.setImageResource(R.drawable.human)
            imageView2.setImageResource(R.drawable.left_reverse)
            imageView3.setImageResource(R.drawable.human)
        }
    }

    fun setTarget2() {
        val rnd = createRndNum(10)
        if(rnd == 0) {
            target4 = 0
            target5 = 0
            target6 = 0
            imageView4.setImageResource(R.drawable.human)
            imageView5.setImageResource(R.drawable.human)
            imageView6.setImageResource(R.drawable.human)
        }else if(rnd == 1) {
            target4 = 1
            target5 = 0
            target6 = 0
            imageView4.setImageResource(R.drawable.drunk)
            imageView5.setImageResource(R.drawable.human)
            imageView6.setImageResource(R.drawable.human)
        }else if(rnd == 2) {
            target4 = 0
            target5 = 1
            target6 = 0
            imageView4.setImageResource(R.drawable.human)
            imageView5.setImageResource(R.drawable.drunk)
            imageView6.setImageResource(R.drawable.human)
        }else if(rnd == 3) {
            target4 = 0
            target5 = 0
            target6 = 1
            imageView4.setImageResource(R.drawable.human)
            imageView5.setImageResource(R.drawable.human)
            imageView6.setImageResource(R.drawable.drunk)
        }else if(rnd == 4) {
            target4 = -1
            target5 = 1
            target6 = 0
            imageView4.setImageResource(R.drawable.right)
            imageView5.setImageResource(R.drawable.human)
            imageView6.setImageResource(R.drawable.human)
        }else if(rnd == 5) {
            target4 = 0
            target5 = 1
            target6 = -1
            imageView4.setImageResource(R.drawable.human)
            imageView5.setImageResource(R.drawable.human)
            imageView6.setImageResource(R.drawable.left)
        }else if(rnd == 6) {
            target4 = -2
            target5 = -1
            target6 = 2
            imageView4.setImageResource(R.drawable.human)
            imageView5.setImageResource(R.drawable.right)
            imageView6.setImageResource(R.drawable.human)
        }else if(rnd == 7) {
            target4 = 2
            target5 = -1
            target6 = -2
            imageView4.setImageResource(R.drawable.human)
            imageView5.setImageResource(R.drawable.left)
            imageView6.setImageResource(R.drawable.human)
        }else if(rnd == 8) {
            target4 = 2
            target5 = -1
            target6 = -2
            imageView4.setImageResource(R.drawable.human)
            imageView5.setImageResource(R.drawable.right_reverse)
            imageView6.setImageResource(R.drawable.human)
        }
        else if(rnd == 9) {
            target4 = -2
            target5 = -1
            target6 = 2
            imageView4.setImageResource(R.drawable.human)
            imageView5.setImageResource(R.drawable.left_reverse)
            imageView6.setImageResource(R.drawable.human)
        }
    }

    fun setTarget3() {
        val rnd = createRndNum(10)
        if(rnd == 0) {
            target7 = 0
            target8 = 0
            target9 = 0
            imageView7.setImageResource(R.drawable.human)
            imageView8.setImageResource(R.drawable.human)
            imageView9.setImageResource(R.drawable.human)
        }else if(rnd == 1) {
            target7 = 1
            target8 = 0
            target9 = 0
            imageView7.setImageResource(R.drawable.drunk)
            imageView8.setImageResource(R.drawable.human)
            imageView9.setImageResource(R.drawable.human)
        }else if(rnd == 2) {
            target7 = 0
            target8 = 1
            target9 = 0
            imageView7.setImageResource(R.drawable.human)
            imageView8.setImageResource(R.drawable.drunk)
            imageView9.setImageResource(R.drawable.human)
        }else if(rnd == 3) {
            target7 = 0
            target8 = 0
            target9 = 1
            imageView7.setImageResource(R.drawable.human)
            imageView8.setImageResource(R.drawable.human)
            imageView9.setImageResource(R.drawable.drunk)
        }else if(rnd == 4) {
            target7 = -1
            target8 = 1
            target9 = 0
            imageView7.setImageResource(R.drawable.right)
            imageView8.setImageResource(R.drawable.human)
            imageView9.setImageResource(R.drawable.human)
        }else if(rnd == 5) {
            target7 = 0
            target8 = 1
            target9 = -1
            imageView7.setImageResource(R.drawable.human)
            imageView8.setImageResource(R.drawable.human)
            imageView9.setImageResource(R.drawable.left)
        }else if(rnd == 6) {
            target7 = -2
            target8 = -1
            target9 = 2
            imageView7.setImageResource(R.drawable.human)
            imageView8.setImageResource(R.drawable.right)
            imageView9.setImageResource(R.drawable.human)
        }else if(rnd == 7) {
            target7 = 2
            target8 = -1
            target9 = -2
            imageView7.setImageResource(R.drawable.human)
            imageView8.setImageResource(R.drawable.left)
            imageView9.setImageResource(R.drawable.human)
        }else if(rnd == 8) {
            target7 = 2
            target8 = -1
            target9 = -2
            imageView7.setImageResource(R.drawable.human)
            imageView8.setImageResource(R.drawable.right_reverse)
            imageView9.setImageResource(R.drawable.human)
        }
        else if(rnd == 9) {
            target7 = -2
            target8 = -1
            target9 = 2
            imageView7.setImageResource(R.drawable.human)
            imageView8.setImageResource(R.drawable.left_reverse)
            imageView9.setImageResource(R.drawable.human)
        }
    }

    var mediaPlayer: MediaPlayer? = null//bgm用
    //BGM
    fun audioSetup(): Boolean {
        var fileCheck = false
        // インタンスを生成
        mediaPlayer = MediaPlayer()
        //音楽ファイル名, あるいはパス
        val filePath = "hiyokonokakekko.mp3"

        try {
            // assetsから mp3 ファイルを読み込み
            val afdescripter = this.assets.openFd(filePath)

            // MediaPlayerに読み込んだ音楽ファイルを指定

            mediaPlayer!!.setDataSource(afdescripter.getFileDescriptor(),
                    afdescripter.getStartOffset(),
                    afdescripter.getLength())
            // 音量調整を端末のボタンに任せる
            setVolumeControlStream(AudioManager.STREAM_MUSIC)
            mediaPlayer!!.prepare()
            fileCheck = true
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return fileCheck
    }

    fun audioPlay() {
        if (mediaPlayer == null) {
            // audio ファイルを読出し
            if (audioSetup()) {
                //Toast.makeText(getApplication(), "Rread audio file", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(getApplication(), "Error: read audio file", Toast.LENGTH_SHORT).show();
                return
            }
        } else {
            // 繰り返し再生する場合
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            // リソースの解放
            mediaPlayer!!.release()
        }

        // 再生する
        mediaPlayer!!.start()
        // 終了を検知するリスナー
        mediaPlayer!!.setOnCompletionListener(MediaPlayer.OnCompletionListener {
            //Log.d("debug","end of audio");
        })
    }

    fun audioStop() {
        // 再生終了
        mediaPlayer!!.stop()
        // リセット
        mediaPlayer!!.reset()
        // リソースの解放
        mediaPlayer!!.release()
        mediaPlayer = null
    }
}