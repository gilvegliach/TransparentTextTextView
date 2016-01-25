Transparent text TextView
=========================
This is a small library containing a `TextView` that renders its text as 
transparent, so that you can see through and underneath the text. The
technique is described in detail [here](http://gilvegliach.it/?id=3).

Example
-------
(add image)

Usage
------
In xml:

    <it.gilvegliach.android.transparenttexttextview.TransparentTextTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/view_bg"
        android:text="Hello World" />

In code:
    
    TransparentTextTextView tv = new TransparentTextTextView(getContext());
    tv.setText("Hello World");
    
Gradle
------
(add gradle).

License
-------
Copyright 2016 Gil Vegliach

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

