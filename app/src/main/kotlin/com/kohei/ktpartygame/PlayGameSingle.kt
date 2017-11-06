package com.kohei.ktpartygame

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_play_game_single.*
import android.os.CountDownTimer
import java.io.IOException


class PlayGameSingle : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game_single)

        imageView1.setOnClickListener { onImageClick(0, 0) }
        imageView2.setOnClickListener { onImageClick(1, 0) }
        imageView3.setOnClickListener { onImageClick(2, 0) }
        imageView4.setOnClickListener { onImageClick(0, 1) }
        imageView5.setOnClickListener { onImageClick(1, 1) }
        imageView6.setOnClickListener { onImageClick(2, 1) }
        imageView7.setOnClickListener { onImageClick(0, 2) }
        imageView8.setOnClickListener { onImageClick(1, 2) }
        imageView9.setOnClickListener { onImageClick(2, 2) }

        startDialog()
    }

    fun onImageClick(x: Int, y: Int) = Toast.makeText(this, "Touch, $x, $y", Toast.LENGTH_SHORT).show()

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