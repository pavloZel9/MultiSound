package com.expert.multisound.activity.server

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.expert.multisound.R
import javazoom.jl.decoder.Bitstream
import javazoom.jl.decoder.Decoder
import javazoom.jl.decoder.Header
import javazoom.jl.decoder.SampleBuffer
import kotlinx.android.synthetic.main.activity_server.*
import java.io.IOException
import java.net.*
import java.nio.ByteBuffer
import java.nio.ByteOrder


class ServerActivity : AppCompatActivity() {

    var ConnetPort = ""
    var fileUriAudio: Uri? = null

    var minBufferSize = 0
    var mAudioTrack: AudioTrack? = null
    var mDecoder: Decoder? = null
    var addr: InetAddress? = null
    var sock: DatagramSocket? = null
    var bytes: ByteArray? = null
    var isWork=false
    var thread:  Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_server)

        UpdateLister()

    }

    @Override
    override fun onDestroy(){
        super.onDestroy()

    }
    //
    fun onCreateChanel(view: View) {

        if(!isWork) {
            if(fileUriAudio!=null){
                ConnetPort = CreatePort();
                port.setText(ConnetPort)

                sendAudio()
            }else{
                Toast.makeText(this,"Виберіть пісню",Toast.LENGTH_LONG).show()
            }

        }else{
            if(thread!=null){
                thread!!.interrupt()
            }
        }
    }

    fun CreatePort(): String {
        var portS = port.text.toString()

        when (portS.length) {
            0 -> {
                val rnds = (1000..10000).random()
                portS = portS + rnds
            }
            1 -> {
                val rnds = (100..1000).random()
                portS = portS + rnds
            }
            2 -> {
                val rnds = (10..100).random()
                portS = portS + rnds
            }
            3 -> {
                val rnds = (1..10).random()
                portS = portS + rnds
            }
            4 -> {}
        }

        return portS;
    }

    fun openfile(view: View) {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        gallery.setType("audio/*");
        resultLauncher.launch(gallery)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val dataUri: Intent? = result.data
            if (dataUri != null) {
                fileUriAudio= dataUri.data
                filetext.setText(dataUri.data!!.getName(this))
            }
        }
    }

    fun Uri.getName(context: Context): String {
        val returnCursor = context.contentResolver.query(this, null, null, null, null)
        val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val fileName = returnCursor.getString(nameIndex)
        returnCursor.close()
        return fileName
    }

    fun sendAudio() {
        val sampleRate = 44100
        minBufferSize = AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT
        )
        mAudioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_STEREO,
            AudioFormat.ENCODING_PCM_16BIT,
            minBufferSize,
            AudioTrack.MODE_STREAM
        )
        mAudioTrack!!.setStereoVolume(1f, 1f)
        mDecoder = Decoder()
        try {
            addr = InetAddress.getByName("228.5.6.7")
            sock = DatagramSocket()
            try {
                val s = MulticastSocket(6789)
                s.joinGroup(addr)
                thread = Thread {
                    try {
                        val inStream =getContentResolver().openInputStream(fileUriAudio!!);

                        val bitstream = Bitstream(inStream)
                        val READ_THRESHOLD = 2147483647
                        var framesReaded = 0
                        var header: Header? = null
                        isWork=true
                        UpdateLister()
                        while (framesReaded++ <= READ_THRESHOLD && bitstream.readFrame().also {
                                header = it
                            } != null) {
                            isWork=true;
                            val sampleBuffer =
                                mDecoder!!.decodeFrame(header, bitstream) as SampleBuffer
                            val buffer = sampleBuffer.buffer
                            ///
                            val B_buffer =
                                ByteBuffer.allocate(buffer.size * 2)
                            B_buffer.order(ByteOrder.LITTLE_ENDIAN)
                            B_buffer.asShortBuffer().put(buffer)
                            bytes = B_buffer.array()
                            val pack = DatagramPacket(
                                bytes, buffer.size * 2,
                                addr, 6789
                            )
                            sock!!.send(pack)
                            mAudioTrack!!.write(buffer, 0, buffer.size)
                            bitstream.closeFrame()
                            if (Thread.interrupted()) {
                                // We've been interrupted: no more crunching.
                                isWork=false
                                UpdateLister()
                                return@Thread;
                            }

                        }
                        isWork=false
                        UpdateLister()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                thread!!.start()

                mAudioTrack!!.setPlaybackPositionUpdateListener(object :
                    AudioTrack.OnPlaybackPositionUpdateListener {
                    override fun onPeriodicNotification(track: AudioTrack) {
                        // nothing to do

                    }

                    override fun onMarkerReached(track: AudioTrack) {
                        // play the audio B here
                    }
                })
                mAudioTrack!!.play()

            } catch (e: UnknownHostException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
        } catch (e: SocketException) {
            e.printStackTrace()
        }
    }

    fun UpdateLister(){
        runOnUiThread {
            if(isWork) {
                textView3.setText("Трансляція")
                progressBar.visibility=View.VISIBLE
                create_server.setText("Стоп")
            }else{
                textView3.setText("Зупинено")
                progressBar.visibility=View.INVISIBLE
                create_server.setText("Старт")
            }
        }
    }


}



