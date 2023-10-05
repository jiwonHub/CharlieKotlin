package com.example.charliekotlin.home.community

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.isVisible
import com.example.charliekotlin.DBKey.Companion.DB_COMMUNITY
import com.example.charliekotlin.databinding.ActivityCommunityCreateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class CreateCommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityCreateBinding
    private lateinit var userName: String
    private lateinit var userImage: String
    private lateinit var uri : String
    private var selectedUri: Uri? = null
    private val storage: FirebaseStorage by lazy {
        Firebase.storage
    }
    private val communityDB: DatabaseReference by lazy {
        Firebase.database.reference.child(DB_COMMUNITY)
    }
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userName = intent.getStringExtra("userName").toString()
        userImage = intent.getStringExtra("userImage").toString()

        Log.d("name", userName)
        Log.d("image", userImage)

        binding.addImageButton.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    startContentProvider()
                }

                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    showPermissionContextPopup()
                }

                else -> {
                    requestPermissions(
                        arrayOf(
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ),
                        1010
                    )
                }
            }
        }

        binding.createButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()
            val time = System.currentTimeMillis()

            showProgress()
            if (selectedUri != null) {
                val photoUri = selectedUri ?: return@setOnClickListener
                uploadPhoto(photoUri,
                    successHandler = { uri ->
                        uploadCommunity(title, content, uri, time)
                    },
                    errorHandler = {
                        Toast.makeText(this, "사진 업로드에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                        hideProgress()
                    }
                )
            } else {
                uploadCommunity(title, content, "", time)
            }
        }
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun uploadPhoto( // 사진 업로드 함수
        uri: Uri,                           // 사진 uri
        successHandler: (String) -> Unit,   // 업로드 성공 핸들러
        errorHandler: () -> Unit            // 업로드 실패 핸들러
    ) {
        val fileName = "${System.currentTimeMillis()}.png" // 중복 방지를 위해 현재 시간을 ms으로 변환한 값을 파일명으로 설정.
        storage.reference.child("community/photo").child(fileName) // firebase storage 저장 경로 설정.
            .putFile(uri) // uri 넣기
            .addOnCompleteListener {
                if (it.isSuccessful) { // uri put이 성공했다면 실제로 uri에 해당하는 사진을 업로드 시작.
                    storage.reference.child("community/photo").child(fileName)
                        .downloadUrl
                        .addOnSuccessListener { uri ->      // 성공 시 성공 핸들러에 해당하는 일 시작.
                            successHandler(uri.toString())
                        }.addOnFailureListener {            // 실패 시 실패 핸들러에 해당하는 일 시작.
                            errorHandler()
                        }
                } else {
                    errorHandler() // put에 실패했다면 에러핸들러 실행.
                }
            }
    }

    private fun uploadCommunity( // 게시글 업로드 함수
        title: String,      // 제목
        content: String,    // 내용
        imageUri: String,   // 사진 uri
        time: Long
    ) { // 게시글 업로드
        val model = CommunityData(
            title,
            content,
            imageUri,
            time

        ) // 게시글 데이터 형식으로 받아온 값들을 model에 저장
        communityDB.push().setValue(model) // 최종적으로 DB에 푸쉬

        hideProgress() // 로딩창 숨기기
        finish()
    }

    override fun onRequestPermissionsResult( // 권한 체크 시 그 결과를 확인하는 함수
        requestCode: Int,               // 요청할 때 보낸 코드
        permissions: Array<out String>,
        grantResults: IntArray          // 요청에 ok했을 때의 정보를 갖음.
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) { // 요청할 때 보낸 코드가 1010이면
            1010 ->
                if (grantResults.isNotEmpty()) { // 요청 결과에 ok가 있다면
                    startContentProvider() // 갤러리 실행
                } else { // 요청 결과에 ok가 없다면
                    Toast.makeText(this, "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show()
                }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult( // 갤러리 실행 후 결과를 체크하는 함수
        requestCode: Int, // 갤러리 실행 시 보낸 코드
        resultCode: Int,  // 사진 가져오기가 성공 시 갖는 코드
        data: Intent?     // 사진
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) { // 성공하지 못했다면
            return
        }

        when (requestCode) { // 갤러리 실행 시 보낸 코드가 2020일 때
            2020 -> {
                val uri = data?.data // 선택한 사진을 uri에 저장
                if (uri != null) { // 선택한 사진이 null이 아니라면
                    binding.photoImageView.setImageURI(uri) // 게시글 만들기 화면에 사진 띄우기
                    selectedUri = uri // 선택한 사진 변수 selectedUri에도 uri저장
                } else { // null이라면
                    Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            else -> { // 2020이 아닐 때
                Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startContentProvider() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 2020)
    }

    private fun showProgress() { // 로딩창 o
        binding.progressBar.isVisible = true
    }

    private fun hideProgress() { // 로딩창 x
        binding.progressBar.isVisible = false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showPermissionContextPopup() { // 권한 동의x 를 누른 후 띄워지는 팝업
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("사진을 가져오기 위해 필요합니다.")
            .setPositiveButton("동의") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1010)
            }
            .create()
            .show()
    }
}