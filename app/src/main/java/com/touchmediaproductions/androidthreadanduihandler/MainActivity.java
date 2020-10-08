package com.touchmediaproductions.androidthreadanduihandler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ProgressBar loadingBar;

    TextView digit1;
    TextView digit2;

    Random random;

    String number1 = "";
    String number2 = "";

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        digit1 = findViewById(R.id.tv_digit1);
        digit2 = findViewById(R.id.tv_digit2);

        random = new Random();

        /*
         * Handler without a Looper is depricated.
         * Used below in method1 for demonstration only.
         */
        handler = new Handler();

        progressBar = findViewById(R.id.pb_main_infinite);
        loadingBar = findViewById(R.id.pb_main_loadingbar);

        loadingBar.setIndeterminate(false);

    }

    public void method1(View view) {
        generateNumberMethod1();
    }
    public void method2(View view) {
        generateNumberMethod2();
    }
    public void method3(View view) {
        generateNumberMethod3();
    }
    public void method4(View view) {
        generateNumberMethod4();
    }
    public void method5(View view) {
        generateNumberMethod5();
    }
    public void method6(View view) {
        generateNumberMethod6();
    }
    public void method7(View view) {
        generateNumberMethod7();
    }

    private void generateNumberMethod1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Moves the current Thread into the background
                // This approach reduces resource competition between the Runnable object's thread and the UI thread.
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                for(int i = 0; i <= 30; i++){
                    int x1 = random.nextInt(9);
                    int x2 = random.nextInt(9);
                    number1 = String.valueOf(x1);
                    number2 = String.valueOf(x2);

                    // Chosen over Thread.sleep as it handles interruption automatically without having to catch.
                    SystemClock.sleep(50);

                    //Handler post to put a new task to run here:
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            digit1.setText(number1);
                            digit2.setText(number2);
                        }
                    });

                }
            }
        }; new Thread(runnable).start();
    }

    private void generateNumberMethod2() {
        new Thread(
                new Runnable() {
            @Override
            public void run() {
                // Moves the current Thread into the background
                // This approach reduces resource competition between the Runnable object's thread and the UI thread.
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

                for(int i = 0; i < 30; i++){
                    int x1 = random.nextInt(9);
                    int x2 = random.nextInt(9);
                    number1 = String.valueOf(x1);
                    number2 = String.valueOf(x2);

                    SystemClock.sleep(50);

                    digit1.post(new Runnable() {
                        @Override
                        public void run() {
                            digit1.setText(number1);
                        }
                    });
                    digit2.post(new Runnable() {
                        @Override
                        public void run() {
                            digit2.setText(number2);
                        }
                    });
                }
            }
        }).start();
    }

    private void generateNumberMethod3() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        // Moves the current Thread into the background
                        // This approach reduces resource competition between the Runnable object's thread and the UI thread.
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

                        for(int i = 0; i <= 30; i++){
                            int x1 = random.nextInt(9);
                            int x2 = random.nextInt(9);
                            number1 = String.valueOf(x1);
                            number2 = String.valueOf(x2);

                            SystemClock.sleep(50);

                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    digit1.setText(number1);
                                    digit2.setText(number2);
                                }
                            });
                        }
                    }
                }).start();
    }

    private void generateNumberMethod4() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        // Moves the current Thread into the background
                        // This approach reduces resource competition between the Runnable object's thread and the UI thread.
                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

                        for(int i = 0; i <= 30; i++){
                            int x1 = random.nextInt(9);
                            int x2 = random.nextInt(9);
                            number1 = String.valueOf(x1);
                            number2 = String.valueOf(x2);

                            SystemClock.sleep(50);

                            digit1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    digit1.setText(number1);
                                }
                            }, 5000);
                            digit2.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    digit2.setText(number2);
                                }
                            }, 5000);
                        }
                    }
                }).start();
    }

    private void generateNumberMethod5() {
        progressBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Moves the current Thread into the background
                // This approach reduces resource competition between the Runnable object's thread and the UI thread.
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                for(int i = 0; i <= 30; i++){
                    int x1 = random.nextInt(9);
                    int x2 = random.nextInt(9);
                    number1 = String.valueOf(x1);
                    number2 = String.valueOf(x2);

                    SystemClock.sleep(100);

                    //Handler post to put a new task to run here:
                    digit1.post(new Runnable() {
                        @Override
                        public void run() {
                            digit1.setText(number1);
                        }
                    });
                    //Handler post to put a new task to run here:
                    digit1.post(new Runnable() {
                        @Override
                        public void run() {
                            digit2.setText(number2);
                        }
                    });


                }
                progressBar.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }

    private void generateNumberMethod6() {
        loadingBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Moves the current Thread into the background
                // This approach reduces resource competition between the Runnable object's thread and the UI thread.
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                for(int i = 0; i <= 30; i++){
                    int x1 = random.nextInt(9);
                    int x2 = random.nextInt(9);
                    number1 = String.valueOf(x1);
                    number2 = String.valueOf(x2);

                    SystemClock.sleep(100);

                    //Handler post to put a new task to run here:
                    digit1.post(new Runnable() {
                        @Override
                        public void run() {
                            digit1.setText(number1);
                        }
                    });
                    digit2.post(new Runnable() {
                        @Override
                        public void run() {
                            digit2.setText(number2);
                        }
                    });

                    final int finalI = i;
                    loadingBar.post(new Runnable() {
                        @Override
                        public void run() {
                           loadingBar.setProgress(finalI);
                        }
                    });
                }
                loadingBar.post(new Runnable() {
                    @Override
                    public void run() {
                        loadingBar.setVisibility(View.INVISIBLE);
                        loadingBar.setProgress(0);
                    }
                });
            }
        }).start();
    }

    private void generateNumberMethod7() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Moves the current Thread into the background
                // This approach reduces resource competition between the Runnable object's thread and the UI thread.
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                for(int i = 0; i <= 30; i++){
                    int x1 = random.nextInt(9);
                    int x2 = random.nextInt(9);
                    number1 = String.valueOf(x1);
                    number2 = String.valueOf(x2);

                    SystemClock.sleep(100);

                    //Handler post to put a new task to run here:
                    //https://developer.android.com/training/multiple-threads/communicate-ui
                    handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            digit1.setText(number1);
                            digit2.setText(number2);
                        }
                    });
                }
            }
        }).start();
    }
}