/***
  Copyright (c) 2008-2012 CommonsWare, LLC
  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
  by applicable law or agreed to in writing, software distributed under the
  License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
  OF ANY KIND, either express or implied. See the License for the specific
  language governing permissions and limitations under the License.
  
  From _The Busy Coder's Guide to Advanced Android Development_
    http://commonsware.com/AdvAndroid
*/

package com.commonsware.android.video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewDemo extends Activity {
  private WebView video;
  
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.webview);
  
//    File clip=new File(Environment.getExternalStorageDirectory(),
//                       "test.mp4");
    
   // if (clip.exists()) {
      video=(WebView)findViewById(R.id.webview);
//      video.setVideoPath(clip.getAbsolutePath());
      video.getSettings().setJavaScriptEnabled(true);
      video.loadUrl("http://www.youtube.com/v/gzDS-Kfd5XQ?version=3&f=videos&app=youtube_gdata");
      
    //}
  }
}