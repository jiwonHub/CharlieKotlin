package com.example.charliekotlin

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.charliekotlin.databinding.ActivityLoginBinding
import com.example.charliekotlin.databinding.ActivityMainBinding
import com.example.charliekotlin.home.user.UserInformationActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userName_: String
    private lateinit var userImage_: String
    private var userId_: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KakaoSdk.init(this, getString(R.string.kakao_native_app_key)) // 카카오 SDK 초기화

        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            }
        }

        binding.loginButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                        UserApiClient.instance.me { user, error ->
                            if (error != null) {
                                Log.e(TAG, "사용자 정보 요청 실패", error)
                            }
                            else if (user != null) {
                                Log.i(TAG, "사용자 정보 요청 성공" +
                                        "\n회원번호: ${user.id}" +
                                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                                val userId = user.id
                                val username = user.kakaoAccount?.profile?.nickname
                                val userImage = user.kakaoAccount?.profile?.thumbnailImageUrl

                                val sharedPreferences = getSharedPreferences("kakao", MODE_PRIVATE)
                                val editor : SharedPreferences.Editor =  sharedPreferences.edit()

                                editor.putString("USER_NAME", username)
                                editor.putLong("USER_ID", userId!!)
                                editor.putString("USER_IMAGE", userImage)
                                editor.commit()

                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent) //인트로 실행 후 바로 MainActivity로 넘어감.
                                finish()
                            }
                        }
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e(TAG, "사용자 정보 요청 실패", error)
                    }
                    else if (user != null) {
                        Log.i(TAG, "사용자 정보 요청 성공" +
                                "\n회원번호: ${user.id}" +
                                "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                        val userId = user.id
                        val username = user.kakaoAccount?.profile?.nickname
                        val userImage = user.kakaoAccount?.profile?.thumbnailImageUrl

                        val sharedPreferences = getSharedPreferences("kakao", MODE_PRIVATE)
                        val editor : SharedPreferences.Editor =  sharedPreferences.edit()

                        editor.putString("USER_NAME", username)
                        editor.putLong("USER_ID", userId!!)
                        editor.putString("USER_IMAGE", userImage)
                        editor.commit()

                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent) //인트로 실행 후 바로 MainActivity로 넘어감.
                        finish()
                    }
                }
            }
        }
    }
}