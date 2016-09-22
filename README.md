<a href="http://www.methodscount.com/?lib=it.gilvegliach.android%3Atransparent-text-textview%3A1.0.2"><img src="https://img.shields.io/badge/Methods and size-32 | 5 KB-e91e63.svg"/></a>

Transparent text TextView
=========================
This is a small library containing a `TextView` that renders its text as 
transparent, so that you can see through and underneath the text. The
technique is described in detail [here](http://gilvegliach.it/?id=3).

Example
-------
<img src="/github/images/example.png" width="200">

Usage
------
In xml:

```java
<it.gilvegliach.android.transparenttexttextview.TransparentTextTextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:background="@drawable/view_bg"
    android:text="Hello World" />
```

In code:

```java
TransparentTextTextView tv = new TransparentTextTextView(getContext());
tv.setBackgroundResource(R.drawable.view_bg);
tv.setText("Hello World");
```
    
Gradle
------
```groovy
compile 'it.gilvegliach.android:transparent-text-textview:1.0.2'
```

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

