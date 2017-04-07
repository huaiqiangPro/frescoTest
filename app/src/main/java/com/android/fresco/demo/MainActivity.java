package com.android.fresco.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.anbetter.log.MLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.fresco.helper.ImageLoader;
import com.facebook.fresco.helper.Phoenix;
import com.facebook.fresco.helper.listener.IDownloadResult;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long startTime = currentTimeMillis();
        Phoenix.init(this); // Context
        long result = System.currentTimeMillis() - startTime;
        ((TextView) findViewById(R.id.tv_init_count_time)).setText("初始化耗时：" + result + "ms");


        findViewById(R.id.btn_base_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FrescoBaseUseActivity.class));
            }
        });

        findViewById(R.id.btn_clear_memory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fresco.getImagePipeline().clearMemoryCaches();
                System.gc();
            }
        });

        findViewById(R.id.btn_big).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BigImageActivity.class));
            }
        });

        findViewById(R.id.btn_small).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImageSizeActivity.class));
            }
        });

        findViewById(R.id.btn_gif).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GifActivity.class));
            }
        });

        findViewById(R.id.btn_meizi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MeiziActivity.class));
            }
        });

        findViewById(R.id.btn_meizi_webp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WebpActivity.class));
            }
        });

        findViewById(R.id.btn_use_databinding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UseDataBindingActivity.class));
            }
        });

        findViewById(R.id.btn_blur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BlurActivity.class));
            }
        });

        findViewById(R.id.btn_blur2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Blur2Activity.class));
            }
        });

        findViewById(R.id.btn_load_bitmap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BitmapActivity.class));
            }
        });

    }

    public void preLoad() {
        String url = "http://ww1.sinaimg.cn/large/610dc034jw1fahy9m7xw0j20u00u042l.jpg";
        // 将图片资源预加载到磁盘缓存
        Phoenix.prefetchToDiskCache(url);

        // 检查磁盘缓存中是否存在
        if (Phoenix.isInDiskCacheSync(Uri.parse(url))) {
            MLog.i("---->isInDiskCacheSync");
        }
    }

    public void downloadImage(Context context) {
        String url = "http://feed.chujianapp.com/20161108/452ab5752287a99a1b5387e2cd849006.jpg@1080w";
        String filePath = "";
        ImageLoader.downloadImage(context, url, new IDownloadResult(filePath) {

            @Override
            public void onResult(String filePath) {

            }
        });
    }

    public void downloadImage() {
        String url = "http://feed.chujianapp.com/20161108/452ab5752287a99a1b5387e2cd849006.jpg@1080w";
        String filePath = "";
        Phoenix.with(url)
                .setResult(new IDownloadResult(filePath) {
                    @Override
                    public void onResult(String filePath) {
                        MLog.i("filePath = " + filePath);

                    }
                })
                .download();
    }

}
